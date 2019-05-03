package de.lathspell.test.graphql.resolver

import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/** FIXME */
val customDateTime = GraphQLScalarType("DateTime", "DataTime scalar", object : Coercing<LocalDateTime, String> {
    override fun serialize(input: Any): String {
        return (input as LocalDateTime).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }

    override fun parseValue(input: Any): LocalDateTime {
        return input as LocalDateTime
    }

    override fun parseLiteral(input: Any): LocalDateTime {
        return LocalDateTime.parse(input as String)
    }
})
