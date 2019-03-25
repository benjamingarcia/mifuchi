package org.benji.mifuchi

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object GameCommandControllerTest: Spek ({

    describe(description = "GameCommandController test suite"){
        var embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        var client: HttpClient = HttpClient.create(embeddedServer.url)

        it("test /gameCommand/init return game uuid") {
            var rsp: String = client.toBlocking().retrieve(HttpRequest.POST("/gameCommand/init", """
                {
                  "blueUserName" : "Guizmo",
                  "orangeUserName" : "Spike"
                }
            """.trimIndent()))
            assert(rsp.isNotEmpty())
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }

})