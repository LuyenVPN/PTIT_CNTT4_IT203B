package Session6;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class B1_s6 {
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

        public TicketPool(String roomName, int totalTickets) {
            this.roomName = roomName;

            for (int i = 1; i <= totalTickets; i++) {
                String id = roomName + "-" + String.format("%03d", i);
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

        public int remainingTickets() {
            int count = 0;
            for (Ticket t : tickets) {
                if (!t.isSold()) {
                    count++;
                }
            }
            return count;
        }

        public String getRoomName() {
            return roomName;
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

                if (roomA.remainingTickets() == 0 && roomB.remainingTickets() == 0) {
                    break;
                }

                Ticket ticket = null;

                if (random.nextBoolean()) {
                    ticket = roomA.sellTicket();
                    if (ticket == null) {
                        ticket = roomB.sellTicket();
                    }
                } else {
                    ticket = roomB.sellTicket();
                    if (ticket == null) {
                        ticket = roomA.sellTicket();
                    }
                }

                if (ticket != null) {
                    soldCount++;
                    System.out.println(counterName + " đã bán vé " + ticket.getTicketId());
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nKết thúc chương trình");

        System.out.println("Quầy 1 bán được: " + counter1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + counter2.getSoldCount() + " vé");

        System.out.println("Vé còn lại phòng A: " + roomA.remainingTickets());
        System.out.println("Vé còn lại phòng B: " + roomB.remainingTickets());
    }
}
