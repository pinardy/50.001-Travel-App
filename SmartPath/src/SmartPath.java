import java.util.Arrays;

/**
 * Created by Nguyen Trung Huan on 11/14/2016.
 */
public class SmartPath {
    private static int[] destinations;
    private static int[] path = new int[destinations.length + 2];
    private static int[] transport;
    private static double[][] distanceTimeRatio;
    private static final double[][][] COST = new double[3][destinations.length][destinations.length];
    static {
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

    private static final int[][][] TIME = new int[3][destinations.length][destinations.length];
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

    private static DestinationsData pathdata;

    public int getIndexOfMin(int[] a){
        int i = a[0];
        int c = 0;
        for (int j = 0; j <= a.length; j++){
            i = (i > a[j] ? a[j] : i);
            c = (i > a[j] ? j : c);
        }
        return c;
    }

    private int[] findPath(){
        int[][][] cloneTime = TIME.clone();
        path[0] = 0;
        int currentLocation = 0;
        int i = 0;
        while (i < destinations.length){
            int bestNextDestination = getIndexOfMin(cloneTime[0][currentLocation]);
            if (Arrays.asList(path).contains(bestNextDestination)) cloneTime[0][currentLocation][bestNextDestination] = 9999;
            else {
                i++;
                path[i] = bestNextDestination;
                currentLocation = bestNextDestination;
            }
        }
        path[destinations.length + 1] = 0;
        return path;
    }

    private double[][] computeDistanceOverPrice(){
        distanceTimeRatio = new double[3][destinations.length + 1];
        this.findPath();
        for (int i = 0; i <= path.length - 2; i++){
            distanceTimeRatio[1][i] = TIME[1][path[i]][path[i+1]]/COST[1][path[i]][path[i+1]];
            distanceTimeRatio[2][i] = TIME[2][path[i]][path[i+1]]/COST[2][path[i]][path[i+1]];
        }

//        for (int i = 1; i <= 2; i++){
//            for (int j = 0; j <= destinations.length; j++){
//                for (int k = 0; k <= destinations.length; k++){
//                    distanceTimeRatio[i][j][k] = TIME[i][j][k] / COST[i][j][k];
//                }
//            }
//        }
        return distanceTimeRatio;
    }

    private int[] findModeOfTransport(double budget){
        this.computeDistanceOverPrice();
        int l = distanceTimeRatio[1].length;
        double[] distanceTimeRatiosort = new double[(path.length - 1) * 2];
        System.arraycopy(distanceTimeRatio[1], 0, distanceTimeRatiosort, 0, l);
        System.arraycopy(distanceTimeRatio[2], l, distanceTimeRatiosort, 0, l);

        ArrayIndexComparator comparator = new ArrayIndexComparator(distanceTimeRatiosort);
        Integer[] indexes = comparator.createIndexArray();
        Arrays.sort(indexes, comparator);

        int[] potentialTransport = new int[path.length];
        int i = 0;
        int j = 0;
        while (budget > 0){
            if potentialTransport[i] =
        }
    }
}
