package org.benji.mifuchi.domain

import java.util.*

data class Player(val uuid: UUID, val name:String, val deck:List<Card>, val discard:List<Card> = emptyList(), val hand:List<Card> = emptyList(), val treasure:List<Treasure> = emptyList(), val color: PlayerColor)