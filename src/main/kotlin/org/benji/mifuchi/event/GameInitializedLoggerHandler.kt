package org.benji.mifuchi.event

import org.benji.mifuchi.common.Event
import org.benji.mifuchi.common.EventHandler
import org.benji.mifuchi.domain.GameRepository
import javax.inject.Singleton

@Singleton
class GameInitializedLoggerHandler(private val gameRepository: GameRepository) : EventHandler {

    override fun handle(event: Event) {
        val game = gameRepository.get(event.getUuid())
    }


    override fun listenTo(): String {
        return GameInitialized::class.qualifiedName!!
    }

}