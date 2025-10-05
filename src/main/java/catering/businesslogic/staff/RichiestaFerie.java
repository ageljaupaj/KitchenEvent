package catering.businesslogic.staff;

import java.time.LocalDate;

public class RichiestaFerie {
    public enum Stato { INVIATA, APPROVATA, RIFIUTATA, REVOCATA }

    private final LocalDate dal;
    private final LocalDate al;
    private final String motivo;
    private Stato stato = Stato.INVIATA;

    public RichiestaFerie(LocalDate dal, LocalDate al, String motivo) {
        this.dal = dal;
        this.al = al;
        this.motivo = motivo == null ? "" : motivo;
    }

    public LocalDate getDal() { return dal; }
    public LocalDate getAl() { return al; }
    public String getMotivo() { return motivo; }
    public Stato getStato() { return stato; }
    public void setStato(Stato stato) { this.stato = stato; }
}

