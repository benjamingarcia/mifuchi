package org.benji.mifuchi.repository

import io.reactiverse.reactivex.pgclient.PgPool
import io.reactiverse.reactivex.pgclient.PgRowSet
import javax.inject.Singleton

@Singleton
class EventRepository(private val client: PgPool) {

    fun testPgPool(): Int {
        return client.rxQuery("SELECT * FROM pg_stat_database")
                .map { pgRowSet: PgRowSet ->
                    pgRowSet.rowCount()
                }.blockingGet()
    }
}