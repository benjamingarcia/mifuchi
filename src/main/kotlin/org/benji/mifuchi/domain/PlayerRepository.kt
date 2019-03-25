package org.benji.mifuchi.domain

import org.benji.mifuchi.common.Repository
import java.util.*

interface PlayerRepository : Repository {
    fun get(uuid: UUID): Player
    fun add(player: Player)
    fun delete(uuid: UUID)
}

