import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    BufferedImage volvoImage;

    ArrayList<Car> cars;
    Map<String, BufferedImage> carImages = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars) {
        this.cars = cars;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            carImages.put("Volvo240", volvoImage);

            BufferedImage saabImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            carImages.put("Saab95", saabImage);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Car car : cars) {
            int x = (int) Math.round(car.getPosition().getX());
            int y = (int) Math.round(car.getPosition().getY());
            BufferedImage image = carImages.get(car.getModelName());
            g.drawImage(image, x, y, null);
        }
    }
}
