public class PrintPrimes {

  //Class Constants //

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
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      printPrimes.calculatePrimes();
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
      boolean isPrime;
      int primeIndex;
      int primeMultiples[] = new int[maxComparisonIndex + 1];

      int currentNumber = 1;
      int comparisonIndex = 2;
      int square = 9;

      for(int numberOfPrimesFoundSoFar = 2; numberOfPrimesFoundSoFar <= numberOfPrimes; numberOfPrimesFoundSoFar++) {
        do {
          currentNumber += 2;
          if (currentNumber == square) {
            comparisonIndex++;
            square = listOfPrimes[comparisonIndex] * listOfPrimes[comparisonIndex];
            primeMultiples[comparisonIndex - 1] = currentNumber;
          }
          primeIndex = 2;
          isPrime = true;
          while (primeIndex < comparisonIndex && isPrime) {
            while (primeMultiples[primeIndex] < currentNumber){
              primeMultiples[primeIndex] = primeMultiples[primeIndex] + listOfPrimes[primeIndex] + listOfPrimes[primeIndex];
            }
            if (primeMultiples[primeIndex] == currentNumber){
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
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          
          for (int rowOffset = pageOffset; rowOffset < pageOffset + rowsPerPage; rowOffset++){
            for (int columns = 0; columns < columnsPerPage; columns++){
              if (rowOffset + columns * rowsPerPage <= numberOfPrimes){
                System.out.format("%10d", listOfPrimes[rowOffset + columns * rowsPerPage]);
              }
            }
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber++;
          pageOffset = pageOffset + rowsPerPage * columnsPerPage;
        }
    }
}
