// 500. Inverted Index

// Create an inverted index with given documents.
//
// Ensure that data does not include punctuation.
//
// Have you met this question in a real interview?
// Example
// Given a list of documents with id and content. (class Document)
//
// [
//   {
//     "id": 1,
//     "content": "This is the content of document 1 it is very short"
//   },
//   {
//     "id": 2,
//     "content": "This is the content of document 2 it is very long bilabial bilabial heheh hahaha ..."
//   },
// ]
// Return an inverted index (HashMap with key is the word and value is a list of document ids).
//
// {
//    "This": [1, 2],
//    "is": [1, 2],
//    ...
// }
// DifficultyEasy
// Total Accepted2216
// Total Submitted7687
// Accepted Rate28%


/**
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
public class Solution {
    /**
     * @param docs a list of documents
     * @return an inverted index
     */
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        // Write your code here
        Map<String, List<Integer>> map = new HashMap<>();
        if (docs == null || docs.size() == 0) {
            return map;
        }
        for (int i = 0; i < docs.size(); i++) {
            // get current document
            Document doc = docs.get(i);
            // get id and content
            int id = doc.id;
            String content = doc.content;
            getMap(map, id, content);
        }
        return map;
    }
    private void getMap(Map<String, List<Integer>> map, int id, String content) {
        // change string to array for easier operations
        String[] string = content.split("\\s+"); // there may be several whitespace
        // use set to omit same word in one sentence
        Set<String> set = new HashSet<>();
        for (int i = 0; i < string.length; i++) {
            // get current word
            String word = string[i];
            if (set.contains(word)) {
                continue; // omit same word in same sentence
            }
            set.add(word); // add into set
            if (!map.containsKey(word)) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(id);
                map.put(word, list);
            } else {
                List<Integer> list = map.get(word);
                list.add(id);
                map.put(word, list);
            }
        }
    }
}
