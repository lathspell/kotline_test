Kotlin GraphQL Demo
===================

Start `GraphqlApplication` and go to `http://localhost:8080/graphiql/` (note the "i"!)

GraphQL
=======

GraphQL is a language to query and mutatate data from a server using a human and machine 
readable JSON-like language.

It is not designed as successor to REST but can be used as a better replacement 
in certain cases. Whereas REST tends to always send as much data as possible to the 
client as it uses the complete JSON representation of an object and one HTTP request
per class/resource, GraphQL triest to send the least amount of data i.e. only the
requested fields and a whole object graph in one answer.   

GraphQL consists of
* a human readable **schema definition** on the server
* some **resolvers** classes in the backend that connect to the data store
* an automatically generated machine readable JSON schema representation for the client  
* a strongly typed **query language** for the client that is send as HTTP POST content type *application/graphql*

Features
--------
- Types as GraphQL is strongly typed
  * built-in scalar types like ID, String, Int, Float, Boolean
  * object types like class names
  * "Foo!" as notation for non-nullable
  * "[...]" as notation for arrays
  * "enum" for enum types
  * "interface" for interfaces
  * "union" for result type variance
- Fields (only returned what's being asked for)
- Arguments to Queries that act as filters
- Arguments to Fields that can modify e.g. the unit conversion
- Aliases to avoid name conflicts
- Fragments to avoid repeated specification of the same result fields for multiple queries
- Variables inside Fragments
- Parameters that are separate from the Query definition
- Operation name for Queries (just for documentation)
- Directives like @include(if) and @skip(if) to change query depending on Parameters
- Mutations that act like Queries but can change things
- Meta-Fields to e.g. get the Type of a parameter with the returned result

Caveats
-------

GraphQL is designed to only only the requested fields of the result objects. That means that just like with plain SQL
(as opposed to ORMs) it is often impossible to deserialize a GraphQL response to model objects.
Using the result like a multidimensional map though takes away a lot of the type safe benefits. Some client libraries
use the GraphQL Schema (or an "introspection" result) and pre-defined queries to generate client source code. That's
more type-safe but less flexible.   

Examples
--------

See examples.graphql

Links
=====

GraphQL Foundation
* https://graphql.org/learn/ (Howto)

Java Server and Client implementation
* https://www.graphql-java.com/
* https://www.graphql-java.com/documentation/master/

Kotlin Server implementation
* https://github.com/pgutkowski/KGraphQL

Blog posts
* https://philsturgeon.uk/api/2017/01/24/graphql-vs-rest-overview/
* https://blog.pusher.com/writing-graphql-service-using-kotlin-spring-boot/
* http://adavis.info/2018/02/graphql-api-in-kotlin.html
