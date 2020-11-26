package com.belajar.githubuserlist

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView


class UserDetailActivity : AppCompatActivity() {

    companion object {
        var EXTRA_USER = "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        //Inisiasi Objek yang berubah valuenya di setiap user
        val avaDetail : CircleImageView = findViewById(R.id.avatar_detail)
        val nameDetail : TextView = findViewById(R.id.nama_detail)
        val usernameDetail : TextView = findViewById(R.id.username_detail)
        val compDetail : TextView = findViewById(R.id.comp_detail)
        val locDetail : TextView = findViewById(R.id.loc_detail)
        val followingNDetail : TextView = findViewById(R.id.nfollowing_detail)
        val followersNDetail : TextView = findViewById(R.id.nfollowers_detail)
        val repoNDetail : TextView = findViewById(R.id.nrepo_detail)

        //Inisiasi Objek yang tidak berubah valuenya
        val followersTDetail : TextView = findViewById(R.id.tfollowers_detail)
        val followingTDetail : TextView = findViewById(R.id.tfollowing_detail)
        val repoTDetail : TextView = findViewById(R.id.trepo_detail)

        followersTDetail.text = getString(R.string.teks_followers)
        followingTDetail.text = getString(R.string.teks_following)
        repoTDetail.text = getString(R.string.teks_repo)

        val getUser : Users? = intent.getParcelableExtra(EXTRA_USER)

        avaDetail.setImageResource(getUser?.avatar!!)
        nameDetail.text = getUser.name
        usernameDetail.text = getUser.username
        compDetail.text = getUser.company
        locDetail.text = getUser.location
        followersNDetail.text = getUser.followers.toString()
        followingNDetail.text = getUser.following.toString()
        repoNDetail.text = getUser.repository.toString()
    }


}