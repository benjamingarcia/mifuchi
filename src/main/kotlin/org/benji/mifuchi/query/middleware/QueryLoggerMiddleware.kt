package org.benji.mifuchi.query.middleware

import org.benji.mifuchi.common.Query
import org.benji.mifuchi.query.QueryBus
import org.slf4j.LoggerFactory
import java.time.Instant

class QueryLoggerMiddleware(val next:QueryBus): QueryBus {

    override fun dispatch(query: Query) : String{
        val startTime:Instant = Instant.now()
        val response = next.dispatch(query)
        val endTime:Instant = Instant.now()
        val elapsed = endTime.minusMillis(startTime.toEpochMilli())
        LOG.info("Command ${query::class} took $elapsed")
        return response
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(QueryLoggerMiddleware::class.java)
    }

}
