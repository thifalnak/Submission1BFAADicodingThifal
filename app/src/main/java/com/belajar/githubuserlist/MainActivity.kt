package com.belajar.githubuserlist

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataRepo: Array<String>
    private lateinit var dataAva: TypedArray
    private lateinit var dataComp: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataLocation: Array<String>
    private var users: ArrayList<Users> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        prepare()
        addItem()
        showRecyclerList()
    }
    private fun showRecyclerList() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(users)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Users) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: Users) {
        Toast.makeText(this, "Kamu memilih " + user.name, Toast.LENGTH_SHORT).show()
    }
    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataRepo = resources.getStringArray(R.array.repository)
        dataAva = resources.obtainTypedArray(R.array.avatar)
        dataComp = resources.getStringArray(R.array.company)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataLocation = resources.getStringArray(R.array.location)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val user = Users(
                    name = dataName[position],
                    username = dataUsername[position],
                    repository = dataRepo[position].toInt(),
                    avatar = dataAva.getResourceId(position, -1),
                    company = dataComp[position],
                    followers = dataFollowers[position].toInt(),
                    following = dataFollowing[position].toInt(),
                    location = dataLocation[position]
            )
            users.add(user)
        }
    }
}