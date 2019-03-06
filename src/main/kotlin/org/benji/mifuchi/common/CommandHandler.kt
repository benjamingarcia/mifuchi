package org.benji.mifuchi.common

interface CommandHandler<T> {

    fun handle(command: Command ): CommandResponse<T>

    fun listenTo():String
}