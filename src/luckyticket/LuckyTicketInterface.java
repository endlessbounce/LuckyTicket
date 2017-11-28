package luckyticket;

public interface LuckyTicketInterface {
    boolean isLucky(String number);
    long countLucky(long min, long max);
    long countLucky(String min, String max);
}

