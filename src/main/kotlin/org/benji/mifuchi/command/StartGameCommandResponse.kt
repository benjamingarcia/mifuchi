package org.benji.mifuchi.command

import org.benji.mifuchi.common.CommandResponse
import org.benji.mifuchi.common.Event
import java.util.*

class StartGameCommandResponse(uuidChild: UUID, vararg eventsChild: Event) : CommandResponse(uuidChild, eventsChild) {

    override fun withValue(uuid: UUID, vararg events: Event): CommandResponse {
        return StartGameCommandResponse(uuid, *events)
    }

    override fun hasEvents(): Boolean {
        return events.isNotEmpty()
    }

}