package org.benji.mifuchi.common

interface CommandHandler {

    fun handle(command: Command ): CommandResponse

    fun listenTo():String
}