package com.example.practice

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import api.API


// TODO: Display list of users with the user information mentioned in the assignment
// Note: A nice looking UI is appreciated but clean code is more important

class MainActivity : AppCompatActivity() {

    private val userDisplayList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.LightGray,
                contentColor = Color.Black
            ) {
                getUserList()
                LazyColumn(contentPadding = PaddingValues(15.dp)) {
                    items((userDisplayList)) { row ->
                        Text(" $row", color = Color.Black)
                    }
                }
            }
        }
    }

    private fun getUserList() {
        //excludingUserID will be clicked item position
        // passing default value for testing
        API.create().fetchUsersList("1", success = {
            Log.d("Main", "success ${it.size}")
            it.let {
                for (i in it.iterator()) {
                    userDisplayList.add(
                        i.id + ", " + i.name
                                + ", " + i.email
                                + ", " + i.address.city
                                + ", " + i.company.name
                    )
                }
            }
        }, failure = {
            Log.d("Main", "failure $it")
            // TODO : show an error
        })
        return userDisplayList.reverse()
    }

}