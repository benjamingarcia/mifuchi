package org.benji.mifuchi.common

interface CommandHandler {

    fun handle(command: Command ){}

    fun listenTo():String
}