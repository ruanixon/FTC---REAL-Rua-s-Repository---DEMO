package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvWebcam;


@Autonomous(name = "RedLeft")
public class RedLeft extends LinearOpMode {
    Webcam webcam;
    private AutoMethods autoMethods;
    private DcMotor motorLeft, motorLeft2,
            motorRight, motorRight2, motorIntake, motorHang;
    private boolean isLeft = false, isRight = false, isCenter = false;

    @Override
    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.dcMotor.get("front_Left");
        motorRight = hardwareMap.dcMotor.get("front_Right");
        motorLeft2 = hardwareMap.dcMotor.get("back_Left");
        motorRight2 = hardwareMap.dcMotor.get("back_Right");
        motorIntake = hardwareMap.dcMotor.get("Intake");
        motorHang = hardwareMap.dcMotor.get("Hanger");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorLeft2.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        autoMethods = new AutoMethods(motorLeft, motorLeft2, motorRight, motorRight2, motorIntake, motorHang);
        webcam = new Webcam(hardwareMap.get(WebcamName.class, "Webcam 1"), true);
        while(!opModeIsActive()){

            Double x = webcam.CheckCamera();
            if (x < 150){
                isLeft = true;
                isRight = false;
                isCenter = false;
            }
            else if (x > 450 || x == null){
                isLeft = false;
                isRight = true;
                isCenter = false;
            }
            else{
                isLeft = false;
                isRight = false;
                isCenter = true;
            }
            telemetry.addData("detected x", x);
            telemetry.update();
        }


        autoMethods.RunMotors(25,0.2);
        sleep(8000);
        autoMethods.ZeroMotors();
        autoMethods.StrafeByInch(4, true, 0.2);
        sleep(1000);
        autoMethods.ZeroMotors();
        motorIntake.setPower(-0.4);
        sleep(1500);
        motorIntake.setPower(0);
        autoMethods.StrafeByInch(72,true, 0.2);
        sleep(8000);
        autoMethods.ZeroMotors();
        autoMethods.Turn90(false, 0.2);
        autoMethods.RunMotorHang(6.5,0.75);
        sleep(3000);
        autoMethods.ZeroMotors();
        //autoMethods.StrafeByInch(2, false, 0.2);
        //sleep(1000);
        autoMethods.RunMotors(12, 0.2);
        sleep(2000);
        autoMethods.ZeroMotors();
        motorHang.setPower(0);
        autoMethods.RunMotorHang(-6.5,0.75);
        autoMethods.RunMotors(-2,0.2);
        sleep(1000);
        autoMethods.ZeroMotors();
        //autoMethods.StrafeByInch(24, false, 0.2);
        sleep(4000);
        motorHang.setPower(0);
    }
}