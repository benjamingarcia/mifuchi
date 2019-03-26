package org.benji.mifuchi

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.benji.mifuchi.common.QueryHandler
import org.benji.mifuchi.common.QueryResponse
import org.benji.mifuchi.query.QueryBus
import org.benji.mifuchi.query.middleware.QueryBusDispatcher
import org.benji.mifuchi.query.middleware.QueryLoggerMiddleware
import javax.inject.Inject

@Factory
class QueryBusFactory(@Inject private val handlers: List<QueryHandler<QueryResponse>>) {

    @Bean
    fun build(): QueryBus{
        return QueryLoggerMiddleware(QueryBusDispatcher(handlers))
    }

}