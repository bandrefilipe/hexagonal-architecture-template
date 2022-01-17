package com.example.persistence

import com.example.commons.randomLong
import com.example.domain.Greeting
import mu.KotlinLogging

class RepositoryExample {

    fun get(): Greeting {
        log.info { "Faking operation" }
        return Greeting(
            message = "Hello, World!",
            fingerprint = randomLong(),
        )
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
