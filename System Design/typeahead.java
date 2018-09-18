// 231. Typeahead
// Description
// Implement typeahead. Given a string and a dictionary, return all words that contains the string as a substring. The dictionary will give at the initialize method and wont be changed. The method to find all words with given substring would be called multiple times.
//
// Have you met this question in a real interview?
// Example
// Given dictionary = {"Jason Zhang", "James Yu", "Bob Zhang", "Larry Shi"}
//
// search "Zhang", return ["Jason Zhang", "Bob Zhang"].
//
// search "James", return ["James Yu"].


public class Typeahead {

    private HashMap<String, List<String>> map = new HashMap<String , List<String>>();

    /*
    * @param dict: A dictionary of words dict
    */
    public Typeahead(Set<String> dict) {
        // do intialization if necessary
        for (String str : dict) {
            int len = str.length();
            for (int i = 0; i < len; ++i)
                for (int j = i + 1; j <= len; ++j) {
                    String tmp = str.substring(i, j);
                    if (!map.containsKey(tmp)) {
                        map.put(tmp, new ArrayList<String>());
                        map.get(tmp).add(str);
                    } else {
                        List<String> index = map.get(tmp);
                        if (!str.equals(index.get(index.size() - 1))) {
                            index.add(str);
                        }
                    }
                }
        }
    }

    /*
     * @param str: a string
     * @return: a list of words
     */
    public List<String> search(String str) {
        // write your code here
        if (!map.containsKey(str)) {
            return new ArrayList<String>();
        } else {
            return map.get(str);
        }
    }
}

wrong example when there is no following if clause:
if (!str.equals(index.get(index.size() - 1))) {
    index.add(str);
}
//
// Input
// dict=["Tree","Lint Code","Problem","Run Code","Fizz Buzz","Binary Tree","Binary Tree Sum"]
// search("e")
// search("ee")
// search("zz")
// Output
// ["Binary Tree","Binary Tree","Binary Tree Sum","Binary Tree Sum","Lint Code","Problem","Run Code","Tree","Tree"]
// ["Binary Tree","Binary Tree Sum","Tree"]
// ["Fizz Buzz","Fizz Buzz"]
// Expected
// ["Binary Tree","Binary Tree Sum","Lint Code","Problem","Run Code","Tree"]
// ["Binary Tree","Binary Tree Sum","Tree"]
// ["Fizz Buzz"]

answer:

public class Typeahead {
    private HashMap<String, List<String>> map = new HashMap<String , List<String>>();
    // @param dict A dictionary of words dict
    public Typeahead(Set<String> dict) {
        // do initialize if necessary
        for (String str : dict) {
            int len = str.length();
            for (int i = 0; i < len; ++i)
                for (int j = i + 1; j <= len; ++j) {
                    String tmp = str.substring(i, j);
                    if (!map.containsKey(tmp)) {
                        map.put(tmp, new ArrayList<String>());
                        map.get(tmp).add(str);
                    } else {
                        List<String> index = map.get(tmp);
                        if (!str.equals(index.get(index.size() - 1))) {
                            index.add(str);
                        }
                    }
                }
        }
    }

    // @param str: a string
    // @return a list of words
    public List<String> search(String str) {
        // Write your code here
        if (!map.containsKey(str)) {
            return new ArrayList<String>();
        } else {
            return map.get(str);
        }
    }
}
