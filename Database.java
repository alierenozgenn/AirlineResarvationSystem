import java.util.HashMap;
import java.util.Map;

public class Database {
    private final Map<String, Boolean> seats;
    private final Map<String, String> reservations;
    private ReservationSystemGUI gui;

    public Database() {
        seats = new HashMap<>();
        reservations = new HashMap<>();
        // Initialize the seats
        for (int i = 1; i <= 10; i++) {
            seats.put("Seat" + i, false);
        }
    }

    public synchronized boolean reserveSeat(String seat, String customer) {
        if (seats.get(seat)) {
            System.out.println("Seat already reserved!");
            return false;
        } else {
            seats.put(seat, true);
            reservations.put(seat, customer);
            System.out.println(customer + " reserved " + seat);
            return true;
        }
    }

    public synchronized String viewReservations() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : reservations.entrySet()) {
            result.append(entry.getKey()).append(" reserved by ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    public void setGUI(ReservationSystemGUI gui) {
        this.gui = gui;
    }
}
