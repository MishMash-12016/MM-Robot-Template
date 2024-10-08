package org.firstinspires.ftc.teamcode.Libraries.MMLib.Examples.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Libraries.MMLib.Examples.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.MMRobot;

public class ShootByPower extends CommandBase {

    Shooter shooter = MMRobot.getInstance().mmSystems.shooter;
    double power;
    public ShootByPower(double power){
        this.power = power;
        this.addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setPower(0.);
    }
}
