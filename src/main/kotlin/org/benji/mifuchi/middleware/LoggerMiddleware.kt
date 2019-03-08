package org.benji.mifuchi.middleware

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandResponse
import java.time.Instant
import java.util.*

class LoggerMiddleware(val next:CommandBusMiddleware<UUID>): CommandBusMiddleware<UUID> {

    override fun dispatch(command: Command): CommandResponse<UUID> {
        val startTime:Instant = Instant.now()
        val response = next.dispatch(command)
        val endTime:Instant = Instant.now()
        val elapsed = endTime.minusMillis(startTime.toEpochMilli())
        println("Command ${command::class} took $elapsed")
        return response
    }
}
