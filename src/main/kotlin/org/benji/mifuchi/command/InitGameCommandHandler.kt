package org.benji.mifuchi.command

import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.common.CommandResponse
import org.benji.mifuchi.domain.*
import org.benji.mifuchi.domain.repositories.CardRepository
import org.benji.mifuchi.domain.repositories.GameRepository
import org.benji.mifuchi.domain.repositories.PlayerRepository
import org.benji.mifuchi.domain.repositories.TreasureRepository
import org.benji.mifuchi.event.GameInitialized
import java.util.*
import javax.inject.Singleton

@Singleton
class InitGameCommandHandler(private val gameRepository: GameRepository,
                             private val playerRepository : PlayerRepository,
                             private val cardRepository: CardRepository,
                             private val treasureRepository: TreasureRepository) : CommandHandler<InitGameCommand> {

    private val defaultDeck = LinkedList<CardName>()
    private val defaultTreasure = LinkedList<TreasureName>()

    init {
        //3 cawotte
        defaultDeck.add(CardName.CAWOTTE)
        defaultDeck.add(CardName.CAWOTTE)
        defaultDeck.add(CardName.CAWOTTE)

        //3 lenald
        defaultDeck.add(CardName.LENALD)
        defaultDeck.add(CardName.LENALD)
        defaultDeck.add(CardName.LENALD)

        //6 rocks
        defaultDeck.add(CardName.ROCK)
        defaultDeck.add(CardName.ROCK)
        defaultDeck.add(CardName.ROCK)
        defaultDeck.add(CardName.ROCK)
        defaultDeck.add(CardName.ROCK)
        defaultDeck.add(CardName.ROCK)

        //6 scissors
        defaultDeck.add(CardName.SCISSORS)
        defaultDeck.add(CardName.SCISSORS)
        defaultDeck.add(CardName.SCISSORS)
        defaultDeck.add(CardName.SCISSORS)
        defaultDeck.add(CardName.SCISSORS)
        defaultDeck.add(CardName.SCISSORS)

        //6 leafs
        defaultDeck.add(CardName.LEAF)
        defaultDeck.add(CardName.LEAF)
        defaultDeck.add(CardName.LEAF)
        defaultDeck.add(CardName.LEAF)
        defaultDeck.add(CardName.LEAF)
        defaultDeck.add(CardName.LEAF)

        // 1 dofus
        defaultDeck.add(CardName.DOFUS)

        defaultTreasure.add(TreasureName.CAPE)
        defaultTreasure.add(TreasureName.CROWN)
        defaultTreasure.add(TreasureName.STICK)
    }

    override fun handle(command: InitGameCommand): CommandResponse {
        val uuid = UUID.randomUUID()

        val bluePlayer = Player(UUID.randomUUID(), command.blueUserName, PlayerColor.BLUE)
        val orangePlayer = Player(UUID.randomUUID(), command.orangeUserName, PlayerColor.ORANGE)
        playerRepository.add(bluePlayer)
        playerRepository.add(orangePlayer)

        val blueCardList: List<Card> = defaultDeck.map { cardName -> Card(UUID.randomUUID(), cardName, CardState.DECK, bluePlayer.uuid) }
        val orangeCardList: List<Card> = defaultDeck.map { cardName -> Card(UUID.randomUUID(), cardName, CardState.DECK, orangePlayer.uuid) }
        cardRepository.add(blueCardList)
        cardRepository.add(orangeCardList)

        val newGame = Game(uuid, bluePlayer.uuid, orangePlayer.uuid, 4, WaWabbitOrientation.NONE)
        gameRepository.add(newGame)

        val treasureList: List<Treasure> = defaultTreasure.map { treasureName -> Treasure(UUID.randomUUID(), treasureName, TreasureState.BOARD, newGame.uuid) }
        treasureRepository.add(treasureList)



        return InitGameCommandResponse(uuid, GameInitialized(newGame.uuid, newGame.gamer1Id, newGame.gamer2Id))
    }

    override fun listenTo(): String {
        return InitGameCommand::class.qualifiedName!!
    }
}