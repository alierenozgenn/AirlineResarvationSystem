public class Reader implements Runnable {
    private final Database database;

    public Reader(Database database) {
        this.database = database;
    }

    @Override
    public void run() {
        System.out.println(database.viewReservations());
    }
}
