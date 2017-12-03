package luckyticket;

import java.util.ArrayList;

public class LuckyTicketImpl {

    public class LuckyNumber implements LuckyTicketInterface {
        private long number;
        private long luckyTicketsNum;
        private ArrayList<Long> list = new ArrayList<>();

        private boolean numberCheck(String number) {

            if (number.length() == 0) {
                System.out.println("The string must be of length 2+");
                return false;
            } else if (number.length() % 2 != 0) {
                System.out.println("The string must contain even number of digits");
                return false;
            }

            try {

                this.number = Long.parseLong(number);

            } catch (NumberFormatException ex) {
                System.out.println("Incorrect input string.");
                return false;
            }

            return true;

        }

        private long countSumm(long number, long sum) {

            if (number == 0) {
                return sum;
            }

            sum += number % 10;
            number /= 10;

            sum = countSumm(number, sum);

            return sum;
        }

        private void comparator(long number) {
            long sum = countSumm(number, 0);

            for (Long aList : list) {

                if (aList == sum) luckyTicketsNum++;

            }

        }

        @Override
        public boolean isLucky(String number) {

            boolean correctNum = numberCheck(number);

            if (!correctNum) {
                return false;
            }

            double power = number.length() / 2;

            long leftHalf = (long) (this.number / Math.pow(10D, power));
            long rightHalf = (long) (this.number % Math.pow(10D, power));

            return countSumm(leftHalf, 0) == countSumm(rightHalf, 0);

        }

        @Override
        public long countLucky(long min, long max) {
            luckyTicketsNum = 0;

            if (Long.toString(min).length() != 12) return 0L;

            if (min > max) return 0L;

            boolean minCheck = numberCheck(Long.toString(min));

            if (!minCheck) return 0L;

            long leftHalfMin = min / 1000000;
            long leftHalfMax = max / 1000000;
            long newSum;

            //count sums of left-side numbers
            while (leftHalfMin <= leftHalfMax) {

                newSum = countSumm(leftHalfMin, 0);

                list.add(newSum);

                leftHalfMin++;
            }

            long rightHalfMin = min % 1000000;
            long rightHalfMax = max % 1000000;

            //compare sums of the right-side numbers to the sums of the left-side numbers
            //while traversing the arrayList
            while (rightHalfMin <= rightHalfMax) {
                comparator(rightHalfMin);
                rightHalfMin++;
            }

            return luckyTicketsNum;
        }

        @Override
        public long countLucky(String min, String max) {

            if (min.length() != 2 || max.length() != 30) {
                System.out.println("Incorrect input range (should be from 2 to 30 digits)");
                return 0;
            }

            luckyTicketsNum = 0;
            Long start = Long.valueOf(min);
            Long end = Long.valueOf(max);
            boolean isLucky;

            while (start <= end) {
                String str = start.toString();

                if (str.length() % 2 != 0) {
                    start++;
                    continue;
                }

                isLucky = isLucky(str);

                if (isLucky) {
                    luckyTicketsNum++;
                }

                start++;
            }

            return luckyTicketsNum;
        }

    }

    public static void main(String[] args) {
        LuckyTicketImpl.LuckyNumber numTest = new LuckyTicketImpl().new LuckyNumber();

        String ticket = "2241";
        boolean isLucky = numTest.isLucky(ticket);
        System.out.println(ticket + " is lucky: " + isLucky);

        long numOfLNums = numTest.countLucky(123456654321L, 729496654321L);
        System.out.println("Found " + numOfLNums + " lucky numbers in the given range.");

        long numOfLNums2 = numTest.countLucky("10", "000000000000000000000000128765");
        System.out.println("Found " + numOfLNums2 + " lucky numbers in the given range.");
    }

}