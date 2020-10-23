package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "MecanumDemo", group = "Iterative Opmode")
//@Disabled
public class MecanumDemo extends OpMode
{

	DcMotor leftFrontMotor;
	DcMotor leftBackMotor;
	DcMotor rightFrontMotor;
	DcMotor rightBackMotor;

//	double power = 0.5;
	// private DcMotor leftDrive = null;
	// private DcMotor rightDrive = null;
	// Declare OpMode members.
//	private ElapsedTime runtime = new ElapsedTime();

	@Override
	public void init()
	{
		//Motors have been classified here
		leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
		leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
		rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
		rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");

		leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
		leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
		rightBackMotor.setDirection(DcMotor.Direction.FORWARD);
		rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);

		telemetry.addData("Status", "Initialized");
		telemetry.update();
//		runtime.reset();

	}

	@Override
	public void loop()
	{
//		double leftFrontPower = 0;
//		double leftBackPower = 0;
//		double rightFrontPower = 0;
//		double rightBackPower = 0;

		//Robot will continue driving forward for the duration of 3000 ms
//		sleep(3000);

		double horizontal = gamepad1.left_stick_x;
		double vertical = -gamepad1.left_stick_y;
		double twist = gamepad1.right_trigger - gamepad1.left_trigger;

		// You may need to multiply some of these by -1 to invert direction of
		// the motor.  This is not an issue with the calculations themselves.
		double[] speeds = {
				(vertical + horizontal + twist),
				(vertical + horizontal - twist),
				(vertical - horizontal + twist),
				(vertical - horizontal - twist),
		};

		// Because we are adding vectors and motors only take values between
		// [-1,1] we may need to normalize them.

		// Loop through all values in the speeds[] array and find the greatest
		// *magnitude*.  Not the greatest velocity.
		double max = Math.abs(speeds[0]);
		for(int i = 0; i < speeds.length; i++) {
			if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
		}

		// If and only if the maximum is outside of the range we want it to be,
		// normalize all the other speeds based on the given speed value.
		if (max > 1) {
			for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
		}


//		power = 0.0;

		//This reassigns the power
//		leftDrive.setPower(power);
//		rightDrive.setPower(power);

		leftFrontMotor.setPower(speeds[0]);
		leftBackMotor.setPower(speeds[2]);
		rightBackMotor.setPower(speeds[1]);
		rightFrontMotor.setPower(speeds[3]);
	}
}
