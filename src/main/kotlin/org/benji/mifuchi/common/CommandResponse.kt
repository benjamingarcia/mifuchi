package org.benji.mifuchi.common

interface CommandResponse<T>{
    fun getValue(): T
}