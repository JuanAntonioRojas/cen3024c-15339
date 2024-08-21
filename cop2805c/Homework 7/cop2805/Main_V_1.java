
//  COP2805C Homework 7
//  Author: Tony Rojas
//  Version 1:   7/8/24   Just the required assignment
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




public class Main_V_1 {
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

        //  Get the system time before and after the function is called
        long startRecurs = (long) System.currentTimeMillis();   //  Start the clock
        int  nFiboRecurs = new Main_V_1().recursive(n);             //  call the running function
        long endRecursiv = (long) System.currentTimeMillis();   //  Stop the clock
        long totRecuTime = endRecursiv - startRecurs;           //  Calculate the difference in time


        //  DYNAMIC:

        //  Get the system time when this function is called
        //  It won't be called at the same time as previous, so we need a new "start the clock" command
        long startDynamc = (long) System.currentTimeMillis();
        long nFiboDynamc = new Main_V_1().dynamic(n);               //  call the running function
        long endDynamic  = (long) System.currentTimeMillis();   //  Stop the clock
        long totDynaTime = endDynamic - startDynamc;           //  Calculate the difference in time




        //  DISPLAY THE TIME IT TOOK TO COMPLETE:
        String answer = " Thread found the answer: ";

        prtLn("Dynamic"   + answer + nFiboDynamc + " in " + totDynaTime + "ms.");
        prtLn("Recursion" + answer + nFiboRecurs + " in " + totRecuTime + "ms.");
    }


    public int recursive(int n)
    {
        if(n == 0) return 0;
        else if(n == 1) return 1;
        else { return recursive(n-1) + recursive(n-2); }
    }


    public int dynamic(int n)
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