/*

   Jose Franco Baquera
   April 5th, 2018
   CS 372 - Data Structures and Algorithms
   
   Purpose of The Code: The code's purpose is to
   prompt the user to input a text file. The code 
   will then generate a binary tree with this input
   and check if it is a RedBlackTree. The four 
   conditions that must be met are the following:
   1) The tree must be a binary search tree.
   2) The root of the tree must be black.
   3) Any red node must have only black children.
   4) Paths from a node to descendant leaves must contain the
      same number of black nodes.
   Assume that the user inputs the nodes in the following 
   format: (position,key,color). 
   
   NOTE: BECUASE WE IMPLEMENT THE BINARY TREE AS AN ARRAY,
   THE ROOT NODE IS FOUND AT LOCATION T[0]. In addition, 
   this means that the any node T[i] will have a left 
   child T[2i + 1] and right child T[2i + 2]. This is only 
   a slight modification of my pseoudo code I turned in
   in class. Everything else is bascically the same.
    
*/

// Import statements.
import java.io.*;
import java.util.*;

// BinaryTree class.
public class BinaryTree {

   // A BinaryTree object will have two attributes: a integer key
   // and a char color. 
   private final int key;
   private final char color;
   
   // BinaryTree class constructor.
   public BinaryTree( int paramKey, char paramColor ) {
   
      // Inizalize the atrributes of a Binarya node with a 
      key = paramKey;
      color = paramColor;  
   
   } // end constructor.
   
   // isRootBlack function. This function will check if the root 
   // node of the parameter BinaryTree is black. 
   public static boolean isRootBlack ( BinaryTree temporary [ ] ) {
   
      // If the root is black, return true, else false.
      if( temporary[ 0 ].color == 'b' )
         return true;
         
      // Return false.
      return false;
   
   } // end isRootBlack function.
   
   // isBinarySearchTree function. This function will check if the tree is a binary 
   // search tree by calling the helperFunctionBST function.       
   public static boolean isBinarySearchTree ( BinaryTree temporary [ ], int index ) {
   
      // Return the boolean value.
      return helperFunctionBST( temporary, index, Integer.MIN_VALUE, Integer.MAX_VALUE );
   
   } // end isBinaryTreeSearchTree function.
   
   // helperFunctionBST function. This function will check if the binary tree is a binary search tree.
   public static boolean helperFunctionBST ( BinaryTree paramTemp [ ], int paramIndex, int minimum, int maximum ) {
   
      // Stoping case. If either one is true return true since the tree is 
      // a binary search tree. 
      if( paramIndex >= paramTemp.length || paramTemp[paramIndex] == null )
         return true;
      
      // Stopping case. If either the key is lower or larger than the maximum, 
      // the binary tree is not a binary search tree. Return false.   
      if( paramTemp[paramIndex].key < minimum || paramTemp[ paramIndex ].key > maximum )   
         return false;
         
      // Recursively call the helper function. This will make two subproblems of size 1/2.
      // That is, it can be represented by the following reccurence: 2T(n/2) + O(1).
      // By the Master Theorem, we conclude that its running time is O(n).       
      return( helperFunctionBST( paramTemp, paramIndex*2 + 1, minimum, paramTemp[ paramIndex ].key - 1 ) && helperFunctionBST( paramTemp, paramIndex*2 + 2, paramTemp[ paramIndex ].key + 1, maximum ) );
   
   }// end helperFunctionBST function.
   
   // isRedRedNodes function. This function will check if the inputted binary tree does not have a red node followed 
   // by another red node.
   public static boolean isRedRedNodes ( BinaryTree temporary [ ] ) { 
   
      // Use a for loop that will traverse through the entire binary tree.
      for( int i = 0; i < temporary.length; i++ ) {
      
         // Check if the node at T[i] is red. If it is, check if any of its leaves are also red. 
         // If one leaf is found, return true. 
         if( ( ( i*2 + 2 ) <= ( temporary.length - 1 ) ) && ( temporary[i] != null ) && ( temporary[ i ].color == 'r' ) ){ 
            if( ( ( temporary[ i*2 + 1] != null ) && ( temporary[ i*2 + 1].color == 'r' ) ) || ( ( temporary[ i*2 + 2] != null ) && ( temporary[ i*2 + 2].color == 'r' ) ) ) {
               return true;
 
            } // end if.
         } // end if.
      } // end for.
      
      // Return false is two consecutive red nodes are not found.
      return false;
   
   } // end isRedRedNodes function.
   
   // blackHeightCheck function. This function will check if the paths from a node 
   // to descendant leaves contain different number of black nodes. If this is true, 
   // return -1, else any other integer that is NOT -1.
   public static int blackHeightCheck( BinaryTree paramTree [ ], int index ) {
   
      // Stopping case. If the node is null, return 0.
      if( index >= paramTree.length || paramTree[ index ] == null )   
          return 0;
      
      // Inizalize a counting variable. 
      int count;
      
      // If the node is red, return 0.    
      if( paramTree[ index ].color == 'r' )
          count = 0;
          
      else
         // If the node is not red then it must be black.
         // Return 1.
         count = 1;
           
      // Inizalize two variables to compare the the return values 
      // of the recusive calls.
      int t1 = blackHeightCheck( paramTree, index*2 + 1 );
      int t2 = blackHeightCheck( paramTree, index*2 + 2 );
        
      // If either of the temp variables are -1, return -1 since
      // the tree (or subtree) does not have a consistent black height.
      if( t1 == -1 || t2 == -1 )
         return -1;
         
      // Else if the temp variables are the same, return a non-negative 1.  
      else if( t1 == t2 )
         return t1 + count;
         
      // Return -1 if any of the cases does not apply.  
      return -1;
      
   } // end blackHeightCheck function. 
   
   // Main function.
   public static void main ( String args [ ] ) {
   
      // Prompt the user for a file name. Create a File object with the String name of the 
      // file inputted.
      System.out.println( "Please enter the filename." ); 
      System.out.println( "(NOTE: You MUST type the file extension (e.g. tree.txt, etc.) and \nthe file MUST be in the same folder as this Class. \nBEWARE: Case matters! tree.txt != TREE.TXT )" );
      String fileName;
      File inputFile;
      Scanner scan = new Scanner( System.in );
      fileName = scan.nextLine( );
      
      // Create a new File object with the string 
      // inputted.  
      inputFile = new File ( fileName );
      Scanner fileScan = null;
      
      // Use a try and catch error that will catch if the file can be read
      // or if it is in the same class as this folder.
      try {
      
         fileScan = new Scanner ( inputFile );
         
      } // end try.
      
      catch ( FileNotFoundException e ) {
      
         System.out.println( "File could not be opened or was not found in the same folder. Exiting Now." );
         System.exit( 0 );
         
      } // end catch.
      
      // Delete all the parenthesis and commas from the
      // inputted file.
      fileScan.useDelimiter("[,()]");
      
      // Declare and inizalize variables that will be used for
      // temporary holding the position, key, and color of each
      // inputted node.
      char color = 'a';
      int position = 0;   
      int key = 0;
      
      // Declare a boolean to check if the first number is the position.
      boolean first = true;
      BinaryTree testTree[ ] = null;
      
      // Use a while loop that will read all the characters until the EOF.
      while ( fileScan.hasNext( ) ) {
      
         // Check if the next character being read is a integer.
         if ( fileScan.hasNextInt( ) ) {
         
            // If first is true, then the number we are going to 
            // read is a node's position, else is  
            if ( first ) {
         
               position = fileScan.nextInt();         
               first = false;

            } // end if.
         
            else {
         
               key = fileScan.nextInt();
               first = true;
            
            } // end else.

         } // end if.
         
         // If the next character is not an integer, then it must be the node's color.
         else {
         
            // Save the color to a temporary integer.
            color = fileScan.next( ).charAt( 0 );   
            
            if ( color == 'r' || color == 'b' ) {
            
               // If position equals to one, create the root node.           
               if( position == 1 ) {
               
                  testTree = new BinaryTree[ 3 ];
                  testTree[ 0 ] = new BinaryTree( key, color );

               } // end if.
               
               // If position does not equal to 1, then the node we are inserting is 
               // not the root node.
               else {
               
                  // Create a temp array with new length.
                  BinaryTree temp [ ] = new BinaryTree [ testTree.length + 2 ];
                  
                     // Use a for loop to copy all the object nodes in the original array 
                     // to this temporary array.
                     for ( int i = 0; i < testTree.length; i++ ) 
                        temp[ i ] = testTree[ i ];
                     
                     // Insert the node node into the array. 
                     temp[ position - 1 ] = new BinaryTree( key, color );
                     
                     // Make the original tree point to the newly created array.        
                     testTree = temp;
                                              
               } // end else.
               
            } // end checker that a correct color was inserted. 
              
         } // end else.
   
      } // end while
      
      // Declare a boolean flag. This is used for printing that the inputted tree is a
      // redBlackTree.     
      boolean flag = true;
      
      // Check if the black height property is met.
      if( blackHeightCheck( testTree, 0 ) == -1 ) {
      
         System.out.println( "NOT A RED BLACK TREE: Paths from a node to descendant leaves contain different number of black nodes." );
         flag = false;
         
      } // end if.
      
      // Check is the root is black.  
      if( !isRootBlack( testTree ) ) { 
      
         System.out.println( "NOT A RED BLACK TREE: The root is not black." );
         flag = false;
         
      } // end if.
      
      // Check if there are consecutive red nodes. 
      if( isRedRedNodes( testTree ) ) {
      
         System.out.println( "NOT A RED BLACK TREE: A red node has a red child." );
         flag = false;
         
      } // end if.
      
      // Check if the tree is a binary search tree.     
      if ( !isBinarySearchTree( testTree, 0 ) )  {
      
         System.out.println( "NOT A RED BLACK TREE: The tree is not a Binary Search Tree (BST)." );
         flag = false;
         
      } // end if.
      
      // If the flag was never set to false, the node is a RedBlackTree.
      if ( flag )    
         System.out.println( "Congrats! The binary tree you inputted is a RED BLACK TREE. YAYYYY!!!!!!" );
                
   } // end main.

} // end class.