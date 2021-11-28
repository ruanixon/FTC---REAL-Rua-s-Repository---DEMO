package org.firstinspires.ftc.team6220_2021;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "BlueFullAuto", group = "Autonomous")
public class BlueFullAuto extends MasterOpMode{
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorDuck;
    DcMotor motorArm;
    Servo servoGrabber;
    Servo servoArm;
    int Detection = 3;
    int ArmPosition;
    int DriveAdjust;
    double ServoPosition;
    @Override
    public void runOpMode() {
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorArm = hardwareMap.dcMotor.get("motorArm");
        motorDuck = hardwareMap.dcMotor.get("motorDuck");
        servoGrabber = hardwareMap.servo.get("servoGrabber");
        servoArm = hardwareMap.servo.get("servoArm");
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorDuck.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Set run mode of arm motor (encoders --> run to position)
        motorArm.setTargetPosition(0);
        motorArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        servoGrabber.setPosition(0.34);
        pauseMillis(500);
        servoArm.setPosition(0.01);
        waitForStart();
        if (Detection == 1){
            ArmPosition = -220;
            ServoPosition = 0.6;
            DriveAdjust = 23;
        }
        else if (Detection == 2){
            ArmPosition = -470;
            ServoPosition = 0.8;
            DriveAdjust = 21;
        }
        else if (Detection == 3){
            ArmPosition = -720;
            ServoPosition = 1;
            DriveAdjust = 25;
        }
        Forward(10,0.3);
        pauseMillis(1000);
        TurnAngle(90);
        pauseMillis(750);
        Forward(15,0.5);
        pauseMillis(1250);
        stopbase();
        BlueDuck();
        pauseMillis(2000);
        Forward(-5,0.5);
        pauseMillis(750);
        TurnAngle(-125);
        pauseMillis(1000);
        servoArm.setPosition(ServoPosition);
        motorArm.setTargetPosition(ArmPosition);
        motorArm.setPower(0.9);
        pauseMillis(500);
        Forward(DriveAdjust,0.5);
        pauseMillis(2000);
        stopbase();
        servoGrabber.setPosition(0.7);
        pauseMillis(750);
        Forward(-20,0.5);
        pauseMillis(1500);
        TurnAngle(130);
        pauseMillis(2000);
        stopbase();
        servoGrabber.setPosition(0.34);
        pauseMillis(500);
        servoArm.setPosition(0.01);
        motorArm.setTargetPosition(-220);
        motorArm.setPower(0.9);
        pauseMillis(500);
        Forward(-84, 0.8);
        pauseMillis(2000);
        motorArm.setTargetPosition(10);
        motorArm.setPower(0.9);
        pauseMillis(500);
    }
}