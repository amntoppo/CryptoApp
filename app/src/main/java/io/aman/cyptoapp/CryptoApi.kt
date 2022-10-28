package io.aman.cyptoapp

import io.aman.cyptoapp.model.CryptoModel
import retrofit2.http.GET

interface CryptoApi {

    companion object {
        const val BASE_URL = "http://demo9414936.mockable.io/";
    }


    @GET("home")
    suspend fun getCryptoData(): CryptoModel

    @GET("empty-home")
    suspend fun getCryptoEmptyData(): CryptoModel
}