package com.example.application

import com.example.domain.Greeting
import com.example.persistence.RepositoryExample
import mu.KotlinLogging

class ServiceExample(
    private val repository: RepositoryExample,
) {

    fun serve(): Greeting {
        log.info { "Application logic" }
        return repository.get()
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
