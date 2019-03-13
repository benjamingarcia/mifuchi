package org.benji.mifuchi.common

import java.util.*

abstract class CommandResponse(val uuid: UUID, val events: Array<out Event>){

    abstract fun withValue(uuid: UUID, vararg events: Event): CommandResponse

    abstract fun hasEvents(): Boolean
}