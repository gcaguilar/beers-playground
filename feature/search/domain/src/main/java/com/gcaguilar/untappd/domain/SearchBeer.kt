package com.gcaguilar.untappd.domain

import javax.inject.Inject

class SearchBeer @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(name: String) = searchRepository.searchBeer(name)
}