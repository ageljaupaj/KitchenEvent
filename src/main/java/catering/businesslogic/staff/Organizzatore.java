package catering.businesslogic.staff;

import catering.businesslogic.CatERing;
import catering.businesslogic.event.Event;

public class Organizzatore {
    private final String nome;

    public Organizzatore(String nome) {
        this.nome = (nome == null || nome.isBlank()) ? "Organizzatore" : nome;
    }
    public String getNome() { return nome; }

    public ValutazioneVO valutaLavoratoreSuEvento(Event e, Lavoratore l, int punteggio, String nota) {
        return CatERing.getInstance().getStaffManager().valutaPostEvento(this, e, l, punteggio, nota);
    }
}


