package api

import data.UsersList
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    fun getUsersList() : Call<ArrayList<UsersList>>
}
