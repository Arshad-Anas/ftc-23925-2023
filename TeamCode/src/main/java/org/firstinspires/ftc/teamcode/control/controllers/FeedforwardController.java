package org.firstinspires.ftc.teamcode.control.controllers;

import static org.firstinspires.ftc.teamcode.subsystems.centerstage.Robot.maxVoltage;

import org.firstinspires.ftc.teamcode.control.State;
import org.firstinspires.ftc.teamcode.control.gainmatrices.FeedforwardGains;

public class FeedforwardController implements Controller {

    private FeedforwardGains gains;

    private State target;

    public FeedforwardController() {
        this(new FeedforwardGains());
    }

    public FeedforwardController(FeedforwardGains gains) {
        setGains(gains);
    }

    public void setGains(FeedforwardGains gains) {
        this.gains = gains;
    }

    public double calculate(double voltage, double additionalOutput) {
        double baseOutput = (gains.kV * target.v) + (gains.kA * target.a);
        return (Math.signum(baseOutput + additionalOutput) * gains.kStatic + baseOutput) * (maxVoltage / voltage);
    }

    public double calculate(double voltage) {
        return calculate(voltage, 0.0);
    }

    public double calculate() {
        return calculate(maxVoltage);
    }

    /**
     * @param target The V and A attributes of the {@link State} parameter are used as velocity and acceleration references
     */
    public void setTarget(State target) {
        this.target = target;
    }
}
