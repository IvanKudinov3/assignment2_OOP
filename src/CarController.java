import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // TODO A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }

    /* Each step, the TimerListener moves all cars in the list and tells the
     * view to update its images. TODO Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                if (x >= 700 && car.getDirection() == Direction.EAST) {
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.getPosition().setLocation(700, car.getPosition().getY());
                    car.startEngine();
                }

                if (x <= 0 && car.getDirection() == Direction.WEST) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.getPosition().setLocation(0, car.getPosition().getY());
                    car.startEngine();
                }

                if (y <= 0 && car.getDirection() == Direction.SOUTH) {
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.getPosition().setLocation(car.getPosition().getX(),0);
                    car.startEngine();
                }

                if (y >= 500 && car.getDirection() == Direction.NORTH) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.getPosition().setLocation(car.getPosition().getX(), 500);
                    car.startEngine();
                }




                frame.drawPanel.repaint();
            }

        }
    }

    void gas(int amount) {
        // TODO adapt to your needs
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }
    void turnRight(){
        for (Car car : cars) {
            car.turnRight();
        }
    }
    void turnLeft() {
        for (Car car : cars) {
            car.turnLeft();
        }
    }

    void brake(double amount){
        double brake = (amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void stopAllCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    void startAllCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void turboOn() {
       for (Car  car : cars) {
           if (car instanceof Saab95 saab95) {
               saab95.setTurboOn();
           }
       }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95 saab95) {
                saab95.setTurboOff();
            }
        }
    }
}
