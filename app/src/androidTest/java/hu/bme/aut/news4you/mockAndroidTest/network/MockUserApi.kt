package hu.bme.aut.news4you.mockAndroidTest.network

import hu.bme.aut.news4you.network.api.UserApi
import hu.bme.aut.news4you.network.model.User

class MockUserApi : UserApi {
    private val user = User()

    override suspend fun getUser(): User {
        return user
    }

    override fun updateUser(body: User) {
        user.username = body.username
    }

    override fun createUser(body: User) {
        user.id = body.id
        user.username = body.username
    }
}