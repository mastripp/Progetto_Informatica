package com.example.progetto_informatica;

import java.util.HashMap;
import java.util.Map;

public class GestorePosti {

    private static GestorePosti istanza;
    private Map<String, String> statoPosti = new HashMap<>();

    private GestorePosti() {}

    public static synchronized GestorePosti getInstance() {
        if (istanza == null) {
            istanza = new GestorePosti();
        }
        return istanza;
    }

    public synchronized String getStato(String posto) {
        return statoPosti.getOrDefault(posto, "libero");
    }

    public synchronized boolean seleziona(String posto) {
        if (getStato(posto).equals("libero")) {
            statoPosti.put(posto, "selezionato");
            return true;
        }
        return false;
    }

    public synchronized void compra(String posto) {
        statoPosti.put(posto, "comprato");
    }

    public synchronized void libera(String posto) {
        statoPosti.put(posto, "libero");
    }
}