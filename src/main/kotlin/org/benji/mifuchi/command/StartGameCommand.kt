package org.benji.mifuchi.command

import org.benji.mifuchi.common.Command

data class StartGameCommand(val firstGamerName:String, val secondGamerName:String) : Command