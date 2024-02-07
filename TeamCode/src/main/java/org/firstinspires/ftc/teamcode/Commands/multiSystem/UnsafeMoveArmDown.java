package org.firstinspires.ftc.teamcode.Commands.multiSystem;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.ArmPosition;
import org.firstinspires.ftc.teamcode.Commands.antiTurret.AntiTurretGetToPosition;
import org.firstinspires.ftc.teamcode.Commands.elbow.ElbowGetToPosition;
import org.firstinspires.ftc.teamcode.Commands.elevator.ElevatorGetToHeightPID;
import org.firstinspires.ftc.teamcode.Commands.extender.ExtenderSetPosition;
import org.firstinspires.ftc.teamcode.Commands.turret.RotateTurretByPID;
import org.firstinspires.ftc.teamcode.SubSystems.AntiTurret;
import org.firstinspires.ftc.teamcode.SubSystems.Elbow;
import org.firstinspires.ftc.teamcode.SubSystems.Elevator;
import org.firstinspires.ftc.teamcode.SubSystems.Extender;
import org.firstinspires.ftc.teamcode.SubSystems.Turret;

public class UnsafeMoveArmDown extends SequentialCommandGroup {
    public UnsafeMoveArmDown(Elevator elevator, Elbow elbow, Extender extender, Turret turret, AntiTurret antiTurret, ArmPosition position, boolean isLeftOfBoard) {
        super(
                new RotateTurretByPID(turret, position.getTurretAngle(isLeftOfBoard)),
                new WaitCommand(UnsafeMoveArm.ELEVATOR_WAIT_TIME), //avoiding elevator's shaking while going down too fast.
                new ElevatorGetToHeightPID(elevator, position.getElevatorHeight()),
                new ElbowGetToPosition(elbow, position.getElbowPosition()), /*These are instant commands so their isFinished always true */
                new WaitCommand(UnsafeMoveArm.EXTENDER_WAIT_TIME), //Trying to avoid elbow's servos overload
                new ExtenderSetPosition(extender, position.getExtenderPosition()),
                new AntiTurretGetToPosition(antiTurret, position.getAntiTurretPosition())
        );
    }
}
