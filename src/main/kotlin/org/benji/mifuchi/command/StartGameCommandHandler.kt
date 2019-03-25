package org.benji.mifuchi.command

import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.common.CommandResponse
import org.benji.mifuchi.domain.*
import org.benji.mifuchi.event.GameInitialized
import java.util.*
import javax.inject.Singleton

@Singleton
class StartGameCommandHandler(private val gameRepository: GameRepository, private val playerRepository : PlayerRepository) : CommandHandler<StartGameCommand> {

    private val defaultDeck = HashMap<Int, Card>(25)
    init {
        //3 cawotte
        defaultDeck[0] = Card.CAWOTTE
        defaultDeck[1] = Card.CAWOTTE
        defaultDeck[2] = Card.CAWOTTE

        //3 lenald
        defaultDeck[3] = Card.LENALD
        defaultDeck[4] = Card.LENALD
        defaultDeck[5] = Card.LENALD

        //6 rocks
        defaultDeck[6] = Card.ROCK
        defaultDeck[7] = Card.ROCK
        defaultDeck[8] = Card.ROCK
        defaultDeck[9] = Card.ROCK
        defaultDeck[10] = Card.ROCK
        defaultDeck[11] = Card.ROCK

        //6 scissors
        defaultDeck[12] = Card.SCISSORS
        defaultDeck[13] = Card.SCISSORS
        defaultDeck[14] = Card.SCISSORS
        defaultDeck[15] = Card.SCISSORS
        defaultDeck[16] = Card.SCISSORS
        defaultDeck[17] = Card.SCISSORS

        //6 leafs
        defaultDeck[18] = Card.LEAF
        defaultDeck[19] = Card.LEAF
        defaultDeck[20] = Card.LEAF
        defaultDeck[21] = Card.LEAF
        defaultDeck[22] = Card.LEAF
        defaultDeck[23] = Card.LEAF

        // 1 dofus
        defaultDeck[24] = Card.DOFUS
    }

    override fun handle(command: StartGameCommand): CommandResponse {
        val uuid = UUID.randomUUID()

        val blueDeck = defaultDeck.toMap()
        val orangeDeck = defaultDeck.toMap()

        val bluePlayer = Player(UUID.randomUUID(), command.blueUserName, blueDeck, color = PlayerColor.BLUE)
        val orangePlayer = Player(UUID.randomUUID(), command.orangeUserName, orangeDeck, color = PlayerColor.ORANGE)
        playerRepository.add(bluePlayer)
        playerRepository.add(orangePlayer)
        val newGame = Game(uuid, bluePlayer.uuid, orangePlayer.uuid, 4, WaWabbitOrientation.NONE)
        gameRepository.add(newGame)
        return StartGameCommandResponse(uuid, GameInitialized(newGame.uuid, newGame.gamer1Id, newGame.gamer2Id))
    }


    override fun listenTo(): String {
        return StartGameCommand::class.qualifiedName!!
    }
}