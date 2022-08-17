package com.lira.rickandmortywiki.data.model.character

data class Info(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any? = null
)
