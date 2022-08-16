package com.lira.rickandmortywiki.data.model.character

data class Character(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val url: String
)
