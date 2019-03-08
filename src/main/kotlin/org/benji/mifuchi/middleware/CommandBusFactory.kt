package org.benji.mifuchi.middleware

import io.micronaut.context.annotation.EachBean
import io.micronaut.context.annotation.Factory
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandHandler
import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Singleton

@Factory
class CommandBusFactory {

    @Singleton
    private val handlers = emptyList<CommandHandler<UUID>>()


    @EachBean(CommandHandler::class)
    fun buildCommandHandlerList(handler : CommandHandler<UUID>){
        handlers.plus(handler)
    }

    @PostConstruct
    fun build(): CommandBusMiddleware<UUID>{
        return LoggerMiddleware(CommandBusDispatcher(handlers))
    }



}