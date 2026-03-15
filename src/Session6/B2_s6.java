package Session6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class B2_s6 {

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

        public String getRoomName() {
            return roomName;
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

        public TicketPool(String roomName, int totalTickets) {

            this.roomName = roomName;

            for (int i = 1; i <= totalTickets; i++) {
                String id = roomName + "-" + String.format("%03d", nextId++);
                tickets.add(new Ticket(id, roomName));
            }
        }

        public synchronized Ticket sellTicket() {

            for (Ticket t : tickets) {
                if (!t.isSold()) {
                    t.setSold(true);
                    return t;
                }
            }
            return null;
        }

        public synchronized void addTickets(int count) {

            for (int i = 0; i < count; i++) {
                String id = roomName + "-" + String.format("%03d", nextId++);
                tickets.add(new Ticket(id, roomName));
            }

            System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
        }

        public int remainingTickets() {

            int count = 0;

            for (Ticket t : tickets) {
                if (!t.isSold()) {
                    count++;
                }
            }

            return count;
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

                Ticket ticket = null;

                if (random.nextBoolean()) {
                    ticket = roomA.sellTicket();
                } else {
                    ticket = roomB.sellTicket();
                }

                if (ticket != null) {

                    soldCount++;

                    System.out.println(counterName + " đã bán vé " + ticket.getTicketId());
                }

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

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB);

        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 3, 3000, 3);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);
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

        System.out.println("Vé còn lại phòng A: " + roomA.remainingTickets());
        System.out.println("Vé còn lại phòng B: " + roomB.remainingTickets());
    }
}