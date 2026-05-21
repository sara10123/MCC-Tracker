package edu.miracosta.cs112.models;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class PomodoroTimer {
    final static int DEFAULT_WORK_DURATION = 25 * 60; //25 mins
    final static int DEFAULT_BREAK_DURATION = 5 * 60; // 5 mins

    private int workDuration;
    private int breakDuration;
    private int remainingTime;
    private int sessionCount;

    private boolean isRunning;
    private boolean isWorkSession;


    private String alarmSound;

    private Timeline timer;
    private Runnable onTick;

    /**
     *  creates PomodoroTimer with default values.
     */
    public PomodoroTimer() {
        workDuration = DEFAULT_WORK_DURATION;
        breakDuration = DEFAULT_BREAK_DURATION;
        remainingTime = DEFAULT_WORK_DURATION;
        sessionCount = 0;
        isRunning = false;
        isWorkSession = true;

        //creates a swing timer that calls on tick() every seccond. Timer activates every second. Swing handles repeating behavior.
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> tick()));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the countdown timer if not already running.
     * Uses isRunning variable to prevent timer from starting multiple times and begins the Timeline.
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.playFromStart();
        }
    }

    /**
     * Pauses the timer and keeps current time saved.
     * Sets isRunning to false and pauses the Timeline for user to resume if they choose.
     * **/
    public void pause() {
        isRunning = false;
        timer.pause();
    }

    /**
     * Stops and resets timer session. Resets remainingTime back to the default session and updates the display.
     * **/
    public void reset() {
        timer.stop();
        isRunning = false;

        if (isWorkSession) {
            remainingTime = workDuration;
        } else {
            remainingTime = breakDuration;
        }
        //updates the GUI and what it shows user.
        notifyGui();
    }

    /**
     * Updates timer every second while running. Uses if statments to check if there
     * is still time left. Decreases remainingTime and refreshes GUI. If timer gets to 0
     * the alarm plays and the session is switched.
     * */
    private void tick() {
        if (remainingTime > 0) {
            remainingTime--;
            notifyGui();
        } else {
            switchSession();
            notifyGui();
        }
    }

    //first stops the timer, therefore, running is set to false. Uses if else to switch the session so if it's currently a work session it...
    private void switchSession() {
        timer.stop();
        isRunning = false;
        if (isWorkSession) {
            sessionCount++;
            isWorkSession = false;
            remainingTime = breakDuration;
        } else {
            isWorkSession = true;
            remainingTime = workDuration;
        }
    }


    private void notifyGui() {
        if (onTick != null) {
            onTick.run();
        }
    }

    //getters

    /**
     *formats time
     */
    public String getFormattedTime() {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    public String getSessionLabel() {
        if (isWorkSession) {
            return "Work";
        } else {
            return "Break";
        }
    }

    public int getWorkDuration() {
        return workDuration;
    }

    public int getBreakDuration() {
        return breakDuration;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isWorkSession() {
        return isWorkSession;
    }

    //setters:
    public void setOnTick(Runnable onTick) {
        this.onTick = onTick;
    }

}



    
    
