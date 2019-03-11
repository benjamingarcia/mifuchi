package org.benji.mifuchi.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import org.benji.mifuchi.command.StartGameCommand
import org.benji.mifuchi.common.CommandBusMiddleware
import java.util.*

@Controller("/game")
class GameController(private val commandBus: CommandBusMiddleware<UUID>){


    @Post("/init")
    @Produces(MediaType.APPLICATION_JSON)
    fun initGame(): String {
        val commandResponse = commandBus.dispatch(StartGameCommand("Spyke", "Guizmo"))
        return """{"gameId":"${commandResponse.getValue()}"}""".trimIndent()
    }
}