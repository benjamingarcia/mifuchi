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

    /**
     * a deck have 3 cawotte, 1 dofus, 3 lenald, 6 rocks, 6 scissors, 6 leafs = 25 cards.
     */
    private fun createDeck(): Map<Int, Card> {
        val deckMap = HashMap<Int, Card>(25)

        //3 cawotte
        deckMap[0] = Card.CAWOTTE
        deckMap[1] = Card.CAWOTTE
        deckMap[2] = Card.CAWOTTE

        //3 lenald
        deckMap[3] = Card.LENALD
        deckMap[4] = Card.LENALD
        deckMap[5] = Card.LENALD

        //6 rocks
        deckMap[6] = Card.ROCK
        deckMap[7] = Card.ROCK
        deckMap[8] = Card.ROCK
        deckMap[9] = Card.ROCK
        deckMap[10] = Card.ROCK
        deckMap[11] = Card.ROCK

        //6 scissors
        deckMap[12] = Card.SCISSORS
        deckMap[13] = Card.SCISSORS
        deckMap[14] = Card.SCISSORS
        deckMap[15] = Card.SCISSORS
        deckMap[16] = Card.SCISSORS
        deckMap[17] = Card.SCISSORS

        //6 leafs
        deckMap[18] = Card.LEAF
        deckMap[19] = Card.LEAF
        deckMap[20] = Card.LEAF
        deckMap[21] = Card.LEAF
        deckMap[22] = Card.LEAF
        deckMap[23] = Card.LEAF

        // 1 dofus
        deckMap[24] = Card.DOFUS
        return deckMap
    }

    override fun listenTo(): String {
        return StartGameCommand::class.qualifiedName!!
    }
}