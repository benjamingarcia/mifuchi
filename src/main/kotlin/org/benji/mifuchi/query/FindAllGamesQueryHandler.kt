package org.benji.mifuchi.query

import org.benji.mifuchi.common.Query
import org.benji.mifuchi.common.QueryHandler
import org.benji.mifuchi.domain.Game
import org.benji.mifuchi.domain.GameRepository
import javax.inject.Singleton

@Singleton
class FindAllGamesQueryHandler(private val gameRepository: GameRepository): QueryHandler<List<Game>>{

    override fun handle(query: Query): List<Game> {
        return gameRepository.getAllGame()
    }

    override fun listenTo(): String {
        return FindAllGamesQuery::class.qualifiedName!!
    }
}