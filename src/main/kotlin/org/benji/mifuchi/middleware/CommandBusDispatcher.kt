package org.benji.mifuchi.middleware

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.common.CommandResponse
import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Singleton

class CommandBusDispatcher(private val handlers: List<CommandHandler<UUID>>) : CommandBusMiddleware<UUID> {

    private val handlersMap = handlers.map { commandHandler -> Pair(commandHandler.listenTo(), commandHandler) }.toMap()


    override fun dispatch(command: Command): CommandResponse<UUID> {
        val commandName = command::class.qualifiedName
        val handler = handlersMap[commandName] ?: throw Exception("No handler for this command")
        return handler.handle(command)
    }
}