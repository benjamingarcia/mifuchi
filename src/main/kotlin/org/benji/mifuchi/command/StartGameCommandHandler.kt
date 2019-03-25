package org.benji.mifuchi.command

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.common.CommandResponse
import org.benji.mifuchi.domain.*
import org.benji.mifuchi.event.GameInitialized
import java.util.*
import javax.inject.Singleton

@Singleton
class StartGameCommandHandler(private val gameRepository: GameRepository, private val playerRepository : PlayerRepository) : CommandHandler<StartGameCommand> {

    override fun handle(command: StartGameCommand): CommandResponse {
        val uuid = UUID.randomUUID()
        //todo create players
        val blueDeck = createDeck()
        val orangeDeck = createDeck()

        val bluePlayer = Player(UUID.randomUUID(), command.blueUserName, blueDeck, color = PlayerColor.BLUE)
        val orangePlayer = Player(UUID.randomUUID(), command.orangeUserName, orangeDeck, color = PlayerColor.ORANGE)
        playerRepository.add(bluePlayer)
        playerRepository.add(orangePlayer)
        val newGame = Game(uuid, bluePlayer.uuid, orangePlayer.uuid, 4, WaWabbitOrientation.NONE)
        gameRepository.add(newGame)
        return StartGameCommandResponse(uuid, GameInitialized(newGame.uuid, newGame.gamer1Id, newGame.gamer2Id))
    }

    private fun createDeck(): Map<Int, Card> {
        return emptyMap()
    }

    override fun listenTo(): String {
        return StartGameCommand::class.qualifiedName!!
    }
}