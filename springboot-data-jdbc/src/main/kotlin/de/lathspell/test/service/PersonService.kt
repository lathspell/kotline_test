package de.lathspell.test.service

import de.lathspell.test.jpa.GroupRepo
import de.lathspell.test.jpa.PersonRepo
import de.lathspell.test.model.Group
import de.lathspell.test.model.Person
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PersonService(
    private val personRepo: PersonRepo,
    private val groupRepo: GroupRepo
) {

    fun addPersonAndGroup(p: Person) {
        personRepo.insert(p) // Our extension to PersonRepo to support UUID and not only auto-generated ids

        val groups = personRepo.findAll().map { Group(name = "G" + it.gid) }.toSet()

        groupRepo.deleteAll()
        groups.forEach { groupRepo.save(it) }
    }

}
