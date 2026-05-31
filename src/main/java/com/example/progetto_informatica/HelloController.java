package com.example.progetto_informatica;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Label labelPostiSelezionati;

    private List<String> postiSelezionati = new ArrayList<>();

    @FXML
    private void onPostoClick(javafx.event.ActionEvent event) {
        Button btn = (Button) event.getSource();
        String posto = btn.getText();

        if (postiSelezionati.contains(posto)) {
            postiSelezionati.remove(posto);
            btn.setStyle("-fx-background-color: #d3d3d3;");
        } else {
            postiSelezionati.add(posto);
            btn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white;");
        }

        if (postiSelezionati.isEmpty()) {
            labelPostiSelezionati.setText("Posti selezionati: nessuno");
        } else {
            labelPostiSelezionati.setText("Posti selezionati: " + String.join(", ", postiSelezionati));
        }
    }

    @FXML
    private void onConfermaClick() {
        if (postiSelezionati.isEmpty()) {
            labelPostiSelezionati.setText("Seleziona almeno un posto!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("riepilogo-view.fxml"));
            Scene scene = new Scene(loader.load());
            RiepilogoController controller = loader.getController();
            controller.impostaDati(postiSelezionati);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}