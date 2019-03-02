package time;

public class TimeManager {

    /**
     * REQUIRES: string<br/>
     *
     * MODIFIES: nothing<br/>
     *
     * EFFECT: a time object with the following properties: hours, minutes, seconds, milliseconds
     * @param strTime
     * @return Time || null
     */
    public Time parseTime(String strTime) {

        int colonsCounter = 0;
        for (int i = 0; i < strTime.length(); i++) {
            colonsCounter = strTime.charAt(i) == ':' ? ++colonsCounter : colonsCounter;
        }

        for (int i = 0; i < 3 - colonsCounter; i++) {
            strTime += ":";
        }

        int[] timeArr = new int[4];

        String[] strTimes = strTime.split(":");

        try {

            int i = 0;
            for (String t : strTimes) {
                timeArr[i++] = Integer.parseInt(t);
            }

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: invalid input value");
            return null;
        }

        int h = 0, m = 0, s = 0, ms = 0;
        try {

            h = timeArr[0] < 0 || timeArr[0] > 23 ? -1 : timeArr[0];
            m = timeArr[1] < 0 || timeArr[1] > 59 ? -1 : timeArr[1];
            s = timeArr[2] < 0 || timeArr[2] > 59 ? -1 : timeArr[2];
            ms = timeArr[3] < 0 || timeArr[3] > 999 ? -1 : timeArr[3];

            if (h == -1) throw new Exception();
            if (m == -1) throw new Exception();
            if (s == -1) throw new Exception();
            if (ms == -1) throw new Exception();

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: invalid input value");
            return null;
        } catch (Exception e) {
            System.out.println("Exception: invalid input value");
            return null;
        }

        return new Time(h, m, s, ms);
    }   //  parseTime(strTime)

    /**
     * REQUIRES: 0 to 4 values that can represent time values<br/>
     *
     * MODIFIES: nothing<br/>
     *
     * EFFECT: a time object with the following parameters: hours, minutes, seconds, milliseconds
     * @param hour
     * @param min
     * @param sec
     * @param millisec
     * @return Time || null
     */
    public Time time(int hour, int min, int sec, int millisec) {

        int h = 0, m = 0, s = 0, ms = 0;
        try {

            h = hour < 0 || hour > 23 ? -1 : hour;
            m = min < 0 || min > 59 ? -1 : min;
            s = sec < 0 || sec > 59 ? -1 : sec;
            ms = millisec < 0 || millisec > 999 ? -1 : millisec;

            if (h == -1) throw new Exception();
            if (m == -1) throw new Exception();
            if (s == -1) throw new Exception();
            if (ms == -1) throw new Exception();

        } catch (Exception e) {
            System.out.println("Exception: invalid input value");
            return null;
        }

        return new Time(h, m, s, ms);
    }   //  time(hour, min, sec, millisec)


    /**
     * REQUIRES: a number or a string that represents time expressed in milliseconds<br/>
     *
     * MODIFIES: nothing<br/>
     *
     * EFFECT: a time object with the following parameters: hours, minutes, seconds, milliseconds, time
     * @param millisec
     * @return Time
     */
    public Time millisec2time(long millisec) {

        int h = (int)Math.floor(millisec/3600000);
        int m = (int)Math.floor(millisec/60000) - h*60;
        int s = (int)Math.floor(millisec/1000) - h*3600 - m*60;
        int ms = (int)(millisec - h*3600000 - m*60000 - s*1000);
        return new Time(h, m, s, ms);
    }   //  millsec2time


    /**
     * REQUIRES: a formatted string representing a time value (hh:mm:ss:mls)<br/>
     *
     * MODIFIES: nothing<br/>
     *
     * EFFECT: returns the time value expressed in minutes
     * @param strTime
     * @return int
     */
    public int time2min(String strTime) {
        Time t = this.parseTime(strTime);
        return (t.getMinutes() + t.getHours()*60);
    }   //  time2min(strTime)


    /**
     * REQUIRES: a formatted string representing a time value (hh:mm:ss:mls)<br/>
     *
     * MODIFIES: nothing<br/>
     *
     * EFFECT: returns the time value expressed in seconds
     * @param strTime
     * @return int
     */
    public int time2sec(String strTime) {
        Time t = this.parseTime(strTime);
        return (t.getSeconds() + t.getMinutes()*60 + t.getHours()*3600);
    }   //  time2sec(strTime)


    /**
     * REQUIRES: a formatted string representing a time value (hh:mm:ss:mls)<br/>
     *
     * MODIFIES: nothing<br/>
     *
     * EFFECT: returns the time value expressed in milliseconds
     * @param strTime
     * @return long
     */
    public long time2millisec(String strTime) {
        Time t = this.parseTime(strTime);
        return (t.getMillisec() + t.getSeconds()*1000 + t.getMinutes()*60000 + t.getHours()*3600000);
    }   //  time2millisec(strTime)


    /**
     * REQUIRES: two formatted strings representing time values (hh:mm:ss:mls)
     *
     * MODIFIES: nothing
     *
     * EFFECT: returns the difference of the two time values in milliseconds
     * @param {string} t1
     * @param {string} t2
     */
    public long subtractTime(String strTime1, String strTime2) {
        long timeArr1 = this.time2millisec(strTime1);
        long timeArr2 = this.time2millisec(strTime2);
        return timeArr1 - timeArr2;
    }


}