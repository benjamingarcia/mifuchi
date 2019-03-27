package org.benji.mifuchi.domain

import java.util.*

data class Treasure(val uuid: UUID, val name: TreasureName, val state: TreasureState, val playerId: UUID, val gameId: UUID)