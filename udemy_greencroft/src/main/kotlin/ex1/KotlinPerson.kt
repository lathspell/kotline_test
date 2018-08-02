package ex1

import java.util.*

data class KotlinPerson(
        private val id: Long,
        private val title: String,
        private val firstName: String,
        private val surname: String,
        private val dateOfBirth: Calendar?) {

    companion object {
        fun getAge(dateOfBirth: Calendar?): Int {
            if (dateOfBirth == null) return -1;
            val today = GregorianCalendar()
            val years = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
            return if (dateOfBirth.get(Calendar.DAY_OF_YEAR) >= today.get(Calendar.YEAR)) {
                years - 1;
            } else {
                years;
            }
        }
    }

    override fun toString() = "$title $firstName $surname"

    fun getAge() = getAge(dateOfBirth)
}
