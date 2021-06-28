package zadatak4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

// Kreirati JavaFX aplikaciju koja na scenu postavlja 5 krugova, random poluprečnika.
// Na scenu postaviti handlere za klik miša.
// Na svaki klik miša treba sve krugove pomeriti na nove, random pozicije.
public class Zadatak4 extends Application {
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final String COLOR_EGGSHELL = "#f0ead6";
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: " + COLOR_EGGSHELL);
        pane.setMaxWidth(500);
        pane.setMaxHeight(500);
        ArrayList<Circle> circles = new ArrayList<>();
        generateCircles(circles, pane);
        pane.getChildren().addAll(circles);
        // sets up an mouse click event listener, and reconfigures the Pane circles when triggered
        pane.setOnMouseClicked(e -> {
            reconfigureCirclesProps(circles);
        });

        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Zadatak 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Configures the passed circle with needed properties and styles
     *
     * @param circle Circle
     * @return Circle
     */
    Circle configureCircle(Circle circle) {
        Random rand = new Random();
        int radius = rand.nextInt(120) + 10;
        int centerX = rand.nextInt(WINDOW_WIDTH - (radius * 2));
        int centerY = rand.nextInt(WINDOW_HEIGHT - (radius * 2));
        int strokeWidth = rand.nextInt(10) + 1;

        System.out.println("Radius: " + radius + ", centerX: " + centerX + ", centerY: " + centerY);

        circle.setRadius(radius);
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
        circle.setStrokeWidth(strokeWidth);
        circle.setStyle("-fx-stroke: " + generateRandomHexColor() + "; -fx-fill: " + generateRandomHexColor());
        return circle;
    }

    void generateCircles(ArrayList<Circle> list, Pane pane) {
        for (int i = 0; i < 5; i++) {
            Circle circle = new Circle();
            list.add(configureCircle(circle));
        }
    }

    void reconfigureCirclesProps(ArrayList<Circle> circles) {
        for (Circle circle : circles) {
            configureCircle(circle);
        }
    }

    /**
     * Generates random hex color
     *
     * @see "https://www.codespeedy.com/generate-random-hex-color-code-in-java/"
     * @return String - hex formatted color
     */
    String generateRandomHexColor() {
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        // format as hexadecimal string
        return String.format("#%06x", rand_num);
    }
}
