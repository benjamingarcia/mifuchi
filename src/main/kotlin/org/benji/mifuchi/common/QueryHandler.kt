package org.benji.mifuchi.common

interface QueryHandler {

    fun handle(query: Query):String

    fun listenTo():String
}