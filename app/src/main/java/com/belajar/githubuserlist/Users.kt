package com.belajar.githubuserlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    var username: String? = null,
    var name: String? = null,
    var location: String? = null,
    var repository: String? = null,
    var company: String? = null,
    var followers: Int? = null,
    var following: Int? = null,
    var avatar: String? = null
) : Parcelable