package org.benji.mifuchi.event

import org.benji.mifuchi.common.Event
import org.benji.mifuchi.common.EventHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventBus(@Inject val handlers: List<EventHandler>) {


    fun dispatch(event: Event) {
        val eventClass = event::class.qualifiedName!!
        val filteredEventHandlers = handlers.filter { eventHandler -> eventHandler.listenTo() == eventClass }
        filteredEventHandlers.forEach { eventHandler -> eventHandler.handle(event) }
    }
}