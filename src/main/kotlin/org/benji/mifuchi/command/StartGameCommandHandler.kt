package org.benji.mifuchi.command

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.common.CommandResponse
import org.benji.mifuchi.domain.Game
import org.benji.mifuchi.domain.GameRepository
import org.benji.mifuchi.event.GameInitialized
import java.util.*
import javax.inject.Singleton

@Singleton
class StartGameCommandHandler(private val gameRepository: GameRepository) : CommandHandler {

    override fun handle(command: Command): CommandResponse {
        val uuid = UUID.randomUUID()
        val newGame = Game(uuid, UUID.randomUUID(), UUID.randomUUID(), 4)
        gameRepository.add(newGame)
        return StartGameCommandResponse(uuid, GameInitialized(newGame.uuid, newGame.gamer1Id, newGame.gamer2Id))
    }

    override fun listenTo(): String {
        return StartGameCommand::class.qualifiedName!!
    }
}