package catering.businesslogic.staff;

import java.util.ArrayList;
import java.util.List;

public class EventoValutazioneManager {
    private List<ValutazioneEvento> valutazioniEvento;

    public EventoValutazioneManager() {
        this.valutazioniEvento = new ArrayList<>();
    }

    public void aggiungiValutazione(String nomeEvento, int punteggio, String commento) {
        ValutazioneEvento v = new ValutazioneEvento(nomeEvento, punteggio, commento);
        valutazioniEvento.add(v);
    }

    public List<ValutazioneEvento> getTutteLeValutazioni() {
        return valutazioniEvento;
    }

    public List<ValutazioneEvento> getValutazioniPerEvento(String nomeEvento) {
        List<ValutazioneEvento> filtrate = new ArrayList<>();
        for (ValutazioneEvento v : valutazioniEvento) {
            if (v.getNomeEvento().equalsIgnoreCase(nomeEvento)) {
                filtrate.add(v);
            }
        }
        return filtrate;
    }
}

