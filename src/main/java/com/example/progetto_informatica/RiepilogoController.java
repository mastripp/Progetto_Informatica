package com.example.progetto_informatica;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.List;

public class RiepilogoController {

    @FXML
    private Label labelPosti;

    @FXML
    private Label labelTotale;

    public void impostaDati(List<String> posti) {
        double totale = 0;

        for (String posto : posti) {
            char fila = posto.charAt(0);
            if (fila == 'M' || fila == 'N') {
                totale += 17.50;
            } else {
                totale += 15.00;
            }
        }

        labelPosti.setText("Posti: " + String.join(", ", posti));
        labelTotale.setText("Totale: " + totale + "€");
    }

    @FXML
    private void onPagaClick() {
        System.out.println("Pagamento effettuato!");
    }
}