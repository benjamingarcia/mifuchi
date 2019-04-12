package org.benji.mifuchi.domain.repositories

import org.benji.mifuchi.common.Repository
import org.benji.mifuchi.domain.Player
import java.util.*

interface PlayerRepository : Repository {
    fun get(uuid: UUID): Player
    fun add(player: Player)
    fun delete(uuid: UUID)
}

