package catering.businesslogic.staff;

public class SeniorityPolicy implements CareerPolicy {
    @Override
    public String calcolaPromozione(Lavoratore l) {
        return "Promosso per anzianità";
    }
}
