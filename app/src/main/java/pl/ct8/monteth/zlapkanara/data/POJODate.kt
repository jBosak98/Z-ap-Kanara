package pl.ct8.monteth.zlapkanara.data

import android.util.Log
import java.util.*

class POJODate() {
    private var date: Calendar = Calendar.getInstance()
    private var street = ""
    private var routes = mutableListOf<String>()

    constructor(street: String, routes: String, sDate: String) : this() {
        this.setDate(sDate)
        this.setRoutes(routes)
        this.setStreet(street)
    }

    fun setStreet(street:String){
        this.street = street
    }

    fun setRoutes(routes: String){//0L, 115
        this.routes =  routes.split(", ").toMutableList()
    }

    fun setDate(sDate:String){
        val inDate = sDate.split(".").toList().map { it.toInt() }//day, month, year
        Log.e("XD", inDate[2].toString() +  inDate[1].toString() + inDate[0].toString())
        this.date.set(inDate[2], inDate[1]-1, inDate[0])//year, month, day
    }

    fun getDay():Day{
//      Day(date: String, street: String, lines: List<String>):
        return Day("${date.get(Calendar.DAY_OF_MONTH)}.${date.get(Calendar.MONTH)+1}", street, routes)
    }
    fun getDate(): Calendar {
        return date
    }
}