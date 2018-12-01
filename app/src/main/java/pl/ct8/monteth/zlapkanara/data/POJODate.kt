package pl.ct8.monteth.zlapkanara.data

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
        this.date.set(inDate[2], inDate[1], inDate[0])//year, month, day
    }

    fun getDay():Day{
//      Day(date: String, street: String, lines: List<String>):
        return Day("${date.get(Calendar.DAY_OF_MONTH)},${date.get(Calendar.MONTH)}", street, routes)
    }
    fun getDate(): Calendar {
        return date
    }
}