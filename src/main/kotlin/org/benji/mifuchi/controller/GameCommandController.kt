package org.benji.mifuchi.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import org.benji.mifuchi.command.InitGameCommand
import org.benji.mifuchi.common.CommandBusMiddleware

data class InitializeGame(val blueUserName: String, val orangeUserName: String)

@Controller("/gameCommand")
class GameCommandController(private val commandBus: CommandBusMiddleware) {


    @Post("/init")
    @Produces(MediaType.APPLICATION_JSON)
    fun initGame(@Body initializeGame: InitializeGame): String {
        val commandResponse =
                commandBus.dispatch(InitGameCommand(initializeGame.blueUserName, initializeGame.orangeUserName))
        return """{"gameId":"${commandResponse.uuid}"}""".trimIndent()
    }

    @Get("/start")
    @Produces(MediaType.TEXT_PLAIN)
    fun startGame(): String {
        return "Ready !!! 🦊"
    }
}

