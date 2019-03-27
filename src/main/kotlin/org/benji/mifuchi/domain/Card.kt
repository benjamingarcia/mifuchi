package org.benji.mifuchi.domain

import java.util.*

data class Card(val uuid: UUID, val name: CardName, val state : CardState, val playerId: UUID)