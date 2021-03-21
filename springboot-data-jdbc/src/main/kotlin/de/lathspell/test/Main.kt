package de.lathspell.test

import de.lathspell.test.jpa.PersonRepo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@SpringBootApplication
@EnableJdbcRepositories(basePackageClasses = [PersonRepo::class])
class Main
