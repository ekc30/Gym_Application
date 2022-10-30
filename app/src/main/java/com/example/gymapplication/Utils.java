package com.example.gymapplication;

import java.util.ArrayList;

// static methods --> don't need to instantiate the class to call its methods
public class Utils {

    private static ArrayList<Training> trainings;
    private static ArrayList<Plan> plans;

    // set the data for trainings list
    public static void initTrainings() {
        // create array list if null
        if(trainings == null) {
            trainings = new ArrayList<>();
        }

        // define some training data
        Training pushUp = new Training(1, "Push up", "Short description for push ups", "Long description for push ups", "https://assets.gqindia.com/photos/5cee7eb00379a73d25177759/master/pass/Pushup.jpg");
        Training squat = new Training(2, "Squat", "Short description for squat", "Long description for squat", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/30-day-squat-challenge-lead-1588712943.png");
        Training legPress = new Training(3, "Leg Press", "Short description for leg press", "Long description for leg press", "https://www.ubuy.hu/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNzFRakI1TWp0QkwuX0FDX1NMMTUwMF8uanBn.jpg");
        trainings.add(pushUp);
        trainings.add(squat);
        trainings.add(legPress);
    }

    // returns trainings
    public static ArrayList<Training> getTrainings() {
        return trainings;
    }

    // add a plan to plans list
    public static boolean addPlan(Plan plan) {
        if(plans == null) {
            plans = new ArrayList<>();
        }

        return plans.add(plan);
    }

    // returns all plans
    public static ArrayList<Plan> getPlans() {
        return plans;
    }

    // returns the plans of the specified day
    public static ArrayList<Plan> getPlansByDay(String day) {
        ArrayList<Plan> dayPlans = new ArrayList<>();
        for(Plan p : plans) {
            if(p.getDay().equalsIgnoreCase(day)) {
                dayPlans.add(p);
            }
        }
        return dayPlans;
    }

    // remove plan from list
    public static boolean removePlan(Plan plan) {
        return plans.remove(plan);
    }
}
