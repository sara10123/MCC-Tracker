
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

    //constructor: FINISH LATER 
    //public PomodoroTimer(int workDuration, int breakDuration, String alarmSound, int volume) {
        //if(worDuration)
        //error checking
    //}

    //getters
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

    public int getVolume() {
        return volume;
    }
    
    //setters: 
    public void setAlarmSound(String alarmSound) {
        this.alarmSound = alarmSound;
    }

    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
        }
    }

    
    
    /**
     * Methods: 
     *  public void start() //starts the countdown. Uses the running variable sets it to true and can probably outprint a
     * message to tell user that the timer has started. 
     * public void pause() //stops countdown and sets running to false. Also outprints a message to tell user the timer has 
     * stopped.    
     * public void reset() //resets the timer meaning remainingTime and progress are both reset as well. running is changed to
     * false. Outprints message telling user the timer is reset. 
     * public void tick() //uses if statements to check whether timer is running and time left. Then decreases remainingTime and
     * updates progress. A second if statement is used to check if the timer has reached zero, if so it calls the switchSession
     * method to begin next session.
     *  
     */

}
