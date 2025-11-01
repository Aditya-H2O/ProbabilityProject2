package ProbabilityProject2;
import java.util.*; 
import java.util.Random;
import java.util.ArrayList; 
 
 class Main{

     public static int ticketCount = 0;
     public static int noTicketCount = 0;
     private static final Random random = new Random();

     public static void main(String[] args)
    {
        final int NumberOfWValues = 1000; 

        ArrayList<Double> WValues = new ArrayList<>();
        for (int i = 0; i < NumberOfWValues; i++)
        {
            WValues.add(call()); 
        }

        Collections.sort(WValues); 

        System.out.println("Average W Value: " + getMean(WValues)); 

        double median = (WValues.get(NumberOfWValues/2) + WValues.get((NumberOfWValues/2) - 1 )) / 2; 

        System.out.println("Median W Value: " + median);

        System.out.println("Probability of Ticket: " + (double)ticketCount/(ticketCount + noTicketCount));

        System.out.println("W Values: " + WValues.toString()); 

        

    }

    private static double getMean(ArrayList<Double> array)
    {
        double sum = 0.0;
        for (int i = 0; i < array.size(); i++)
        {
            sum += array.get(i); 
        }
        
        return sum/array.size(); 
    }


    public static double call()
    {
        
        boolean doneCalling = false; 

        //Represents seconds spent in call 
        double timeSpent = 0; 

        //Number of Intented Attempts N 
        int N = getIntendedAttempts(); 
        
        
        while(!doneCalling && N > 0)
        {
            //Dial and Wait to Get Connected (8 Seconds) 
            timeSpent += 8; 
            
            double probabilityAgent = random.nextDouble(); 
            
            //Connected
            if(probabilityAgent < 0.75)
            {
                timeSpent += getTimeWithAgent(); 
                timeSpent += 2; 
                doneCalling = true;
                ticketCount++;
                return timeSpent; 
            }
            
            //On Hold 
            else
            {
                probabilityAgent = random.nextDouble(); 
                //Find Agent 
                double exponential = generateExponential(0.01);
                if (exponential < 120)
                {
                    timeSpent += exponential;
                    timeSpent += getTimeWithAgent(); 
                    timeSpent += 2; 
                    doneCalling = true;
                    ticketCount++;
                    return timeSpent; 
                }
                //Don't Find Agent 
                else 
                {
                    timeSpent +=120; 
                    timeSpent+=2;
                    doneCalling = false; 
                    N--; 
                }
            }
            
        }        

        noTicketCount++;
        return timeSpent; 
    }
    
    


    public static int getIntendedAttempts()
    {
        Random random = new Random(); 
        
        double randomDouble = random.nextDouble(); 

        if (randomDouble < 0.4) 
            return 1; 
        if (randomDouble >= 0.4 && randomDouble<0.7)
            return 2; 
        if (randomDouble >= 0.7 && randomDouble<0.9)
            return 3; 
        if (randomDouble >= 0.9 && randomDouble<0.98)
            return 4;
        
        return 5; 
    }

    public static double generateExponential(double lambda) {
        Random random = new Random();
        double u = random.nextDouble(); 
        return -Math.log(1-u) / lambda;
    }

    public static double getTimeWithAgent() {
        Random random = new Random();
        double u = random.nextDouble(); 
        double c = 0.0731237647787; 
        
        double x = Math.pow(((u / (2*c)) +  Math.sqrt(10)), 2); 

        return x; 
    }    

    
}