package catering.businesslogic.staff;

public class Lavoratore {
    private String nome;
    private boolean ferieRichieste;
    private boolean ferieApprovate;
    private String motivoFerie;

    public Lavoratore(String nome) {
        this.nome = nome;
        this.ferieRichieste = false;
        this.ferieApprovate = false;
        this.motivoFerie = "";
    }

    public String getNome() {
        return nome;
    }

    public boolean isFerieRichieste() {
        return ferieRichieste;
    }

    public void setFerieRichieste(boolean ferieRichieste) {
        this.ferieRichieste = ferieRichieste;
    }

    public boolean isFerieApprovate() {
        return ferieApprovate;
    }

    public void setFerieApprovate(boolean ferieApprovate) {
        this.ferieApprovate = ferieApprovate;
    }

    public String getMotivoFerie() {
        return motivoFerie;
    }

    public void setMotivoFerie(String motivoFerie) {
        this.motivoFerie = motivoFerie;
    }

    @Override
    public String toString() {
        return "Lavoratore: " + nome + 
               ", Ferie richieste: " + ferieRichieste + 
               ", Ferie approvate: " + ferieApprovate + 
               ", Motivo ferie: " + motivoFerie;
    }
}
