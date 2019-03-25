package org.benji.mifuchi.common

interface CommandHandler <T : Command> {

    fun handle(command: T ): CommandResponse

    fun listenTo():String
}