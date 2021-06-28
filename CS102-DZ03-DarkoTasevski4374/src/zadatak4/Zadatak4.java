package zadatak4;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

// Napraviti JavaFX aplikaciju u kojoj se vrši animiranje objekta klase Rectangle.
// Treba obezbediti da se nad objektom mogu vršiti tri vrste animacija pomoću klasa PathTransition, FadeTransition i ScaleTransition.
// Biranje animacije koja će se izvršiti se obavlja pomoću klase CheckBox i moguće je vršiti više animacija istovremeno.
public class Zadatak4 extends Application {
    CheckBox path;
    CheckBox fade;
    CheckBox scale;
    PathTransition pathTransition;
    /**
     * EventHandler za PathTransition animaciju
     */
    private final EventHandler<ActionEvent> pathAnimHandler = e -> {
        if (path.isSelected()) {
            System.out.println("PathTransition play");
            this.pathTransition.play();
        } else {
            System.out.println("PathTransition stop");
            this.pathTransition.stop();
        }
    };
    FadeTransition fadeTransition;
    /**
     * EventHandler za FadeTransition animaciju
     */
    private final EventHandler<ActionEvent> fadeAnimHandler = e -> {
        if (fade.isSelected()) {
            System.out.println("FadeTransition play");
            this.fadeTransition.play();
        } else {
            System.out.println("FadeTransition stop");
            this.fadeTransition.stop();
            // Pri stopiranju animacije, vraćamo normalne vrednosti prozirnosti
            this.fadeTransition.getNode().setOpacity(1);
        }
    };
    ScaleTransition scaleTransition;
    /**
     * EventHandler za ScaleTransition animaciju
     */
    private final EventHandler<ActionEvent> scaleAnimHandler = e -> {
        if (scale.isSelected()) {
            System.out.println("ScaleTransition play");
            this.scaleTransition.play();
        } else {
            System.out.println("ScaleTransition stop");
            this.scaleTransition.stop();
            // Pri stopiranju animacije, vraćamo normalne vrednosti skaliranja
            this.fadeTransition.getNode().setScaleX(1);
            this.fadeTransition.getNode().setScaleY(1);
        }
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("zadatak4/layoutStyles.css");

        VBox mainContainer = createMainContainer();
        StackPane animationConatiner = createAnimationContainer();
        HBox checkboxContainer = createCheckboxContainer();

        mainContainer.getChildren().addAll(animationConatiner, checkboxContainer);

        root.getChildren().add(mainContainer);
        primaryStage.setTitle("Zadatak 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @return VBox - Kreira VBox okno koje će služiti kao glavni kontejner za ostala okna
     */
    private VBox createMainContainer() {
        VBox mainContainer = new VBox();
        mainContainer.getStyleClass().addAll("border", "vbox");
        return mainContainer;
    }

    /**
     * @return StackPane - Kreira StackPane okno koje će služiti za prikaz animacije i kaokontejner za Rectangle Shape
     * Ova metoda takodje inicializuje i konfiguriše Animation klase na njihove početne vrednosti.
     */
    private StackPane createAnimationContainer() {
        StackPane animationConatiner = new StackPane();
        animationConatiner.getStyleClass().addAll("border", "stackPane");
        animationConatiner.setPrefSize(400, 350);

        Rectangle rect = new Rectangle(0, 0, 80, 80);
        rect.setStroke(Color.BLACK);
        rect.setFill(Color.CORAL);
        animationConatiner.getChildren().add(rect);

        configureAnimations(rect);

        return animationConatiner;
    }

    /**
     * @return HBox - Kreira HBox okno koje će služiti kao kontejner za check box dugmad
     * Pored kreiranja checkbox-ova, ova metoda kači event listener-e na njih
     */
    private HBox createCheckboxContainer() {
        HBox checkboxContainer = new HBox();
        checkboxContainer.getStyleClass().addAll("border", "hbox");
        checkboxContainer.setPrefSize(400, 50);
        this.path = new CheckBox("PathTransition");
        this.fade = new CheckBox("FadeTransition");
        this.scale = new CheckBox("ScaleTransition");

        // Imamo posebne handlere za svaki checkbox da bi se izbegla kompleksna logika i granjanje potrebno za
        // handle-ovanje animacija samo jednim handlerom.
        this.path.setOnAction(pathAnimHandler);
        this.fade.setOnAction(fadeAnimHandler);
        this.scale.setOnAction(scaleAnimHandler);

        checkboxContainer.getChildren().addAll(this.path, this.fade, this.scale);

        return checkboxContainer;
    }

    /**
     * Inicijalizuje i konfiguriše animacije na početne vrednosti, i kreira putanju za PathTransition.
     *
     * @param node - Rectangle
     */
    private void configureAnimations(Shape node) {
        int ANIM_DURATION_MS = 4000;

        Line animationPath = new Line(-120, 40, 200, 40);
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(ANIM_DURATION_MS));
        pathTransition.setPath(animationPath);
        pathTransition.setNode(node);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);

        fadeTransition = new FadeTransition(Duration.millis(ANIM_DURATION_MS / 2.0), node);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);

        scaleTransition = new ScaleTransition(Duration.millis(ANIM_DURATION_MS), node);
        scaleTransition.setByX(1.3);
        scaleTransition.setByY(1.3);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.setAutoReverse(true);
    }
}
