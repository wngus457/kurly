package com.juhyeon.kurly.shared.domain.feature.home.section.usecase

import com.juhyeon.kurly.shared.domain.FlowUseCase
import com.juhyeon.kurly.shared.domain.Result
import com.juhyeon.kurly.shared.domain.di.DefaultDispatcher
import com.juhyeon.kurly.shared.domain.feature.home.HomeRepository
import com.juhyeon.kurly.shared.domain.feature.home.section.SectionList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class GetSectionListUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Int, SectionList>(dispatcher) {

    override fun execute(parameter: Int): Flow<Result<SectionList>> {
        homeRepository.getSectionList(parameter)

        return homeRepository.getSectionList(parameter).distinctUntilChanged()
    }
}