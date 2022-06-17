package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.data.repository.WordSearchingGateway

interface GatewayUsecase: SearchingHistoryUsecase, WordSearchingUsecase {
}