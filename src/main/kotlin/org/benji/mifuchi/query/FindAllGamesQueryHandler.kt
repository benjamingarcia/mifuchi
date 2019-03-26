package org.benji.mifuchi.query

import io.reactiverse.reactivex.pgclient.PgPool
import org.benji.mifuchi.common.Query
import org.benji.mifuchi.common.QueryHandler
import org.benji.mifuchi.common.QueryResponse
import javax.inject.Singleton

@Singleton
class FindAllGamesQueryHandler(private val client : PgPool): QueryHandler<QueryResponse>{

    override fun handle(query: Query): QueryResponse {
        return FindAllGameQueryResponse(client.rxQuery("SELECT * from game").blockingGet().toString())
    }

    override fun listenTo(): String {
        return FindAllGamesQuery::class.qualifiedName!!
    }


}