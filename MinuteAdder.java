import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MinuteAdder {

    private static final int MINS_IN_DAY = 1440;
    private static final int MINS_IN_HOUR = 60;
    private static final int MINS_IN_12HOURS = 720;
    private static final int HOURS_IN_HALF_A_DAY = 12;
    private static final String AM = "AM";
    private static final String PM = "PM";
    public static void main(String[] args) {
        // Tester for addMinutes

        // test 1
        int minsToAdd = 0;
        String actual = addMinutes("12:00 PM", minsToAdd);
        String expected = "12:00 PM";
        assertEquals(actual, expected, 1, minsToAdd, "12:00 PM");

        // test 2
        minsToAdd = 0;
        actual = addMinutes("12:00 AM", minsToAdd);
        expected = "12:00 AM";
        assertEquals(actual, expected, 2, minsToAdd, "12:00 AM");

        // test 3
        minsToAdd = 720;
        actual = addMinutes("12:00 AM", minsToAdd);
        expected = "12:00 PM";
        assertEquals(actual, expected, 3, minsToAdd, "12:00 AM");

        // test 4
        minsToAdd = 720;
        actual = addMinutes("12:00 PM", minsToAdd);
        expected = "12:00 AM";
        assertEquals(actual, expected, 4, minsToAdd, "12:00 PM");

        // test 5
        minsToAdd = 1440;
        actual = addMinutes("12:00 AM", minsToAdd);
        expected = "12:00 AM";
        assertEquals(actual, expected, 5, minsToAdd, "12:00 AM");

        // test 6
        minsToAdd = 1440;
        actual = addMinutes("12:00 PM", minsToAdd);
        expected = "12:00 PM";
        assertEquals(actual, expected, 6, minsToAdd, "12:00 PM");

        // test 7
        minsToAdd = 5;
        actual = addMinutes("12:00 AM", minsToAdd);
        expected = "12:05 AM";
        assertEquals(actual, expected, 7, minsToAdd, "12:00 AM");

        // test 8
        minsToAdd = -5;
        actual = addMinutes("12:00 AM", minsToAdd);
        expected = "11:55 PM";
        assertEquals(actual, expected, 8, minsToAdd, "12:00 AM");

        // test 9
        minsToAdd = 5;
        actual = addMinutes("12:00 PM", minsToAdd);
        expected = "12:05 PM";
        assertEquals(actual, expected, 9, minsToAdd, "12:00 PM");

        // test 10
        minsToAdd = -5;
        actual = addMinutes("12:00 PM", minsToAdd);
        expected = "11:55 AM";
        assertEquals(actual, expected, 10, minsToAdd, "12:00 PM");

        // test 11
        minsToAdd = 1343457;
        actual = addMinutes("12:00 AM", minsToAdd);
        expected = "10:57 PM";
        assertEquals(actual, expected, 11, minsToAdd, "12:00 AM");

        // test 12
        minsToAdd = -1343457;
        actual = addMinutes("12:00 AM", minsToAdd);
        expected = "1:03 AM";
        assertEquals(actual, expected, 12, minsToAdd, "12:00 AM");

        // test 13
        minsToAdd = 65;
        actual = addMinutes("12:00 AM", minsToAdd);
        expected = "1:05 AM";
        assertEquals(actual, expected, 13, minsToAdd, "12:00 AM");

        // test 14
        minsToAdd = 65;
        actual = addMinutes("12:00 PM", minsToAdd);
        expected = "1:05 PM";
        assertEquals(actual, expected, 14, minsToAdd, "12:00 PM");

        // test 15
        minsToAdd = -65;
        actual = addMinutes("1:05 PM", minsToAdd);
        expected = "12:00 PM";
        assertEquals(actual, expected, 15, minsToAdd, "1:05 PM");

        // test 16
        minsToAdd = 347;
        actual = addMinutes("4:25 PM", minsToAdd);
        expected = "10:12 PM";
        assertEquals(actual, expected, 16, minsToAdd, "4:25 PM");

        // test 17
        minsToAdd = 347;
        actual = addMinutes("4:25 AM", minsToAdd);
        expected = "10:12 AM";
        assertEquals(actual, expected, 17, minsToAdd, "4:25 AM");

        // test 18
        minsToAdd = -347;
        actual = addMinutes("4:25 AM", minsToAdd);
        expected = "10:38 PM";
        assertEquals(actual, expected, 18, minsToAdd, "4:25 AM");

        // test 19
        minsToAdd = -347;
        actual = addMinutes("4:25 PM", minsToAdd);
        expected = "10:38 AM";
        assertEquals(actual, expected, 19, minsToAdd, "4:25 PM");

        // test 20
        minsToAdd = 200;
        actual = addMinutes("9:13 AM", minsToAdd);
        expected = "12:33 PM";
        assertEquals(actual, expected, 20, minsToAdd, "9:13 AM");
    }

    /**
     * Takes in a 12-hour time string and a (signed) integer of minutes 
     * to add to the time string. A new 12-hour time string with the
     * added minutes is returned.
     * <br>pre: time != null, time is of format "[H]H:MM {AM|PM}"
     * <br>post: Return new 12-hour time string with added minutes
     * @param time 12-hour time string with the format "[H]H:MM {AM|PM}"
     * @param minsToAdd (signed) integer of number of minutes to add to the time 
     * of day represented by the first argument 
     * @return A new time string with the same format as the time argument but with 
     * the added minutes
    */
    public static String addMinutes(String time, int minsToAdd) {
        if (time == null || !timeIsProperFormat(time)) {
            throw new IllegalArgumentException("time must be a string of must be in" +
                                               " 12-hour time format [H]H:MM {AM|PM}");
        }
        int minsSince12AM = getMinsSince12AM(time);
        minsSince12AM += minsToAdd;

        // if the magnitude of minsSince12AM is over MINS_IN_DAY, the remainder will be
        // the minsSince12AM of the most recent day which we want to get the time for
        minsSince12AM %= MINS_IN_DAY; 

        // if minsSince12AM is negative, the minsSince12AM will be the mins before 12 AM, 
        // so convert it back into the mins since 12 AM
        if (minsSince12AM < 0) {
            minsSince12AM = MINS_IN_DAY + minsSince12AM;
        }

        return convertTo12HourTime(minsSince12AM);
    }

    // Converts the time string into the minutes passed since 12 AM. 
    private static int getMinsSince12AM(String time) {
        // separate time string into mins, hours, and period
        String[] timePeriod = time.split(" "); 
        String[] hoursAndMins = timePeriod[0].split(":");
        final String MINS = hoursAndMins[1];
        final String HOURS = hoursAndMins[0];
        final String PERIOD = timePeriod[1];

        // add minutes from time string
        int minsSince12AM = Integer.parseInt(MINS); 

        // add the minutes from the hours in time string unless the hour 
        // and period is 12 AM (12 AM -> 0 hours passed since 12 AM)
        if (!(PERIOD.equals(AM) && HOURS.equals("12"))) {
            // convert hours to mins
            minsSince12AM += Integer.parseInt(HOURS) * MINS_IN_HOUR;

            // if it is PM but not 12 PM, add additional 12 hours (720 mins)
            if (PERIOD.equals(PM) && !HOURS.equals("12")) {
                minsSince12AM += MINS_IN_12HOURS;
            }
        }

        return minsSince12AM;
    }

    // converts the minutes passed since 12 AM into a string 12-hour time format
    private static String convertTo12HourTime(int minsSince12AM) {
        // convert minsSince12AM to hours, mins, and period 
        int hours = minsSince12AM / MINS_IN_HOUR;
        int minutes = minsSince12AM % MINS_IN_HOUR;

        String period = AM;
         if (minsSince12AM >= MINS_IN_12HOURS) { // equal or past 12 PM
            period = PM;

            // If it is PM, hours will be 12 or over, so subtract 12 from 
            // hours if it's over 12 to fit 12-hour time format
            if (hours > HOURS_IN_HALF_A_DAY) {
                hours -= HOURS_IN_HALF_A_DAY;
            }
        } else if (hours == 0) { // 0 hours in 12 hour-time means 12 AM
            hours += HOURS_IN_HALF_A_DAY;
        }

        return getResultTimeString(hours, minutes, period);
    }

    // Builds the resulting time string in a 12-hour time format
    private static String getResultTimeString(int hours, int minutes, String period) {
        final String COLON = ":";
        final String SPACE = " ";

        StringBuilder sb = new StringBuilder();
        sb.append(hours);
        sb.append(COLON);
        // if minutes is a single digit add a 0 before the digit
        if (minutes < 10) {
            sb.append("0");
        }
        sb.append(minutes);
        sb.append(SPACE);
        sb.append(period);
        return sb.toString();
    }

    // Checks to make sure the the passed time string is of the proper format.
    private static boolean timeIsProperFormat(String time) {
        // Regex to check valid time in 12-hour format. 
        String regexPattern = "(1[012]|[1-9]):[0-5][0-9](\\s)(AM|PM)"; 
        Pattern compiledPattern = Pattern.compile(regexPattern); 
        Matcher m = compiledPattern.matcher(time); 
        // Return if the time matched the ReGex 
        return m.matches(); 
    }

    // method to test if addMinutes outputs the proper time and format
    private static void assertEquals(Object actual, Object expected, int testNum,
                                     int minsToAdd, String initialTime) {
        System.out.println("\nTest " + testNum + ": Add " + minsToAdd + 
                           " minutes to " + initialTime);
        if (actual.equals(expected)) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("FAILED TEST " + testNum);
            System.out.println("expected: " + expected + " | actual: " + actual);
        }
    }
}