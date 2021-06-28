package zadatak4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Kreirati JavaFX aplikaciju koja koristi GridPane i ima 3 vrste i 1 kolonu.
// Kreirati 3 niti za bacanje kockice za jamb i prikazati svaku vrednost rezultata u labeli u okviru ćelija.
public class Zadatak4 extends Application {
    private static Text kreirajTextNode() {
        Text text = new Text();
        Font font = Font.font("Arial", FontWeight.BOLD, 72);
        text.setFont(font);
        return text;
    }

    private static GridPane kreirajGridPane(){
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("gridPane");
        gridPane.setPrefSize(190, 300);
        return gridPane;
    }

    private static VBox kreirajGlavniPane(){
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getStyleClass().add("mainContainer");
        return vbox;
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 220, 400);
        scene.getStylesheets().add("zadatak4/layoutStyles.css");

        VBox mainContainer = kreirajGlavniPane();
        GridPane gridPane = kreirajGridPane();
        Text roll1 = kreirajTextNode();
        gridPane.add(roll1, 0, 1);
        Text roll2 = kreirajTextNode();
        gridPane.add(roll2, 0, 2);
        Text roll3 = kreirajTextNode();
        gridPane.add(roll3, 0, 3);

        Button rollDicesBtn = new Button("Baci kockice");
        rollDicesBtn.setPrefSize(190, 40);

        rollDicesBtn.setOnAction(e -> {
            Kockica k1 = new Kockica("Kockica 1");
            Kockica k2 = new Kockica("Kockica 2");
            Kockica k3 = new Kockica("Kockica 3");
            // Kreira fiksni pul niti sa najviše tri niti
            ExecutorService executor = Executors.newFixedThreadPool(3);
            executor.execute(k1);
            executor.execute(k2);
            executor.execute(k3);
            executor.shutdown();

            // Čekamo da sve niti završe sa svojim zadacima. Isti rezultat možemo da dobijemo i sa k1.join()... ali tu ima dosta duplikacije koda
            try {
                // https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html
                if (!executor.awaitTermination(50, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println("Kockica 1: " + k1.getRoll());
            System.out.println("Kockica 2: " + k2.getRoll());
            System.out.println("Kockica 3: " + k3.getRoll());
            // Sa obrzirom da po zadatku možemo da imamo samo jendu kolonu sa tri reda,
            // modifikujemo postojeća tri elementa da prikažu nove vrednosti
            roll1.setText(String.valueOf(k1.getRoll()));
            roll2.setText(String.valueOf(k2.getRoll()));
            roll3.setText(String.valueOf(k3.getRoll()));
        });

        mainContainer.getChildren().addAll(gridPane, rollDicesBtn);

        root.getChildren().add(mainContainer);
        primaryStage.setTitle("Zadatak 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
