public class Main {
    public static void main(String[] args) {
        //allPrimeRulersGenerator(100);
        long startTime = System.nanoTime();
        for (int i = 1; i < 5; i++) { // generate optimal rulers for orders 1 to 4 only because of time complexity
            Generator.optimalRulerGenerator(i);
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed Time: " + (elapsedTime / 1000000) + " milliseconds");
    }
}
