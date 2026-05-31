package com.example.progetto_informatica;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.List;

public class ScontrinoController {

    @FXML
    private Label labelPosti;

    @FXML
    private Label labelTotale;

    @FXML
    private Label labelPagamento;

    public void impostaDati(List<String> posti, double totale, String pagamento) {
        StringBuilder sb = new StringBuilder();
        for (String posto : posti) {
            char fila = posto.charAt(0);
            double prezzo = (fila == 'M' || fila == 'N') ? 17.50 : 15.00;
            sb.append(posto).append("  →  ").append(prezzo).append("€\n");
        }
        labelPosti.setText(sb.toString());
        labelTotale.setText("Totale: " + totale + "€");
        labelPagamento.setText("Pagamento: " + pagamento);
    }
}