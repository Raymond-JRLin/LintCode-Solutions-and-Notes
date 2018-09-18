// 560. Friendship Service
// Description
// Support follow & unfollow, getFollowers, getFollowings.
//
// Have you met this question in a real interview?
// Example
// follow(1, 3)
// getFollowers(1) // return [3]
// getFollowings(3) // return [1]
// follow(2, 3)
// getFollowings(3) // return [1,2]
// unfollow(1, 3)
// getFollowings(3) // return [2]


public class FriendshipService {

    Map<Integer, Set<Integer>> following;
    Map<Integer, Set<Integer>> follower;

    public FriendshipService() {
        // do intialization if necessary
        following = new HashMap<>();
        follower = new HashMap<>();
    }

    /*
     * @param user_id: An integer
     * @return: all followers and sort by user_id
     */
    public List<Integer> getFollowers(int user_id) {
        // write your code here
        if (!follower.containsKey(user_id)) {
            return Collections.emptyList();
        }

        return new ArrayList<>(follower.get(user_id));
    }

    /*
     * @param user_id: An integer
     * @return: all followings and sort by user_id
     */
    public List<Integer> getFollowings(int user_id) {
        // write your code here
        if (!following.containsKey(user_id)) {
            return Collections.emptyList();
        }

        return new ArrayList<>(following.get(user_id));
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int to_user_id, int from_user_id) {
        // write your code here
        // we should use TreeSet() here because question requires "getFollowes(): return all followers and sort by user_id", HashSet() doesn't have order inside
        Set<Integer> set1 = following.getOrDefault(from_user_id, new TreeSet<>());
        set1.add(to_user_id);
        following.put(from_user_id, set1);
        Set<Integer> set2 = follower.getOrDefault(to_user_id, new TreeSet<>());
        set2.add(from_user_id);
        follower.put(to_user_id, set2);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int to_user_id, int from_user_id) {
        // write your code here
        if (following.containsKey(from_user_id)) {
            Set<Integer> set = following.get(from_user_id);
            set.remove(to_user_id);
            following.put(from_user_id, set);
        }

        if (follower.containsKey(to_user_id)) {
            Set<Integer> set = follower.get(to_user_id);
            set.remove(from_user_id);
            follower.put(to_user_id, set);
        }
    }
}


answer:

public class FriendshipService {

    private Map<Integer, Set<Integer>> followers, followings;

    public FriendshipService() {
        // initialize your data structure here.
        this.followers = new HashMap<Integer, Set<Integer>>();
        this.followings = new HashMap<Integer, Set<Integer>>();
    }

    // @param user_id an integer
    // return all followers and sort by user_id
    public List<Integer> getFollowers(int user_id) {
        // Write your code here
        if (!followers.containsKey(user_id))
            return new ArrayList<Integer>();

        return new ArrayList<Integer>(followers.get(user_id));
    }

    // @param user_id an integer
    // return all followings and sort by user_id
    public List<Integer>  getFollowings(int user_id) {
        // Write your code here
        if (!followings.containsKey(user_id))
            return new ArrayList<Integer>();

        return new ArrayList<Integer>(followings.get(user_id));
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int to_user_id, int from_user_id) {
        // Write your code here
        if (!followers.containsKey(to_user_id))
            followers.put(to_user_id, new TreeSet<Integer>());

        followers.get(to_user_id).add(from_user_id);

        if (!followings.containsKey(from_user_id))
            followings.put(from_user_id, new TreeSet<Integer>());

        followings.get(from_user_id).add(to_user_id);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int to_user_id, int from_user_id) {
        // Write your code here
        if (followers.containsKey(to_user_id))
            followers.get(to_user_id).remove(from_user_id);

        if (followings.containsKey(from_user_id))
            followings.get(from_user_id).remove(to_user_id);
    }
}
