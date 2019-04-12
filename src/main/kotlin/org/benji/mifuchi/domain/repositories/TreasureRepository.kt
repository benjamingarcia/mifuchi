package org.benji.mifuchi.domain.repositories

import org.benji.mifuchi.common.Repository
import org.benji.mifuchi.domain.Card
import org.benji.mifuchi.domain.Treasure
import java.util.*

interface TreasureRepository : Repository {
    fun add(treasures : List<Treasure>)
}