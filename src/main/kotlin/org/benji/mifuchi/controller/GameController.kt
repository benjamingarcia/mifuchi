package org.benji.mifuchi.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import org.benji.mifuchi.command.StartGameCommand
import org.benji.mifuchi.command.StartGameCommandHandler
import org.benji.mifuchi.domain.GameRepository

@Controller("/game")
class GameController(private val gameRepository: GameRepository){


    @Post("/init")
    @Produces(MediaType.APPLICATION_JSON)
    fun initGame(): String {
        val handle = StartGameCommandHandler(gameRepository)
        val id = handle.handle(StartGameCommand("spike", "guizmo"))
        return """{"gameId":1,"size":$id}""".trimIndent()
    }
}