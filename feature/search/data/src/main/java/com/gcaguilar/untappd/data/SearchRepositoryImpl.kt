package com.gcaguilar.untappd.data

import com.gcaguilar.untappd.domain.SearchRepository
import com.gcaguilar.untappd.domain.Beer
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun searchBeer(name: String): List<Beer> =
        searchRemoteDataSource.searchBeer(name).response.beers.items.map { item ->
            item.toBeer()
        }
}