package org.benji.mifuchi

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.middleware.CommandBusDispatcher
import org.benji.mifuchi.middleware.LoggerMiddleware
import java.util.*
import javax.inject.Inject

@Factory
class CommandBusFactory(@Inject private val handlers: List<CommandHandler<UUID>>) {



    @Bean
    fun build(): CommandBusMiddleware<UUID>{
        return LoggerMiddleware(CommandBusDispatcher(handlers))
    }



}