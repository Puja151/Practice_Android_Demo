package com.example.practice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import repositery.Callbacks
import repositery.RetrofitAPI


// TODO: Display list of users with the user information mentioned in the assignment
// Note: A nice looking UI is appreciated but clean code is more important

class MainActivity : ComponentActivity(), Callbacks {

    private lateinit var retrofitAPI : RetrofitAPI
    var itemSelected : Int = 0
    lateinit var userList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrofitAPI = RetrofitAPI(this)
        retrofitAPI.fetchUsersList()
    }

    override fun successData(userData: ArrayList<String>) {
        userList = userData
        setContent {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.LightGray,
                contentColor = Color.Black
            ) {
                //   ItemSelectable() inprogress to get seleted index
                LazyColumn(contentPadding = PaddingValues(15.dp,15.dp,0.dp, 15.dp)) {
                    items((userList)) { row ->
                        Text(" $row", color = Color.Magenta)
                    }
                }

            }
        }
    }

    override fun failureCallback(errorString: String) {
        Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show()
        print("error")
    }

    @Composable
    fun ItemSelectable(){
        val selectedIndex = remember {
            mutableListOf(-1)
        }
    }
}
