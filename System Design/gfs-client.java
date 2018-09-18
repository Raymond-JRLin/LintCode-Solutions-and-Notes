// 566. GFS Client
// Description
// Implement a simple client for GFS (Google File System, a distributed file system), it provides the following methods:
//
// read(filename). Read the file with given filename from GFS.
// write(filename, content). Write a file with given filename & content to GFS.
// There are two private methods that already implemented in the base class:
//
// readChunk(filename, chunkIndex). Read a chunk from GFS.
// writeChunk(filename, chunkIndex, chunkData). Write a chunk to GFS.
// To simplify this question, we can assume that the chunk size is chunkSize bytes. (In a real world system, it is 64M). The GFS Client's job is splitting a file into multiple chunks (if need) and save to the remote GFS server. chunkSize will be given in the constructor. You need to call these two private methods to implement read & write methods.
//
// Have you met this question in a real interview?
// Example
// GFSClient(5)
// read("a.txt")
// >> null
// write("a.txt", "World")
// >> You don't need to return anything, but you need to call writeChunk("a.txt", 0, "World") to write a 5 bytes chunk to GFS.
// read("a.txt")
// >> "World"
// write("b.txt", "111112222233")
// >> You need to save "11111" at chink 0, "22222" at chunk 1, "33" at chunk 2.
// write("b.txt", "aaaaabbbbb")
// read("b.txt")
// >> "aaaaabbbbb"


/* Definition of BaseGFSClient
 * class BaseGFSClient {
 *     private Map<String, String> chunk_list;
 *     public BaseGFSClient() {}
 *     public String readChunk(String filename, int chunkIndex) {
 *         // Read a chunk from GFS
 *     }
 *     public void writeChunk(String filename, int chunkIndex,
 *                            String content) {
 *         // Write a chunk to GFS
 *     }
 * }
 */
public class GFSClient extends BaseGFSClient {
    public int size;
    // 实际上存储和查看是调用 readChunk & writeChunk， 我们只需要分割出 chunk 再分别调用就好了
    // 那我们就需要知道这个文件有多少个 chunk 然后才能分别调用多少次
    public HashMap<String, Integer> chunkNum;
    public GFSClient(int chunkSize) {
        // initialize your data structure here
        size = chunkSize;
        chunkNum = new HashMap<String, Integer>();
    }

    // @param filename a file name
    // @return conetent of the file given from GFS
    public String read(String filename) {
        // Write your code here
        if (!chunkNum.containsKey(filename)) {
            return null;
        }
        int num = chunkNum.get(filename);
        String content = "";
        for (int i = 0; i < num; i++) {
            content += readChunk(filename, i);
        }
        return content;
    }

    // @param filename a file name
    // @param content a string
    // @return void
    public void write(String filename, String content) {
        // Write your code here
        // from example, if we write a existed filename, it would cover original one
        int n = content.length();
        int num = n / size;
        if (n % size != 0) {
            num++;
        }
        // record it in HashMap
        chunkNum.put(filename, num);
        // call writeChunk method
        for (int i = 0; i < num; i++) {
            int end = (i + 1) * size;
            if (i + 1 == num) {
                end = n;
            }
            String sub = content.substring(i * size, end);
            writeChunk(filename, i, sub);
        }
    }
}


answer:
