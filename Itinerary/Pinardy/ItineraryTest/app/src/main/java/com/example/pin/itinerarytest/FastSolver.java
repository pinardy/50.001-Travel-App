package com.example.pin.itinerarytest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nguyen Trung Huan on 11/14/2016.
 */
public class FastSolver implements Parcelable {
    private static int[] destinations;
    private static int[] path;
    private static int[] transport;
    private static double[][] distanceTimeRatio;
    private static DestinationsData_fast pathdata = new DestinationsData_fast();

    private static final double[][][] COST = pathdata.COST;
    private static final int[][][] TIME = pathdata.TIME;

    private static int totalTime = 0;
    private static double totalCost = 0;
    private static double budget;

    public FastSolver() {

    }


    public static int getIndexOfMin(int[] a){
        int i = a[0];
        int c = 0;
        for (int j = 0; j < a.length; j++){
            c = (i > a[j] ? j : c);
            i = (i > a[j] ? a[j] : i);
        }
        return c;
    }

    public static boolean checkListInt(int[] a, int c){
        for (int i = 0; i < a.length; i++){
            if (a[i] == c) return true;
        }
        return false;
    }

    private static int[] findPath(){
        int[][][] cloneTime = TIME.clone();

        path = new int[destinations.length + 2];
        path[0] = 0;
        int currentLocation = 0;
        int i = 0;
        int bestNextDestination = 0;
        while (i < destinations.length){
            bestNextDestination = getIndexOfMin(cloneTime[0][currentLocation]);
            if (checkListInt(path, bestNextDestination) || !checkListInt(destinations, bestNextDestination)) cloneTime[0][currentLocation][bestNextDestination] = 9999;
            else {
                i++;
                currentLocation = bestNextDestination;
                path[i] = currentLocation;
            }
        }
        path[destinations.length + 1] = 0;
        return path;
    }

    private static double[][] computeDistanceOverPrice(){
        distanceTimeRatio = new double[3][destinations.length + 1];
        findPath();
        for (int i = 0; i <= path.length - 2; i++){
            distanceTimeRatio[1][i] = TIME[1][path[i]][path[i+1]]*COST[1][path[i]][path[i+1]];
            distanceTimeRatio[2][i] = TIME[2][path[i]][path[i+1]]*COST[2][path[i]][path[i+1]];
        }

        return distanceTimeRatio;
    }

    public static boolean checkList(int[] a, int c){
        for (int i = 0; i < a.length; i++){
            if (a[i] == c) return false;
        }
        return true;
    }

    public static double[] addArray(double[] a, double[] b){
        double[] c = new double[a.length + b.length];
        for (int i = 0; i < a.length; i++){
            c[i] = a[i];
        }

        for (int i = 0; i < b.length; i++){
            c[i + a.length] = b[i];
        }
        return c;
    }
    public static void computeModeOfTransport(){
        transport = new int[destinations.length + 1];
        computeDistanceOverPrice();

        int l = distanceTimeRatio[1].length;
        double[] distanceTimeRatiosort = addArray(distanceTimeRatio[1], distanceTimeRatio[2]);

//        for (double i : distanceTimeRatiosort){
//            System.out.println(i);
//        }

        ArrayIndexComparator comparator = new ArrayIndexComparator(distanceTimeRatiosort);
        Integer[] indexes = comparator.createIndexArray();
        Arrays.sort(indexes, comparator);
//        for (double i : distanceTimeRatiosort){
//            System.out.println(i);
//        }
//
//        for (int i : indexes){
//            System.out.println(i);
//        }
        int[] potentialTransport = new int[path.length];
        int i = 0;
        int j = 0;
        double rembudget = budget;
        double cost = 0;
        while (rembudget > 0 && j < path.length && i < indexes.length){
            /*** If the vector has not already been completed by another mode of transport, add in the vector to potential transport; otherwise move on ***/
            if (checkList(potentialTransport, indexes[i] + l) && checkList(potentialTransport, indexes[i] - l)) {
                /*** Finding cost ***/
                if (indexes[i] >= l) {
                    cost = COST[2][path[indexes[i] - l]][path[indexes[i] - l + 1]];
//                    System.out.println(cost);
                }
                else {
                    cost = COST[1][path[indexes[i]]][path[indexes[i] + 1]];
                }
                /*** If budget > cost, add to potential transport; otherwise move on ***/
                if (rembudget > cost){
                    potentialTransport[j] = indexes[i];
                    rembudget -= cost;
                    i++;
                    j++;
                } else i++;
            }
            else i++;
        }

        for (int k : potentialTransport){
            if (k < l) transport[k] = 1;
            else transport[k - l] = 2;
        }
        int n = 0;
        for (int m = 0; m < indexes.length; m++) {
            if (indexes[m] >= l) n = indexes[m] - l;
            if (indexes[m] >= l && transport[n] == 1 && rembudget > COST[2][path[n]][path[n + 1]] - COST[1][path[n]][path[n + 1]]){
                transport[n] = 2;
                rembudget = rembudget - COST[2][path[n]][path[n + 1]] + COST[1][path[n]][path[n + 1]];
            }
        }
        budget = rembudget;
    }

    public static void getTimeAndCost(){
        totalCost = 0;
        totalTime = 0;
        for (int i = 0; i < path.length - 1; i++){
            totalTime += TIME[transport[i]][path[i]][path[i+1]];
            totalCost += COST[transport[i]][path[i]][path[i+1]];
        }
    }

    public static ArrayList<Integer> getTime(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < transport.length; i++){
            list.add(pathdata.TIME[transport[i]][path[i]][path[i+1]]);
        }
        return list;
    }

    public static ArrayList<Double> getCost(){
        ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 0; i < transport.length; i++){
            list.add(pathdata.COST[transport[i]][path[i]][path[i+1]]);
        }
        return list;
    }

    public static void setBudget(double b){
        budget = b;
    }

    public static int getTotalTime(){
        return totalTime;
    }

    public static double getTotalCost(){
        return totalCost;
    }

    public static void setDestination(int[] a){
        destinations = new int[a.length];
        for (int i = 0; i < a.length; i++){
            destinations[i] = a[i];
        }
    }

    public static ArrayList<String> getTripPath(){
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < path.length; i++){
            list.add(pathdata.DATAMAP.get(path[i]));
        }
        return list;
    }

    public static ArrayList<String> getTripTransport(){
        getTimeAndCost();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < transport.length; i++){
            list.add(pathdata.modeOfTransport.get(transport[i]));
        }
        return list;
    }

    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i < transport.length; i++){
            s+= "Leg #" + i + ": Going from " + getTripPath().get(i) + " to " + getTripPath().get(i+1) + " via " + getTripTransport().get(i) + "\n";
        }
        return s;
    }


    public static void main(String[] args){

        int[] input = {1, 5, 4, 3};
        FastSolver smart = new FastSolver();
        smart.setBudget(25.3);
        smart.setDestination(input);
        smart.computeModeOfTransport();
        System.out.println("This algorithm prioritises the efficiency of each dollar you spent!");
        System.out.println();
        System.out.println(smart);
        System.out.println("Total cost is: $" + smart.getTotalCost());
        System.out.println("Total time is: " + smart.getTotalTime() + " minutes");
        System.out.println("Remaining budget is: " + smart.budget);
    }

    protected FastSolver(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FastSolver> CREATOR = new Parcelable.Creator<FastSolver>() {
        @Override
        public FastSolver createFromParcel(Parcel in) {
            return new FastSolver(in);
        }

        @Override
        public FastSolver[] newArray(int size) {
            return new FastSolver[size];
        }
    };
}