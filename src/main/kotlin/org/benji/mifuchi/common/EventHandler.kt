package org.benji.mifuchi.common

interface EventHandler {

    fun handle(event: Event)

    fun listenTo():String
}