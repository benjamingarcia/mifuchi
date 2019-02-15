package org.benji.mifuchi

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("org/benji/mifuchi")
                .mainClass(Application.javaClass)
                .start()
    }
}