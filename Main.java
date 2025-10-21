package ProbabilityProject2;
import java.util.Random;
import java.math.*;

 
 class Main{
    public static void main(String[] args)
    {
        System.out.println(call());
    }


    public static double call()
    {
        Random random = new Random(); 
        
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
        return -Math.log(u) / lambda;
    }

    public static double getTimeWithAgent() {
        Random random = new Random();
        double u = random.nextDouble(); 
        double c = 0.0731237647787; 
        
        double x = Math.pow(((u / (2c)) +  Math.sqrt(10)), 2); 

        return x; 
    }    

    
}