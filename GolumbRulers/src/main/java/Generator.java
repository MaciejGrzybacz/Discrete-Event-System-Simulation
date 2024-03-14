import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Generator {
    // generate all prime rulers for prime numbers up to max_prime_val
    public static void allPrimeRulersGenerator(int max_prime_val) {
        List<Integer> primes = getPrimes(max_prime_val);
        for (int p : primes) {
            List<Integer> ruler = generatePrimeGolombRuler(p);
            if (isValid(ruler)) {
                System.out.print("Golomb Ruler for " + p + ": ");
                for (int mark : ruler) {
                    System.out.print(mark + " ");
                }
                System.out.println();
            }
        }
    }

    // generate optimal ruler for given order
    public static void optimalRulerGenerator(int order) {
        if (order <= 1) {
            System.out.print("Optimal Golomb Ruler for length " + order + ": 0\n");
            return;
        }
        int i = order*(order-1)/2+1; // minimum possible lenght is n*(n-1)/2+1 because ruler is 0,1,2,...,n-1 minimum
        while (true) {
            List<Integer> ruler = rulerGenerator(order, i);
            if (!ruler.isEmpty()) {
                System.out.print("Optimal Golomb Ruler for length " + order + ": ");
                for (int mark : ruler) {
                    System.out.print(mark + " ");
                }
                System.out.println();
                return;
            }
            i++;
        }
    }

    // check if ruler is valid
    public static boolean isValid(List<Integer> ruler) {
        Set<Integer> distances = new HashSet<>();
        for (int i = 0; i < ruler.size(); i++) {
            for (int j = i + 1; j < ruler.size(); j++) {
                distances.add(Math.abs(ruler.get(j) - ruler.get(i)));
            }
        }
        return distances.size() == ruler.size() * (ruler.size() - 1) / 2;
    }

    // generate prime golomb ruler
    public static List<Integer> generatePrimeGolombRuler(int p) {
        List<Integer> ruler = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            ruler.add(2 * p * i + (i * i) % p);
        }
        return ruler;
    }

    // get all prime numbers up to n
    public static List<Integer> getPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 3; i <= n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    // check if n is prime
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // generate all rules for given order and length and return the first valid one
    public static List<Integer> rulerGenerator(int order, int length) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if(i < order-2)
                nums.add(1);
            else
                nums.add(0);
        }

        List<List<Integer>> permutations = Permutations.generatePermutations(nums);
        for (List<Integer> perm : permutations) {
            List<Integer> ruler = backToPositions(perm);
            if (isValid(ruler)) {
                return ruler;
            }
        }
        return new ArrayList<>();
    }


    // convert 1s to positions
    public static List<Integer> backToPositions(List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == 1) {
                result.add(i);
            }
        }
        result.add(nums.size() - 1);
        return result;
    }
}
