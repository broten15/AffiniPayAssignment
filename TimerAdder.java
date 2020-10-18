import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimerAdder {

    private static final int MINS_IN_DAY = 1440;
    private static final int MINS_IN_HOUR = 60;
    private static final int MINS_IN_12HOURS = 720;
    private static final int HOURS_IN_HALF_A_DAY = 12;
    public static void main(String[] args) {
        // tester for addMinutes
        String result = addMinutes("12:00 PM", 0);
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
                                               " 12-hour time format: [H]H:MM {AM|PM}");
        }
        int minsSince12AM = convertToMinutes(time);
        minsSince12AM += minsToAdd;
        // the remainder is the time of the currennt day
        minsSince12AM %= MINS_IN_DAY; 
        // if minsSince12AM is negative, the minsSince12AM will be mins before 12 AM 
        // so convert it back into the mins since 12 AM
        if (minsSince12AM < 0) {
            minsSince12AM = MINS_IN_DAY + minsSince12AM;
        }
        return convertTo12HourTimeString(minsSince12AM);
    }

    // helper method for addMinutes:
    // converts the hours and minutes into a string in 12-hour time format
    private static String convertTo12HourTimeString(int minsSince12AM) {
        // convert MinsSince12AM to hours, mins, and period 
        int hours = minsSince12AM / MINS_IN_HOUR;
        int minutes = minsSince12AM % MINS_IN_HOUR;
        String period = "AM";
        if (hours == 0) { // 0 hours in 12 hour time is a time of 12 AM
            hours += HOURS_IN_HALF_A_DAY;
        } else if (minsSince12AM >= MINS_IN_12HOURS) { // more than 
            period = "PM";
            // adjust hours that are over 12 to fit 12-hour time format
            if (hours > HOURS_IN_HALF_A_DAY) {
                hours -= HOURS_IN_HALF_A_DAY;
            }
        }
        // if minutes is a single digit add a 0 before the digit
        String minutesStr = "" + minutes;
        if (minutes < 10) {
            minutesStr = 0 + minutesStr;
        }
        return hours + ":" + minutesStr + " " + period;
    }

    // helper method for addMinutes:
    // Converts the time string into minutes passed since 12 AM. 
    // Splits up time string into hours, minutes, and period of day
    // and parse hours and minutes as ints.
    private static int convertToMinutes(String time) {
        // timePeriod[0] = hour:mins, timePeriod[1] = AM or PM
        String[] timePeriod = time.split(" "); 
        // hourMin[0] = hours string, hourMin[1] = minutes string
        String[] hourMin = timePeriod[0].split(":");
        int minsSince12AM = Integer.parseInt(hourMin[1]); 
        // add  appropriate amount of mins to minSince12AM 
        if (!(timePeriod[1].equals("AM") && hourMin[0].equals("12"))) {
            // add the minutes from the hours in time string unless the hours is 12 AM 
            // (12AM is 0 mins since 12AM)
            minsSince12AM += Integer.parseInt(hourMin[0]) * MINS_IN_HOUR; // convert hours to mins
            if (timePeriod[1].equals("PM") && !hourMin[0].equals("12")) {
                // if it is PM and but not 12PM add an additional 12 hours (720 mins)
                minsSince12AM += MINS_IN_12HOURS;
            }
        }
        return minsSince12AM;
    }

    // checks to make sure the the passed time string is of the proper format
    private static boolean timeIsProperFormat(String time) {
        // Regex to check valid time in 12-hour format. 
        String regexPattern = "(1[012]|[1-9]):[0-5][0-9](\\s)(?i)(AM|PM)"; 
        Pattern compiledPattern = Pattern.compile(regexPattern); 
        Matcher m = compiledPattern.matcher(time); 
        // Return if the time matched the ReGex 
        return m.matches(); 
    }
}