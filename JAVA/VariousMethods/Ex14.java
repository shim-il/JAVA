package JAVA.VariousMethods;

    /**
    * Maman 14.
    *
    * @author Shimon Port
    * @version 07/01/2021
    */
   public class Ex14
   {
       /**
        * Methed checks for the only nummber found in the non sorted array without a connected duplicate.
        * The method uses a binary search.
        * 
        * @parm a The given array to search.
        * @parm low Curent start of array being searched.
        * @parm high Curent end of array being searched.
        * @parm mid Curent middle of array being searched.
        * @return Value inside the array which has no connected duplicate.
        * if there is none, returns -1.
        * 
        * O(logn) - Because method uses binary search which means insted of
        * searching (in the worst case) "N" times, it only searches log2^N times
        * because it "cuts" the amount into 2 every step of the search. The rest
        * of the perminnt actions are irralavent for our count.
        * 
        */
       
       public static int findSingle (int [] a)
       {
           if (a.length == 1)
               return a[0];
               
           int low = 0;
           int high = a.length - 1;
           while (low <= high)
           {
               int mid = (low + high) / 2;
               if (a[low] != a[low + 1])
                   return a[low];
               if (a[high] != a[high - 1])
                   return a[high];
               if (a[mid] == a[mid + 1])
               {
                   if((high - (mid + 1)) % 2 == 0) // Checking for even side
                   {
                       high = mid - 1;
                   }
                   else
                       low = mid + 2;  
               }
               else if (a[mid] == a[mid - 1]) 
                   {
                       if((high - mid) % 2 == 0) // Checking for even side
                       {
                           high = mid - 2;
                       }
                       else 
                           low = mid + 1;
                   }
                   else 
                       return a[mid];
           }
         return -1;
       }
       
       
       /**
        * Methed checks for the minimun number of arrays that contain a higher sum than a given number "x".
        * Prints the diffrient options for the combonations.
        * 
        * @parm arr Array to search through.
        * @parm x The number for the method is looking to find a bigger sum.
        * @parm start Possition for the begining of sum array. 
        * @parm end Possition for the end of sum array.
        * @parm sumLength The length of the minimal sum array that is bigger than "x".
        * @parm temp Holds the current sum array > x.
        * @return Number of arrays needed from the given array, to top number "x".
        * if not found any amount of arrays within the given array bigger than "x"
        * the method will return -1.
        * 
        * O(n) - Because the method is depandent on the amount of numbers set in
        * the array and in any case will search through the whole array in order
        * to make sure there is no better solution than the one which may have
        * been found.
        * 
        */
       
       public static int smallestSubSum(int arr[], int x)
       {
           int start = 0; //First arry of search.
           int end = 0; // Last arry of search. 
           int sumLength = Integer.MAX_VALUE;
           int temp = -1;
           if (arr == null || arr.length == 0)
               return -1;
               
           if (arr[0] > x)
               return 1;
               
           int sum = arr[0];
           while (end <= arr.length - 1)
               {
                   if (sum > x)
                   {
                       temp = end - start + 1;
                       if (temp < sumLength) // Change value only if smaller than previous array > x.
                       {   
                           sumLength = temp;
                       }
                       sum = sum - arr[start];
                       start++;
                   }
                   else
                   {
                       end++;
                       if (end <= arr.length - 1)
                           sum = sum + arr[end];
                   }
               }
           if (temp < 0) // Never found sum arrys > x.
               sumLength = temp; 
           return sumLength;
       }
   
       
       /**
        * Recursive methed. Checks the amount of combenations possible to reach the "num" resalt with 3 numbers between 1-10.
        * Prints the diffrient options for the combonations.
        * 
        * @parm num Is the number to reach.
        * @parm x1 Is the fist variable between 1-10.
        * @parm x2 Is the seconed variable between 1-10.
        * @parm x3 Is the third variable between 1-10.
        * @parm numOfSolutions Gives the amount of possible combonations.
        * @return Number of possible combonations to reach the given "num".
        */
       
       public static int solutions(int num)
       {
           if (num < 3 || num > 30) //Num out of bound.
               return 0;
           return solutions(num, 1, 1, 1);
       }
       
       private static int solutions(int num, int x1, int x2, int x3)
       {
           int numOfSolutions = 0;
           if (x1 + x2 + x3 == num) // Stop term.
           {
               System.out.println(x1 + " + " + x2 + " + " + x3);
               numOfSolutions++;
           }
           
           if (x1 < 10 && x1 < num - 1) // Any one variable bigger than "num" is irrelevant.
           {
               return solutions(num, x1+1, x2, x3) + numOfSolutions;
           }
           
           if (x2 < 10 && x2 < num - 1)
           {
               return solutions(num, 1, x2+1, x3) + numOfSolutions;
           }
           
           if (x3 < 10 && x3 < num - 1)
           {
               return solutions(num, 1, 1, x3+1) + numOfSolutions;
           }
           
           return numOfSolutions;
       }
       
       
       /**
        * Recursive methed. Checks the amount of "true" squares in a row (not diagnal) with in a given matrix nxn.
        * 
        * @parm mat Matrix to search.
        * @parm i Current row.
        * @parm j Current columm.
        * @parm BEEN_HERE turns current location to "false" in order to avoid double counting.
        * @parm numOfSquare Keep track of "true" squares in a row as one unit.
        * @parm temp Holds the current postion.
        * @return Number of "true" continuity units.
        */
       
           public static int cntTrueReg (boolean[][]mat)
       {
           return cntTrueReg(mat, 0, 0);
       }
       
       public static int cntTrueReg (boolean[][]mat, int i, int j)
       {
           final boolean BEEN_HERE = false;
           int numOfSquare = 0;
           
           if (i > mat.length-1 || j > mat[0].length-1)
               return numOfSquare;
               
           // Avoid double count of true squares.    
           boolean temp = mat[i][j];
           mat[i][j] = BEEN_HERE;
           
           //Method for findind all true squares in a row.
           if (temp == true)   
           {
               if (i-1 >= 0 && mat[i-1][j] == true)
                   return cntTrueReg (mat, i-1, j);
                   
               if (i+1 < mat.length && mat[i+1][j] == true)
                   return cntTrueReg (mat, i+1, j);
                   
               if (j-1 >= 0 && mat[i][j-1] == true)
                   return cntTrueReg (mat, i, j-1);
                   
               if (j+1 < mat[0].length && mat[i][j+1] == true)
                   return cntTrueReg (mat, i, j+1);
               
               numOfSquare++;
               return numOfSquare + cntTrueReg (mat,0, 0);
           }
           
           // Search through the matrix.
           return numOfSquare + cntTrueReg (mat,i+1, j) + cntTrueReg (mat,0, j+1);
       }
   }
