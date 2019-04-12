package org.benji.mifuchi.infrastructure

import io.reactiverse.reactivex.pgclient.PgPool
import io.reactiverse.reactivex.pgclient.Tuple
import org.benji.mifuchi.domain.Treasure
import org.benji.mifuchi.domain.repositories.TreasureRepository
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class TreasureRepositoryImpl(private val client: PgPool)  : TreasureRepository{

    override fun add(treasures: List<Treasure>) {
        val batch = treasures.map { treasure ->
            Tuple.of(treasure.uuid, treasure.name.toString(), treasure.state.toString(), treasure.playerId, treasure.gameId)
        }

        client.preparedBatch("INSERT INTO treasure (uuid, name, state, player_id, game_id) VALUES " +
                "($1, $2, $3, $4, $5)", batch) {res ->
            if(res.succeeded()) {
                val rows = res.result()
                LOG.info("batch succeed ${rows.size()}")
            } else {
                LOG.error("batch failed : ${res.cause()}")
            }
        }

    }


    companion object {
        private val LOG = LoggerFactory.getLogger(TreasureRepositoryImpl::class.java)
    }

}