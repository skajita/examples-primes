public class PrintPrimes {

  //Class Constants //

  //Class Variables //
  int numberOfPrimes;
  int rowsPerPage; //(RR)
  int columnsPerPage; //(CC)
  int ORDMAX;
  int listOfPrimes[];

  //Default Constructor //
  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columnsPerPage, int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowsPerPage  = rowsPerPage;
    this.columnsPerPage  = columnsPerPage;
    this.ORDMAX = ORDMAX;
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
      int N;
      int MULT[] = new int[ORDMAX + 1];

      int currentNumber = 1;
      int ORD = 2;
      int square = 9;

      for(int numberOfPrimesFoundSoFar = 2; numberOfPrimesFoundSoFar <= numberOfPrimes; numberOfPrimesFoundSoFar++) {
        do {
          currentNumber += 2;
          if (currentNumber == square) {
            ORD = ORD + 1;
            square = listOfPrimes[ORD] * listOfPrimes[ORD];
            MULT[ORD - 1] = currentNumber;
          }
          N = 2;
          isPrime = true;
          while (N < ORD && isPrime) {
            while (MULT[N] < currentNumber){
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            }
            if (MULT[N] == currentNumber){
              isPrime = false;
            }
            N = N + 1;
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
            for (int C = 0; C < columnsPerPage;C++){
              if (rowOffset + C * rowsPerPage <= numberOfPrimes){
                System.out.format("%10d", listOfPrimes[rowOffset + C * rowsPerPage]);
              }
            }
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + rowsPerPage * columnsPerPage;
        }
    }
}

      
