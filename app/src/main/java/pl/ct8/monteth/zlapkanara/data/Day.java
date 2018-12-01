package pl.ct8.monteth.zlapkanara.data;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Day {
    String date;
    String street;
    List<String> routes;

    public Day(String date, String street, List<String> lines) {
        this.date = date;
        this.street = street;
        this.routes = lines;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(List<String> lines) {
        this.routes = lines;
    }
}