package com.lira.rickandmortywiki.data.model.character

data class Result(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val episode: List<String>,
    val location: Location,
    val image: String,
    val url: String
)
