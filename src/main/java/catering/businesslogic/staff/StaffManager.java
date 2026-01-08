package catering.businesslogic.staff;

import catering.businesslogic.event.Event;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffManager {
    private List<Lavoratore> lavoratori;
    private List<Assegnazione> assegnazioni;
    private List<ValutazioneEvento> valutazioniEvento;
    private List<ValutazioneLavoratore> valutazioniLavoratori;

    public StaffManager() {
        lavoratori = new ArrayList<>();
        assegnazioni = new ArrayList<>();
        valutazioniEvento = new ArrayList<>();
        valutazioniLavoratori = new ArrayList<>();
    }

    public Lavoratore registraLavoratore(String nome) {
        Lavoratore l = new Lavoratore(nome);
        lavoratori.add(l);
        return l;
    }

    public void assegnaLavoratoreEvento(Lavoratore lavoratore, String evento) {
        Assegnazione a = new Assegnazione(lavoratore, evento);
        assegnazioni.add(a);
    }

    public void richiediFerie(Lavoratore lavoratore, String motivo) {
        lavoratore.setFerieRichieste(true);
        lavoratore.setMotivoFerie(motivo);
        System.out.println("Ferie richieste da " + lavoratore.getNome() + ": " + motivo);
    }

    public void approvaFerie(Lavoratore lavoratore) {
        if (lavoratore.isFerieRichieste()) {
            lavoratore.setFerieApprovate(true);
            System.out.println("Ferie approvate per " + lavoratore.getNome());
        }
    }

    public void valutaLavoratore(Lavoratore lavoratore, String evento, int punteggio, String commento) {
        ValutazioneLavoratore v = new ValutazioneLavoratore(lavoratore, evento, punteggio, commento);
        valutazioniLavoratori.add(v);
    }

    public void valutaEvento(String evento, int punteggio, String commento) {
        ValutazioneEvento v = new ValutazioneEvento(evento, punteggio, commento);
        valutazioniEvento.add(v);
    }

    public ValutazioneVO valutaPostEvento(Organizzatore autore, Event e, Lavoratore l, int punteggio, String nota) {
        ValutazioneVO v = new ValutazioneVO(e, autore, punteggio, nota, LocalDate.now());
        this.valutaLavoratore(l, e.getName(), punteggio, nota);
        return v;
    }

    // Metodi getter per test
    public List<Lavoratore> getLavoratori() {
        return lavoratori;
    }

    public List<Assegnazione> getAssegnazioni() {
        return assegnazioni;
    }

    public List<ValutazioneEvento> getValutazioniEvento() {
        return valutazioniEvento;
    }

    public List<ValutazioneLavoratore> getValutazioniLavoratori() {
        return valutazioniLavoratori;
    }
}