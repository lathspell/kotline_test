Links
=====

Language
========

Variables
---------
* `var` declares a mutable variable
* `val` declares an immutable variable
* the variable type can usually be inferred
* variables can be used inside strings using "string templates" like `"Hello $name!"`

Special Classes
---------------
* Unit corresponds to Java "void" and is used to signify that a function does not return anything.
  It is implemented as a singleton object though and not as a language construct.
* Nothing is class with a private constructor and used to mark classes that will always 
  return with an Exception as it is impossible to create an instance of Nothing for "return". 

Naming
------
* Public classes do not need to be in a file with the same name
* Packages do not need to be in corresponding directories although that's recommended

Visibility and Access
---------------------
* See <https://kotlinlang.org/docs/reference/visibility-modifiers.html>
* default is "public"
* there is no "package level", only "internal" for access within the same Kotlin module
* Classes and methods are "final public" by default. `open` is needed to subclass them,
  `override` to override them (to avoid accidentally hiding a method)
* Inner classes are by default Static Inner Classes. Only if marked as `inner class` they
  may access their outer instance using `this@Outer`. 

Objects
-------

* The "object" keyword creates a single instance of a class. A singleton. 

Compagnion Object
-----------------

* See <https://kotlinlang.org/docs/reference/object-declarations.html#companion-objects>
* Instead of Java's "public static" methods, Kotlin uses a compagnion object.
* Can optionally be declared with @JvmStatic to be accessible in Java like a static method 
and not via `MyClass.Companion.create()`.
```
class MyClass {
  companion object Factory {
    @JvmStatic
    fun create(): MyClass = MyClass()
  }
}

val instance = MyClass.create()
```

Reflection
----------
* Referring to the class like ".class" via the "class literal" operator "::",
  e.g. `Exception::class`

Casting
-------
* Type check is done via "a is B", not "instanceOf"
* Type casting is done via "a as B", not "(B)a"
* Once casted or checkd the compiler will remember and members are accessible from there on!

Ranges
------
* The range operator ".." can be used to create an IntRange which is a subtype of IntProgression
* An expression like "10 downTo 1 step 2" can be used to create other "progressions"
* As e.g. IntProgression is a class ".map {}" can be used directly without a for loop

Exceptions
----------
* No difference between "checked" and "unchecked" exceptions!
* "try" is, of course, an expression and not a statement, as well

Expressions
-----------
* Most Java statements like "if", "for", "switch" have Kotlin counterparts that are 
  expressions and yield a result. In this case no "return" is written, the result is
  the last statement.

Functions
---------
* function arguments can be named like `sin(x = 3)`
* default values to arguments can be specified like `foo(a: Int = 4)`
* `@JvmOverloads` generates additional methods that use the default values when called from Java 
* Extension functions are not automatically globally available but must be imported
* imported functions can be aliased like `import foo.bar as baz` 

Deconstructuring Declarations
-----------------------------
* Pairs and other tupel like data can be immediately splitted into multiple variables when 
  assigning. Kotlin supports this for all types who follows the convention to have functions 
  with names like `componentX()`. Example: `val (a, b, c) = createTriple()`
* `data class` automatically generates `componentX()` functions.
* The Kotlin standard library adds componentX() extension functions for Map.Entry   

Constructors
------------
* "Primary Constructors" like `class Foo(val a : Int, b : Int)` are part of the class
  declaration. The keyword "constructor" as in `class Foo constructor(a: Int)` is optional.
* "Secondary Constructors" are written like Java constructors and can have a method body.
* There are "init" blocks just like in Java

Operator Overloading
--------------------
* In Kotlin "a + b" is the same as "a.plus(b)" therefore operator overloading archived by just 
  adding a function with the correct signature to a class.
* Operator groups are among others: unary (+,-,!,--x,x--,++x,x++), binary (+,-,/,%,*), compount assignment (+=,-=)
* Comparison operators (=,!=,<,<=,=>,>) can be overloaded as well
* Kotlin can compare not only primitive types as Java but also objects. So no more explicit `.equals()`

Lambdas
-------
* `return` inside a lambda means exiting the function that called the lambda
* If a function parameter is declared as Lambda that looks like an Extension Function then its a a 
  "Lambda with Receiver": `fun buildString(f : StringBuilder.() : Unit) ...` When calling the outer
  function the Lambda can be shortened as the magic variable "it" does not have to be used:
  `buildString { append("Hello") }`. 

Generics
--------
* Kotlin does not have raw types like `List` without a type parameter in Java. The type of e.g. the elements
  of a list must either be inferable or specified explicitly.  

Delegated Properties
--------------------
* Initialization can be delayed by declaring e.g. `val foo : Int by myInitr` where `myInitr` is a object that
  contains the two operator functions `getValue()` and `setValue()`
* Creating an initializer object is simplified by the `lazy()` function that takes a lambda as argument and creates
  an instance of class Lazy<T> e.g. `val foo : Int by lazy { return 42 }`

Standard Library
================

toString/equals/hash
--------------------
* The `toString()` method works just like in Java
* The `equals()` method is required to compare the object's values and not the references
* The `==` operator calls `equals()`
* Strings can thus be compared using `==` and not only using `.equals()` like in Java
* The `hashCode()` method is required to return the same code if the objects are the
  same according to `equals()`

Collections
-----------
* Quick creation using `arrayOf`, `listOf()` and `mapOf(Pair())` or `arrayListOf()`, `hashmapOf()` 
* Map to List using `.map()`
* List to Map using `.associate(Pair(it, ...))`
* List to String using `.joinToString(sep)`
* Pairs can be created using the infix operator "to": `"a" to 1` is `Pair("a", 1)`
* Every Kotlin collection is implemented using a Java Collection type. Kotlin Array is a Java Array.
* For every Kotlin collection type there are two interfaces, Collection/Map/Set is read only and
  MutableCollection,MutableMap,MutableSet is extended with write access methods.
  (read only does not mean immutable, other references to the same object maybe of a mutable type)
* The "spread operator" turns a Array into multiple individual method parameters: `myfunc(*params)`
 
Sequences
---------
* Behave just like collections but are optimized for working with large data as they
  do not create temporary lists for every filter/map operation.
* Similar to Java 8 Streams and created as those were not available for all Kotlin
  platforms (like e.g. Android)
* Seem to be a special case datatype, list/map are for general use.
* Created with `list.asSequence()`, ended with `sequence.toList()`

apply/with/buildString
----------------------
* `with` is a function that applies a code block to a variable
* `apply` is like `with` but also returns the result 
* `buildString()` is a nice Kotlin standard library method to build strings 
  using StringBuilder and with().
