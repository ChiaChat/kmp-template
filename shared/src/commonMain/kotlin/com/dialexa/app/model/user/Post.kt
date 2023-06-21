package com.dialexa.app.model.user

internal data class Post(
    val user: User,
    val text: String,
    val timestamp: String,
    val likes: Int,
    val replies: Int,
    val reposts: Int
)

internal val posts =
    listOf(
        Post(
            user = User(name = "Jane Doe", handle = "@janedoe"),
            text =
            "Just learned about Jetpack Compose! It looks like a great way to build Android UIs.",
            timestamp = "5m",
            likes = 23,
            replies = 4,
            reposts = 12
        ),
        Post(
            user = User(name = "John Doe", handle = "@johndoe"),
            text =
            "I've been using Jetpack Compose for a while now and it's really changed the way I approach Android development.",
            timestamp = "23m",
            likes = 45,
            replies = 9,
            reposts = 19
        ),
        Post(
            user = User(name = "Jetpack Compose", handle = "@jetpackcompose"),
            text = "Jetpack Compose is a modern toolkit for building native Android UIs.",
            timestamp = "1h",
            likes = 103,
            replies = 20,
            reposts = 37
        ),
        Post(
            user = User(name = "Jane Doe", handle = "@janedoe"),
            text =
            "Just learned about Jetpack Compose! It looks like a great way to build Android UIs.",
            timestamp = "5m",
            likes = 23,
            replies = 4,
            reposts = 12
        ),
        Post(
            user = User(name = "John Doe", handle = "@johndoe"),
            text =
            "I've been using Jetpack Compose for a while now and it's really changed the way I approach Android development.",
            timestamp = "23m",
            likes = 45,
            replies = 9,
            reposts = 19
        ),
        Post(
            user = User(name = "Jetpack Compose", handle = "@jetpackcompose"),
            text = "Jetpack Compose is a modern toolkit for building native Android UIs.",
            timestamp = "1h",
            likes = 103,
            replies = 20,
            reposts = 37
        ),
        Post(
            user = User(name = "Jane Doe", handle = "@janedoe"),
            text =
            "Just learned about Jetpack Compose! It looks like a great way to build Android UIs.",
            timestamp = "5m",
            likes = 23,
            replies = 4,
            reposts = 12
        ),
        Post(
            user = User(name = "John Doe", handle = "@johndoe"),
            text =
            "I've been using Jetpack Compose for a while now and it's really changed the way I approach Android development.",
            timestamp = "23m",
            likes = 45,
            replies = 9,
            reposts = 19
        ),
        Post(
            user = User(name = "Jetpack Compose", handle = "@jetpackcompose"),
            text = "Jetpack Compose is a modern toolkit for building native Android UIs.",
            timestamp = "1h",
            likes = 103,
            replies = 20,
            reposts = 37
        ),
    )
