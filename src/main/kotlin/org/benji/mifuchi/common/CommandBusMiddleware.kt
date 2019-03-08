package org.benji.mifuchi.common

interface CommandBusMiddleware<T> {

    fun dispatch(command: Command) : CommandResponse<T>
}