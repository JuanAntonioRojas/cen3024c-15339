
//  COP2805C Homework 7
//  Author: Tony Rojas
//  Version 1:   7/8/24   Just the timing calculation. No Threads
//  Version 2:   7/9/24   The required assignment with Threads: run(), extends Thread, and get methods.
//
//  Goal: to implement two versions of the Fibonacci sequence and use threads to determine how much faster the second method is.
//
//  Description:
//  The first solution uses recursion and is the simplest.
//  The second method uses dynamic programming to store the previous calculations rather than re-calculating them.
//  I created separate methods (recursive & dynamic) that I call from the main method.
//  Then, I calculate the differences in time (finish-start) for both, and finally print those results.
//



package cop2805;
import java.util.Scanner;




public class Main {
    public static void prtLn(String txt) {
        System.out.println(txt);
    }


    public static void main(String[] args)
    {
        //  Ask user the "nth" number limit (e.g.: 40).
        Scanner scan = new Scanner(System.in);
        prtLn("Please enter a limit for the Fibonacci sequence: ?");
        int n = scan.nextInt();



        //  RECURSIVE:

        //  Create a new recursive thread and start it:
        Recursive recursiveThread = new Recursive(n);
        recursiveThread.run();



        //  DYNAMIC:

        //  Create a new dynamic thread and start it:
        Dynamic dynamicThread = new Dynamic(n);
        dynamicThread.run();




        //  DISPLAY THE TIME IT TOOK TO COMPLETE:
        String answer = " Thread found the answer: ";

        prtLn("Dynamic"   + answer + Dynamic.getFiboNumber() + " in " + Dynamic.getTimeDiff() + "ms.");
        prtLn("Recursion" + answer + Recursive.getFiboNumber() + " in " + Recursive.getTimeDiff() + "ms.");
    }



    public static class Recursive extends Thread
    {
        private static int n, fiboNumber;
        private static long timeDiff;

    public Recursive(int n) { this.n = n; }
        public static int getFiboNumber() { return fiboNumber; }

        public static long getTimeDiff() {  return timeDiff;  }

        @Override
        public void run()
        {
            long startTimer = System.currentTimeMillis();   // Start the clock
            fiboNumber = fibonacci(n);                      // Call the recursive method
            long stopTimer = System.currentTimeMillis();    // Stop the clock
            timeDiff = stopTimer - startTimer;              // Calculate the difference in time
        }

        private int fibonacci(int n) {
            if (n == 0) return 0;
            else if (n == 1) return 1;
            else {
                return fibonacci(n - 1) + fibonacci(n - 2);
            }
        }
    }





    public static class Dynamic extends Thread
    {
        private static int n, fiboNumb;
        private static long timeDiff;

        public Dynamic(int n) { this.n = n; }
        public static int getFiboNumber() { return fiboNumb; }

        public static long getTimeDiff() { return timeDiff; }

        @Override
        public void run()
        {
            long startTimer = System.currentTimeMillis();   // Start the clock
            fiboNumb = fibonacci(n);                        // Call the recursive method
            long stopTimer = System.currentTimeMillis();    // Stop the clock
            timeDiff = stopTimer - startTimer;              // Calculate the difference in time
        }

        private int fibonacci(int n)
        {
            int v1 = 0, v2 = 1, v3 = 0;
            for(int i=2; i<=n; i++){
                v3 = v1 + v2;
                v1 = v2;
                v2 = v3;
            }
            return v3;
        }
    }
}

