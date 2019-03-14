package org.benji.mifuchi

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.command.middleware.CommandBusDispatcher
import org.benji.mifuchi.command.middleware.EventDispatcherBusMiddleware
import org.benji.mifuchi.command.middleware.LoggerMiddleware
import org.benji.mifuchi.event.EventBus
import javax.inject.Inject

@Factory
class CommandBusFactory(@Inject private val handlers: List<CommandHandler>, @Inject private val eventBus: EventBus) {

    @Bean
    fun build(): CommandBusMiddleware{
        return LoggerMiddleware(EventDispatcherBusMiddleware(CommandBusDispatcher(handlers), eventBus))
    }



}