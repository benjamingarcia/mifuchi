package org.benji.mifuchi.query

import org.benji.mifuchi.common.Query
import org.benji.mifuchi.common.QueryResponse

interface QueryBus {

    fun dispatch(query: Query) : QueryResponse
}