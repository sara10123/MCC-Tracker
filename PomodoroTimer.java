import javax.swing.Timer;


public class PomodoroTimer {
    final static int DEFAULT_WORK_DURATION = 25 * 60; //25 mins
    final static int DEFAULT_BREAK_DURATION = 5 * 60; // 5 mins
    final static int DEFAULT_VOLUME = 5;
    final static String DEFAULT_ALARM_SOUND = "Default"; 

    private int workDuration;
    private int breakDuration; 
    private int remainingTime; 
    private int sessionCount;
    private int volume;
    
    private boolean isRunning;
    private boolean isWorkSession;
    
    private double progress;

    private String alarmSound;

    private Timer timer;
    private Runnable onTick;

    public PomodoroTimer() {
        workDuration = DEFAULT_WORK_DURATION;
        breakDuration = DEFAULT_BREAK_DURATION;
        remainingTime = DEFAULT_WORK_DURATION;
        sessionCount = 0;
        volume = DEFAULT_VOLUME;
        isRunning = false;
        isWorkSession = true;
        progress = 0.0;
        alarmSound = DEFAULT_ALARM_SOUND;
        //creates a swing timer that calls on tick() every seccond. Timer activates every second. Swing handles repeating behavior. 
        timer = new Timer(1000, e -> tick());
    }

    /**
     * public void start()
     * beings the timer and checks whether timer is already running or not. 
     * If not, then it marks it as running and begins the Swing timer.
     * **/
    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.start();
        }
    }

//if the timer is paused, isRunning is set to fault and the timer is stopped. 
    public void pause() {
        isRunning = false;
        timer.stop();
    }

    public void reset() {
        timer.stop();
        isRunning = false;

        if (isWorkSession) {
            remainingTime = DEFAULT_WORK_DURATION;
        } else {
            remainingTime = DEFAULT_BREAK_DURATION;
        }

        progress = 0.0;
        notifyGui();
    }
    
    private void tick() {
        if (remainingTime > 0) {
            remainingTime--;
            updateProgress();
            notifyGui();
        } else {
            playAlarm();
            switchSession();
            notifyGui();
        }
    }

    private void switchSession() {
        timer.stop();
        isRunning = false;
        if (isWorkSession) {
            sessionCount++;
            isWorkSession = false;
            remainingTime = DEFAULT_BREAK_DURATION;
        } else {
            isWorkSession = true;
            remainingTime = DEFAULT_WORK_DURATION;
        }
        progress = 0.0;
    }

    private void updateProgress() {
        int totalTime;

        if (isWorkSession) {
            totalTime = workDuration;
        } else {
            totalTime = breakDuration;
        }

        progress = 1.0 - ((double) remainingTime / totalTime);
}

    private void playAlarm() {
        System.out.println("Playing " + alarmSound + " at volume " + volume);
    }

    private void notifyGui() {
        if (onTick != null) {
            onTick.run(); 
        }
    }

    //getters
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

    public String getAlarmSound() {
        return alarmSound; 
    }

    public int getVolume() {
        return volume;
    }

    public double getProgress() {
        return progress;
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

    public void setAlarmSound(String alarmSound) {
        this.alarmSound = alarmSound;
    }

    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 50) {
            this.volume = volume;
        } else {
            throw new IllegalArgumentException("Volume must be between 0-50"); 
        }
    }
    
}
