package org.benji.mifuchi.command.middleware

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandResponse
import org.benji.mifuchi.event.EventBus

class EventDispatcherBusMiddleware(val next:CommandBusMiddleware, private val eventBus: EventBus) : CommandBusMiddleware{

    override fun dispatch(command: Command): CommandResponse {
        val commandResponse = next.dispatch(command)

        if(commandResponse.hasEvents()){
            commandResponse.events.forEach { event -> eventBus.dispatch(event) }
        }

        return commandResponse
    }
}