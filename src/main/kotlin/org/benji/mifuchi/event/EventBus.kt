package org.benji.mifuchi.event

import org.benji.mifuchi.common.Event

interface EventBus {

    fun dispatch(event: Event)
}