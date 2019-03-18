package org.benji.mifuchi.query

import org.benji.mifuchi.common.Query

interface QueryBus {

    fun dispatch(query: Query) : String
}