package org.firstinspires.ftc.teamcode.powerplayV2.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {
    private final ServoImplEx servo;
    private final double MIN = 0, MAX = 1; // MAX = 0.925;
    private double pos;
    private double step = 0.01;
//    private double threshold = 0.02;
    private double[] autoPos = {0.72, 0.75, 0.78, 0.8, MAX};
    private Telemetry telemetry;

    enum State {
        INTAKE,
        TRAVEL,
        MID
    }

    State state;

    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        servo = hardwareMap.get(ServoImplEx.class, "arm");
        this.setMid();
        this.telemetry = telemetry;
    }

    public void setIntake() {
        pos = MAX;
        servo.setPosition(MAX);
        state = State.INTAKE;
    }

    public void setTravel() {
        pos = MIN;
        servo.setPosition(MIN);
        state = State.TRAVEL;
    }

    public void setMid() {
        pos = 0.5;
        servo.setPosition(0.5);
        state = State.MID;
    }

    public void increasePos() {
        pos += step;
        pos = pos > MAX ? MAX : pos;
        servo.setPosition(pos);
    }

    public void decreasePos() {
        pos -= step;
        pos = pos < MIN ? MIN : pos;
        servo.setPosition(pos);
    }

    public void setAutonomousPosition(int index) {
        servo.setPosition(autoPos[index]);
    }

    public void toggleState() {
        if (state == State.INTAKE)
            setTravel();
        else
            setIntake();
    }

//    public boolean atTargetPosition() {
//        return servo.getPosition() > (pos - threshold) && servo.getPosition() < (pos + threshold);
//    }

    public void periodic(){
        telemetry.addData("Arm Pos: ", servo.getPosition());
    }
}