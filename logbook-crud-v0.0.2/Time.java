package time;

public class Time {

    private int hours;
    private int minutes;
    private int seconds;
    private int millisec;

    public Time(int hours, int minutes, int seconds, int millisec) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.millisec = millisec;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMillisec() {
        return millisec;
    }

    @Override
    public String toString() {
        return hours + ":" + minutes + ":" + seconds + ":" + millisec;
    }
}
