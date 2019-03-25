package org.benji.mifuchi.command

import org.benji.mifuchi.common.Command

data class StartGameCommand(val blueUserName:String, val orangeUserName:String) : Command