package catering.businesslogic.staff;

public class ValutazioneEvento {
    private String nomeEvento;
    private int punteggio;
    private String commento;

    public ValutazioneEvento(String nomeEvento, int punteggio, String commento) {
        this.nomeEvento = nomeEvento;
        this.punteggio = punteggio;
        this.commento = commento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public String getCommento() {
        return commento;
    }

    @Override
    public String toString() {
        return "Valutazione Evento: " + nomeEvento + 
               ", Punteggio: " + punteggio + 
               ", Commento: " + commento;
    }
}
