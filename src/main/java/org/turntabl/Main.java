package org.turntabl;

public class Main {
    public static void main(String[] args){
            System.out.println("Hello world!");
        double capacity = 190;
        double weightLimit = 150;

        double aboveWeightLimit = 20;

        double extraCharge = 5;


        double remainingWeight = capacity-weightLimit;
        System.out.println("Remaining weight" + remainingWeight);

        double extraChargeForWeight = remainingWeight / aboveWeightLimit;

        System.out.println();
    }

}