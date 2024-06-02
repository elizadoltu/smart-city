package com.example.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotStats {
    public static Map<String, Map<String, Integer>> getParkingLotStats() {
        Map<String, Map<String, Integer>> parkingLotData = new HashMap<>();

        List<String> parkingLots = DatabaseUtil.getParkingLots();
        for (String parkingLot : parkingLots) {
            String[] parts = parkingLot.split(" - ");
            String name = parts[0];
            int available = Integer.parseInt(parts[1].split(": ")[1]);

          
            Map<String, Integer> stats = new HashMap<>();
            stats.put("Monday", available + 5);
            stats.put("Tuesday", available - 3);
            stats.put("Wednesday", available + 2);
            stats.put("Thursday", available - 1);
            stats.put("Friday", available + 10);
            stats.put("Saturday", available + 15);
            stats.put("Sunday", available + 8);

            parkingLotData.put(name, stats);
        }

        return parkingLotData;
    }
}
