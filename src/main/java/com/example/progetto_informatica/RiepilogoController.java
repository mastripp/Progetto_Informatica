package com.example.progetto_informatica;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.util.List;

public class RiepilogoController {

    @FXML
    private Label labelPosti;

    @FXML
    private Label labelTotale;

    @FXML
    private Label labelErrore;

    @FXML
    private RadioButton radioContanti;

    @FXML
    private RadioButton radioCarta;

    @FXML
    private RadioButton radioBonifico;

    private List<String> posti;
    private double totale;

    private ToggleGroup gruppoPagamento = new ToggleGroup();

    @FXML
    public void initialize() {
        radioContanti.setToggleGroup(gruppoPagamento);
        radioCarta.setToggleGroup(gruppoPagamento);
        radioBonifico.setToggleGroup(gruppoPagamento);
    }

    public void impostaDati(List<String> posti) {
        this.posti = posti;
        this.totale = 0;

        StringBuilder sb = new StringBuilder();
        for (String posto : posti) {
            char fila = posto.charAt(0);
            double prezzo = (fila == 'M' || fila == 'N') ? 17.50 : 15.00;
            totale += prezzo;
            sb.append(posto).append("  →  ").append(prezzo).append("€\n");
        }

        labelPosti.setText(sb.toString());
        labelTotale.setText("Totale: " + totale + "€");
    }

    @FXML
    private void onPagaClick() {
        RadioButton selezionato = (RadioButton) gruppoPagamento.getSelectedToggle();

        if (selezionato == null) {
            labelErrore.setText("Seleziona un metodo di pagamento!");
            return;
        }

        String metodoPagamento = selezionato.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scontrino-view.fxml"));
            Scene scene = new Scene(loader.load());
            ScontrinoController controller = loader.getController();
            controller.impostaDati(posti, totale, metodoPagamento);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}