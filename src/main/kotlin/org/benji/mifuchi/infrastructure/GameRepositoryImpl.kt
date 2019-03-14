package org.benji.mifuchi.infrastructure

import io.reactiverse.reactivex.pgclient.PgPool
import org.benji.mifuchi.domain.Game
import org.benji.mifuchi.domain.GameRepository
import java.util.*
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl(private val client: PgPool) : GameRepository {

    override fun get(uuid: UUID): Game {
        println(uuid)
        return client.rxQuery("""SELECT * FROM game where uuid = '$uuid'""").map { pgRowSet ->
            val row = pgRowSet.iterator().next()
            Game(row.getUUID(0), row.getUUID(1), row.getUUID(2), row.getInteger(3))
        }.blockingGet()
    }

    override fun add(game: Game) {
        client.rxQuery("""INSERT INTO game (uuid, gamer1_id, gamer2_id, wawabbit_position)
            |VALUES ('${game.uuid}', '${game.gamer1Id}', '${game.gamer2Id}', ${game.wawabbitPosition})""".trimMargin())
                .blockingGet()

    }

    override fun delete(uuid: UUID) {
        client.preparedQuery("DELETE FROM game where uuid = $uuid") { ar ->
            if (ar.succeeded()) {
                val rows = ar.result()
                println(rows.rowCount())
            } else {
                println("Failure: ${ar.cause().message}")
            }
        }
    }
}