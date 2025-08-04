package catering.persistence;

import java.util.ArrayList;
import java.util.List;

import catering.businesslogic.staff.Lavoratore;

public class StaffPersistence {
    private List<Lavoratore> lavoratoriSalvati;

    public StaffPersistence() {
        lavoratoriSalvati = new ArrayList<>();
    }

    public void salvaLavoratore(Lavoratore lavoratore) {
        lavoratoriSalvati.add(lavoratore);
        System.out.println("Lavoratore salvato: " + lavoratore.getNome());
    }

    public List<Lavoratore> caricaTuttiLavoratori() {
        return lavoratoriSalvati;
    }

    public void stampaTutti() {
        System.out.println("Lavoratori salvati:");
        for (Lavoratore l : lavoratoriSalvati) {
            System.out.println("- " + l);
        }
    }
}
