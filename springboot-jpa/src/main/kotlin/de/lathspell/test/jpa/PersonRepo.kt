package de.lathspell.test.jpa

import de.lathspell.test.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface PersonRepo : JpaRepository<Person, UUID>
