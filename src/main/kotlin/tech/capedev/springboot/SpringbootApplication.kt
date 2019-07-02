package tech.capedev.springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

/**
 * Unsecure aaplication
 * From https://spring.io/guides/tutorials/spring-boot-kotlin/
 */
@SpringBootApplication
class SpringbootApplication

fun main(args: Array<String>) {
    runApplication<SpringbootApplication>(*args)
}
