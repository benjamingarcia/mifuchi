package org.benji.mifuchi.domain.repositories

import org.benji.mifuchi.common.Repository
import org.benji.mifuchi.domain.Card

interface CardRepository : Repository {
    fun add(cards : List<Card>)
}