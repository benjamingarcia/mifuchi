package org.benji.mifuchi.command

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.common.CommandResponse
import org.benji.mifuchi.domain.Game
import org.benji.mifuchi.domain.GameRepository
import java.util.*
import javax.inject.Singleton

@Singleton
class StartGameCommandHandler(private val gameRepository: GameRepository) : CommandHandler<UUID> {

    override fun handle(command: Command): CommandResponse<UUID> {
        val uuid = UUID.randomUUID()
        gameRepository.add(Game(uuid, UUID.randomUUID(), UUID.randomUUID(), 4))
        return StartGameCommandResponse(uuid)
    }

    override fun listenTo(): String {
        return StartGameCommand::class.toString()
    }
}