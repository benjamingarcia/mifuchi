package org.benji.mifuchi

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GameQueryControllerTest: Spek({

    describe("GameCommandController test suite" ){
        var embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        var client: HttpClient = HttpClient.create(embeddedServer.url)


        it("test /gameQuery/list return something") {
            var rsp: String = client.toBlocking().retrieve("/gameQuery/list")
            assert(rsp.isNotEmpty())
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }
})