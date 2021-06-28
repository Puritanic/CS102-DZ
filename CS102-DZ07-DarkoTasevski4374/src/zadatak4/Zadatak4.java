package zadatak4;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class Zadatak4 extends Application {
    private static Map<String, ArrayList<String>> kreirajPocetneListElemente() {
        String[] itemsArr = {"Item 4", "Item 1", "Item 2", "Item 5", "Item 3"};
        String[] placesArr = {"Belgrade", "Sofia", "Budapest", "Rome", "Zagreb"};
        String[] countriesArr = {"Serbia", "Croatia", "N. Macedonia", "Italy", "Hungary"};

        Map<String, ArrayList<String>> hashMap = new HashMap<>();

        ArrayList<String> itemsList = new ArrayList<>(Arrays.asList(itemsArr));
        hashMap.put("Items", itemsList);

        ArrayList<String> placesList = new ArrayList<>(Arrays.asList(placesArr));
        hashMap.put("Places", placesList);

        ArrayList<String> countriesList = new ArrayList<>(Arrays.asList(countriesArr));
        hashMap.put("Countries", countriesList);

        return hashMap;
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 430, 410);
        scene.getStylesheets().add("zadatak4/layoutStyles.css");

        Map<String, ArrayList<String>> hashMap = kreirajPocetneListElemente();

        Set<String> keys = hashMap.keySet();
        ObservableList<String> listItems = FXCollections.observableArrayList(keys);
        ListView<String> listView = new ListView<>(listItems);
        listView.setPrefSize(400, 200);

        VBox mainContainer = new VBox();
        mainContainer.getStyleClass().add("mainContainer");
        ScrollPane scrollPane = new ScrollPane(listView);
        HBox btnContainer = new HBox();
        btnContainer.getStyleClass().add("btnContainer");

        TextField keyEntry = new TextField();
        TextField valueEntry = new TextField();

        Button btnReset = new Button("Reset");
        btnReset.setPrefSize(190, 40);

        Button btnSubmit = new Button("Submit");
        btnSubmit.setPrefSize(190, 40);


        // https://stackoverflow.com/a/29641380/7453363
        // Onemogućimo submit dugme u slučaju ako su jedan ili oba TextField elementa bez teksta
        BooleanBinding booleanBind = keyEntry.textProperty().isEmpty()
                .or(valueEntry.textProperty().isEmpty());
        btnSubmit.disableProperty().bind(booleanBind);

        btnContainer.getChildren().addAll(btnReset, btnSubmit);

        listView.getSelectionModel().selectedItemProperty().addListener(e -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();

            if (selectedItem != null && hashMap.containsKey(selectedItem)) {
                ArrayList<String> val = hashMap.get(selectedItem);
                Collections.sort(val);
                System.out.println("VAL: " + val);
                keyEntry.setText(selectedItem);
            }
        });

        btnReset.setOnAction(e -> {
            keyEntry.setText("");
            valueEntry.setText("");
            listView.getSelectionModel().clearSelection();
        });

        btnSubmit.setOnAction(e -> {
            String keyValue = keyEntry.getText();
            String strValues = valueEntry.getText();
            // https://stackoverflow.com/a/41953571/7453363
            // Delimo string na mestima gde su zarezi, i pri tome brišemo sav okolni whitespace
            ArrayList<String> valuesList = new ArrayList<>(Arrays.asList(strValues.trim().split("\\s*,\\s*")));

            if (hashMap.containsKey(keyValue)) {
                ArrayList<String> keyValues = hashMap.get(keyValue);
                keyValues.addAll(valuesList);
            } else {
                listItems.add(keyValue);
                hashMap.put(keyValue, valuesList);
            }
            keyEntry.setText("");
            valueEntry.setText("");
            listView.getSelectionModel().clearSelection();
        });

        mainContainer.getChildren().addAll(
                scrollPane,
                new Label("Ključ: "),
                keyEntry,
                new Label("Vrednosti ključa (odvojene zapetama): "),
                valueEntry,
                btnContainer
        );
        root.getChildren().add(mainContainer);
        primaryStage.setTitle("Zadatak 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
