package org.benji.mifuchi.query

import io.reactiverse.reactivex.pgclient.PgPool
import io.reactiverse.reactivex.pgclient.PgRowSet
import org.benji.mifuchi.common.Query
import org.benji.mifuchi.common.QueryHandler
import org.benji.mifuchi.domain.CardName
import org.benji.mifuchi.domain.Player
import org.benji.mifuchi.domain.PlayerColor
import org.benji.mifuchi.domain.TreasureName

class FindAllPlayerQueryHandler(private val client : PgPool) : QueryHandler<FindAllPlayerQueryResponse> {

    override fun handle(query: Query): FindAllPlayerQueryResponse {
        val players = client.rxQuery("SELECT * FROM player").map { t: PgRowSet ->
            var playerTemp = emptyList<Player>()
            for (row in t){
                val uuid = row.getUUID(0)
                val name = row.getString(1)
                val color = PlayerColor.valueOf(row.getString(2))
                playerTemp+=playerTemp.plus(Player(uuid, name, color))
            }
            playerTemp
        }.blockingGet()
        return FindAllPlayerQueryResponse(players)
    }

    override fun listenTo(): String {
        return FindAllPlayerQuery::class.qualifiedName!!
    }
}