package org.benji.mifuchi.infrastructure

import io.reactiverse.reactivex.pgclient.PgPool
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import org.benji.mifuchi.domain.*
import org.benji.mifuchi.query.middleware.QueryLoggerMiddleware
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class PlayerRepositoryImpl(private val client: PgPool) : PlayerRepository{

    override fun get(uuid: UUID): Player {
        return client.rxQuery("""SELECT * FROM player where uuid = '$uuid'""").map { pgRowSet ->
            val row = pgRowSet.iterator().next()
            Player(uuid = row.getUUID(1), name = row.getString(1), deck = emptyList(), color = PlayerColor.valueOf(row.getString(2)))
        }.blockingGet()
    }

    override fun add(player: Player) {
        val jsonDeck = JsonArray(player.deck)
        val jsonDiscard = JsonArray(player.discard)
        val jsonHand = JsonArray(player.hand)
        val jsonTreasure = JsonArray(player.treasure)

        client.rxQuery("""INSERT INTO player (uuid, name, color, deck, discard, hand, treasure)
            |VALUES ('${player.uuid}', '${player.name}', '${player.color}', '$jsonDeck', '$jsonDiscard', '$jsonHand', '$jsonTreasure')""".trimMargin())
                .blockingGet()

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