import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage saabImage;
    // To keep track of a single car's position
    ArrayList<Car> cars;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars) {
        this.cars = cars;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        for (Car car: cars){
            try {
                // You can remove the "pics" part if running outside of IntelliJ and
                // everything is in the same folder.
                // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

                if (car instanceof Volvo240)
                    volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
                else if (car instanceof Saab95)
                    saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }



    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Car car: cars){
            // position to where ot draw is being accessed directly from car class. It is changed already by the car controller, so additional moveit class is redandunt (I removed it).
            if (car instanceof Volvo240)
                g.drawImage(volvoImage, (int) car.getPosition().getX(), (int) car.getPosition().getY(), null); // see javadoc for more info on the parameters
            else if (car instanceof Saab95)
                g.drawImage(saabImage, (int) car.getPosition().getX(), (int) car.getPosition().getY(), null);
        }
    }
}
