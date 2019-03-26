package org.benji.mifuchi.query.middleware

import org.benji.mifuchi.common.Query
import org.benji.mifuchi.common.QueryHandler
import org.benji.mifuchi.common.QueryResponse
import org.benji.mifuchi.query.QueryBus

class QueryBusDispatcher(handlers: List<QueryHandler<QueryResponse>>) : QueryBus {

    private val handlersMap = handlers.map { queryHandler -> Pair(queryHandler.listenTo(), queryHandler) }.toMap()

    override fun dispatch(query: Query): QueryResponse {
        val queryName = query::class.qualifiedName!!
        val handler = handlersMap[queryName] ?: throw Exception("No handler for this command")
        return handler.handle(query)
    }
}