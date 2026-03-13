package dev.apollointhehouse.studyspace

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform