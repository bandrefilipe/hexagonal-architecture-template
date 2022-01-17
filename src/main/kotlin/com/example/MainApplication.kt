package com.example

import com.example.application.ServiceExample
import com.example.persistence.RepositoryExample
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MainApplication {

    @Bean
    fun repositoryExample() = RepositoryExample()

    @Bean
    fun serviceExample(repository: RepositoryExample) = ServiceExample(repository)
}

fun main(args: Array<String>) {
    runApplication<MainApplication>(*args)
}
