package luckyticket;

public class LuckyTicketImpl {
    
    public class LuckyNumber implements LuckyTicketInterface{
        private long nums[];
        private long luckyTicketsNum;

        private boolean numberCheck(String number){

            if(number.length() == 0){
                System.out.println("The string must be of length 2+");
                return false;
            }else if(number.length() % 2 != 0){
                System.out.println("The string must contain even number of digits");
                return false;
            }

            nums = new long[number.length()];

            try{    

                //convert the string to arr of integers
                for(int i = 0; i < nums.length; i++){

                    char temp = number.charAt(i);
                    nums[i] = (long)Integer.parseInt(Character.toString(temp));

                }

            }catch(NumberFormatException ex){
                System.out.println("Incorrect input string.");
                return false;
            }

            return true;

        }

        @Override
        public boolean isLucky(String number) {
            boolean correctNum = numberCheck(number);
            int firstNumsSum = 0;
            int lastNumsSum = 0;

            if(!correctNum){
                return false;
            }

            int i = 0;

            while(i < number.length()/2){
                firstNumsSum += nums[i];
                i++;
            }

            while(i >= number.length()/2 && i < number.length()){
                lastNumsSum += nums[i];
                i++;
            }

            if(firstNumsSum == lastNumsSum){
                return true;
            }else{
                return false;
            }

        }

        @Override
        public long countLucky(long min, long max) {
            Long num = min;

            if(num.toString().length() != 12){
                return 0L;
            }

            luckyTicketsNum = 0;

            for(long i = min; i <= max; i++){
                boolean isLucky = isLucky((num++).toString());

                if(isLucky){
                    luckyTicketsNum++;
                }
            }

            return luckyTicketsNum;
        }

        @Override
        public long countLucky(String min, String max) {
            
            if(min.length() != 2 || max.length() != 30){
                System.out.println("Incorrect input range (should be from 2 to 30 digits)");
                return 0;
            }
            
            luckyTicketsNum = 0;
            Long start = Long.valueOf(min);
            Long end = Long.valueOf(max);
            boolean isLucky = false;
            
            while(start <= end){
                String str = start.toString();
                
                if(str.length() % 2 != 0){
                    start++;
                    continue;
                }
                
                isLucky = isLucky(str);
                
                if(isLucky){
                    luckyTicketsNum++;
                    
                    if(luckyTicketsNum > Long.MAX_VALUE){
                        System.out.println("Incorrect input range (should include lucky numbers no more than Long.MAX_VALUE)");
                        return 0L;
                    }
                }
                
                start++;
            }
            
            return luckyTicketsNum;
        }

    }
    
    public static void main(String[] args) {
        LuckyTicketImpl.LuckyNumber numTest = new LuckyTicketImpl().new LuckyNumber();
        
        String ticket = "123601";
        boolean isLucky = numTest.isLucky(ticket);
        System.out.println(ticket + " is lucky: " + isLucky);
        
        long numOfLNums = numTest.countLucky(123456123456L, 123456123465L);
        System.out.println("Found " + numOfLNums + " lucky numbers in the given range.");
        
        long numOfLNums2 = numTest.countLucky("10", "000000000000000000000000128765");
        System.out.println("Found " + numOfLNums2 + " lucky numbers in the given range.");
    }   
    
}