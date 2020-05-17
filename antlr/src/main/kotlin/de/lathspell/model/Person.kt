package de.lathspell.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Person(
        @Id
        val id: UUID = UUID.randomUUID(),
        val name: String,
        val age: Int
)
