package org.benji.mifuchi.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import org.benji.mifuchi.repository.EventRepository

@Controller("/game")
class GameController(val eventRepository: EventRepository){


    @Post("/init")
    @Produces(MediaType.APPLICATION_JSON)
    fun initGame(): String {
        val size = eventRepository.testPgPool()
        return """{"gameId":1,"size":$size}""".trimIndent()
    }
}