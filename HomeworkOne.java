// Jose Franco Baquera
// Homework #1; Question 6 a)
// CS 372 - Data Structures and Algorithm Design
// February 2, 2018
// Program Name: HomeworkOne.java
// Purpose of the program: The purpose 
// of the program is to have two functions
// that will calculate the Fibonacci result
// given any integer. The main function will
// then call these functions and measure its
// execution time as measured by the system
// clock in nanoseconds/milliseconds.

public class HomeworkOne {

   // Implementation of the fibonacciOne function.
   // NOTE: This is the exponential algorithm
   
   public static int fibonacciOne ( int n ) {
   
      // Two base cases.
      
      if ( n == 0 )
      
         return 0;
         
      if ( n == 1 )
      
         return 1;
         
      // Recursive call. Return a smaller, simpler 
      // version of the problem.
      
      return fibonacciOne( n - 1 ) + fibonacciOne( n - 2 );
   
   } // end fibonacciOne method.
   
   // Implementation of the fibonacciTwo function.
   // Note: This is the polynomial algorithm.
   
   public static int fibonacciTwo ( int n ) {
   
      // "Base case".
      
      if ( n == 0 )
      
         return 0;
         
      // Create an array of size n + 1.
      
      int f[ ] = new int [ n + 1 ];
      
      // Fill the first indeces of the array with the 
      // first two numbers in the Fibonacci sequence. 
      
      f[ 0 ] = 0;
      
      f[ 1 ] = 1;
      
      // This for loop will execute only if the parameter is
      // greater than or equal to 2.
      
      for ( int i = 2; i <= n; i++ ) {
      
         // Fill in the array as the number gets available. 
      
         f[ i ] = f[ i - 1 ] +f[ i -2 ];
      
      } // end for.
      
      // Return the final answer.
      
      return f[ n ];
  
   } // end fibonacciTwo method.

   public static void main ( String args [ ] ) {
   
      // Declare 10 arbitrary numbers that will be used to
      // execution time as measured by the system clock.
      
      final int testNumbers [ ] = { 4, 5, 8, 10, 13, 18, 24, 27, 33, 40 }; 
      
      // Declare variables that will allow us to measure the 
      // execution time.
       
      long startTime = 0;
      
      long endTime = 0;
      
      long estimatedTime = 0;
      
      // Declare an integer variable that will store the answer
      // returned by the fibonacci functions.
      
      int tempAnswer = 0;
      
      // Finding the largest number n for the exponential algorithm.
      
      System.out.println( "\nThe largest value of n for which the nth Fibonacci number can be computed in less than 10 milliseconds " );
      
      System.out.println( "by the exponential algorithm is 32.");
      
      startTime = System.nanoTime( ); 
         
      fibonacciOne( 32 );
         
      endTime = System.nanoTime( );
      
      estimatedTime = endTime - startTime;
      
      System.out.println( "For n = 32, the exponential algorithm took about " + ( estimatedTime / 1000000.0 )  + " milliseconds to execute." );
      
      // Finding the largest number n for the polynomial algorithm.
      
      System.out.println( "\nThe largest value of n for which the nth Fibonacci number can be computed in less than 10 milliseconds " );     
      
      System.out.println( "by the polynomial algorithm is 2500000.");
      
      startTime = System.nanoTime( ); 
         
      fibonacciTwo( 2500000 );
         
      endTime = System.nanoTime( );
      
      estimatedTime = endTime - startTime;
      
      System.out.println( "For n = 2500000, the polynomial algorithm took about " + ( estimatedTime / 1000000.0 )  + " milliseconds to execute." );
      
      System.out.println( "\n\nWe will test the numbers 4, 5, 8, 10, 13, 18, 24, 27, 33, and 40.\n" );
      
      // Use a for loop to accomplish the task.

      for ( int i = 0; i < 10; i++ ) {
      
         // Testing the execution time of fibonacciOne, that is, the exponential function.
      
         startTime = System.nanoTime( ); 
         
         tempAnswer = fibonacciOne( testNumbers[ i ] );
         
         endTime = System.nanoTime( );
         
         estimatedTime = endTime - startTime;
         
         System.out.println( "**********Testing value n = " + testNumbers[ i ] + ".**********" );      
         
         System.out.println( "The value of the " + testNumbers[ i ] + "nth Fibonacci is " + tempAnswer + "." );
         
         System.out.println( "----Here is the execution time of the exponential algorithm: " );
         
         System.out.println( estimatedTime + " nanoseconds = " + ( estimatedTime / 1000000.0 ) + " milliseconds" );
         
         // Testing the execution time of fibonacciTwo, that is, the polynomial function.
         
         startTime = System.nanoTime( );
      
         fibonacciTwo( testNumbers[ i ] );
         
         endTime = System.nanoTime( );
         
         estimatedTime = endTime - startTime;
         
         System.out.println( "----Here is the execution time of the polynomial algorithm: " );
         
         System.out.println( estimatedTime + " nanoseconds = " + ( estimatedTime / 1000000.0 ) + " milliseconds\n" );

      } // end for.
               
   } // end main.

} // end class.