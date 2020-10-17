import java.util.Scanner;

public class TimerAdder {
    public static void main(String[] args) {
        // tester for addMinutes
        String result = addMinutes("12:10 PM", -540);
        System.out.println(result);
        for (int i = 0; i < 10000000; i++) {
            addMinutes("12:10 PM", -530);
        }
    }

    /**
     * Takes in a 12-hour time string and a (signed) integer of minutes 
     * to add to the time string. A new 12-hour time string with the
     * added minutes is returned.
     * <br>pre: time is of format "[H]H:MM {AM|PM}"
     * <br>post: Return new 12-hour time string with added or subtrcted minutes
     * @param time 12-hour time string with the format "[H]H:MM {AM|PM}"
     * @param minsToAdd (signed) integer of number of minutes to add to the time 
     * of day represented by the first argument 
     * @return A new time string with the same format as the time argument but with 
     * the added minutes
    */
    public static String addMinutes(String time, int minsToAdd) {
        int minsSince12AM = convertToMinutes(time);
        minsSince12AM += minsToAdd;
        // if the mins to add if negative and it makes minsSince12AM negative,
        // minsSince12AM is now the mins before 12 AM so convert it back into
        // minsSince12AM
        if (minsSince12AM < 0) {
            minsSince12AM = 1440 - minsSince12AM;
        }
        // if the result of adding mins to add is 1440 (24 hours) that means
        // a new day is started so the minutes passed since 12 AM will be 0
        minsSince12AM %= 1440;
        // convert MinsSince12AM to hours, mins, and period to make 12-hour time string
        int hours = minsSince12AM / 60;
        int minutes = minsSince12AM % 60;
        // converts the hours into a 12-hour format
        String period = "AM";
        if (hours == 0) {
            hours += 12;
        } else if (hours > 12) {
            period = "PM";
            hours %= 12;
        }
        return hours + ":" + minutes + " " + period;
    }

    // helper method for addMinutes:
    // converts the time string into minutes passed since 12 AM and returns 
    // that amount of minutes
    private static int convertToMinutes(String time) {
        // split up time into hours, minutes, and period of day
        // and parse hours and minutes as ints
        String[] timePeriod = time.split(" ");
        String[] hourMin = timePeriod[0].split(":");
        int minsSince12AM = Integer.parseInt(hourMin[0]) * 60; // convert hours to mins
        minsSince12AM += Integer.parseInt(hourMin[1]); 
        // if the period of day is PM add 720 mins (12 hours) to the reslt
        if (timePeriod[1].equals("PM")) {
            minsSince12AM += 720;
        }
        return minsSince12AM;
    }
}