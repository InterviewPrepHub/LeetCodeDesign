package com.example.LeetCodeDesign.Design;

import java.util.HashMap;

//https://www.youtube.com/watch?v=HJeMXqx0ADQ&ab_channel=AlgorithmsMadeEasy

public class DesignUndergroundSystem {

    HashMap<Integer, ArrivalInfo> checkInMap;
    HashMap<String, double[]> checkoutMap;
    int avg;

    public DesignUndergroundSystem() {
        checkInMap = new HashMap<>();
        checkoutMap = new HashMap<>();
        avg = 0;
    }

    public void checkIn(int id, String stationName, int t) {
        ArrivalInfo a = new ArrivalInfo(id, stationName, t);
        checkInMap.put(id, a);
    }

    public void checkOut(int id, String stationName, int t) {
        if (checkInMap.containsKey(id)) {
            String startSt = checkInMap.get(id).stationName;
            String endSt = stationName;

            String key = startSt+"_"+endSt;

            int startTime = checkInMap.get(id).time;
            int endTime = t;

            int total_time = endTime - startTime;

            double[] pair = new double[2];
            pair[0] += total_time;
            pair[1] ++; // this is the total no times the travel happened between start and end station
            checkoutMap.put(key, pair);

        }
    }

    public double getAverageTime(String startStation, String endStation) {
        double[] res = checkoutMap.get(startStation + "_" + endStation);
        return (double) (res[0]/res[1]);
    }

    public static void main(String[] args) {
        DesignUndergroundSystem undergroundSystem = new DesignUndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
        double res1 = undergroundSystem.getAverageTime("Paradise", "Cambridge"); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        System.out.println(res1);
        double res2 = undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
        System.out.println(res2);
        undergroundSystem.checkIn(10, "Leyton", 24);
        double res3 = undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000
        System.out.println(res3);
        undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
        double res4 = undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
        System.out.println(res4);
    }
}


class ArrivalInfo {
    int id;
    String stationName;
    int time;

    public ArrivalInfo(int id, String stationName, int time) {
        this.id = id;
        this.stationName = stationName;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public int getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
