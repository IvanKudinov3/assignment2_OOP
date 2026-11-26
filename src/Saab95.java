import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public Saab95() {
        super(2, 125, Color.red, "Saab95", 200, 0);
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
        IO.println("Turbo is turned on for " + this.getClass());
    }

    public void setTurboOff() {
        turboOn = false;
        IO.println("Turbo is turned off for " + this.getClass());
    }

    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

}