package org.benji.mifuchi.command

import org.benji.mifuchi.common.Command

data class InitGameCommand(val blueUserName:String, val orangeUserName:String) : Command