package org.benji.mifuchi.domain

import java.util.*

data class Player(val uuid: UUID, val name:String, val deck:Map<Int, Card>, val discard:Map<Int, Card> = emptyMap(), val hand:List<Card> = emptyList(), val treasure:List<Treasure> = emptyList(), val color: PlayerColor)