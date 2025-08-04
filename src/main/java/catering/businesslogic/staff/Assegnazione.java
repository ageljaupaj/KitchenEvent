package catering.businesslogic.staff;

public class Assegnazione {
    private Lavoratore lavoratore;
    private String nomeEvento;

    public Assegnazione(Lavoratore lavoratore, String nomeEvento) {
        this.lavoratore = lavoratore;
        this.nomeEvento = nomeEvento;
    }

    public Lavoratore getLavoratore() {
        return lavoratore;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    @Override
    public String toString() {
        return "Assegnazione: " + lavoratore.getNome() + " -> Evento: " + nomeEvento;
    }
}

