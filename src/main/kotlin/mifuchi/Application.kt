package mifuchi

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("mifuchi")
                .mainClass(Application.javaClass)
                .start()
    }
}