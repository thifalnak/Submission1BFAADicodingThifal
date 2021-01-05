package com.belajar.githubuserlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.fragment_following_followers.*
import org.json.JSONArray
import org.json.JSONObject

class FollowingFollowersFragment : Fragment() {

    private lateinit var listUserAdapter : ListUserAdapter
    private var usersFollowers : ArrayList<Users> = ArrayList()
    private var usersFollowing : ArrayList<Users> = ArrayList()

    companion object {
        val TAG: String = FollowingFollowersFragment::class.java.simpleName
        const val EXTRA_USER = "0"
        private const val ARG_SECTION_NUM = "section_number"

        fun newInstance(index : Int) =
                FollowingFollowersFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_SECTION_NUM, index)
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = arguments?.getInt(ARG_SECTION_NUM, 0)
        val dataUser : Users? = activity?.intent?.getParcelableExtra(EXTRA_USER)


        when (index) {
            0 -> getFollowersData(dataUser?.username.toString())
            1 -> getFollowingData(dataUser?.username.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following_followers, container, false)
    }

    private fun getFollowingData(id: String) {
        progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token fb3a48b669bd6155ef2047c0230b407b8c261d89")
        val url = "https://api.github.com/users/$id/following"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray
            ) {
                progressBar.visibility = View.INVISIBLE

                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for ( i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username = jsonObject.getString("login")
                        getUserData(username)
                    }
                } catch (e : Exception) {
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                            .show()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                val errorMessage = when (statusCode){
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                        .show()
            }

        })

    }

    private fun getFollowersData(id: String) {
        progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token fb3a48b669bd6155ef2047c0230b407b8c261d89")
        val url = "https://api.github.com/users/$id/followers"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray
            ) {
                progressBar.visibility = View.INVISIBLE

                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for ( i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username = jsonObject.getString("login")
                        getUserData(username)
                    }
                } catch (e : Exception) {
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                            .show()
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
                val errorMessage = when (statusCode){
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                        .show()
            }

        })

    }

    private fun getUserData(id: String) {
        progressBar.visibility = View.VISIBLE
        val index = arguments?.getInt(ARG_SECTION_NUM, 0)
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token fb3a48b669bd6155ef2047c0230b407b8c261d89")
        val url = "https://api.github.com/users/$id"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                progressBar.visibility = View.INVISIBLE
                val result = String(responseBody!!)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val username = jsonObject.getString("login")
                    val name = jsonObject.getString("name")
                    val location = jsonObject.getString("location")
                    val repository = jsonObject.getString("public_repos")
                    val company = jsonObject.getString("company")
                    val followers = jsonObject.getInt("followers")
                    val following = jsonObject.getInt("following")
                    val avatar = jsonObject.getString("avatar_url")
                    when (index) {
                        0 -> usersFollowers.add(
                                Users(
                                        username, name, location, repository, company, followers, following, avatar
                                )
                        )
                        1 -> usersFollowing.add(
                                Users(
                                        username, name, location, repository, company, followers, following, avatar
                                )
                        )
                    }
                    showRecyclerList()
                } catch (e : Exception) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

                override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode){
                401 -> "$statusCode : Bad Request"
                403 -> "$statusCode : Forbidden"
                404 -> "$statusCode : Not Found"
                else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                    .show()
                }

        })
    }
    private fun showRecyclerList() {
        val index = arguments?.getInt(ARG_SECTION_NUM)
        rv_users_followingfollowers.layoutManager = LinearLayoutManager(activity)
        when (index) {
            0 -> listUserAdapter = ListUserAdapter(usersFollowers)
            1 -> listUserAdapter = ListUserAdapter(usersFollowing)
        }
        rv_users_followingfollowers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Users) {
//                showSelectedUser(data)
            }
        })
    }

}