package org.benji.mifuchi.command.middleware

import org.benji.mifuchi.common.Command
import org.benji.mifuchi.common.CommandBusMiddleware
import org.benji.mifuchi.common.CommandResponse
import java.time.Instant

class LoggerMiddleware(val next:CommandBusMiddleware): CommandBusMiddleware {

    override fun dispatch(command: Command): CommandResponse {
        val startTime:Instant = Instant.now()
        val response = next.dispatch(command)
        val endTime:Instant = Instant.now()
        val elapsed = endTime.minusMillis(startTime.toEpochMilli())
        println("Command ${command::class} took $elapsed")
        return response
    }
}
