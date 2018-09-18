// 935. Cartesian Product
// Description
// We use a two-dimensional array setList[][] to represent a collection array, and each element in setList[i] is an integer and is not the same. Find the cartesian product of setList[0],setList[1],...,setList[setList.length - 1].
// In general, the Cartesian product of the collection A and the set B is A×B = {(x,y)|x∈A∧y∈B}。
//
// 1 <= setList.length <= 5
// 1 <= setList[i].length <= 5
// Have you met this question in a real interview?
// Example
// Given setList = [[1,2,3],[4],[5,6]], return [[1,4,5],[1,4,6],[2,4,5],[2,4,6],[3,4,5],[3,4,6]],
//
// Explanation:
// The cartesian product of [1,2,3], [4] and [5,6] is [[1,4,5],[1,4,6],[2,4,5],[2,4,6],[3,4,5],[3,4,6]].
// Given setList = [[1,2,3],[4]] , return [[1,4],[2,4],[3,4]]。
//
// Explanation:
// The cartesian product of [1,2,3] and [4] is [[1,4],[2,4],[3,4]].



public class Solution {
    /**
     * @param setList: The input set list
     * @return: the cartesian product of the set list
     */
    public List<List<Integer>> getSet(int[][] setList) {
        // Write your code here
        if (setList == null || setList.length == 0 || setList[0].length == 0) {
            return Collections.emptyList();
        }

        return mytry(setList);
    }

    private List<List<Integer>> mytry(int[][] setList) {
        // DFS: similar to traverse path from root to each leaf in a tree
        int n = setList.length;
        int m = setList[0].length;
        List<List<Integer>> result = new ArrayList<>();
        dfs(setList, result, new ArrayList<Integer>(), 0);
        return result;
    }
    private void dfs(int[][] setList, List<List<Integer>> result, List<Integer> list, int row) {
        if (row == setList.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int j = 0; j < setList[row].length; j++) {
            list.add(setList[row][j]);
            dfs(setList, result, list, row + 1);
            list.remove(list.size() - 1);
        }
    }
}
