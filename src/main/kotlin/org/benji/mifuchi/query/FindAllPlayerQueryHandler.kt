package org.benji.mifuchi.query

import io.reactiverse.reactivex.pgclient.PgPool
import io.reactiverse.reactivex.pgclient.PgRowSet
import org.benji.mifuchi.common.Query
import org.benji.mifuchi.common.QueryHandler
import org.benji.mifuchi.domain.Player

class FindAllPlayerQueryHandler(private val client : PgPool) : QueryHandler<FindAllPlayerQueryResponse> {

    override fun handle(query: Query): FindAllPlayerQueryResponse {
//        val players = client.rxQuery("SELECT * FROM player").map { t: PgRowSet ->
//            var playerTemp = emptyList<Player>()
//
//        }.blockingGet()
        return FindAllPlayerQueryResponse()
    }

    override fun listenTo(): String {
        return FindAllPlayerQuery::class.qualifiedName!!
    }
}