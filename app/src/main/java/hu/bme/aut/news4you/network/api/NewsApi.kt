package hu.bme.aut.news4you.network.api

import hu.bme.aut.news4you.network.model.LatestNewsResponse
import retrofit2.http.GET

interface NewsApi {
    /**
     * Finds the latest news
     *
     * @return Call<NewsResponse>
    </NewsResponse> */
    @GET("magazine.json")
    suspend fun getLatestNews(): LatestNewsResponse
}