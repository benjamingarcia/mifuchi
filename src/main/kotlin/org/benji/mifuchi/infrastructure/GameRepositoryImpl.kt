package org.benji.mifuchi.infrastructure

import io.reactiverse.reactivex.pgclient.PgPool
import io.reactiverse.reactivex.pgclient.PgRowSet
import io.reactiverse.reactivex.pgclient.Tuple
import org.benji.mifuchi.domain.Game
import org.benji.mifuchi.domain.GameRepository
import java.util.*
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class GameRepositoryImpl(private val client: PgPool) : GameRepository {

    override fun get(uuid: UUID): Game {
        return client.rxQuery("SELECT * FROM game where uuid = $uuid")
                .map {
                    to(Game(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 0))
                }.blockingGet().second
    }

    override fun add(game: Game) {
        client.preparedQuery("INSERT INTO game (gamer1_id, game2_id, wawabbit_position) VALUES (\$$1, \$$2, \$$3)",
                Tuple.of(game.gamer1Id, game.gamer2Id, game.wawabbitPosition)) { ar ->
            if (ar.succeeded()) {
                val rows = ar.result()
                println(rows.rowCount())
            } else {
                println("Failure: ${ar.cause().message}")
            }
        }
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

    override fun getAllGame(): List<Game> {
        return client.rxQuery("SELECT * FROM game")
                .map { t: PgRowSet ->
                    var gameList = ArrayList<Game>(0)
                    for (r in t) {
                        gameList.add((r to Game(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 0)).second)
                    }
                    gameList
                }.blockingGet()
    }
}