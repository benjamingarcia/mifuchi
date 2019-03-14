package org.benji.mifuchi.domain

import java.util.UUID

data class Game(val uuid: UUID, val gamer1Id:UUID, val gamer2Id:UUID, val wawabbitPosition : Int, val board: IntArray = IntArray(9))
