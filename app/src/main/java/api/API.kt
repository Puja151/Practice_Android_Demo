package api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// A networking API used within the app.
//
// Implement the API using retrofit, ktor or any networking library of your choice
// without modifying this API. See `RetrofitAPI.kt` template to start with.
//
// Note: The concrete implementation of this API should not contain anything that's unrelated
//       to this task. There is no plan to expand this project therefore features that are not
//       related to the task should be avoided.
//       i.e. Do not add support for fetching images or anything unrelated to the task at hand.
//
interface API {

    fun fetchUsersList(excludingUserWithID: Int =0)

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(usersListURL)
            .build()
    }

    companion object {
        const val usersListURL = "https://jsonplaceholder.typicode.com/"
    }

}
