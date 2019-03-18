package org.benji.mifuchi.query

import org.benji.mifuchi.common.Query
import org.benji.mifuchi.common.QueryHandler
import org.benji.mifuchi.domain.Game
import javax.inject.Singleton

@Singleton
class FindAllGamesQueryHandler: QueryHandler<List<Game>>{
    override fun handle(query: Query): List<Game> {
        return emptyList()
    }

    override fun listenTo(): String {
        return FindAllGamesQuery::class.qualifiedName!!
    }


}