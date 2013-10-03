public class PrintPrimes {

  //Class Variables //
  int numberOfPrimes;
  int rowsPerPage; 
  int columnsPerPage; 
  int maxComparisonIndex; 
  int listOfPrimes[];

  //Default Constructor //
  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columnsPerPage, int maxComparisonIndex) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowsPerPage  = rowsPerPage;
    this.columnsPerPage  = columnsPerPage;
    this.maxComparisonIndex = maxComparisonIndex;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }

  //Main Method //
  public static void main(String[] args) {
    
      //Initialize the printPrimes Object
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      
      //Calculate numberOfPrimes (in this case 300) prime numbers 
      printPrimes.calculatePrimes();
      
      //Print all primes calculated
      printPrimes.printPrimes();
  }

  //Calculate all the even primes //
  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }

  //Calculate all the odd prime numbers
  private void calculateOddPrimes() {
    
      //Declare local variables
      boolean isPrime;
      int primeIndex;
      int multiplesOfPrime[] = new int[maxComparisonIndex + 1];
      
      //Initialize local variables
      int currentNumber = 1;
      int comparisonIndex = 2;
      int square = 9;
      
      //Find and store prime numbers in array lisOfPrimes[ ] 
      for(int numberOfPrimesFoundSoFar = 2; numberOfPrimesFoundSoFar <= numberOfPrimes; numberOfPrimesFoundSoFar++) {
        
        //do while the number is not prime (until the prime number is found)
        do {
          
          currentNumber += 2;
          
          if (currentNumber == square) {
            
            comparisonIndex++;
            square = listOfPrimes[comparisonIndex] * listOfPrimes[comparisonIndex];
            multiplesOfPrime[comparisonIndex - 1] = currentNumber;
          }
          
          primeIndex = 2;
          isPrime = true;
          
          while (primeIndex < comparisonIndex && isPrime) {
            
            while (multiplesOfPrime[primeIndex] < currentNumber){
              multiplesOfPrime[primeIndex] = multiplesOfPrime[primeIndex] + listOfPrimes[primeIndex] + listOfPrimes[primeIndex];
            }
            
            if (multiplesOfPrime[primeIndex] == currentNumber){
              isPrime = false;
            }
            
            primeIndex++;
          }
          
        } while (!isPrime);
        
        
        listOfPrimes[numberOfPrimesFoundSoFar] = currentNumber;
      }
    }

    //Method to print all the prime numbers //
    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        
        while (pageOffset <= numberOfPrimes) {
          
          //Print the header
          System.out.println("The First " + numberOfPrimes + " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          
          //Print primes from top to bottom 
          for (int rowOffset = pageOffset; rowOffset < pageOffset + rowsPerPage; rowOffset++){
            
            //Print primes from left to right 
            for (int columns = 0; columns < columnsPerPage; columns++){
              
              //Find and print the right prime number based on their position
              if (rowOffset + columns * rowsPerPage <= numberOfPrimes){
                System.out.format("%10d", listOfPrimes[rowOffset + columns * rowsPerPage]);
              }
              
            }
            
            System.out.println("");
          }
          
          System.out.println("\f");
          
          //Update pageNumber and pageOffset
          pageNumber++;
          pageOffset = pageOffset + rowsPerPage * columnsPerPage;
        }
    }
}
