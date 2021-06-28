import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Kreirati JavaFX aplikaciju koja sa stranice
 * https://www.gigatron.rs/laptop_racunari/hp_pavilion_gaming__15ec0024nm__8pk23ea-299138
 * učitava detalje proizvoda. Tokom trajanja preuzimanja, aplikacija treba da prikazuje progressbar
 * koji se update-uje kako se elementi proizvoda preuzimaju.
 * Koristiti posebne niti za preuzimanje i update progress bara.
 */
public class Zadatak4 extends Application {
    Stage window;
    private static final String productUrl = "https://www.gigatron.rs/laptop_racunari/hp_pavilion_gaming__15ec0024nm__8pk23ea-299138";

    /**
     * Kreira glavni Pane JavaFX aplikacije
     * @return VBox
     */
    private static VBox kreirajGlavniPane(){
        VBox vbox = new VBox();
        vbox.getStyleClass().add("mainContainer");
        vbox.setPadding(new Insets(10,20,10,20));
        vbox.setSpacing(10);
        return vbox;
    }

    /**
     * Kreira jednostavni Text node sa podešenim fontom, veličinom i bojom fonta
     * @return Text
     */
    private static javafx.scene.text.Text kreirajTextNode() {
        javafx.scene.text.Text text = new Text();
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        text.setFont(font);
        text.setFill(Color.ALICEBLUE);
        return text;
    }

    /**
     * Kreira download Task, koji učitava web stranicu, nalazi potrebne elemente i vraća mapu sa stringovima
     * potrebnim za kreiranje liste specifikacije.
     * @return Task<Map<String, String>> download task
     */
    private static Task<Map<String, String>> runDownloadTask(){
        return new Task<Map<String, String>>() {
            @Override
            protected Map<String, String> call() throws Exception {
                int MAX = 100;
                // Koristimo LinkedHashMap zato što je bitan redosled elemenata
                Map<String, String> specs = new LinkedHashMap<>();

                try {
                    updateProgress(0, MAX);
                    Thread.sleep(200);
                    Document doc = Jsoup.connect(productUrl).get();
                    updateProgress(25, MAX);
                    Thread.sleep(200);

                    Elements name = doc.select(".product-title > h1");
                    Elements summary = doc.select(".sumTopWrap > .summary > li");

                    specs.put("Product", name.text());

                    updateProgress(50, MAX);
                    Thread.sleep(200);

                    for (Element e : summary) {
                        String text = e.text();
                        String[] splitText = text.split(":");
                        specs.put(splitText[0], splitText[1]);
                    }
                    updateProgress(75, MAX);
                    Thread.sleep(200);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                updateProgress(MAX, MAX);
                return specs;
            }
        };
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        VBox mainContainer = kreirajGlavniPane();

        Task<Map<String, String>> task = runDownloadTask();

        task.setOnSucceeded(t -> {
            System.out.println("Task completed!");
            Map<String, String> taskValue = task.getValue();

            for (Map.Entry<String, String> entry : taskValue.entrySet()) {
                System.out.println("Key : " + entry.getKey() + "   Value : " + entry.getValue());
                Text spec = kreirajTextNode();
                spec.setText(entry.getKey() + ": " + entry.getValue());
                mainContainer.getChildren().add(spec);
            }
        });

        ProgressBar pb = new ProgressBar();
        pb.setMinSize(460, 40);
        // Povezujemo vrednost progress bar statusa sa progress-om task-a
        pb.progressProperty().bind(task.progressProperty());
        pb.getStyleClass().add("progressBar");

        HBox hbProgress = new HBox(15, pb);
        hbProgress.setAlignment(Pos.CENTER);

        mainContainer.getChildren().add(hbProgress);
        Scene scene = new Scene(mainContainer, 500, 260);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        window.setScene(scene);
        window.show();

        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
