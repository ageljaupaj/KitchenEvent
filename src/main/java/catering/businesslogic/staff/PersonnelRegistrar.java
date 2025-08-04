package catering.businesslogic.staff;

public class PersonnelRegistrar {
    private StaffManager staffManager;
    private EventoValutazioneManager valutazioneManager;

    public PersonnelRegistrar() {
        this.staffManager = new StaffManager();
        this.valutazioneManager = new EventoValutazioneManager();
    }


    public Lavoratore registraLavoratore(String nome) {
        return staffManager.registraLavoratore(nome);
    }

    public void assegnaLavoratoreEvento(Lavoratore lavoratore, String nomeEvento) {
        staffManager.assegnaLavoratoreEvento(lavoratore, nomeEvento);
    }

    public void richiediFerie(Lavoratore lavoratore, String motivo) {
        staffManager.richiediFerie(lavoratore, motivo);
    }

    public void approvaFerie(Lavoratore lavoratore) {
        staffManager.approvaFerie(lavoratore);
    }

    public void valutaLavoratore(Lavoratore lavoratore, String evento, int punteggio, String commento) {
        staffManager.valutaLavoratore(lavoratore, evento, punteggio, commento);
    }

    public void valutaEvento(String evento, int punteggio, String commento) {
        valutazioneManager.aggiungiValutazione(evento, punteggio, commento);
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }

    public EventoValutazioneManager getValutazioneManager() {
        return valutazioneManager;
    }
}
