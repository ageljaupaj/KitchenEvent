package catering.businesslogic.staff;

public class PerformancePolicy implements CareerPolicy {
    @Override
    public String calcolaPromozione(Lavoratore l) {
        return "Promosso per performance";
    }
}
