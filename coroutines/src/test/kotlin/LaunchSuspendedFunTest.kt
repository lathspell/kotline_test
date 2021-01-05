import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** Uses launch but with "suspend fun" where calls to "delay()" are permitted. */
class LaunchSuspendedFunTest {

    @Test
    fun `coroutines with launch and suspend fun`() {
        myPrintlnLog.clear()

        runBlocking {
            val job1 = launch { demoDelayed(1) }
            val job2 = launch { demoDelayed(2) }

            job1.join()
            job2.join()
        }

        assertEquals(listOf("1a", "2a", "1b", "2b"), myPrintlnLog)
    }

    private suspend fun demoDelayed(jobNr: Int) {
        myPrintln("${jobNr}a")
        delay(1 * 1_000)
        myPrintln("${jobNr}b")
    }

}