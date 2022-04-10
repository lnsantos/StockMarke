package com.lnsantos.stock.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") key: String
    ): ResponseBody

    companion object {
        const val API_KEY = "G1USXWKX272RK4BP"
        const val BASE_URL = "https://alphavantage.co"
    }

}