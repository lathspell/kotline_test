package de.lathspell.test

import de.lathspell.test.dao.ContactDao
import de.lathspell.test.dao.CustomerDao
import org.springframework.stereotype.Component

@Component
class Init {
    constructor(contactDao: ContactDao, customerDao: CustomerDao) {
        val alfred = contactDao.create("Alfred", "alfred@aaa.com")
        val berta = contactDao.create("Berta", "berta@boo.com")
        val charlie = contactDao.create("Charlie", "charlie@chee.com")

        customerDao.create("aaa",  alfred)
        customerDao.create("boo",  berta)
        customerDao.create("chee",  charlie)
    }
}