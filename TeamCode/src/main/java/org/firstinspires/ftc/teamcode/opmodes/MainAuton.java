package org.firstinspires.ftc.teamcode.opmodes;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.*;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.List;

@Config
@Autonomous(group = "23925 Autonomous", preselectTeleOp = "MainTeleOp")
public class MainAuton extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;
    GamepadEx Gamepad1;
    public static boolean red = true;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize multiple telemetry outputs:
        myTelemetry = new MultipleTelemetry(telemetry);
        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        boolean right = true;
        while (!(Gamepad1.isDown(RIGHT_BUMPER) && Gamepad1.isDown(LEFT_BUMPER))) {
            if (Gamepad1.wasJustPressed(DPAD_RIGHT)) right = true;
            if (Gamepad1.wasJustPressed(DPAD_LEFT)) right = false;
            if (Gamepad1.wasJustPressed(B)) red = true;
            if (Gamepad1.wasJustPressed(X)) red = false;
            myTelemetry.addLine((red ? "Red" : "Blue") + "alliance");
            myTelemetry.addLine(right ? "Right" : "Left");
            myTelemetry.update();
        }

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule hub : hubs) hub.clearBulkCache();

            myTelemetry.update();
        }
    }
}
