package org.benji.mifuchi

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.command.middleware.CommandBusDispatcher
import org.benji.mifuchi.command.middleware.LoggerMiddleware
import javax.inject.Inject

@Factory
class CommandBusFactory(@Inject private val handlers: List<CommandHandler>) {

    @Bean
    fun build(): CommandBusMiddleware{
        return LoggerMiddleware(CommandBusDispatcher(handlers))
    }



}