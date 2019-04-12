package org.benji.mifuchi.domain.repositories

import org.benji.mifuchi.common.Repository
import org.benji.mifuchi.domain.Game
import java.util.*

interface GameRepository : Repository {
    fun get(uuid: UUID): Game
    fun add(game: Game)
    fun delete(uuid: UUID)
}

