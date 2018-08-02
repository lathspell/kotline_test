package ex1

import java.util.*

fun main(args: Array<String>) : Unit {
    val john = KotlinPerson(1L, "Mr", "John", "Blue", GregorianCalendar(1977, 9, 3));
    val jane = KotlinPerson(2L, "Mrs", "Jane", "Green", null);
    println("$john's age is " + john.getAge());
    println("$jane's age is " + jane.getAge());
    println("The age of someone born on 3rd May 1988 is " + KotlinPerson.getAge(GregorianCalendar(1988, 5, 3)));
}