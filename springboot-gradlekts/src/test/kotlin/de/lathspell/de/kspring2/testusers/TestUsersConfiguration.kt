package de.lathspell.de.kspring2.testusers

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
class TestUsersConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "testusers", ignoreInvalidFields = false, ignoreUnknownFields = false)
    fun testusers() = HashMap<String, TestUser>()

}
