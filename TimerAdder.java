import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimerAdder {

    private static final int MINS_IN_DAY = 1440;
    private static final int MINS_IN_HOUR = 60;
    private static final int MINS_IN_12HOURS = 720;
    private static final int HOURS_IN_HALF_A_DAY = 12;
    public static void main(String[] args) {
        // tester for addMinutes

        // test 1
        System.out.println("\nTest 1: Add 0 minutes to 12:00 PM");
        String actual = addMinutes("12:00 PM", 0);
        String expected = "12:00 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 1");
        } else {
            System.out.println("Failed test 1");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 2
        System.out.println("\nTest 2: Add 0 minutes to 12:00 AM");
        actual = addMinutes("12:00 AM", 0);
        expected = "12:00 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 2");
        } else {
            System.out.println("Failed test 2");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 3
        System.out.println("\nTest 3: Add 720 minutes to 12:00 AM");
        actual = addMinutes("12:00 AM", 720);
        expected = "12:00 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 3");
        } else {
            System.out.println("Failed test 3");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 4
        System.out.println("\nTest 4: Add 720 minutes to 12:00 PM");
        actual = addMinutes("12:00 PM", 720);
        expected = "12:00 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 4");
        } else {
            System.out.println("Failed test 4");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 5
        System.out.println("\nTest 5: Add 1440 minutes to 12:00 AM");
        actual = addMinutes("12:00 AM", 1440);
        expected = "12:00 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 5");
        } else {
            System.out.println("Failed test 5");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 6
        System.out.println("\nTest 6: Add 1440 minutes to 12:00 PM");
        actual = addMinutes("12:00 PM", 1440);
        expected = "12:00 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 6");
        } else {
            System.out.println("Failed test 6");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 7
        System.out.println("\nTest 7: Add 5 minutes to 12:00 AM");
        actual = addMinutes("12:00 AM", 5);
        expected = "12:05 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 7");
        } else {
            System.out.println("Failed test 7");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 8
        System.out.println("\nTest 8: Add -5 minutes to 12:00 AM");
        actual = addMinutes("12:00 AM", -5);
        expected = "11:55 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 8");
        } else {
            System.out.println("Failed test 8");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 9
        System.out.println("\nTest 9: Add 5 minutes to 12:00 PM");
        actual = addMinutes("12:00 PM", 5);
        expected = "12:05 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 9");
        } else {
            System.out.println("Failed test 9");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 10
        System.out.println("\nTest 10: Add -5 minutes to 12:00 PM");
        actual = addMinutes("12:00 PM", -5);
        expected = "11:55 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 10");
        } else {
            System.out.println("Failed test 10");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 11
        System.out.println("\nTest 11: Add 1343457 minutes to 12:00 AM");
        actual = addMinutes("12:00 AM", 1343457);
        expected = "10:57 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 11");
        } else {
            System.out.println("Failed test 11");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 10
        System.out.println("\nTest 10: Add -1343457 minutes to 12:00 AM");
        actual = addMinutes("12:00 AM", -1343457);
        expected = "1:03 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 10");
        } else {
            System.out.println("Failed test 10");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 11
        System.out.println("\nTest 11: Add 65 minutes to 12:00 AM");
        actual = addMinutes("12:00 AM", 65);
        expected = "1:05 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 11");
        } else {
            System.out.println("Failed test 11");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 11
        System.out.println("\nTest 11: Add 65 minutes to 12:00 PM");
        actual = addMinutes("12:00 PM", 65);
        expected = "1:05 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 11");
        } else {
            System.out.println("Failed test 11");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 12
        System.out.println("\nTest 12: Add -65 minutes to 1:05 PM");
        actual = addMinutes("1:05 PM", -65);
        expected = "12:00 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 12");
        } else {
            System.out.println("Failed test 12");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 13
        System.out.println("\nTest 13: Add 347 minutes to 4:25 PM");
        actual = addMinutes("4:25 PM", 347);
        expected = "10:12 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 13");
        } else {
            System.out.println("Failed test 13");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 14
        System.out.println("\nTest 14: Add 347 minutes to 4:25 AM");
        actual = addMinutes("4:25 AM", 347);
        expected = "10:12 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 14");
        } else {
            System.out.println("Failed test 14");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 14
        System.out.println("\nTest 14: Add -347 minutes to 4:25 AM");
        actual = addMinutes("4:25 AM", -347);
        expected = "10:38 PM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 14");
        } else {
            System.out.println("Failed test 14");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }

        // test 14
        System.out.println("\nTest 14: Add -347 minutes to 4:25 PM");
        actual = addMinutes("4:25 PM", -347);
        expected = "10:38 AM";
        if (expected.equals(actual)) {
            System.out.println("Passed test 14");
        } else {
            System.out.println("Failed test 14");
            System.out.println("expected: " + expected + " | actual: " + actual);
        }
    }

    /**
     * Takes in a 12-hour time string and a (signed) integer of minutes 
     * to add to the time string. A new 12-hour time string with the
     * added minutes is returned.
     * <br>pre: time is of format "[H]H:MM {AM|PM}"
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
        int minsSince12AM = convertToMinutes(time);
        minsSince12AM += minsToAdd;
        // the remainder is the time of the current day
        minsSince12AM %= MINS_IN_DAY; 
        // if minsSince12AM is negative, the minsSince12AM will be mins before 12 AM 
        // so convert it back into the mins since 12 AM
        if (minsSince12AM < 0) {
            minsSince12AM = MINS_IN_DAY + minsSince12AM;
        }
        return convertTo12HourTime(minsSince12AM);
    }

    // Converts the time string into the minutes passed since 12 AM. 
    private static int convertToMinutes(String time) {
        // timePeriod[0] = (hour):(mins), timePeriod[1] = AM or PM
        String[] timePeriod = time.split(" "); 
        // hourMin[0] = hours, hourMin[1] = minutes
        String[] hourMin = timePeriod[0].split(":");
        int minsSince12AM = Integer.parseInt(hourMin[1]); 
        // add the minutes from the hours in time string unless the hour 
        // and period is 12 AM (12 AM -> 0 hours passed since 12 AM)
        if (!(timePeriod[1].equals("AM") && hourMin[0].equals("12"))) {
            // convert hours to mins
            minsSince12AM += Integer.parseInt(hourMin[0]) * MINS_IN_HOUR;
            // if it is PM but not 12 PM, add additional 12 hours (720 mins)
            if (timePeriod[1].equals("PM") && !hourMin[0].equals("12")) {
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
        String period = "AM";
         if (minsSince12AM >= MINS_IN_12HOURS) { // equal or past 12 PM
            period = "PM";
            // If it is PM hours will be 12 or over so subtract 12 from 
            // hours if it's over 12 to fit 12-hour time format
            if (hours > HOURS_IN_HALF_A_DAY) {
                hours -= HOURS_IN_HALF_A_DAY;
            }
        } else if (hours == 0) { // 0 hours in 12 hour time is a time of 12 AM
            hours += HOURS_IN_HALF_A_DAY;
        }
        return buildResultTimeString(hours, minutes, period);
    }

    // Builds the resulting 12 hour time format string
    private static String buildResultTimeString(int hours, int minutes, String period) {
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
}