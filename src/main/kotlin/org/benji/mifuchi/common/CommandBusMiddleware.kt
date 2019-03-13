package org.benji.mifuchi.common

interface CommandBusMiddleware {

    fun dispatch(command: Command) : CommandResponse
}