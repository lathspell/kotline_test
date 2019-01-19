package de.lathspell.test.graphql

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

/**
 * Simple test to see the raw text that is transmitted.
 *
 * DO NOT USE - quoting and whitespace removal is hard coded and not safe
 *
 * Does not work with webEnvironment = MOCK as GraphQL needs a WebSocket which is not mocked.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class RawPostTest {

    @Autowired
    private lateinit var rest: TestRestTemplate

    @Test
    fun testRaw() {
        val query = """
    		query firstoperation {
    			getCustomerByName(name: "aaa") {
    				name,
    				contact {
    					name
    				}
    			}
    		}
    		"""
        val jsonQuotedQuery = query.replace("\"", "\\\"")
        val requestJson = """
            {
                "operationName": "firstoperation",
                "query": "$jsonQuotedQuery",
                "variables": null
            }
            """.trimIndent().replace(Regex("""[\n\t]"""), "")

        val expected = """
    		{
    			"data": {
					"getCustomerByName": {
						"name": "aaa",
						"contact": {
							"name": "Alfred"
						}
					}
				}
			}
			""".replace(Regex("""\s"""), "")

        val entity: ResponseEntity<String> = rest.postForEntity("/graphql/", requestJson)

        assertThat(entity.statusCodeValue).isEqualTo(200)
        assertThat(entity.body).isEqualTo(expected)
    }

}

