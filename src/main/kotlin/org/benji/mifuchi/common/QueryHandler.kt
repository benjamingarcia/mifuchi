package org.benji.mifuchi.common

interface QueryHandler<T> {

    fun handle(query: Query): T

    fun listenTo():String
}