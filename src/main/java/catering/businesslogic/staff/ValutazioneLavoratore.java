package catering.businesslogic.staff;

public class ValutazioneLavoratore {
    private Lavoratore lavoratore;
    private String nomeEvento;
    private int punteggio; 
    private String commento;

    public ValutazioneLavoratore(Lavoratore lavoratore, String nomeEvento, int punteggio, String commento) {
        this.lavoratore = lavoratore;
        this.nomeEvento = nomeEvento;
        this.punteggio = punteggio;
        this.commento = commento;
    }

    public Lavoratore getLavoratore() {
        return lavoratore;
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
        return "Valutazione per " + lavoratore.getNome() + " all'evento " + nomeEvento +
               ": " + punteggio + "/5 - " + commento;
    }
}
