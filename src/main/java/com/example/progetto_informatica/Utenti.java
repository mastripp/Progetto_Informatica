package com.example.progetto_informatica;

import javafx.application.Platform;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utenti extends Thread {

    private List<Button> bottoni;
    private Random random = new Random();
    private GestorePosti gestore = GestorePosti.getInstance();

    private String[] file = {"A","B","C","D","E","F","G","H","I","L","M","N"};

    public Utenti(List<Button> bottoni) {
        this.bottoni = bottoni;
        setDaemon(true);
    }

    private Button trovaBottone(String nome) {
        for (Button btn : bottoni) {
            if (btn.getText().equals(nome)) {
                return btn;
            }
        }
        return null;
    }

    private int indiceFila(String fila) {
        for (int i = 0; i < file.length; i++) {
            if (file[i].equals(fila)) return i;
        }
        return -1;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);

                int quantita = random.nextInt(5) + 1;
                int colonnaStart = random.nextInt(20) + 1;
                int filaStart = random.nextInt(file.length);

                List<Button> daSelezionare = new ArrayList<>();
                int fila = filaStart;
                int colonna = colonnaStart;

                while (daSelezionare.size() < quantita && fila < file.length) {
                    if (colonna > 20) {
                        fila++;
                        if (fila >= file.length) break;
                    }

                    String nomePosto = file[fila] + colonna;
                    Button btn = trovaBottone(nomePosto);

                    if (btn != null && gestore.getStato(nomePosto).equals("libero")) {
                        daSelezionare.add(btn);
                    }

                    colonna++;
                }

                if (daSelezionare.isEmpty()) continue;

                for (Button btn : daSelezionare) {
                    gestore.seleziona(btn.getText());
                    Platform.runLater(() -> btn.setStyle("-fx-background-color: yellow;"));
                }

                Thread.sleep(2000);

                for (Button btn : daSelezionare) {
                    gestore.compra(btn.getText());
                    Platform.runLater(() -> btn.setStyle("-fx-background-color: red; -fx-text-fill: white;"));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}