package repositery

import api.API
import api.UserService
import data.UsersList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Implement a simple networking API using retrofit or any library you're familiar with 🙌
// Note: Fetching users-list is the *only* use-case therefore avoid implementing unrelated,
// extra, features no matter how common or useful they might be for potential future cases.
//
class RetrofitAPI(private var responseCallbacks: Callbacks) : API {

    lateinit var callbacks : Callbacks

    override fun fetchUsersList(
        excludingUserWithID: Int
    ) {
        callbacks = responseCallbacks
        val retrofitObj = getRetrofitInstance().create(UserService::class.java)
        val call = retrofitObj.getUsersList()
        call.enqueue(object : Callback<ArrayList<UsersList>> {
            override fun onResponse(call: Call<ArrayList<UsersList>>, response: Response<ArrayList<UsersList>>) {
                if (response.isSuccessful){
                    val userData = getList(response.body(), excludingUserWithID)
                    callbacks.successData(userData)
                }
            }
            override fun onFailure(call: Call<ArrayList<UsersList>>, t: Throwable) {
                t.message
                callbacks.failureCallback(t.message.toString())
            }
        })
    }
}

private fun getList(response: ArrayList<UsersList>?, excludingUserWithID: Int) : ArrayList<String>{
    val userDisplayList = ArrayList<String>()
    response?.let {
        for(i in response.iterator()){
            userDisplayList.add(i.id + ", " + i.name
                    + ", " + i.email
                    + ", " + i.address.city
                    + ", " + i.company.name
            )
        }
        userDisplayList.reverse()
    }
    return userDisplayList
}