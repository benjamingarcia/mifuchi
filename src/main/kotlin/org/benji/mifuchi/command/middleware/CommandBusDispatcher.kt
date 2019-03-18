package org.benji.mifuchi.command.middleware

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.common.CommandResponse

class CommandBusDispatcher(handlers: List<CommandHandler>) : CommandBusMiddleware {

    private val handlersMap = handlers.map { commandHandler -> Pair(commandHandler.listenTo(), commandHandler) }.toMap()

    override fun dispatch(command: Command): CommandResponse {
        val commandName = command::class.qualifiedName!!
        val handler = handlersMap[commandName] ?: throw Exception("No handler for this command")
        return handler.handle(command)
    }
}