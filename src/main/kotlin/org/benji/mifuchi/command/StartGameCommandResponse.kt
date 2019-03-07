package org.benji.mifuchi.command

import org.benji.mifuchi.common.CommandResponse
import java.util.*

class StartGameCommandResponse(private val value: UUID) : CommandResponse<UUID> {
    override fun getValue():UUID {
        return value
    }
}