package org.benji.mifuchi.domain

import org.benji.mifuchi.common.Repository
import java.util.*

interface GameRepository : Repository {
    fun get(uuid: UUID): Game
    fun add(game: Game)
    fun delete(uuid: UUID)
    fun getAllGame(): List<Game>
}

