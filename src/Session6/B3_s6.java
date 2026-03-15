package Session6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class B3_s6 {

    public static class Ticket {

        private String ticketId;
        private String roomName;
        private boolean isSold;

        public Ticket(String ticketId, String roomName) {
            this.ticketId = ticketId;
            this.roomName = roomName;
            this.isSold = false;
        }

        public String getTicketId() {
            return ticketId;
        }

        public boolean isSold() {
            return isSold;
        }

        public void setSold(boolean sold) {
            isSold = sold;
        }
    }

    public static class TicketPool {

        private String roomName;
        private List<Ticket> tickets = new ArrayList<>();
        private int nextId = 1;

        public TicketPool(String roomName, int total) {
            this.roomName = roomName;

            for (int i = 0; i < total; i++) {
                String id = roomName + "-" + String.format("%03d", nextId++);
                tickets.add(new Ticket(id, roomName));
            }
        }

        public synchronized Ticket sellTicket() {

            while (true) {

                for (Ticket t : tickets) {
                    if (!t.isSold()) {
                        t.setSold(true);
                        return t;
                    }
                }

                try {
                    System.out.println(Thread.currentThread().getName()
                            + ": Hết vé phòng " + roomName + ", đang chờ...");
                    wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
        }

        public synchronized void addTickets(int count) {

            for (int i = 0; i < count; i++) {
                String id = roomName + "-" + String.format("%03d", nextId++);
                tickets.add(new Ticket(id, roomName));
            }

            System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);

            notifyAll();
        }
    }

    public static class BookingCounter implements Runnable {

        private String counterName;
        private TicketPool roomA;
        private TicketPool roomB;
        private int soldCount = 0;

        private Random random = new Random();

        public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
            this.counterName = counterName;
            this.roomA = roomA;
            this.roomB = roomB;
        }

        public int getSoldCount() {
            return soldCount;
        }

        @Override
        public void run() {

            while (true) {

                Ticket ticket;

                if (random.nextBoolean()) {
                    ticket = roomA.sellTicket();
                } else {
                    ticket = roomB.sellTicket();
                }

                if (ticket == null) {
                    break;
                }

                soldCount++;

                System.out.println(counterName + " bán vé " + ticket.getTicketId());

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public static class TicketSupplier implements Runnable {

        private TicketPool roomA;
        private TicketPool roomB;
        private int supplyCount;
        private int interval;
        private int rounds;

        public TicketSupplier(TicketPool roomA, TicketPool roomB, int supplyCount, int interval, int rounds) {
            this.roomA = roomA;
            this.roomB = roomB;
            this.supplyCount = supplyCount;
            this.interval = interval;
            this.rounds = rounds;
        }

        @Override
        public void run() {

            for (int i = 0; i < rounds; i++) {

                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    return;
                }

                roomA.addTickets(supplyCount);
                roomB.addTickets(supplyCount);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        TicketPool roomA = new TicketPool("A", 5);
        TicketPool roomB = new TicketPool("B", 5);

        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB);

        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 3, 3000, 3);

        Thread t1 = new Thread(counter1, "Quầy 1");
        Thread t2 = new Thread(counter2, "Quầy 2");
        Thread t3 = new Thread(supplier);

        t1.start();
        t2.start();
        t3.start();

        t3.join();

        Thread.sleep(5000);

        t1.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();

        System.out.println("\nKết thúc chương trình");

        System.out.println("Quầy 1 bán được: " + counter1.getSoldCount());
        System.out.println("Quầy 2 bán được: " + counter2.getSoldCount());
    }
}