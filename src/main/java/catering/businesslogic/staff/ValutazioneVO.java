package catering.businesslogic.staff;

import catering.businesslogic.event.Event;

import java.time.LocalDate;
import java.util.Objects;

public final class ValutazioneVO {
    private final Event evento;
    private final Organizzatore autore;
    private final int punteggio;
    private final String nota;
    private final LocalDate data;

    public ValutazioneVO(Event evento, Organizzatore autore, int punteggio, String nota, LocalDate data) {
        this.evento = Objects.requireNonNull(evento);
        this.autore = Objects.requireNonNull(autore);
        this.punteggio = punteggio;
        this.nota = nota == null ? "" : nota;
        this.data = (data == null ? LocalDate.now() : data);
    }

    public Event getEvento() { return evento; }
    public Organizzatore getAutore() { return autore; }
    public int getPunteggio() { return punteggio; }
    public String getNota() { return nota; }
    public LocalDate getData() { return data; }
}

