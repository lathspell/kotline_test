package de.lathspell.test.graphql

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.lathspell.test.graphql.model.Customer
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
 *
 * GraphQL is designed to return only *some* attributes of our model classes so it does not
 * necessarily make sense to try to deserialize those values to the model classes.
  */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class RawPostTest {

    @Autowired
    private lateinit var rest: TestRestTemplate

    @Test
    fun testRaw2() {
        // define query
        val query = """
            {
                getCustomerByName(name: "aaa") {
                    name
                    contact { name }
                }
            }
            """

        // build GraphQL request
        val jsonQuotedQuery = ObjectMapper().writeValueAsString(query.trimIndent())
        val requestJson = """{ "query": $jsonQuotedQuery }"""

        // send GraphQL request
        val entity: ResponseEntity<String> = rest.postForEntity("/graphql/", requestJson)
        assertThat(entity.statusCodeValue).isEqualTo(200)

        // deserialize the result
        val jsonNode = ObjectMapper().readTree(entity.body)
        assertThat(jsonNode["data"]["getCustomerByName"]["name"].textValue()).isEqualTo("aaa")
        assertThat(jsonNode["data"]["getCustomerByName"]["contact"]["name"].textValue()).isEqualTo("Alfred")
    }

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

