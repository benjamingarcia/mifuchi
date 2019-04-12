package org.benji.mifuchi.infrastructure

import io.reactiverse.reactivex.pgclient.PgPool
import io.reactiverse.reactivex.pgclient.Tuple
import org.benji.mifuchi.domain.Card
import org.benji.mifuchi.domain.repositories.CardRepository
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class CardRepositoryImpl(private val client: PgPool) : CardRepository {
    override fun add(cards: List<Card>) {
        val batch = cards.map { card ->
            Tuple.of(card.uuid, card.name.toString(), card.state.toString(), card.playerId)
        }

        client.preparedBatch("INSERT INTO card (uuid, name, state, player_id) VALUES " +
                "($1, $2, $3, $4)", batch) { res ->
            if (res.succeeded()) {
                val rows = res.result()
                LOG.info("batch succeed ${rows.size()}")
            } else {
                LOG.error("batch failed : ${res.cause()}")
            }
        }


    }

    companion object {
        private val LOG = LoggerFactory.getLogger(CardRepositoryImpl::class.java)
    }
}