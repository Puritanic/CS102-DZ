package zadatak4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Zadatak4 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Glavni okvir, koji će rasporediti Nodes vertikalno
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setStyle("-fx-background-color: #bba046;");
        // Okvir u sredini, koji će rasporediti Nodes u o obliku matrice
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #f0ead6;");
        // Podešavanje širine prve kolone, tako da zauzima 60% dostupnog prostora
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(60);
        gridPane.getColumnConstraints().add(column1);

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(5, 5, 5, 5));

        gridPane.add(createSimpleBoldLabel("Hello subPanel(): 12"), 0, 0);
        gridPane.add(createSimpleButton("remove me", 12), 1, 0);
        gridPane.add(createSimpleBoldLabel("Hello subPanel(): 13"), 0, 1);
        gridPane.add(createSimpleButton("remove me", 12), 1, 1);
        gridPane.add(createSimpleBoldLabel("Hello subPanel(): 15"), 0, 2);
        gridPane.add(createSimpleButton("remove me", 12), 1, 2);

        vBox.getChildren().addAll(createSimpleButton("Add subPanel", 14), gridPane, createSimpleButton("Remove All", 14));

        Scene scene = new Scene(vBox, 240, 160);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Kreira jednostavno dugme, sa prosledjenim teksto, boldovanim fontom i proizvoljnom veličinom fonta.
     * Takodje konfiguriše dugme da zauzme celokupnu dostupnu širinu i visinu parent Node-a
     *
     * @param label - tekst koji će biti prikazan u dugmetu
     * @param fontSize - veličina fonta teksta
     * @return Button
     */
    Button createSimpleButton(String label, int fontSize){
        Button btn = new Button(label);
        btn.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMaxHeight(Double.MAX_VALUE);
        return btn;
    }

    /**
     * Kreira jednostavni Label element sa prosledjenim tekstom i boldovanim fontom
     *
     * @param text Tekst koji će biti prikazan u Label elementu
     * @return Label
     */
    Label createSimpleBoldLabel(String text){
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        return label;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
