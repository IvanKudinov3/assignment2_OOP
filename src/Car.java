import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;

public abstract class Car implements Movable {

    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private Point2D.Double position;
    private Direction direction;


    // Constructor
    public Car(int nrDoors, double enginePower, Color color, String modelName, double x, double y) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        currentSpeed = 0;
        this.position = new Point2D.Double(x,y);
        direction = Direction.NORTH;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public String getModelName() {
        return modelName;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public Direction getDirection() {
        return direction;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public Point2D.Double getPosition(){ return position; }

    // However each car has its own speed factor
    public abstract double speedFactor();

    // Fixed
    private void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        if  (currentSpeed > enginePower) {
            currentSpeed = enginePower;
        }
    }
    // Fixed
    private void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
        if (currentSpeed < 0) {
            currentSpeed = 0;
        }
    }

    // Fixed
    public void gas(double amount) {
        if (amount >=0 && amount <=1) {
            incrementSpeed(amount);
        }
    }

    // Fixed
    public void brake(double amount) {
        if (amount >=0 && amount <=1) {
            decrementSpeed(amount);
        }
    }

    public void DoUTurn(){
        if (direction == Direction.NORTH) {
            direction = Direction.SOUTH;
        }   else if (direction == Direction.WEST)  {
            direction = Direction.EAST;
        }   else if (direction == Direction.SOUTH) {
            direction = Direction.NORTH;
        }   else if (direction == Direction.EAST) {
            direction = Direction.WEST;
        }
    }

    public void CheckForWall() {
        if (getPosition().getX() == 700 || getPosition().getX() > 700) {
            DoUTurn();
        } else if (getPosition().getX() < 0) {
            DoUTurn();
        } else if (getPosition().getY() == 500 || getPosition().getY() > 500 ) {
            DoUTurn();
        } else if (getPosition().getY() < 0) {
            DoUTurn();

        }
    }

    @Override
    public void move() {
        if (direction == Direction.NORTH) {
            position.y += currentSpeed;
        }  else if (direction == Direction.EAST) {
            position.x += currentSpeed;
        }  else if (direction == Direction.SOUTH) {
            position.y -= currentSpeed;
        }  else if (direction == Direction.WEST) {
            position.x -= currentSpeed;
        }
    }

    @Override
    public void turnLeft() {
        if (direction == Direction.NORTH) {
            direction = Direction.WEST;
        }   else if (direction == Direction.WEST)  {
            direction = Direction.SOUTH;
        }   else if (direction == Direction.SOUTH) {
            direction = Direction.EAST;
        }   else if (direction == Direction.EAST) {
            direction = Direction.NORTH;
        }
    }

    @Override
    public void turnRight() {
        if (direction == Direction.NORTH) {
            direction = Direction.EAST;
        }   else if (direction == Direction.EAST)  {
            direction = Direction.SOUTH;
        }   else if (direction == Direction.SOUTH) {
            direction = Direction.WEST;
        }   else if (direction == Direction.WEST) {
            direction = Direction.NORTH;
        }
    }
}