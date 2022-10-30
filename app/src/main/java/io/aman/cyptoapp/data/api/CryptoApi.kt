package io.aman.cyptoapp.data.api

import io.aman.cyptoapp.data.model.CryptoModel
import retrofit2.http.GET

interface CryptoApi {

    companion object {
        const val BASE_URL = "https://demo9414936.mockable.io/";
    }


    @GET("home")
    suspend fun getCryptoData(): CryptoModel

    @GET("empty-home")
    suspend fun getCryptoEmptyData(): CryptoModel
}