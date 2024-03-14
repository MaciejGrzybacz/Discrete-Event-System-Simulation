import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static List<List<Integer>> generatePermutations(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutationsHelper(nums, new ArrayList<>(), result);
        return result;
    }

    private static void generatePermutationsHelper(List<Integer> nums, List<Integer> permutation, List<List<Integer>> result) {
        if (nums.isEmpty()) {
            result.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            int currentNum = nums.get(i);
            List<Integer> remainingNums = new ArrayList<>(nums);
            remainingNums.remove(i);

            List<Integer> newPermutation = new ArrayList<>(permutation);
            newPermutation.add(currentNum);

            generatePermutationsHelper(remainingNums, newPermutation, result);
        }
    }
}
