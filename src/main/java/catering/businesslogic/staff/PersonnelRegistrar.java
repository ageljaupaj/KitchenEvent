package catering.businesslogic.staff;

public class PersonnelRegistrar {
    private StaffManager staffManager;

    public PersonnelRegistrar() {
        this.staffManager = new StaffManager();
    }

    public Lavoratore registraLavoratore(String nome) {
        return staffManager.registraLavoratore(nome);
    }

    public void assegnaLavoratoreEvento(Lavoratore lavoratore, String evento) {
        staffManager.assegnaLavoratoreEvento(lavoratore, evento);
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

    public StaffManager getStaffManager() {
        return staffManager;
    }
}
