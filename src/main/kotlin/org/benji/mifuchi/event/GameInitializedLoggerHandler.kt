package org.benji.mifuchi.event

import org.benji.mifuchi.common.EventHandler
import org.benji.mifuchi.domain.GameRepository

class GameInitializedLoggerHandler(private val gameRepository: GameRepository) : EventHandler {

    fun handle(event: GameInitialized) {
        val game = gameRepository.get(event.gameUUID)
        println(game.wawabbitPosition)
    }

    override fun listenTo(): String {
        return GameInitialized::class.qualifiedName!!
    }

}