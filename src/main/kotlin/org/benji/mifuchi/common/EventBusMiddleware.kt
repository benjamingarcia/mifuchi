package org.benji.mifuchi.common

interface EventBusMiddleware {

    fun dispatch(event: Event)
}