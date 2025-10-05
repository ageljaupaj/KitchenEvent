package catering.businesslogic.staff;

public interface StaffEventListener {
    void onEvent(StaffEventPublisher.StaffEvent e);
}

