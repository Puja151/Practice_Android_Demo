package api

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import model.UserList
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// TODO: Implement a simple networking API using retrofit or any library you're familiar with 🙌
// Note: Fetching users-list is the *only* use-case therefore avoid implementing unrelated,
// extra, features no matter how common or useful they might be for potential future cases.
//
class RetrofitAPI(private val usersListBaseURL: String) : API {

    private var retrofitAPI : RetrofitInterface

    init {

        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(usersListBaseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitAPI = retrofit.create(RetrofitInterface::class.java)
    }

    override fun fetchUsersList(
        excludingUserWithID: String?,
        success: UsersList<List<UserList>>,
        failure: FetchError<String>
    ) {
        print("Fetch users list from $usersListBaseURL")

        GlobalScope.launch {
            val response = retrofitAPI.fetchUserList()
            if (response.isSuccessful) {
                success(response.body()!!)
            } else {
                failure(response.message())
            }
        }
    }

    interface RetrofitInterface {
        @GET("/users")
        suspend fun fetchUserList(): Response<ArrayList<UserList>>
    }

}

