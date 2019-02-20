package org.benji.mifuchi.command

import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.repository.StartGameRepository
import javax.inject.Singleton

@Singleton
class StartGameCommandHandler(private val startGameRepository: StartGameRepository) : CommandHandler {

    override fun listenTo(): String {
        return StartGameCommand::class.toString()
    }
}