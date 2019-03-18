package org.benji.mifuchi.query

import io.reactiverse.reactivex.pgclient.PgPool
import org.benji.mifuchi.common.Query
import org.benji.mifuchi.common.QueryHandler
import javax.inject.Singleton

@Singleton
class FindAllGamesQueryHandler(private val client : PgPool): QueryHandler{

    override fun handle(query: Query): String {
        return client.rxQuery("SELECT * from game").blockingGet().toString()
    }

    override fun listenTo(): String {
        return FindAllGamesQuery::class.qualifiedName!!
    }


}