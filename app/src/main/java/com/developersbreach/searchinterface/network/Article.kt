package com.developersbreach.searchinterface.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val id: Int,
    val banner: String,
    val name: String,
    val description: String
) : Parcelable