package de.lathspell.de.kspring2.testusers

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class TestUsersTests {

    @Autowired
    lateinit var testUsers: Map<String, TestUser>

    @Test
    fun `test map injection`() {
        assertEquals(2, testUsers.size)
        assertEquals(2, (testUsers["max"] ?: error("Max not found")).id)
    }
}
