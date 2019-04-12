package org.benji.mifuchi.infrastructure

import io.reactiverse.reactivex.pgclient.PgPool
import org.benji.mifuchi.domain.*
import org.benji.mifuchi.domain.repositories.PlayerRepository
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class PlayerRepositoryImpl(private val client: PgPool) : PlayerRepository {

    override fun get(uuid: UUID): Player {
        return client.rxQuery("""SELECT * FROM player where uuid = '$uuid'""").map { pgRowSet ->
            val row = pgRowSet.iterator().next()
            Player(row.getUUID(0), row.getString(1), PlayerColor.valueOf(row.getString(2)))
        }.blockingGet()
    }

    override fun add(player: Player) {
        client.rxQuery("""INSERT INTO player (uuid, name, color)
            |VALUES ('${player.uuid}', '${player.name}', '${player.color}')""".trimMargin()).blockingGet()

    }

    override fun delete(uuid: UUID) {
        client.preparedQuery("DELETE FROM player where uuid = $uuid") { ar ->
            if (ar.succeeded()) {
                val rows = ar.result()
                LOG.info("${rows.rowCount()}")
            } else {
                LOG.info("Failure: ${ar.cause().message}")
            }
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(PlayerRepositoryImpl::class.java)
    }

}