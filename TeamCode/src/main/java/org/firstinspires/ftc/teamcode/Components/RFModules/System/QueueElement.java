package org.firstinspires.ftc.teamcode.Components.RFModules.System;

import static org.firstinspires.ftc.teamcode.Robots.BasicRobot.logger;
import static org.firstinspires.ftc.teamcode.Robots.BasicRobot.op;
import static org.firstinspires.ftc.teamcode.Robots.BasicRobot.time;

public class QueueElement {
    //place in line
    private int queuePos = 0;
    //after which item is done u can run
    public int startCondition = 0;
    //if event is asynchronous(if should be sequential or ignore)
    private boolean asynchronous = false;
    //if started
    private boolean started = false;
    //if done
    private boolean isDone = false;
    //For calculating delay, 1000 is just a placeholder that should never be reached normally
    private double readyTime = 1000;
    //should it wait for everything else to finish
    private boolean mustFinish = false;
    //should it wait for everything else to finish
    private boolean shouldFinish = false;
    //is it an optional event
    private boolean isOptional = false;
    //is it skippable right now
    private boolean option = false;

    public QueueElement(int queueNum, boolean p_asynchronous, int p_startCondition, boolean p_mustFinish) {
        queuePos = queueNum;
        asynchronous = p_asynchronous;
        startCondition = p_startCondition;
        mustFinish = p_mustFinish;
    }

    public QueueElement(int queueNum, boolean p_asynchronous, int p_startCondition, boolean p_mustFinish, boolean p_shouldFinish, boolean p_isOptional) {
        queuePos = queueNum;
        asynchronous = p_asynchronous;
        startCondition = p_startCondition;
        mustFinish = p_mustFinish;
        shouldFinish = p_shouldFinish;
        isOptional = p_isOptional;
    }

    //calculate if Ready
    public boolean isReady(int currentEvent, boolean extraCondition) {
        //is it this elements turn to run
        if (currentEvent >= startCondition && !isDone) {
            // update when start for delay logic
            if (readyTime > time) {
                readyTime = time;
                logger.log("/RobotLogs/GeneralRobot", queuePos + "readyTime =" + readyTime +"optional"+isOptional);
            }
            //consider extraw condition(if it exists)
            if (extraCondition) {
                return true;
            } else {
//                logger.log("/RobotLogs/GeneralRobot", queuePos + "readyTime =" + readyTime);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isMustFinish() {
        return mustFinish;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean p_isDone) {
        isDone = p_isDone;
    }

    public void setStarted(boolean p_started) {
        started = p_started;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isAsynchronous() {
        return asynchronous;
    }

    public void setAsynchronous(boolean p_asynchronous){asynchronous = p_asynchronous;}

    public void setStartCondition(int p_condition){
        startCondition=p_condition;
    }

    public boolean isOptional(){return isOptional;}

    public boolean getSkipOption(){return option;}

    public void setSkipOption(boolean p_option){option = p_option;}

    public double getReadyTime() {
        return readyTime;
    }

    public boolean isShouldFinish() {
        return shouldFinish;
    }
}