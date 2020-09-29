package game_src;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Dinesh on 02/04/2020
 */
public class CountDownTimer {

    private static final int DELAY = 1000;
    private static final int PERIOD = 1000;
    private static final int COUNTDOWN_TIMER_IN_SECS = 60;
    private int timeLeftInSecs;
    private boolean timerRunning = false;
    private static Timer timer;

    public CountDownTimer() {
        timer = new Timer();
        timerRunning = true;
        timeLeftInSecs = COUNTDOWN_TIMER_IN_SECS;
        // schedule the timer with 1 second interval
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                setInterval();
            }
        }, DELAY, PERIOD);
    }

    private void setInterval() {
        if (timeLeftInSecs == 1) {
            stopTimer();
        }
        --timeLeftInSecs; // reduce the time left by 1 second
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public int getCountdownTimeInSeconds() {
        return timeLeftInSecs;
    }

    protected void stopTimer() {
        timerRunning = false;
        timer.cancel();
    }

    // convert the time left into minutes and seconds
    public String getLeftTimeInString() {
        String time = "";
        int timeLeft = timeLeftInSecs;
        int minutes = (int) (timeLeft) / 60;
        int seconds = (int) (timeLeft) % 60;
        time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        return time;
    }
}
