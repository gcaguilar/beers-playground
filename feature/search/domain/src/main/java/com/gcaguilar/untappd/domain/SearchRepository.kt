package com.gcaguilar.untappd.domain

interface SearchRepository {
    suspend fun searchBeer(name: String): List<Beer>
}