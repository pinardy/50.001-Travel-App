package com.example.user.travelapp;

import java.util.HashMap;
import java.util.Iterator;

public class DestinationsData_fast {
    public static final HashMap<Integer, String> DATAMAP = new HashMap();
    public static final HashMap<Integer, String> modeOfTransport;
    public static final int DESTINATION_SIZE;
    public static final double[][][] COST;
    public static final int[][][] TIME;

    public DestinationsData_fast() {
    }

    public static Object getKeyFromValue(HashMap hm, Integer index) {
        Iterator var2 = hm.keySet().iterator();

        Object o;
        do {
            if(!var2.hasNext()) {
                return null;
            }

            o = var2.next();
        } while(!hm.get(o).equals(index));

        return o;
    }

    public static void main(String[] args) {
        byte mode = 2;
        byte from = 0;
        byte to = 1;
        System.out.println("For method: " + (String)modeOfTransport.get(Integer.valueOf(mode)) + "\n" + "Cost from " + getKeyFromValue(DATAMAP, Integer.valueOf(from)) + " to " + getKeyFromValue(DATAMAP, Integer.valueOf(to)) + " is " + "$" + COST[mode][from][to] + "\n");
        System.out.println("For method: " + (String)modeOfTransport.get(Integer.valueOf(mode)) + "\n" + "Time from " + getKeyFromValue(DATAMAP, Integer.valueOf(from)) + " to " + getKeyFromValue(DATAMAP, Integer.valueOf(to)) + " is " + TIME[mode][from][to] + " mins" + "\n");
    }

    static {
        DATAMAP.put(0, "Marina Bay Sands");
        DATAMAP.put(1, "Singapore Flyer");
        DATAMAP.put(2, "Vivo City");
        DATAMAP.put(3, "Resorts World Sentosa");
        DATAMAP.put(4, "Buddha Tooth Relic Temple");
        DATAMAP.put(5, "Zoo");
        modeOfTransport = new HashMap();
        modeOfTransport.put(Integer.valueOf(0), "Walking");
        modeOfTransport.put(Integer.valueOf(1), "Public Transport");
        modeOfTransport.put(Integer.valueOf(2), "Taxi");
        DESTINATION_SIZE = DATAMAP.size();
        COST = new double[3][DESTINATION_SIZE][DESTINATION_SIZE];
        COST[1][0][1] = 0.83D;
        COST[1][0][2] = 1.18D;
        COST[1][0][3] = 4.03D;
        COST[1][0][4] = 0.88D;
        COST[1][0][5] = 1.96D;
        COST[1][1][0] = 0.83D;
        COST[1][1][2] = 1.26D;
        COST[1][1][3] = 4.03D;
        COST[1][1][4] = 0.98D;
        COST[1][1][5] = 1.89D;
        COST[1][2][0] = 1.18D;
        COST[1][2][1] = 1.26D;
        COST[1][2][3] = 2.0D;
        COST[1][2][4] = 0.98D;
        COST[1][2][5] = 1.99D;
        COST[1][3][0] = 1.18D;
        COST[1][3][1] = 1.26D;
        COST[1][3][2] = 0.0D;
        COST[1][3][4] = 0.98D;
        COST[1][3][5] = 1.99D;
        COST[1][4][0] = 0.88D;
        COST[1][4][1] = 0.98D;
        COST[1][4][2] = 0.98D;
        COST[1][4][3] = 3.98D;
        COST[1][4][5] = 1.91D;
        COST[1][5][0] = 1.88D;
        COST[1][5][1] = 1.96D;
        COST[1][5][2] = 2.11D;
        COST[1][5][3] = 4.99D;
        COST[1][5][4] = 1.91D;
        COST[2][0][1] = 3.22D;
        COST[2][0][2] = 6.96D;
        COST[2][0][3] = 8.5D;
        COST[2][0][4] = 4.98D;
        COST[2][0][5] = 18.4D;
        COST[2][1][0] = 4.32D;
        COST[2][1][2] = 7.84D;
        COST[2][1][3] = 9.38D;
        COST[2][1][4] = 4.76D;
        COST[2][1][5] = 18.18D;
        COST[2][2][0] = 8.3D;
        COST[2][2][1] = 7.96D;
        COST[2][2][3] = 4.54D;
        COST[2][2][4] = 6.42D;
        COST[2][2][5] = 22.58D;
        COST[2][3][0] = 8.74D;
        COST[2][3][1] = 8.4D;
        COST[2][3][2] = 3.22D;
        COST[2][3][4] = 6.64D;
        COST[2][3][5] = 22.8D;
        COST[2][4][1] = 4.76D;
        COST[2][4][0] = 5.32D;
        COST[2][4][2] = 4.98D;
        COST[2][4][3] = 6.52D;
        COST[2][4][5] = 18.4D;
        COST[2][5][0] = 22.48D;
        COST[2][5][1] = 19.4D;
        COST[2][5][2] = 21.48D;
        COST[2][5][3] = 23.68D;
        COST[2][5][4] = 21.6D;
        TIME = new int[3][DESTINATION_SIZE][DESTINATION_SIZE];
        TIME[0][0][1] = 14;
        TIME[0][0][2] = 69;
        TIME[0][0][3] = 76;
        TIME[0][0][4] = 28;
        TIME[0][0][5] = 269;
        TIME[0][1][0] = 14;
        TIME[0][1][2] = 81;
        TIME[0][1][3] = 88;
        TIME[0][1][4] = 39;
        TIME[0][1][5] = 264;
        TIME[0][2][0] = 69;
        TIME[0][2][1] = 81;
        TIME[0][2][3] = 12;
        TIME[0][2][4] = 47;
        TIME[0][2][5] = 270;
        TIME[0][3][0] = 76;
        TIME[0][3][1] = 88;
        TIME[0][3][2] = 12;
        TIME[0][3][4] = 55;
        TIME[0][3][5] = 285;
        TIME[0][4][0] = 28;
        TIME[0][4][1] = 39;
        TIME[0][4][2] = 47;
        TIME[0][4][3] = 55;
        TIME[0][4][5] = 264;
        TIME[0][5][0] = 269;
        TIME[0][5][1] = 264;
        TIME[0][5][2] = 270;
        TIME[0][5][3] = 285;
        TIME[0][5][4] = 264;
        TIME[1][0][1] = 17;
        TIME[1][0][2] = 26;
        TIME[1][0][3] = 35;
        TIME[1][0][4] = 19;
        TIME[1][0][5] = 84;
        TIME[1][1][0] = 17;
        TIME[1][1][2] = 31;
        TIME[1][1][3] = 38;
        TIME[1][1][4] = 24;
        TIME[1][1][5] = 85;
        TIME[1][2][0] = 24;
        TIME[1][2][1] = 29;
        TIME[1][2][3] = 10;
        TIME[1][2][4] = 18;
        TIME[1][2][5] = 85;
        TIME[1][3][0] = 33;
        TIME[1][3][1] = 38;
        TIME[1][3][2] = 10;
        TIME[1][3][4] = 27;
        TIME[1][3][5] = 92;
        TIME[1][4][0] = 18;
        TIME[1][4][1] = 23;
        TIME[1][4][2] = 19;
        TIME[1][4][3] = 28;
        TIME[1][4][5] = 83;
        TIME[1][5][0] = 86;
        TIME[1][5][1] = 87;
        TIME[1][5][2] = 86;
        TIME[1][5][3] = 96;
        TIME[1][5][4] = 84;
        TIME[2][0][1] = 3;
        TIME[2][0][2] = 14;
        TIME[2][0][3] = 19;
        TIME[2][0][4] = 8;
        TIME[2][0][5] = 30;
        TIME[2][1][0] = 6;
        TIME[2][1][2] = 13;
        TIME[2][1][3] = 18;
        TIME[2][1][4] = 8;
        TIME[2][1][5] = 29;
        TIME[2][2][0] = 12;
        TIME[2][2][1] = 14;
        TIME[2][2][3] = 9;
        TIME[2][2][4] = 11;
        TIME[2][2][5] = 31;
        TIME[2][3][0] = 13;
        TIME[2][3][1] = 14;
        TIME[2][3][2] = 4;
        TIME[2][3][4] = 12;
        TIME[2][3][5] = 32;
        TIME[2][4][0] = 7;
        TIME[2][4][1] = 8;
        TIME[2][4][2] = 9;
        TIME[2][4][3] = 14;
        TIME[2][4][5] = 30;
        TIME[2][5][0] = 32;
        TIME[2][5][1] = 29;
        TIME[2][5][2] = 32;
        TIME[2][5][3] = 36;
        TIME[2][5][4] = 30;
    }
}
