package org.benji.mifuchi.middleware

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandHandler
import org.benji.mifuchi.common.CommandResponse
import java.util.*

class CommandBusDispatcher(var handlers:Map<String, CommandHandler<UUID>> = emptyMap()): CommandBusMiddleware<UUID> {

    constructor(handlers:List<CommandHandler<UUID>>): this(){
        handlers.forEach{
            this.handlers.plus(Pair(it.listenTo(), it))
        }
    }

    override fun dispatch(command: Command): CommandResponse<UUID> {
        val commandName = Command::class.toString()
        val handler = handlers.get(commandName) ?: throw Exception("No handler for this command")
        return handler.handle(command)
    }
}