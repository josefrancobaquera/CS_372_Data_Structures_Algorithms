// Jose Franco Baquera
// Homework #3; Question 2
// CS 372 - Data Structures and Algorithm Design
// February 19, 2018
// Program Name: HomeworkThree.java
// Purpose of the program: The purpose 
// of the program is to have two quicksort functions
// that will sort the array two different ways: One
// using the last element as a pivot and the other one
// using the estimated median as the pivot. 
// The main function will then call these functions 
// and measure its execution using the system
// clock in nanoseconds/milliseconds.

public class HomeworkThree {

   // Implementation of the quickSortOne method. NOTE:
   // This function will use the last element as the pivot.
   
   public static void quickSortOne ( int A [ ], final int p, final int r ) {
   
      // First check that p is less than r.
      
      if ( p < r ) {
      
         // Call the partitionOne function. NOTE: This
         // function will always choose the last element
         // in the array as the pivot. 
      
         int q = partitionOne ( A, p, r );
         
         // Recursive call two times.
         
         quickSortOne( A, p, q - 1 );
         
         quickSortOne( A, q + 1, r );
         
      } // end if.
      
      // Return if the stopping case did not execute.
      
      return;
 
   } // end quickSortOne method.
   
   // Implementation of the partitionOne method.
   
   public static int partitionOne ( int array [ ], final int newP, final int newR ) {
   
      // Select the last element of the array as the pivot.
      
      int x = array[ newR ];
      
      // Inizalize the variable i that will help us traverse
      // the array.
      
      int i = newP - 1;
      
      // Inizalize a temperary value that will help us exchange
      // two array elements if needed.
      
      int tempValue = 0;
      
      // Use a for loop to traverse the array.
      
      for ( int j = newP; j < newR; j++ ) {
      
         if ( array[ j ] <= x ) {
         
            i++;
            
            // Exchange newArray[ i ] with newArray[ j ]
            
            tempValue = array[ j ];
            
            array[ j ] = array[ i ];
            
            array[ i ] = tempValue;  
         
         } // end if.
      
      } // end for.
      
      // Exchange newArray[ i + 1 ] with newArray[ newR ]
      
      tempValue = array[ i + 1 ];
      
      array[ i + 1 ] = array[ newR ];
      
      array[ newR ] = tempValue;
      
      // Return the location of the pivot.
      
      return i + 1;
   
   } // end partitionOne method.
   
   // Implementation of the quickSortTwo method. NOTE: This will
   // use the estimated median as the pivot.
   
   public static void quickSortTwo ( int A [ ], final int p, final int r ) {
   
      // First check that p is less than r.
      
      if ( p < r ) {
      
         // Call the partitionTwo function. NOTE: This
         // function will always choose the median value
         // of the array as the pivot.
      
         int q = partitionTwo ( A, p, r );
         
         // Recursive call two times.
         
         quickSortTwo( A, p, q - 1 );
         
         quickSortTwo( A, q + 1, r );
         
      } // end if.
      
      // Return if the stopping case did not execute.
      
      return;
 
   } // end quickSortTwo method.
   
   // Implementation of the partitionTwo method.
  
   public static int partitionTwo ( int array [ ], final int newP, final int newR ) {
   
      // This partition will use the "estimated median value" as the pivot.
      // x will be the value of the pivot.
   
      int x = 0;
            
      // Declare a temporary value that will help us swap elements.
      
      int tempValue = 0;
      
      // Use if statements to find the middle element of the three elements.
                  
      if ( ( array[ newP ] - array[ ( newP + newR ) / 2 ] ) * ( array[ newR ] - array[ newP ] ) >= 0 ) {
      
         // The first element in the array will become the pivot.
      
         x = array[ newP ];
         
         // Swap the first and last elements.
                
         tempValue = array[ newP ];
         
         array[ newP ] = array[ newR ];
         
         array[ newR ] = tempValue;
     
      } // end if.
      
      else if ( ( array[ ( newP + newR ) / 2 ] - array[ newP ] ) * ( array[ newR ] - array[ ( newP + newR ) / 2 ]  ) >= 0 ) {
      
         // The middle element will become the pivot.
      
         x = array[ ( newP + newR ) / 2 ];
         
         // Exchange the middle and and last elements.
                 
         tempValue = array[ ( newP + newR ) / 2 ];
         
         array[ ( newP + newR ) / 2 ] = array [ newR ];
         
         array[ newR ] = tempValue;
         
      } //end else if 
      
      else {
      
         // The last element will become the pivot element. 
        
         x = array[ newR ];
                  
      } // end else.
              
      // Inizalize the variable i that will help us traverse
      // the array.
      
      int i = newP - 1;
      
      // Use a for loop to traverse the array.
      
      for ( int j = newP; j < newR; j++ ) {
      
         if ( array[ j ] <= x ) {
         
            i++;
            
            // Exchange newArray[ i ] with newArray[ j ]
            
            tempValue = array[ j ];
            
            array[ j ] = array[ i ];
            
            array[ i ] = tempValue;  
         
         } // end if.
      
      } // end for.
      
      // Exchange newArray[ i + 1 ] with newArray[ newR ]
      
      tempValue = array[ i + 1 ];
      
      array[ i + 1 ] = array[ newR ];
      
      array[ newR ] = tempValue;
      
      // Return the location of the pivot.
      
      return i + 1;
   
   } // end partitionTwo method.
   
   // Implementation of the partitionOne method.

   public static void main ( String args [ ] ) { 
   
      // Declare variables that will allow us to measure the 
      // execution time.
      
      long startTime = 0, endTime = 0, estimatedTime = 0;
      
      // Declare two long numbers that will help us find the average running time clock.
      
      long sumOne = 0;
      
      long sumTwo = 0;
      
      // *******************************Testing the running time for n = 10 for both quicksorts.*******************************
      
      int test10 [ ] = new int [ 10 ];
      
      int test10Copy [ ] = new int [ 10 ];
      
      System.out.println( "\n********Testing for n = 10 with 200 trials**********" );
      
      // Do 200 trials to find an average.
      
      for ( int trial = 1; trial <= 200; trial++ ) {
      
         // Fill the array with random numbers.
      
         for ( int i = 0; i < test10.length; i++ ) {
   
            test10[i] = ( int ) ( ( Math.random( ) * ( 50 - ( 1 ) ) + 1 ) + 1  );
         
            test10Copy[ i ] = test10[ i ];
            
         } // end for.
         
         // Running time of the first quicksort.
      
         startTime = System.nanoTime( );
      
         quickSortOne( test10, 0, test10.length - 1 );
      
         endTime = System.nanoTime( );
   
         estimatedTime = endTime - startTime;
      
         sumOne = sumOne + estimatedTime;
         
         // Running time of the second quicksort.
         
         startTime = System.nanoTime( );
         
         quickSortTwo( test10Copy, 0, test10Copy.length - 1 );
         
         endTime = System.nanoTime( );
         
         estimatedTime = endTime - startTime;
         
         sumTwo = sumTwo + estimatedTime;
      
      } // end outer for.
      
      System.out.print( "Here is the array for the LAST trial sorted by the first quicksort: " );
      
      for ( int i = 0; i < test10.length; i++ )
      
         System.out.print( test10[ i ] + " " );
         
      System.out.print( "\nHere is the same array sorted by the second quicksort: " );
      
         for ( int i = 0; i < test10Copy.length; i++ )
      
            System.out.print( test10Copy[ i ] + " " );

      System.out.println( "\nThe average estimated running time for 200 trials for the quicksort that uses the last element as a pivot is: " + ( sumOne/200 ) + " nanoseconds or " + ( ( sumOne/200 ) / 1000000.0 ) + " milliseconds." );
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the estimated median as a pivot is: " + ( sumTwo/200 ) + " nanoseconds or " + ( ( sumTwo/200 ) / 1000000.0 ) + " milliseconds." );
      
      // *******************************Testing the running time for n = 50 for both quicksorts.*******************************
      
      int test50 [ ] = new int [ 50 ];
      
      int test50Copy [ ] = new int [ 50 ];
      
      System.out.println( "\n********Testing for n = 50 with 200 trials.**********\n" );
      
      sumOne = 0;
      
      sumTwo = 0;
      
      // Do 200 trials to find an average.
      
      for ( int trial = 1; trial <= 200; trial++ ) {
      
         // Fill the array with random numbers.
      
         for ( int i = 0; i < test50.length; i++ ) {
   
            test50[i] = ( int ) ( ( Math.random( ) * ( 50 - ( 1 ) ) + 1 ) + 1  );
         
            test50Copy[ i ] = test50[ i ];
            
         } // end for.
         
         // Running time of the first quicksort.
      
         startTime = System.nanoTime( );
      
         quickSortOne( test50, 0, test50.length - 1 );
      
         endTime = System.nanoTime( );
   
         estimatedTime = endTime - startTime;
      
         sumOne = sumOne + estimatedTime;
         
         // Running time of the second quicksort.
         
         startTime = System.nanoTime( );
         
         quickSortTwo( test50Copy, 0, test50Copy.length - 1 );
         
         endTime = System.nanoTime( );
         
         estimatedTime = endTime - startTime;
         
         sumTwo = sumTwo + estimatedTime;
      
      } // end outer for.
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the last element as a pivot is: " + ( sumOne/200 ) + " nanoseconds or " + ( ( sumOne/200 ) / 1000000.0 ) + " milliseconds." );
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the estimated median as a pivot is : " + ( sumTwo/200 ) + " nanoseconds or " + ( ( sumTwo/200 ) / 1000000.0 ) + " milliseconds." );
                  
      // *******************************Testing the running time for n = 100 for both quicksorts.*******************************
      
      int test100 [ ] = new int [ 100 ];
      
      int test100Copy [ ] = new int [ 100 ]; 
      
      sumOne = 0;
      
      sumTwo = 0;
      
      System.out.println( "\n********Testing for n = 100 with 200 trials.**********\n" );
            
      for ( int trial = 1; trial <= 200; trial++ ) {
      
         for ( int i = 0; i < test100.length; i++ ) {
   
            test100[i] = ( int ) ( ( Math.random( ) * ( 50 - ( 1 ) ) + 1 ) + 1 );
         
            test100Copy[ i ] = test100[ i ];
         
         } // end for.
      
         startTime = System.nanoTime( );
      
         quickSortOne( test100, 0, test100.length - 1 );
      
         endTime = System.nanoTime( );
   
         estimatedTime = endTime - startTime;
      
         sumOne = sumOne + estimatedTime;
      
         startTime = System.nanoTime( );
   
         quickSortTwo( test100Copy, 0, test100Copy.length - 1 );
      
         endTime = System.nanoTime( );
      
         estimatedTime = endTime - startTime;
      
         sumTwo = sumTwo + estimatedTime;
         
      } // end outer for.
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the last element as a pivot is: " + ( sumOne/200 ) + " nanoseconds or " + ( ( sumOne/200 ) / 1000000.0 ) + " milliseconds." );
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the estimated median as a pivot is : " + ( sumTwo/200 ) + " nanoseconds or " + ( ( sumTwo/200 ) / 1000000.0 ) + " milliseconds." );    

      // *******************************Testing the running time for n = 500 for both quicksorts.*******************************
      
      int test500 [ ] = new int [ 500 ];
      
      int test500Copy [ ] = new int [ 500 ];
      
      sumOne = 0;
      
      sumTwo = 0;
       
      System.out.println( "\n********Testing for n = 500 with 200 trials**********\n" );
      
      for ( int trial = 1; trial <= 200; trial++ ) {
      
         for ( int i = 0; i < test500.length; i++ ) {
   
            test500[i] = ( int ) ( ( Math.random( ) * ( 50 - ( 1 ) ) + 1 ) + 1 );
         
            test500Copy[ i ] = test500[ i ];
         
         } // end for.
         
         startTime = System.nanoTime( );
         
         quickSortOne( test500, 0, test500.length - 1 );
      
         endTime = System.nanoTime( );
   
         estimatedTime = endTime - startTime;
         
         sumOne = sumOne + estimatedTime;
         
         startTime = System.nanoTime( );
   
         quickSortTwo( test500Copy, 0, test500Copy.length - 1 );
      
         endTime = System.nanoTime( );
      
         estimatedTime = endTime - startTime;
         
         sumTwo = sumTwo + estimatedTime;
               
      } // end outer for.
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the last element as a pivot is: " + ( sumOne/200 ) + " nanoseconds or " + ( ( sumOne/200 ) / 1000000.0 ) + " milliseconds." );
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the estimated median as a pivot is : " + ( sumTwo/200 ) + " nanoseconds or " + ( ( sumTwo/200 ) / 1000000.0 ) + " milliseconds." ); 
      
      // *******************************Testing the running time for n = 1000 for both quicksorts.*******************************
      
      sumOne = 0;
      
      sumTwo = 0;
      
      int test1000 [ ] = new int [ 1000 ];
      
      int test1000Copy [ ] = new int [  1000 ];
      
      System.out.println( "\n********Testing for n = 1000 with 200 trials**********\n" );
      
      for ( int j = 1; j <= 200; j++  ) {
      
         for ( int i = 0; i < test1000.length; i++ ) {
   
            test1000[i] = ( int ) ( ( Math.random( ) * ( 50 - ( 1 ) ) + 1 ) + 1 );
         
            test1000Copy[ i ] = test1000[ i ];
         
         } // end for.
      
         startTime = System.nanoTime( );
      
         quickSortOne( test1000, 0, test1000.length - 1 );
      
         endTime = System.nanoTime( );
   
         estimatedTime = endTime - startTime;
      
         sumOne = sumOne + estimatedTime;
      
         startTime = System.nanoTime( );
   
         quickSortTwo( test1000Copy, 0, test1000Copy.length - 1 );
      
         endTime = System.nanoTime( );
      
         estimatedTime = endTime - startTime;
      
         sumTwo = sumTwo + estimatedTime;
         
      }// end outer for.
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the last element as a pivot is: " + ( sumOne/200 ) + " nanoseconds or " + ( ( sumOne/200 ) / 1000000.0 ) + " milliseconds." );
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the estimated median as a pivot is : " + ( sumTwo/200 ) + " nanoseconds or " + ( ( sumTwo/200 ) / 1000000.0 ) + " milliseconds." ); 
                  
      // *******************************Testing the running time for n = 5000 for both quicksorts.*******************************
   
      int test5000 [ ] = new int [ 5000 ];
      
      int test5000Copy[ ] = new int [ 5000 ];
      
      sumOne = 0;
      
      sumTwo = 0;
      
      for ( int trial = 1; trial <= 200; trial++ ) {
         
         for ( int i = 0; i < test5000.length; i++ ) {
   
            test5000[i] = ( int ) ( ( Math.random( ) * ( 50 - ( 1 ) ) + 1 ) + 1 );
         
            test5000Copy[ i ] = test5000[ i ];
         
         } // end for.
         
         startTime = System.nanoTime( );
      
         quickSortOne( test5000, 0, test5000.length - 1 );
      
         endTime = System.nanoTime( );
   
         estimatedTime = endTime - startTime;
         
         sumOne = sumOne + estimatedTime;
         
         startTime = System.nanoTime( );
   
         quickSortTwo( test5000Copy, 0, test5000Copy.length - 1 );
      
         endTime = System.nanoTime( );
      
         estimatedTime = endTime - startTime;
         
         sumTwo = sumTwo + estimatedTime;         
            
      } // end outer for.
      
      System.out.println( "\n********Testing for n = 5000 with 200 trials.**********\n" );
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the last element as a pivot is: " + ( sumOne/200 ) + " nanoseconds or " + ( ( sumOne/200 ) / 1000000.0 ) + " milliseconds." );
      
      System.out.println( "The average estimated running time for 200 trials for the quicksort that uses the estimated median as a pivot is : " + ( sumTwo/200 ) + " nanoseconds or " + ( ( sumTwo/200 ) / 1000000.0 ) + " milliseconds." );
      
      System.out.println( "\nNOTE: As n increases, the running time of the quicksort that uses the estimated running time decreases (i.e. becomes faster)." );
      
      System.out.println( "This is what we expected since as n grows, the estimated median quicksort becomes faster than the other quicksort." );
      
      } // end main.

} // end class.