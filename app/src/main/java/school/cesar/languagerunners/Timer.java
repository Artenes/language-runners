package school.cesar.languagerunners;

import android.os.SystemClock;
import android.os.Handler;

public class Timer {

    private long millisecondTime, startTime, timeBuff, updateTime = 0L;

    private Handler handler;

    private int seconds, minutes, hours, milliSeconds;

    private TimeConsumer consumer;

    private boolean isRunning = false;

    public Timer() {

        handler = new Handler();

    }

    public void setConsumer(TimeConsumer consumer) {

        this.consumer = consumer;

    }

    public void start() {

        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
        isRunning = true;

    }

    public void pause() {

        timeBuff += millisecondTime;
        handler.removeCallbacks(runnable);
        isRunning = false;

    }

    public void reset() {

        millisecondTime = 0L;
        startTime = 0L;
        timeBuff = 0L;
        updateTime = 0L;
        seconds = 0;
        minutes = 0;
        milliSeconds = 0;
        hours = 0;
        isRunning = false;

    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    private Runnable runnable = new Runnable() {

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;

            updateTime = timeBuff + millisecondTime;

            seconds = (int) (updateTime / 1000);

            minutes = seconds / 60;

            hours = minutes / 60;

            milliSeconds = (int) (updateTime % 1000);

            if (consumer != null) consumer.consume(hours, minutes, seconds, milliSeconds);

            handler.postDelayed(this, 0);

        }

    };

}
