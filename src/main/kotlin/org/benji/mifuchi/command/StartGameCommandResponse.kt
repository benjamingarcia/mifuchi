package org.benji.mifuchi.command

import org.benji.mifuchi.common.CommandResponse
import java.util.*

class StartGameCommandResponse(val value: UUID) : CommandResponse<UUID>