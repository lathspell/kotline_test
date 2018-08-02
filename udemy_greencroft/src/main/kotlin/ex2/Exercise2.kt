import java.math.BigDecimal

data class Seat(val row: Int, val num: Int, val price: BigDecimal, val description: String) {
    //YOU MAY NOT EDIT THIS CLASS!
    override fun toString(): String = "Seat $row-$num $$price ($description)"
}

class Theater {

    // SEAT PRICES:
    // Seats in rows 14 and 15 cost 14.50
    // Seats in rows 1 to 13 and numbered 1 to 3 or 34 to 36 cost 16.50
    // All other seats in row 1 cost 21.00
    // All other seats cost 18.00

    // SEAT DESCRIPTIONS:
    // Seats in row 15: "Back row"
    // Seats in row 14: "Cheaper seat"
    // All other rows, seats 1 to 3 and 34 to 36: "Restricted View"
    // All other seats in rows 1 and 2 "Best view"
    // All other seats: "Standard seat"

    private fun calcPrice(row: Int, num: Int) = when {
        row in 14..15 -> BigDecimal(14.50)
        row in 1..13 && (num in 1..3 || num in 34..36) -> BigDecimal(16.50)
        row == 1 -> BigDecimal(21)
        else -> BigDecimal(18)
    }

    private fun calcDescription(row: Int, num: Int) = when {
        row == 15 -> "Back row"
        row == 14 -> "Cheaper seat"
        num in 1..3 || num in 34..36 -> "Restricted View"
        row in 1..2 -> "Best view"
        else -> "Standard seat"
    }

    val seats =
            (1..15).flatMap { row ->
                (1..36).map { num ->
                    Seat(row, num, calcPrice(row, num), calcDescription(row, num))
                }
            }
}

fun main(args: Array<String>) {
    println(Theater().seats)
    val cheapSeats = Theater().seats.filter { it.price == BigDecimal(14.50) }
    for (seat in cheapSeats) println(seat)
}