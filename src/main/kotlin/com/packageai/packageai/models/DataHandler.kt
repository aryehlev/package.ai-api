package com.packageai.packageai.models

import com.google.common.collect.Comparators
import com.google.gson.GsonBuilder
import java.io.File
import java.io.IOException
import java.net.URL


class DataHandler(private var alldata: ArrayList<List<String>>){

    fun loadData()
            = File("data/server coding task sample data.csv").forEachLine {
        val list = it.split(",")

        val row = listOf(list[0], list[1], list[2])

        alldata.add(row)
    }

    fun getDistance(location1: String, location2: String):String {


        val newlocation1 = location1.trim().replace("\\s".toRegex(), "+")
        val newlocation2 = location2.trim().replace("\\s".toRegex(), "+")
        val response = try {
            URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=AIzaSyAEA_Ue6NSbOmmC7kNzxdzqpgxh6EMnhbg".format(newlocation1, newlocation2))
                    .openStream()
                    .bufferedReader()
                    .use {
                        it.readText() }
        } catch (e: IOException) {
            "Error with ${e.message}."
        }
        var value = ""
        try{
            value = response.split(':')[7].split("}")[0].replace("\\s".toRegex(), "")
        }
        catch (e: java.lang.IndexOutOfBoundsException){
            println(response)
        }

        println(value)
        return value
        }



    private fun updateDistances(location: String): Int{
        val alldata_list = alldata.map { listOf(it[0], it[1], it[2], getDistance(it[2], location))}
        alldata = alldata_list.toCollection(ArrayList())
        return 0
    }


    fun getNClosest(location: String, number: Int) : String{

        val a  = this.updateDistances(location)

        val comparator = OurComparator()
        val collector = Comparators.least(number, comparator)
        val builder = GsonBuilder()
        val gson = builder.create()
        val jsonString = gson.toJson(alldata.stream().collect(collector))

        return jsonString


    }



    class OurComparator: Comparator<List<String>>{
        override fun compare(o1: List<String>, o2: List<String>): Int {
            if (o1[3] == "" )
                return 1
            if (o2[3] == "" )
                return -1
            return o1[3].toInt().compareTo(o2[3].toInt())
        }
    }
}


