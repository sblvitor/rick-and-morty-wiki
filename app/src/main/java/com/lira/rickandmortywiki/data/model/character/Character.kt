package com.lira.rickandmortywiki.data.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val episode: List<String>,
    val origin: Location,
    val location: Location,
    val image: String,
    val url: String
): Parcelable
