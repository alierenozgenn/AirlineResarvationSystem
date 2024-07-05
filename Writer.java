public class Writer implements Runnable {
    private final Database database;
    private final String customer;
    private final String seat;

    public Writer(Database database, String customer, String seat) {
        this.database = database;
        this.customer = customer;
        this.seat = seat;
    }

    @Override
    public void run() {
        database.reserveSeat(seat, customer);
    }
}
