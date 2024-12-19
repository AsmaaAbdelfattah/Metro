package com.example.metro.Model

import java.util.LinkedList

class Metro {
    val line1 = listOf("new el-marg",
        "el-marg",
        "ezbet el-nakhl",
        "ain shams",
        "el-matareyya",
        "helmeyet al-zaitoun",
        "hadayeq al-zaitoun",
        "saray el-qobba",
        "hammamat el-qobba",
        "kobri el-qobba",
        "manshiet el-sadr",
        "el-demerdash",
        "ghamra",
        "al-shohadaa",
        "orabi",
        "nasser",
        "sadat",
        "saad zaghloul",
        "al-sayeda zeinab",
        "el-malek el-saleh",
        "mar girgis",
        "el-zahraa",
        "dar el-salam",
        "hadayek el-maadi",
        "maadi",
        "sakanat el-maadi",
        "tora el-balad",
        "kozzika",
        "tura el-esmant",
        "elmasraa",
        "hadayek helwan",
        "wadi hof",
        "helwan university",
        "ain helwan",
        "helwan")
    val line2 = listOf("shubra al khaimah",
        "koliet el-zeraa",
        "mezallat",
        "khalafawy",
        "st. teresa",
        "rod el-farag",
        "masarra",
        "al-shohadaa",
        "attaba",
        "mohamed naguib",
        "sadat",
        "opera",
        "dokki",
        "el bohoth",
        "cairo university",
        "faisal",
        "giza",
        "omm el-masryeen",
        "sakiat mekky",
        "el monib")
    val line3 = listOf("adly mansour",
        "haykestep",
        "omar ibn el khattab",
        "qubaa",
        "hesham barakat",
        "el nozha",
        "el shams club",
        "alf masken",
        "heliopolis",
        "haroun",
        "al-ahram",
        "koleyet el-banat",
        "stadium",
        "abbassiya",
        "fair zone",
        "abdou pasha",
        "el-geish",
        "bab el shaariya",
        "attaba",
        "nasser",
        "maspero",
        "safaa hijazy",
        "kit-kat",
        "sudan",
        "imbaba",
        "el-bohy",
        "el-qawmia",
        "ring road",
        "rod el farag corridor",
        "tawfikia",
        "wadi el nile",
        "gamet el dowal",
        "boulak el dakrour",
        "cairo university",
    )
    // Shortest path function (same as before)
    fun findPathBFS(routes: List<List<String>>, start: String, end: String): List<String> {
        // Map to keep track of the connections between stations
        val stationGraph = mutableMapOf<String, MutableList<String>>()

        // Build the graph: Each route connects its stations in sequence
        for (route in routes) {
            for (i in 0 until route.size - 1) {
                val currentStation = route[i]
                val nextStation = route[i + 1]

                // Add both directions since it's an undirected connection
                // Using getOrPut to initialize the list if not present
                stationGraph.getOrPut(currentStation) { mutableListOf() }.add(nextStation)
                stationGraph.getOrPut(nextStation) { mutableListOf() }.add(currentStation)
            }
        }

        // BFS to find the shortest path from start to end
        val queue = ArrayDeque<Pair<String, List<String>>>()
        val visited = mutableSetOf<String>()

        // Initialize the queue with the start station
        queue.add(Pair(start, listOf(start)))
        visited.add(start)

        while (queue.isNotEmpty()) {
            val (currentStation, path) = queue.removeFirst()

            // If we reach the end station, return the path
            if (currentStation == end) {
                return path
            }

            // Visit all neighboring stations
            for (neighbor in stationGraph.getOrDefault(currentStation, listOf())) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(Pair(neighbor, path + neighbor))
                }
            }
        }

        // If no path is found, return an empty list
        return emptyList()
    }

    fun ticketprice(numStations: Int): String {

        if (numStations in (1..9)) {

            return "Ticket price is 8 EGP"
        } else if (numStations in (10..16)) {
            return "Ticket price is 10 EGP"
        } else if (numStations in (17..23)) {
            return "Ticket price is 15 EGP"
        } else {
            return "Ticket price is 20 EGP"
        }
    }
    fun tripTime(numStations: Int): String {
        return "Estimate Time is ${(numStations) * 3}"

    }

}