package com.belajar.githubuserlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    var username: String?,
    var name: String?,
    var location: String?,
    var repository: Int?,
    var company: String?,
    var followers: Int?,
    var following: Int?,
    var avatar: Int?
) : Parcelable