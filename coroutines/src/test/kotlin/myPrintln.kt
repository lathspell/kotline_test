import java.time.LocalDateTime

val myPrintlnLog = mutableListOf<String>()

fun myPrintln(arg: String) {
    println(String.format("%-30s [%s] %s", LocalDateTime.now(), Thread.currentThread().name, arg))
    myPrintlnLog.add(arg)
}
