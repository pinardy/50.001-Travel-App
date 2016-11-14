package com.example.pin.itinerarytest;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * Created by Pin on 06-Nov-16.
 *
 * Data (cost and time) is stored in a 3D array.
 * First dimension is mode of transport
 * Second dimension is location from
 * Third dimension is location to
 */
public class DestinationsData {

    /* Initialize a HashMap to contain the data in the table
      Data based on table in pg 4 of handout
      Key: Name of the location
      Value: an integer (for numbering)         */
    public static final HashMap<String, Integer> DATAMAP = new HashMap<>();
    public static final HashMap<Integer, String> DATAMAP2 = new HashMap<>();

    /* Even if many objects of this type is created,
       static block gets called only once,
       when the class itself is initialized.    */

    static {
        DATAMAP.put("Marina Bay Sands",0);
        DATAMAP.put("Singapore Flyer",1);
        DATAMAP.put("Vivo City",2);
        DATAMAP.put("Resorts World Sentosa",3);
        DATAMAP.put("Buddha Tooth Relic Temple",4);
        DATAMAP.put("Zoo",5);

        DATAMAP2.put(0,"Marina Bay Sands");
        DATAMAP2.put(1,"Singapore Flyer");
        DATAMAP2.put(2,"Vivo City");
        DATAMAP2.put(3,"Resorts World Sentosa");
        DATAMAP2.put(4,"Buddha Tooth Relic Temple");
        DATAMAP2.put(5,"Zoo");
    }

    public static final HashMap<Integer, String> modeOfTransport = new HashMap<>();
    static {
        modeOfTransport.put(0,"Walking");
        modeOfTransport.put(1,"Public Transport");
        modeOfTransport.put(2,"Taxi");
    }

    // num of key-value mappings in this map
    private static final int DESTINATION_SIZE = DATAMAP.size();

    // storing information in a 3d array
    public static final double[][][] COST = new double[3][DESTINATION_SIZE][DESTINATION_SIZE];

    static {
        // Travel by walking
        COST[0][0][1] = 0;
        COST[0][0][2] = 0;
        COST[0][0][3] = 0;
        COST[0][0][4] = 0;
        COST[0][0][5] = 0;

        COST[0][1][0] = 0;
        COST[0][1][2] = 0;
        COST[0][1][3] = 0;
        COST[0][1][4] = 0;
        COST[0][1][5] = 0;

        COST[0][2][0] = 0;
        COST[0][2][1] = 0;
        COST[0][2][3] = 0;
        COST[0][2][4] = 0;
        COST[0][2][5] = 0;

        COST[0][3][0] = 0;
        COST[0][3][1] = 0;
        COST[0][3][2] = 0;
        COST[0][3][4] = 0;
        COST[0][3][5] = 0;

        COST[0][4][1] = 0;
        COST[0][4][2] = 0;
        COST[0][4][3] = 0;
        COST[0][4][4] = 0;
        COST[0][4][5] = 0;

        COST[0][5][1] = 0;
        COST[0][5][2] = 0;
        COST[0][5][3] = 0;
        COST[0][5][4] = 0;
        COST[0][5][5] = 0;


        // Travel by public transport
        COST[1][0][1] = 0.83;
        COST[1][0][2] = 1.18;
        COST[1][0][3] = 4.03;
        COST[1][0][4] = 0.88;
        COST[1][0][5] = 1.96;

        COST[1][1][0] = 0.83;
        COST[1][1][2] = 1.26;
        COST[1][1][3] = 4.03;
        COST[1][1][4] = 0.98;
        COST[1][1][5] = 1.89;

        COST[1][2][0] = 1.18;
        COST[1][2][1] = 1.26;
        COST[1][2][3] = 2.00;
        COST[1][2][4] = 0.98;
        COST[1][2][5] = 1.99;

        COST[1][3][0] = 1.18;
        COST[1][3][1] = 1.26;
        COST[1][3][2] = 0.0;
        COST[1][3][4] = 0.98;
        COST[1][3][5] = 1.99;

        COST[1][4][1] = 0.98;
        COST[1][4][2] = 0.98;
        COST[1][4][3] = 0.98;
        COST[1][4][4] = 3.98;
        COST[1][4][5] = 1.91;

        COST[1][5][1] = 1.88;
        COST[1][5][2] = 1.96;
        COST[1][5][3] = 2.11;
        COST[1][5][4] = 4.99;
        COST[1][5][5] = 1.91;

        // Travel by taxi
        COST[2][0][1] = 3.22;
        COST[2][0][2] = 6.96;
        COST[2][0][3] = 8.50;
        COST[2][0][4] = 4.98;
        COST[2][0][5] = 18.40;

        COST[2][1][0] = 4.32;
        COST[2][1][2] = 7.84;
        COST[2][1][3] = 9.38;
        COST[2][1][4] = 4.76;
        COST[2][1][5] = 18.18;

        COST[2][2][0] = 8.30;
        COST[2][2][1] = 7.96;
        COST[2][2][3] = 4.54;
        COST[2][2][4] = 6.42;
        COST[2][2][5] = 22.58;

        COST[2][3][0] = 8.74;
        COST[2][3][1] = 8.40;
        COST[2][3][2] = 3.22;
        COST[2][3][4] = 6.64;
        COST[2][3][5] = 22.80;

        COST[2][4][1] = 4.76;
        COST[2][4][0] = 5.32;
        COST[2][4][2] = 4.98;
        COST[2][4][3] = 6.52;
        COST[2][4][5] = 18.40;

        COST[2][5][0] = 22.48;
        COST[2][5][1] = 19.40;
        COST[2][5][2] = 21.48;
        COST[2][5][3] = 23.68;
        COST[2][5][4] = 21.60;

        // There is no cost incurred from travelling by walking
    }

    public static final int[][][] TIME = new int[3][DESTINATION_SIZE][DESTINATION_SIZE];
    static{
        //Walking time
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

        // Time by public transport
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

        // Time by taxi
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
    // Given the index, obtain name of location
    // NOTE: Inefficient as time complexity of this method is O(n)
    public static Object getKeyFromValue(HashMap hm, Integer index) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(index)) {
                return o;
            }
        }
        return null;
    }

    // Main method for testing
    public static void main(String[] args) {
        // COST[mode][from][to] or TIME[mode][from][to]

        int mode = 2; // 0: walking, 1: public transport, 2: taxi
        int from = 0;
        int to = 1;

        System.out.println("For method: " + modeOfTransport.get(mode) + "\n" + "Cost from "
                + getKeyFromValue(DATAMAP,from) + " to " + getKeyFromValue(DATAMAP,to)
                + " is " + "$" + COST[mode][from][to] + "\n");

        System.out.println("For method: " + modeOfTransport.get(mode) + "\n" + "Time from "
                + getKeyFromValue(DATAMAP,from) + " to " + getKeyFromValue(DATAMAP,to)
                + " is " + TIME[mode][from][to] + " mins"+ "\n");
    }
}