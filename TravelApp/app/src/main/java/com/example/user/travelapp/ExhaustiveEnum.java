package com.example.user.travelapp;

import android.os.Parcel;
import android.os.Parcelable;

import static com.example.user.travelapp.DestinationsData.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by Pin on 14-Nov-16.
 */

public class ExhaustiveEnum implements Serializable{

    public static void main(String[] args) {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);
        s.add(5);
        TripObject o = fullFunction(s,20);
        System.out.println(o.getTripList());
        System.out.println(o.getTimeAndCostList());
    }

    public static TripObject fullFunction(ArrayList<Integer> arrayList, double money){
        double budget = money;
        ArrayList<ArrayList<Integer>> paths = generatePermutations(arrayList);
        ArrayList<ArrayList<Integer>> newpaths = processPath(paths);
        TripObject tripObject = chooseAggregateCostsMethod(newpaths, budget);
        return tripObject;
    }

//    public static void my_permutationOf(List<String> uniqueList, int permutationSize, List<String> permutation, boolean only) {
//        if (permutation == null) {
//            assert 0 < permutationSize && permutationSize <= uniqueList.size();
//            permutation = new ArrayList<>(permutationSize);
//            if (!only) {
//                System.out.println(Arrays.toString(permutation.toArray()));
//            }
//        }
//        for (String i : uniqueList) {
//            if (permutation.contains(i)) {
//                continue;
//            }
//            permutation.add(i);
//            if (!only) {
//                System.out.println("only:false");
//                System.out.println(Arrays.toString(permutation.toArray()));
//            } else if (permutation.size() == permutationSize) {
//                System.out.println("only:true");
//                System.out.println(Arrays.toString(permutation.toArray()));
//            }
//            if (permutation.size() < permutationSize) {
//                System.out.println("WIP!");
//                my_permutationOf(uniqueList, permutationSize, permutation, only);
//            }
//            permutation.remove(permutation.size() - 1);
//        }
//    }

    public static ArrayList<ArrayList<Integer>> generatePermutations(ArrayList<Integer> destinations)
    {
        //System.out.println("Starting");
        if(destinations.size() == 1)
        {
            //System.out.println("destinations "+destinations);
            ArrayList<ArrayList<Integer>> permutelists = new ArrayList<ArrayList<Integer>>();
            permutelists.add(destinations);
            //System.out.println("returning lists");
            //System.out.println(permutelists);
            return permutelists;//Weird... does not end the program
        }
        //Note that arrlist.remove(int index) returns the element removed!
        //It mutates the arrlist
        //System.out.println("after if");
        Integer lastele = destinations.remove(0); //remove the first element from destinations
        //System.out.println("s.remove: "+lastele);
        ArrayList<ArrayList<Integer>> oldpermutelists = generatePermutations(destinations);
        //System.out.println("old"+oldpermutelists);
        //System.out.println("post recursive call");
        ArrayList<ArrayList<Integer>> newpermutelists = new ArrayList<ArrayList<Integer>>();
        //System.out.println("entering for loop");
        //System.out.println("old permute list: " + oldpermutelists);
        for(ArrayList<Integer> list : oldpermutelists)
        {
            //Not modifying the array list of arraylists itself, but the ararylist elements
            for(int i=0; i<=list.size(); i++)
            {
                //Note newlist only exists within the scope of the for loop
                //With each iteration a new newlist object is created
                //newlist thus points to a new object each time, which is what we want
                //We don't want to add list directly as any subsequent modifications
                //would modify the same objectSystem.out.println("Total time and cost: " + getTotalTime(modes_final,it_optNN)+ " " + getTotalCost(modes_final,it_optNN));

                ArrayList<Integer> newlist = new ArrayList<Integer>(list);
                newlist.add(i,lastele);
                //Test the start and the end appending
                //System.out.println("newlist " +newlist);
                newpermutelists.add(newlist);
                //System.out.println("newpermutelists: " + newpermutelists);
            }
        }
        return newpermutelists;
    }

    public static ArrayList<ArrayList<Integer>> processPath(ArrayList<ArrayList<Integer>>pathLists) {
        for (int i = 0; i < pathLists.size(); i++) {
            ArrayList array = pathLists.get(i);
            array.add(0, 0);
            array.add(0);
        }
        return pathLists;
    }

    public static TripObject chooseAggregateCostsMethod (ArrayList<ArrayList<Integer>>pathLists, double budget){
        int i = pathLists.get(0).size();
        switch (i) {
            case 3:
                return testAggregateCostsV2(pathLists,budget);
            case 4:
                return  testAggregateCostsV3(pathLists,budget);
            case 5:
                return testAggregateCostsV4(pathLists,budget);
            case 6:
                return testAggregateCostsV5(pathLists,budget);
            case 7:
                //System.out.println("chosen");
                return testAggregateCostsV6(pathLists,budget);
            default:
                break;
        }
        return testAggregateCostsV6(pathLists,budget);
    }

    public static TripObject testAggregateCostsV2 (ArrayList<ArrayList<Integer>>pathLists,double budget) {
        ArrayList<TripObject> costsList =new ArrayList<>();

        for (int i = 0; i < pathLists.size(); i++) {
            ArrayList array = pathLists.get(i);
            double cost = 0;
            int time = 0;
            for (int a = 0; a < 3; a++) {
                int source = (int) array.get(0);
                int destination = (int) array.get(1);
                int path1time = TIME[a][source][destination];
                double path1cost = COST[a][source][destination];

                for (int b = 0; b<3; b++) {
                    int source1 = (int) array.get(1);
                    int destination1 = (int) array.get(2);
                    int path2time = TIME[b][source1][destination1];
                    double path2cost = COST[b][source1][destination1];

                    //Where all the magic happens!
                    String mode = modeOfTransport.get(a);
                    String mode1 = modeOfTransport.get(b);
                    String origin = DATAMAP2.get(source);
                    String dest1 = DATAMAP2.get(destination);
                    String dest2 = DATAMAP2.get(destination1);
                    String trip = (origin+"to "+dest1+": "+mode +"\n"+ dest1+"to " +dest2+ ": "+mode1+"\n");
                    double totalCost = path1cost+path2cost;
                    //System.out.println("Total trip: " + trip);
                    int totalTime = path1time + path2time;
                    //costsList.add(result);
                    //System.out.println("Total cost: "+totalCost);
                    ArrayList<String> tripList = new ArrayList<>();
                    tripList.add(origin);
                    tripList.add(dest1);
                    tripList.add(dest2);

                    ArrayList<String> timeAndCost = new ArrayList<>();
                    timeAndCost.add("Mode: "+mode+"\nTime: "+path1time+" mins"+"\nCost: $"+path1cost);
                    timeAndCost.add("Mode: "+mode1+"\nTime: "+path2time+" mins"+"\nCost: $"+path2cost);

                    if(totalCost<=budget){
                        TripObject tripObject = new TripObject(trip,totalTime,totalCost);
                        tripObject.setTripList(tripList);                         tripObject.setTimeAndCostList(timeAndCost);
                        tripObject.setTimeAndCostList(timeAndCost);
                        costsList.add(tripObject);
                    }
                    else{
                        //System.out.println("denied");
                        continue;
                    }
                }
            }
        }
        Collections.sort(costsList);
        return costsList.get(0);
    }


    public static TripObject testAggregateCostsV3 (ArrayList<ArrayList<Integer>>pathLists, double budget) {
        ArrayList<TripObject> costsList =new ArrayList<>();

        for (int i = 0; i < pathLists.size(); i++) {
            ArrayList array = pathLists.get(i);
            //System.out.println("new path!");
            double cost = 0;
            int time = 0;
            for (int a = 0; a < 3; a++) {
                int source = (int) array.get(0);
                int destination = (int) array.get(1);
                int path1time = TIME[a][source][destination];
                double path1cost = COST[a][source][destination];

                for (int b = 0; b<3; b++) {
                    int source1 = (int) array.get(1);
                    int destination1 = (int) array.get(2);
                    int path2time = TIME[b][source1][destination1];
                    double path2cost = COST[b][source1][destination1];

                    for (int c = 0; c < 3; c++) {
                        int source2 = (int) array.get(2);
                        int destination2 = (int) array.get(3);
                        int path3time = TIME[c][source2][destination2];
                        double path3cost = COST[c][source2][destination2];

                        //Where all the magic happens!
                        //System.out.println("a: " + a + " b: " + b + " c: " + c  );
                        String mode = modeOfTransport.get(a);
                        String mode1 = modeOfTransport.get(b);
                        String mode2 = modeOfTransport.get(c);
                        String origin = DATAMAP2.get(source);
                        String dest1 = DATAMAP2.get(destination);
                        String dest2 = DATAMAP2.get(destination1);
                        String dest3 = DATAMAP2.get(destination2);
                        String trip = (origin+"to "+dest1+": "+mode +"\n"+ dest1+"to " +dest2+ ": "+mode1+"\n"+dest2+ "to "+dest3+": "+mode2 +"\n");
                        double totalCost = path1cost+path2cost+path3cost;
                        //System.out.println("Total trip: " + trip);

                        ArrayList<String> tripList = new ArrayList<>();
                        tripList.add(origin);
                        tripList.add(dest1);
                        tripList.add(dest2);
                        tripList.add(dest3);

                        ArrayList<String> timeAndCost = new ArrayList<>();
                        timeAndCost.add("Mode: "+mode+"\nTime: "+path1time+" mins"+"\nCost: $"+path1cost);
                        timeAndCost.add("Mode: "+mode1+"\nTime: "+path2time+" mins"+"\nCost: $"+path2cost);
                                                            timeAndCost.add("Mode: "+mode2+"\nTime: "+path3time+" mins"+"\nCost: $"+path3cost); 


                        int totalTime = path1time + path2time + path3time ;
                        //System.out.println(test);
                        //costsList.add(result);
                        //System.out.println("Total cost: "+totalCost);
                        if(totalCost<=budget){
                            TripObject tripObject = new TripObject(trip,totalTime,totalCost);
                            tripObject.setTripList(tripList);                         tripObject.setTimeAndCostList(timeAndCost);
                            tripObject.setTimeAndCostList(timeAndCost);
                            costsList.add(tripObject);
                        }
                        else{
                            continue;
                        }
                    }
                }
            }
        }
        Collections.sort(costsList);
        return costsList.get(0);
    }



    public static TripObject testAggregateCostsV4 (ArrayList<ArrayList<Integer>>pathLists, double budget) {
        ArrayList<TripObject> costsList =new ArrayList<>();

        for (int i = 0; i < pathLists.size(); i++) {
            ArrayList array = pathLists.get(i);
            //System.out.println("new path!");
            double cost = 0;
            int time = 0;
            for (int a = 0; a < 3; a++) {
                int source = (int) array.get(0);
                int destination = (int) array.get(1);
                int path1time = TIME[a][source][destination];
                double path1cost = COST[a][source][destination];

                for (int b = 0; b<3; b++) {
                    int source1 = (int) array.get(1);
                    int destination1 = (int) array.get(2);
                    int path2time = TIME[b][source1][destination1];
                    double path2cost = COST[b][source1][destination1];

                    for (int c = 0; c < 3; c++) {
                        int source2 = (int) array.get(2);
                        int destination2 = (int) array.get(3);
                        int path3time = TIME[c][source2][destination2];
                        double path3cost = COST[c][source2][destination2];

                        for (int d = 0; d < 3; d++) {
                            int source3 = (int) array.get(3);
                            int destination3 = (int) array.get(4);
                            int path4time = TIME[d][source3][destination3];
                            double path4cost = COST[d][source3][destination3];




                            //Where all the magic happens!
                            //System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d );
                            String mode = modeOfTransport.get(a);
                            String mode1 = modeOfTransport.get(b);
                            String mode2 = modeOfTransport.get(c);
                            String mode3 = modeOfTransport.get(d);


                            String origin = DATAMAP2.get(source);
                            String dest1 = DATAMAP2.get(destination);
                            String dest2 = DATAMAP2.get(destination1);
                            String dest3 = DATAMAP2.get(destination2);
                            String dest4 = DATAMAP2.get(destination3);

                            ArrayList<String> tripList = new ArrayList<>();
                            tripList.add(origin);
                            tripList.add(dest1);
                            tripList.add(dest2);
                            tripList.add(dest3);
                            tripList.add(dest4);

                            String trip = (origin+"to "+dest1+": "+mode +"\n"+ dest1+"to " +dest2+ ": "+mode1+"\n"+dest2+ "to "+dest3+": "+mode2 +"\n"+ dest3+ "to " +dest4+": "+mode3+"\n" );
                            double totalCost = path1cost+path2cost+path3cost+path4cost;
                            //System.out.println("Total trip: " + trip);


                            int totalTime = path1time + path2time + path3time + path4time  ;
                            String test = path1time + " " + path2time + " " + path3time + " " + path4time ;
                            //System.out.println(test);
                            String result = "Time cost" + totalTime;
                            //costsList.add(result);
                            //System.out.println("Total cost: "+totalCost);
                            ArrayList<String> timeAndCost = new ArrayList<>();
                            timeAndCost.add("Mode: "+mode+"\nTime: "+path1time+" mins"+"\nCost: $"+path1cost);
                            timeAndCost.add("Mode: "+mode1+"\nTime: "+path2time+" mins"+"\nCost: $"+path2cost);
                                                                timeAndCost.add("Mode: "+mode2+"\nTime: "+path3time+" mins"+"\nCost: $"+path3cost); 
                            timeAndCost.add("Mode: "+mode3+"\nTime: "+path4time+" mins"+"\nCost: $"+path4cost); 
                            if(totalCost<=budget){

                                TripObject tripObject = new TripObject(trip,totalTime,totalCost);
                                tripObject.setTripList(tripList);                         tripObject.setTimeAndCostList(timeAndCost);
                                costsList.add(tripObject);
                            }
                            else{
                                //System.out.println("denied");
                                continue;
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(costsList);
        return costsList.get(0);
    }




    public static TripObject testAggregateCostsV5 (ArrayList<ArrayList<Integer>>pathLists,double budget) {
        ArrayList<TripObject> costsList =new ArrayList<>();

        for (int i = 0; i < pathLists.size(); i++) {
            ArrayList array = pathLists.get(i);
            //System.out.println("new path!");
            double cost = 0;
            int time = 0;
            for (int a = 0; a < 3; a++) {
                int source = (int) array.get(0);
                int destination = (int) array.get(1);
                int path1time = TIME[a][source][destination];
                double path1cost = COST[a][source][destination];

                for (int b = 0; b<3; b++) {
                    int source1 = (int) array.get(1);
                    int destination1 = (int) array.get(2);
                    int path2time = TIME[b][source1][destination1];
                    double path2cost = COST[b][source1][destination1];

                    for (int c = 0; c < 3; c++) {
                        int source2 = (int) array.get(2);
                        int destination2 = (int) array.get(3);
                        int path3time = TIME[c][source2][destination2];
                        double path3cost = COST[c][source2][destination2];

                        for (int d = 0; d < 3; d++) {
                            int source3 = (int) array.get(3);
                            int destination3 = (int) array.get(4);
                            int path4time = TIME[d][source3][destination3];
                            double path4cost = COST[d][source3][destination3];

                            for (int e = 0; e < 3; e++) {
                                int source4 = (int) array.get(4);
                                int destination4 = (int) array.get(5);
                                int path5time = TIME[e][source4][destination4];
                                double path5cost = COST[e][source4][destination4];


                                //Where all the magic happens!
                                //System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d + " e: " + e);
                                String mode = modeOfTransport.get(a);
                                String mode1 = modeOfTransport.get(b);
                                String mode2 = modeOfTransport.get(c);
                                String mode3 = modeOfTransport.get(d);
                                String mode4 = modeOfTransport.get(e);


                                String origin = DATAMAP2.get(source);
                                String dest1 = DATAMAP2.get(destination);
                                String dest2 = DATAMAP2.get(destination1);
                                String dest3 = DATAMAP2.get(destination2);
                                String dest4 = DATAMAP2.get(destination3);
                                String dest5 = DATAMAP2.get(destination4);

                                ArrayList<String> tripList = new ArrayList<>();
                                tripList.add(origin);
                                tripList.add(dest1);
                                tripList.add(dest2);
                                tripList.add(dest3);
                                tripList.add(dest4);
                                tripList.add(dest5);

                                String trip = (origin+"to "+dest1+": "+mode +"\n"+ dest1+"to " +dest2+ ": "+mode1+"\n"+dest2+ "to "+dest3+": "+mode2 +"\n"+ dest3+ "to " +dest4+": "+mode3+"\n" +dest4 +"to "+dest5+": " + mode4+"\n");
                                double totalCost = path1cost+path2cost+path3cost+path4cost+path5cost;
                                //System.out.println("Total trip: " + trip);


                                int totalTime = path1time + path2time + path3time + path4time + path5time ;
                                String test = path1time + " " + path2time + " " + path3time + " " + path4time + " " + path5time + " ";
                                //System.out.println(test);
                                String result = "Time cost" + totalTime;
                                //costsList.add(result);
                                //System.out.println("Total cost: "+totalCost);

                                ArrayList<String> timeAndCost = new ArrayList<>();
                                timeAndCost.add("Mode: "+mode+"\nTime: "+path1time+" mins"+"\nCost: $"+path1cost);
                                timeAndCost.add("Mode: "+mode1+"\nTime: "+path2time+" mins"+"\nCost: $"+path2cost);
                                timeAndCost.add("Mode: "+mode2+"\nTime: "+path3time+" mins"+"\nCost: $"+path3cost); 
                                timeAndCost.add("Mode: "+mode3+"\nTime: "+path4time+" mins"+"\nCost: $"+path4cost);
                                timeAndCost.add("Mode: "+mode4+"\nTime: "+path5time+" mins"+"\nCost: $"+path5cost);

                                if(totalCost<=budget){
                                    TripObject tripObject = new TripObject(trip,totalTime,totalCost);
                                    tripObject.setTripList(tripList);
                                    tripObject.setTimeAndCostList(timeAndCost);
                                    costsList.add(tripObject);

                                }
                                else{
                                    //System.out.println("denied");
                                    continue;
                                }
                            }

                        }

                    }
                }
            }
        }
        Collections.sort(costsList);
        return costsList.get(0);
    }




    public static TripObject testAggregateCostsV6 (ArrayList<ArrayList<Integer>>pathLists, double budget) {
        ArrayList<TripObject> costsList =new ArrayList<>();

        for (int i = 0; i < pathLists.size(); i++) {
            ArrayList array = pathLists.get(i);
            //System.out.println("new path!");
            double cost = 0;
            int time = 0;
            for (int a = 0; a < 3; a++) {
                int source = (int) array.get(0);
                int destination = (int) array.get(1);
                int path1time = TIME[a][source][destination];
                double path1cost = COST[a][source][destination];

                for (int b = 0; b<3; b++) {
                    int source1 = (int) array.get(1);
                    int destination1 = (int) array.get(2);
                    int path2time = TIME[b][source1][destination1];
                    double path2cost = COST[b][source1][destination1];

                    for (int c = 0; c < 3; c++) {
                        int source2 = (int) array.get(2);
                        int destination2 = (int) array.get(3);
                        int path3time = TIME[c][source2][destination2];
                        double path3cost = COST[c][source2][destination2];

                        for (int d = 0; d < 3; d++) {
                            int source3 = (int) array.get(3);
                            int destination3 = (int) array.get(4);
                            int path4time = TIME[d][source3][destination3];
                            double path4cost = COST[d][source3][destination3];

                            for (int e = 0; e < 3; e++) {
                                int source4 = (int) array.get(4);
                                int destination4 = (int) array.get(5);
                                int path5time = TIME[e][source4][destination4];
                                double path5cost = COST[e][source4][destination4];

                                for (int f = 0; f < 3; f++) {
                                    //Where all the magic happens!
                                    //System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d + " e: " + e);
                                    int source5 = (int) array.get(5);
                                    int destination5 = (int) array.get(6);
                                    int path6time = TIME[f][source5][destination5];
                                    double path6cost = COST[f][source5][destination5];

                                    String mode = modeOfTransport.get(a);
                                    String mode1 = modeOfTransport.get(b);
                                    String mode2 = modeOfTransport.get(c);
                                    String mode3 = modeOfTransport.get(d);
                                    String mode4 = modeOfTransport.get(e);
                                    String mode5 = modeOfTransport.get(f);

                                    String origin = DATAMAP2.get(source);
                                    String dest1 = DATAMAP2.get(destination);
                                    String dest2 = DATAMAP2.get(destination1);
                                    String dest3 = DATAMAP2.get(destination2);
                                    String dest4 = DATAMAP2.get(destination3);
                                    String dest5 = DATAMAP2.get(destination4);
                                    String dest6 = DATAMAP2.get(destination5);

                                    ArrayList<String> tripList = new ArrayList<>();
                                    tripList.add(origin);
                                    tripList.add(dest1);
                                    tripList.add(dest2);
                                    tripList.add(dest3);
                                    tripList.add(dest4);
                                    tripList.add(dest5);
                                    tripList.add(dest6);

                                    ArrayList<String> timeAndCost = new ArrayList<>();
                                    timeAndCost.add("Mode: "+mode+"\nTime: "+path1time+" mins"+"\nCost: $"+path1cost);
                                    timeAndCost.add("Mode: "+mode1+"\nTime: "+path2time+" mins"+"\nCost: $"+path2cost);
                                    timeAndCost.add("Mode: "+mode2+"\nTime: "+path3time+" mins"+"\nCost: $"+path3cost);
                                    timeAndCost.add("Mode: "+mode3+"\nTime: "+path4time+" mins"+"\nCost: $"+path4cost);
                                    timeAndCost.add("Mode: "+mode4+"\nTime: "+path5time+" mins"+"\nCost: $"+path5cost);
                                    timeAndCost.add("Mode: "+mode5+"\nTime: "+path6time+" mins"+"\nCost: $"+path6cost);


                                    String trip = (origin+"to "+dest1+": "+mode +"\n"+ dest1+"to " +dest2+ ": "+mode1+"\n"+dest2+ "to "+dest3+": "+mode2 +"\n"+ dest3+ "to " +dest4+": "+mode3+"\n" +dest4 +"to "+dest5+": " + mode4+"\n"+       dest5+"to " +dest6 + ": "+mode5 );
                                    double totalCost = path1cost+path2cost+path3cost+path4cost+path5cost+path6cost;
                                    //System.out.println("Total trip: " + trip);


                                    int totalTime = path1time + path2time + path3time + path4time + path5time + path6time;
                                    String test = path1time + " " + path2time + " " + path3time + " " + path4time + " " + path5time + " "+path6time;
                                    //System.out.println(test);
                                    String result = "Time cost" + totalTime;
                                    //costsList.add(result);
                                    //System.out.println("Total cost: "+totalCost);

                                    if(totalCost<=budget){
                                        TripObject tripObject = new TripObject(trip,totalTime,totalCost);
                                        tripObject.setTripList(tripList);
                                        tripObject.setTimeAndCostList(timeAndCost);
                                        costsList.add(tripObject);
                                    }
                                    else{
                                        //System.out.println("denied");
                                        continue;
                                    }
                                }


                            }

                        }

                    }
                }
            }
        }
        Collections.sort(costsList);
        return costsList.get(0);
    }
}


//class TripObject implements Comparable<TripObject> {
//    private String tripSignature;
//    //You will want private data fields which are only modifiable by get/set
//    private  int totalTime = 0;// default value
//    private  double totalCost =0;
//    private ArrayList<String> tripList;
//    private ArrayList<String> timeAndCostList;
//
//    //Note that if more than one constructor exists, you must define the no arg constuctor manually,
//    TripObject(String trip, int time, double cost){
//        this.tripSignature = trip;
//        this.totalTime = time;
//        this.totalCost = cost;
//    }
//    public String getTripSignature() {
//        return tripSignature;// Accessor
//    }
//    public int getTotalTime() {
//        return totalTime;// Accessor
//    }
//    public double getTotalCost() {
//        return totalCost;// Accessor
//    }
//    public ArrayList<String> getTripList(){
//        return tripList;
//    }
//    public void setTripList(ArrayList<String> tripList){
//        this.tripList = tripList;
//    }
//    public void setTimeAndCostList(ArrayList<String> a){
//        this.timeAndCostList = a;
//    }
//    public ArrayList<String> getTimeAndCostList(){
//        return this.timeAndCostList;
//    }
//
//    public String toString() {
//        //When you print a class object you use toString
//        return "total time"+ totalTime;
//    }
//    @Override
//    public int compareTo(TripObject that) {
//        if (this.totalTime > that.totalTime)
//            return 1;
//        else if (this.totalTime == that.totalTime)
//            return 0;
//        else
//            return -1;
//    }
//}

class TripObject implements Comparable<TripObject>, Parcelable {
    private String tripSignature;
    //You will want private data fields which are only modifiable by get/set
    private  int totalTime = 0;// default value
    private  double totalCost =0;
    private ArrayList<String> tripList;
    private ArrayList<String> timeAndCostList;

    //Note that if more than one constructor exists, you must define the no arg constuctor manually,
    TripObject(String trip, int time, double cost){
        this.tripSignature = trip;
        this.totalTime = time;
        this.totalCost = cost;
    }
    public String getTripSignature() {
        return tripSignature;// Accessor
    }
    public int getTotalTime() {
        return totalTime;// Accessor
    }
    public double getTotalCost() {
        return totalCost;// Accessor
    }
    public ArrayList<String> getTripList(){
        return tripList;
    }
    public void setTripList(ArrayList<String> tripList){
        this.tripList = tripList;
    }
    public void setTimeAndCostList(ArrayList<String> a){
        this.timeAndCostList = a;
    }
    public ArrayList<String> getTimeAndCostList(){
        return this.timeAndCostList;
    }

    public String toString() {
        //When you print a class object you use toString
        return "total time"+ totalTime;
    }
    @Override
    public int compareTo(TripObject that) {
        if (this.totalTime > that.totalTime)
            return 1;
        else if (this.totalTime == that.totalTime)
            return 0;
        else
            return -1;
    }

    protected TripObject(Parcel in) {
        tripSignature = in.readString();
        totalTime = in.readInt();
        totalCost = in.readDouble();
        if (in.readByte() == 0x01) {
            tripList = new ArrayList<String>();
            in.readList(tripList, String.class.getClassLoader());
        } else {
            tripList = null;
        }
        if (in.readByte() == 0x01) {
            timeAndCostList = new ArrayList<String>();
            in.readList(timeAndCostList, String.class.getClassLoader());
        } else {
            timeAndCostList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tripSignature);
        dest.writeInt(totalTime);
        dest.writeDouble(totalCost);
        if (tripList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tripList);
        }
        if (timeAndCostList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(timeAndCostList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TripObject> CREATOR = new Parcelable.Creator<TripObject>() {
        @Override
        public TripObject createFromParcel(Parcel in) {
            return new TripObject(in);
        }

        @Override
        public TripObject[] newArray(int size) {
            return new TripObject[size];
        }
    };
}
