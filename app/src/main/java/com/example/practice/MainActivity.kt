package com.example.practice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.LayoutWidth
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.unit.dp
import api.API
import model.UserList


// TODO: Display list of users with the user information mentioned in the assignment
// Note: A nice looking UI is appreciated but clean code is more important

class MainActivity : ComponentActivity() {

    private val userDisplayList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        API.create().fetchUsersList(success = {
            Log.d("Main", "success ${it.size}")
            // TODO : success response
            getList(it)

        }, failure = {
            Log.d("Main", "failure $it")
            // TODO : show an error
        })

        setContent {
            MaterialTheme {
                VerticalScroller {
                    Column {
                        userDisplayList.let {
                            it.forEach { user ->
                                printUser(user)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun printUser(user: String) {
        Ripple(bounded = true) {
            Clickable(
                onClick = {
                    Toast.makeText(
                        this,
                        "You click on name $user",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Card {
                    Container(
                        padding = EdgeInsets(16.dp),
                        modifier = LayoutWidth.Fill,
                        alignment = Alignment.CenterStart
                    ) {
                        Column {
                            Text(text = user, style = MaterialTheme.typography().h6)
                        }
                    }
                }
            }
        }
    }

    private fun getList(response: List<UserList>) : ArrayList<String>{
        response.let {
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

}
