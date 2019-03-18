package org.benji.mifuchi.query.middleware

import org.benji.mifuchi.common.Query
import org.benji.mifuchi.query.QueryBus
import java.time.Instant

class QueryLoggerMiddleware(val next:QueryBus): QueryBus {

    override fun dispatch(query: Query) : String{
        val startTime:Instant = Instant.now()
        val response = next.dispatch(query)
        val endTime:Instant = Instant.now()
        val elapsed = endTime.minusMillis(startTime.toEpochMilli())
        println("Command ${query::class} took $elapsed")
        return response
    }
}
