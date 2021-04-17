package hu.bme.aut.news4you.network.api

import hu.bme.aut.news4you.network.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {
    /**
     * Returns user data
     *
     * @return Call<User>
    </User> */
    @GET("user")
    suspend fun getUser(): User

    /**
     * Updates user
     *
     * @param body Updated user object
     * @return Call<Void>
    </Void> */
    @PUT("user")
    fun updateUser(
        @Body body: User
    )

    /**
     * Creates user
     *
     * @param body Created user object
     * @return Call<Void>
    </Void> */
    @POST("user")
    fun createUser(
        @Body body: User
    )
}