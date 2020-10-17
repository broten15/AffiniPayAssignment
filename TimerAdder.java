public class TimerAdder {

    private static final int MINS_IN_DAY = 1440;
    private static final int MINS_IN_HOUR = 60;
    private static final int MINS_IN_12HOURS = 720;
    private static final int HOURS_IN_HALF_A_DAY = 12;
    public static void main(String[] args) {
        // tester for addMinutes
        String result = addMinutes("4:55 PM", 100);
        System.out.println(result);
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
        if (time == null || !timeIsProperFormat(time)) {
            throw new IllegalArgumentException("time must be a string of must be in" +
                                               "12-hour time format: [H]H:MM {AM|PM}");
        }
        int minsSince12AM = convertToMinutes(time);
        minsSince12AM += minsToAdd;
        // if the result of adding |minsToAdd| is over 1440 (24 hours) that means
        // a new day is started so the minutes passed since 12 AM will be 0
        minsSince12AM %= MINS_IN_DAY;
        // if the mins to add is negative and it makes minsSince12AM negative,
        // minsSince12AM is now the mins before 12 AM so convert it back into
        // the mins since 12 AM
        if (minsSince12AM < 0) {
            minsSince12AM = MINS_IN_DAY + minsSince12AM;
        }
        String result = convertTo12HourTimeString(minsSince12AM);
        return result;
    }

    // helper method for addMinutes:
    // converts the hours and minutes into a 12-hour time format 
    private static String convertTo12HourTimeString(int minsSince12AM) {
        // convert MinsSince12AM to hours, mins, and period 
        int hours = minsSince12AM / MINS_IN_HOUR;
        int minutes = minsSince12AM % MINS_IN_HOUR;
        // if it is 
        String period = "AM";
        // 0 hours in 12 hour time is a time of 12
        if (hours == 0) { // period is AM
            hours += HOURS_IN_HALF_A_DAY;
        } else if (minsSince12AM > MINS_IN_12HOURS) { // period is PM
            // if it is 12 hours past 12AM then it is the PM period of the day.
            period = "PM";
            // adjust hours that are over 12 to fit 12-hour time format
            if (hours > HOURS_IN_HALF_A_DAY) {
                hours -= HOURS_IN_HALF_A_DAY;
            }
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
        int minsSince12AM = Integer.parseInt(hourMin[1]); 
        // add the appropriate amount of mins to minSince12AM based on the period
        // of day and the hour from the time string 
        if (!(timePeriod[1].equals("AM") && hourMin[0].equals("12"))) {
            // add the minutes from the hours from time string unless the hours is 12 AM 
            // (12AM is 0 mins since 12AM)
            minsSince12AM += Integer.parseInt(hourMin[0]) * 60; // convert hours to mins
            if (timePeriod[1].equals("PM") && !hourMin[0].equals("12")) {
                // if it is PM and but not 12PM add an additional 12 hours (720 mins)
                minsSince12AM += MINS_IN_12HOURS;
            }
        }
        return minsSince12AM;
    }

    // checks to make sure the the passed time string is of the proper format
    private static boolean timeIsProperFormat(String time) {
        // check the length of the string
        if (time.length() < 7 || time.length() > 8) {
            return false;
        }
        // make sure it contains a colon and a space
        if (!(time.contains(":") && time.contains(" "))) {
            return false;
        }
        // check to make sure characters between colon are digits
        String[] timePeriod = time.split(" ");
        String[] hourMin = timePeriod[0].split(":");
        for (int i = 0; i < hourMin.length; i++) {
            for (int j = 0; j < hourMin[i].length(); j++) {
                if (hourMin[i].charAt(j) < '0' || 
                    hourMin[i].charAt(j) > '9') {
                    return false;
                }
            }
        }
        // in time string make sure hour is less than or equals to 12
        // and make sure minutes is less than or equals to 59
        if (Integer.parseInt(hourMin[0]) > 12 || Integer.parseInt(hourMin[1]) > 59) {
            return false;
        }
        // check to make sure period after time is AM or PM
        if (!(timePeriod[1].equals("AM") || timePeriod[1].equals("PM"))) {
            return false;
        }
        // all checks are passed, time string is of proper form
        return true;
    }
}