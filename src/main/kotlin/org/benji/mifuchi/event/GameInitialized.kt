package org.benji.mifuchi.event

import org.benji.mifuchi.common.Event
import java.util.*

class GameInitialized(val gameUUID: UUID, val gamer1UUID: UUID, val gamer2UUID: UUID) : Event