package org.benji.mifuchi

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.command.middleware.CommandBusDispatcher
import org.benji.mifuchi.command.middleware.EventDispatcherBusMiddleware
import org.benji.mifuchi.command.middleware.LoggerMiddleware
import org.benji.mifuchi.common.QueryHandler
import org.benji.mifuchi.event.EventBus
import org.benji.mifuchi.query.QueryBus
import org.benji.mifuchi.query.middleware.QueryBusDispatcher
import org.benji.mifuchi.query.middleware.QueryLoggerMiddleware
import javax.inject.Inject

@Factory
class QueryBusFactory(@Inject private val handlers: List<QueryHandler>) {

    @Bean
    fun build(): QueryBus{
        return QueryLoggerMiddleware(QueryBusDispatcher(handlers))
    }



}