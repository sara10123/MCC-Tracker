package edu.miracosta.cs112.controllers;

import edu.miracosta.cs112.models.PomodoroTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;


public class PomodoroTimerController implements Runnable {

    private PomodoroTimer timer;

    @FXML
    private Label timeLabel;

    @FXML
    private Label sessionLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button resumeButton;

    @FXML
    private Button resetButton;

    @FXML
    public void initialize(){
        timer = new PomodoroTimer();
        timer.setOnTick(this);

        timeLabel.setText(timer.getFormattedTime());
        sessionLabel.setText(timer.getSessionLabel());

        showStart();
    }

    @FXML
    public void startTimer(){
        timer.start();
        showPauseAndReset();
    }

    @FXML
    public void resumeTimer() {
        timer.start();
        showPauseAndReset();
    }

    public void pauseTimer() {
        timer.pause();
        showResumeAndReset();
    }

    public void resetTimer() {
        timer.reset();
        showStart();
    }

    @Override
    public void run() {
        timeLabel.setText(timer.getFormattedTime());
        sessionLabel.setText(timer.getSessionLabel());

        if(!timer.isRunning()) {
            showStart();
        }
    }

    private void showStart() {
        startButton.setVisible(true);
        startButton.setManaged(true);

        pauseButton.setVisible(false);
        pauseButton.setManaged(false);

        resumeButton.setVisible(false);
        resumeButton.setManaged(false);

        resetButton.setVisible(false);
        resetButton.setManaged(false);
    }

    private void showPauseAndReset() {
        startButton.setVisible(false);
        startButton.setManaged(false);

        pauseButton.setVisible(true);
        pauseButton.setManaged(true);

        resumeButton.setVisible(false);
        resumeButton.setManaged(false);

        resetButton.setVisible(true);
        resetButton.setManaged(true);
    }

    private void showResumeAndReset() {
        startButton.setVisible(false);
        startButton.setManaged(false);

        pauseButton.setVisible(true);
        pauseButton.setManaged(true);

        resumeButton.setVisible(true);
        resumeButton.setManaged(true);

        resetButton.setVisible(true);
        resetButton.setManaged(true);
    }

}
