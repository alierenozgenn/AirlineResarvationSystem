public class ReservationSystemGUI {
    private final Database db;

    public ReservationSystemGUI(Database db) {
        this.db = db;
        db.setGUI(this);
    }

    public void performOperations() {
        // Create writers for 13 customers
        Writer writer1 = new Writer(db, "Customer1", "Seat1");
        Writer[] writers = new Writer[12];
        for (int i = 0; i < 12; i++) {
            writers[i] = new Writer(db, "Customer" + (i + 2), "Seat1");
        }

        // Create reader
        Reader reader = new Reader(db);

        // Start threads
        Thread writerThread1 = new Thread(writer1);
        Thread[] writerThreads = new Thread[12];
        for (int i = 0; i < 12; i++) {
            writerThreads[i] = new Thread(writers[i]);
        }
        Thread readerThread = new Thread(reader);

        writerThread1.start();
        try {
            writerThread1.join(); // Ensure writer1 finishes before starting others
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            System.err.println("Writer thread 1 was interrupted");
        }
        readerThread.start();
        try {
            readerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            System.err.println("Reader thread was interrupted");
        }
        for (Thread writerThread : writerThreads) {
            writerThread.start();
        }
    }

    public static void main(String[] args) {
        Database db = new Database();
        ReservationSystemGUI gui = new ReservationSystemGUI(db);
        gui.performOperations();
    }
}
