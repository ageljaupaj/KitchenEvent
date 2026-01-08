package catering.businesslogic.staff;

public class ValutazioneLavoratore {
    private Lavoratore lavoratore;
    private String evento;
    private int punteggio;
    private String commento;

    public ValutazioneLavoratore(Lavoratore lavoratore, String evento, int punteggio, String commento) {
        this.lavoratore = lavoratore;
        this.evento = evento;
        this.punteggio = punteggio;
        this.commento = commento;
    }

    public Lavoratore getLavoratore() {
        return lavoratore;
    }

    public String getEvento() {
        return evento;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public String getCommento() {
        return commento;
    }

    @Override
    public String toString() {
        return "Valutazione Lavoratore: " + lavoratore.getNome() + 
               ", Evento: " + evento + 
               ", Punteggio: " + punteggio + 
               ", Commento: " + commento;
    }
}
