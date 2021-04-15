package hu.bme.aut.news4you.network

import hu.bme.aut.news4you.network.auth.ApiKeyAuth
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ApiClient() {
    private var apiAuthorizations: MutableMap<String, Interceptor> = LinkedHashMap()
    private lateinit var okClient: OkHttpClient
    private lateinit var adapterBuilder: Retrofit.Builder

    constructor(authName: String, apiKey: String) : this() {
        val auth: Interceptor
        if (authName === "apikey") {
            auth = ApiKeyAuth("query", "api-key")
        } else {
            throw RuntimeException("auth name \"$authName\" not found in available auth names")
        }

        apiAuthorizations[authName] = auth
        setApiKey(apiKey)

        createDefaultAdapter(authName)
    }

    private fun createDefaultAdapter(authName: String) {
        okClient =
            OkHttpClient().newBuilder().apply { addInterceptor(apiAuthorizations[authName]!!) }
                .build()
        var baseUrl = "https://api.nytimes.com/svc/topstories/v2"
        if (!baseUrl.endsWith("/")) baseUrl = "$baseUrl/"
        adapterBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    /**
     * Helper method to configure the first api key found
     * @param apiKey
     */
    private fun setApiKey(apiKey: String) {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is ApiKeyAuth) {
                val keyAuth: ApiKeyAuth = apiAuthorization
                keyAuth.apiKey = apiKey
                return
            }
        }
    }

    fun <S> createService(serviceClass: Class<S>?): S {
        return adapterBuilder.build().create(serviceClass)
    }
}

