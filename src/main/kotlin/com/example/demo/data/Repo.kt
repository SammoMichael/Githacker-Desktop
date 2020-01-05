package com.example.demo.data

import java.util.*


data class Repo (
        val id: Int,
        val full_name: String,
        val html_url: String,
        val owner: Owner
        )

data class Owner (
        val avatar_url: String
)