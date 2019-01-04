// 1580. Transition String
// Description
// Give a startString, an endString, ask if you can transfer from start to end through a series of independent transformations. The rule is to have only 26 lowercase letters, and only one type of letter can be changed per operation. For example, if you change a to b, then all a in the start string must be b. For each type of character you can choose to convert or not convert, the conversion must be conducted between one character in startString and one character in endString. Return true or false.
//
// ，
//
// Have you met this question in a real interview?
// Example
// Given : startString = "abc" ; endString = "cde"
// Return : True
// Explanation :
// a->b, b->d, c->e
// "abc"->"abe"->"ade"->"cde"
// but if you convert to "cbc" first, you will never convert to "cde" again.
// Given : startString = "abc" ; endString = "bca"
// Return : False
// Explanation :
// a->b, b->c, c->a
// "abc"->"abe"->"ace"->"bce"->"bca" is an illegal conversion，because 'a' can only be converted to 'b'
// No matter which character you change first, it cannot be converted to a target string.
// Given : startString = "aba" ; endString = "cde"
// Return : False
// Explanation :
// a->c, b->d,a->e
// It is impossible to converted 'a' to 'c' and 'e' at the same time.


public class Solution {
    /**
     * @param startString:
     * @param endString:
     * @return: Return true or false
     */
    public boolean canTransfer(String startString, String endString) {
        // Write your code here
        if (startString == null && endString == null || startString.isEmpty() && endString.isEmpty()) {
            return true;
        }
        if (startString.equals(endString)) {
            return true;
        }
        if (startString.length() != endString.length()) {
            return false;
        }
        if (startString == null || startString.isEmpty() || endString == null || endString.isEmpty()) {
            return false;
        }


        // return mytry(startString, endString);

        return method2(startString, endString);
    }

    private boolean method2(String start, String end) {
        // Three conditions:
        // 1. Different length;
        // 2. If any character is more than 2, this character cannot map to multiple character; (i.e. 'aa'-->'bc' false;)
        // 3. StartString is a permutation of endString (except for StartString == EndString)

        int[] map1 = new int[26];
        int[] map2 = new int[26];
        Map<Character, Character> mapping = new HashMap<>();
        for (int i = 0; i < start.length(); i++) {
            char s = start.charAt(i);
            char e = end.charAt(i);
            map1[s - 'a']++;
            map2[e - 'a']++;
            if (map1[s - 'a'] > 1 && mapping.get(s) != e) {
                return false;
            }
            mapping.put(s, e);
        }
        // check permutation
        // 其实我这里没看懂， 和 permutation 有什么关系， 我自己的理解是， 两个要能转换， 首先就是要长度一致， 在此条件下如果 start 中某个字符的个数不等于 end 中这个字符的个数， 才有可能转换过去， 类似于七巧板， 要有一个空余的空间来中转移动。 如果字符的个数都相等， 那么转换的时候都会一次性全部转换过去， 那么在不同的位置上的相同字符一定是转换不过去的
        // 想了一下， 其实 permutation 也是这个意思吧， 就是长度一致， end 和 start 有相同的 char 个数， 但是位置不同， 那么一定至少有两个 char 是相同但位置不同的， 既然是 permutation， 那么所有可选的 char 也都是一样的， 必然在转换之后， 会使得这个 char 变成另一个 end 中相同的 char， 但与 start 中变化后的这个 char 位置不同， 那么无论如何转换也是不能的了
        for (int i = 0; i < 26; i++) {
            if (map1[i] != map2[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean mytry(String start, String end) {
        // DFS
        Set<Character> set = new HashSet<>();
        for (char c : start.toCharArray()) {
            set.add(c);
        }
        for (char c : end.toCharArray()) {
            set.add(c);
        }

        char[] array = start.toCharArray();
        return dfs(start, end, set, array, new HashMap<>(), new HashSet<>());
    }
    private boolean dfs(String start, String end, Set<Character> set, char[] array, Map<Character, Character> map, Set<String> visited) {
        if (String.valueOf(array).equals(end)) {
            return true;
        }
        // 似乎不能够 for start 的位置来做， 因为能够换过去， 是和先后顺序有关的， 有的时候并不是第一位先换
        // 但 for char 好像也没做出来 =。= 之后再思考一下
        for (char ori : set) {

            System.out.println("now go to change " + ori);
            if (map.containsKey(ori)) {
                System.out.println("has such mapping " + ori + " -> " + map.get(ori));
                change(array, ori, map.get(ori));
                // array[i] = map.get(ori);
                if (!visited.contains(String.valueOf(array))) {
                    visited.add(String.valueOf(array));
                    System.out.println("go " + String.valueOf(array));
                    if (dfs(start, end, set, array, map, visited)) {
                        return true;
                    }
                    visited.remove(String.valueOf(array));
                }
                change(array, map.get(ori), ori);
            } else {
                System.out.println("no mapping ");
                for (char next : set) {

                    if (next == ori) {
                        continue;
                    }
                    System.out.println("change to next: " + next);
                    // array[i] = next;
                    change(array, ori, next);
                    if (visited.contains(String.valueOf(array))) {
                        change(array, next, ori);
                        continue;
                    }
                    map.put(ori, next);
                    visited.add(String.valueOf(array));
                    if (dfs(start, end, set, array, map, visited)) {
                        return true;
                    }
                    change(array, next, ori);
                    map.remove(ori);
                    visited.remove(String.valueOf(array));
                }
            }
            // array[i] = ori;
        }
        return false;
    }
    private void change(char[] array, char from, char to) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == from) {
                array[i] = to;
            }
        }
    }
}
