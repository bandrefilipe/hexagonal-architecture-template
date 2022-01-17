package com.example.rest.api

import com.example.application.ServiceExample
import com.example.domain.Greeting
import mu.KotlinLogging
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ControllerExample(
    private val service: ServiceExample,
) {

    @RequestMapping("/")
    fun endpoint(): Greeting {
        log.info { "Processing request" }
        return service.serve()
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
