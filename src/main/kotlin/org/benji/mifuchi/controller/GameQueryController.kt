package org.benji.mifuchi.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import org.benji.mifuchi.query.FindAllGamesQuery
import org.benji.mifuchi.query.QueryBus

@Controller("/gameQuery")
class GameQueryController(private val queryBus : QueryBus) {

    @Get("/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllGames():String{
        return queryBus.dispatch(FindAllGamesQuery())
    }
}