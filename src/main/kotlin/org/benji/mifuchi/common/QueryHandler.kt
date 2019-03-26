package org.benji.mifuchi.common

interface QueryHandler<T : QueryResponse> {

    fun handle(query: Query):T

    fun listenTo():String
}