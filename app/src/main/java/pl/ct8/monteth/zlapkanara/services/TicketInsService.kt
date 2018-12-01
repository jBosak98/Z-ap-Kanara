package pl.ct8.monteth.zlapkanara.services

import org.jsoup.Jsoup
import pl.ct8.monteth.zlapkanara.data.Day
import pl.ct8.monteth.zlapkanara.data.POJODate
import java.io.IOException
import java.util.*

object TicketInsService {
    var lastCheckedData: Date? = null

    private var weekData = mutableListOf<POJODate>()

    private fun updateData() {
        if (!isCheckedToday()) {
            scrapData()
        }
    }

    fun getTodaysData(): Day {
        updateData()
        return weekData[0].getDay()
    }

    fun getWeekData(): List<Day> {
        return weekData.map { it.getDay() }
    }

    private fun scrapData() {
        val x = Thread(Runnable {
            val builder = StringBuilder()

            try {
                val html = Jsoup.connect("http://mpk.wroc.pl/kontrole-biletow").get()
                val table = html.select("table")[0]
                val rows = table.select("tr")

                weekData.clear()
                for (i in 1 until rows.size) {
                    val row = rows[i]
                    val date = row.getElementsByClass("date-display-single")
                    val line = row.getElementsByClass("views-field-field-inspection-routes")
                    val street = row.getElementsByClass("views-field-title")
                    weekData.add(POJODate(street = street.text(), routes = line.text(), sDate = date.text()))
                }

            } catch (e: IOException) {
                builder.append("Error : ").append(e.message).append("\n")
            }

        })

        x.start()

        try {
            x.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        weekData = weekData
            .filter { it.getDate().get(Calendar.DAY_OF_YEAR) >= Calendar.getInstance().get(Calendar.DAY_OF_YEAR) }
            .toMutableList()
        weekData.sortBy { it.getDate().get(Calendar.DAY_OF_YEAR) }
        lastCheckedData = Calendar.getInstance().time

    }


    private fun isCheckedToday(): Boolean {
        return if (lastCheckedData == null) {
            false
        } else {
            lastCheckedData != Calendar.getInstance().time
        }
    }

}