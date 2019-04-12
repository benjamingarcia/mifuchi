package org.benji.mifuchi.domain

import java.util.*

data class Treasure(val uuid: UUID, val name: TreasureName, val state: TreasureState, val gameId: UUID, val playerId: UUID? = null)