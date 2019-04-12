package org.benji.mifuchi.event

import org.benji.mifuchi.common.Event
import org.benji.mifuchi.common.EventHandler
import org.benji.mifuchi.domain.repositories.GameRepository
import org.benji.mifuchi.infrastructure.GameRepositoryImpl
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class GameInitializedLoggerHandler(private val gameRepository: GameRepository) : EventHandler {

    override fun handle(event: Event) {
        val game = gameRepository.get(event.getUuid())
        LOG.info("$game")
    }


    override fun listenTo(): String {
        return GameInitialized::class.qualifiedName!!
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(GameRepositoryImpl::class.java)
    }

}