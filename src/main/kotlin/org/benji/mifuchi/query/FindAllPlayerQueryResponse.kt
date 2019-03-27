package org.benji.mifuchi.query

import org.benji.mifuchi.common.QueryResponse
import org.benji.mifuchi.domain.Player

class FindAllPlayerQueryResponse(val players: List<Player>) :QueryResponse{

}
