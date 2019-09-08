// 501. Design Twitter
// 中文English
// Implement a simple twitter. Support the following method:
//
// postTweet(user_id, tweet_text). Post a tweet.
// getTimeline(user_id). Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
// getNewsFeed(user_id). Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
// follow(from_user_id, to_user_id). from_user_id followed to_user_id.
// unfollow(from_user_id, to_user_id). from_user_id unfollowed to to_user_id.
// Example
// Example 1:
//
// Input:
//   postTweet(1, "LintCode is Good!!!")
//   getNewsFeed(1)
//   getTimeline(1)
//   follow(2, 1)
//   getNewsFeed(2)
//   unfollow(2, 1)
//   getNewsFeed(2)
// Output:
//   1
//   [1]
//   [1]
//   [1]
//   []
// Example 2:
//
// Input:
//   postTweet(1, "LintCode is Good!!!")
//   getNewsFeed(1)
//   getTimeline(1)
//   follow(2, 1)
//   postTweet(1, "LintCode is best!!!")
//   getNewsFeed(2)
//   unfollow(2, 1)
//   getNewsFeed(2)
// Output:
//   1
//   [1]
//   [1]
//   2
//   [2,1]
//   []


/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */

class Pair {
    public int time;
    public Tweet tweet;
    public Pair(int time, Tweet tweet) {
        this.time = time;
        this.tweet = tweet;
    }
}

class pairComparator implements Comparator{
    @Override
    public int compare(Object obj1, Object obj2) {
        Pair p1 = (Pair) obj1;
        Pair p2 = (Pair) obj2;
        return p2.time - p1.time; // desceding order by time - lates -> oldest
    }
}

public class MiniTwitter {

    Map<Integer, List<Pair>> tweets; // <user_id, tweets>
    // we cannot use stack here, thought it's convenient to pop as time, but the tweets would be disappear because of poping
    Map<Integer, Set<Integer>> follows; // <user_id, followings>
    int time;

    public MiniTwitter() {
        // initialize your data structure here.
        tweets = new HashMap<>();
        follows = new HashMap<>();
        time = 0;
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        //  Write your code here
        Tweet newTweet = Tweet.create(user_id, tweet_text);
        Pair curr = new Pair(time, newTweet);
        time++;
        if (tweets.containsKey(user_id)) {
            List<Pair> list = tweets.get(user_id);
            list.add(curr);
            tweets.put(user_id, list);
        } else {
            List<Pair> list = new ArrayList<>();
            list.add(curr);
            tweets.put(user_id, list);
        }
        return newTweet;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // Write your code here
        // 注意这里需要拿到此 user 及其 following 的 timeline 然后按照时间取最新的 10 条
        List<Pair> list = new ArrayList<>();
        // get this user's recent 10
        if (tweets.containsKey(user_id)) {
            List<Pair> userTweets = tweets.get(user_id);
            list.addAll(getRecentTen(userTweets));
        }
        // get user's followings and their recent 10
        if (follows.containsKey(user_id)) {
            Set<Integer> followings = follows.get(user_id);
            for (int following : followings) {
                if (tweets.containsKey(following)) {
                    List<Pair> followingTweets = tweets.get(following);
                    list.addAll(getRecentTen(followingTweets));
                }
            }
        }
        // sort: because we create a Class for Comparator, so we should new one object to use it
        Collections.sort(list, new pairComparator());
        // get recent 10 news feed
        List<Tweet> result = new ArrayList<>();
        int size = 10;
        if (list.size() < 10) {
            size = list.size();
        }
        for (int i = 0; i < size; i++) {
            // i < size, which may be updated, because this list may be less than 10
            result.add(list.get(i).tweet);
        }
        return result;
    }

    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
        // Write your code here
        // get user's recent 10
        List<Pair> list = new ArrayList<>();
        if (tweets.containsKey(user_id)) {
            List<Pair> userTweets = tweets.get(user_id);
            list.addAll(getRecentTen(userTweets));
        }
        // sort by time - do not forget
        Collections.sort(list, new pairComparator());
        // get recent 10 tweets
        List<Tweet> result = new ArrayList<>();
        for (Pair pair : list) {
            result.add(pair.tweet);
        }
        return result;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
        if (follows.containsKey(from_user_id)) {
            Set<Integer> following = follows.get(from_user_id);
            following.add(to_user_id);
            follows.put(from_user_id, following);
        } else {
            Set<Integer> following = new HashSet<Integer>();
            following.add(to_user_id);
            follows.put(from_user_id, following);
        }
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
        if (follows.containsKey(from_user_id)) {
            Set<Integer> following = follows.get(from_user_id);
            following.remove(to_user_id);
            follows.put(from_user_id, following);
        }

    }

    private List<Pair> getRecentTen(List<Pair> list) {
        List<Pair> result = new ArrayList<>();
        int size = 10;
        if (list.size() < 10) {
            size = list.size();
        }
        // for (int i = size - 1; i >= 0; i--) {
        //     result.add(list.get(i));
        // }
        // return result;
        return list.subList(list.size() - size, list.size());
    }
}
