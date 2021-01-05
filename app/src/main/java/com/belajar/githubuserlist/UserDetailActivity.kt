package com.belajar.githubuserlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_user_detail.*


class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        tfollowers_detail.text = getString(R.string.teks_followers)
        tfollowing_detail.text = getString(R.string.teks_following)
        trepo_detail.text = getString(R.string.teks_repo)

        val getUser : Users? = intent.getParcelableExtra(EXTRA_USER)

        Glide.with(this)
            .load(getUser?.avatar)
            .into(avatar_detail)
        nama_detail.text = getUser?.name
        username_detail.text = getUser?.username
        comp_detail.text = getUser?.company
        loc_detail.text = getUser?.location
        nfollowers_detail.text = getUser?.followers.toString()
        nfollowing_detail.text = getUser?.following.toString()
        nrepo_detail.text = getUser?.repository.toString()

        viewPagerConfig()
    }

    private fun viewPagerConfig() {
        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }


}