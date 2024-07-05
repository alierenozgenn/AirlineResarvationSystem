public class Main {
    public static void main(String[] args) {
        Database database = new Database();

        // Create writers for 13 customers
        Writer writer1 = new Writer(database, "Customer1", "Seat1");
        Writer[] writers = new Writer[12];
        for (int i = 0; i < 12; i++) {
            writers[i] = new Writer(database, "Customer" + (i + 2), "Seat1");
        }

        // Create reader
        Reader reader1 = new Reader(database);

        // Start threads
        Thread writerThread1 = new Thread(writer1);
        Thread[] writerThreads = new Thread[12];
        for (int i = 0; i < 12; i++) {
            writerThreads[i] = new Thread(writers[i]);
        }
        Thread readerThread1 = new Thread(reader1);

        writerThread1.start();
        try {
            writerThread1.join(); // Ensure writer1 finishes before starting others
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readerThread1.start();
        try {
            readerThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Thread writerThread : writerThreads) {
            writerThread.start();
        }
    }
}
