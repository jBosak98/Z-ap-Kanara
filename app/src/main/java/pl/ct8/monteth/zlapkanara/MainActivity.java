package pl.ct8.monteth.zlapkanara

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.w3c.dom.Text
import pl.ct8.monteth.zlapkanara.services.TicketInsService

import java.io.IOException

class MainActivity : AppCompatActivity() {

    internal var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TicketInsService.getTodaysData()
        print("HALOKURWA2")

        tv = findViewById(R.id.acMain_tv)

        getWebsite()

    }

    private fun getWebsite() {
        val x = Thread(Runnable {
            val builder = StringBuilder()

            try {
                val doc = Jsoup.connect("http://www.ssaurel.com/blog").get()
                val title = doc.title()
                val links = doc.select("a[href]")

                builder.append(title).append("\n")

                for (link in links) {
                    builder.append("\n").append("Link : ").append(link.attr("href"))
                        .append("\n").append("Text : ").append(link.text())
                }
            } catch (e: IOException) {
                builder.append("Error : ").append(e.message).append("\n")
            }

            runOnUiThread { tv.text = builder.toString() }
        })

        x.start()

        try {
            x.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}
