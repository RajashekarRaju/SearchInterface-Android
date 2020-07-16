package com.developersbreach.searchinterface.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val name: String,
    val banner: String,
    val description: String
) : Parcelable