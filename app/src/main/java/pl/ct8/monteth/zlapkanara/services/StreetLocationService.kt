package pl.ct8.monteth.zlapkanara.services

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.nfc.Tag
import android.util.Log
import java.io.IOException
import java.util.*

object StreetLocationService {

    fun getLocation(context:Context, fullAddress:String): Address? {
        var address: Address? = null
        val x = Thread(Runnable {
            var geocoder = Geocoder(context, Locale.getDefault())
            try {
                address = geocoder.getFromLocationName(fullAddress,1)[0]
            } catch (e: IOException) {
                Log.e("StreetLocationService", "Unable to find location", e)
            }

        })
        x.start()
        try {
            x.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return address
    }

}