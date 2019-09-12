// 1402. Recommend Friends
// 中文English
// Give n personal friends list, tell you user, find the person that user is most likely to know. (He and the user have the most common friends and he is not a friend of user)
//
// Example
// Example 1:
//
// Input:list = [[1,2,3],[0,4],[0,4],[0,4],[1,2,3]], user = 0
// Output:4
// Explanation:0 and 4 are not friends, and they have 3 common friends. So 4 is the 0 most likely to know.
// Example 2:
//
// Iuput: list = [[1,2,3,5],[0,4,5],[0,4,5],[0,5],[1,2],[0,1,2,3]], user = 0
// Output :4.
// Explanation: Although 5 and 0 have 3 common friends, 4 and 0 only have 2 common friends, but 5 is a 0's friend, so 4 is the 0 most likely to know.
// Notice
// n <= 500.
// The relationship between friends is mutual. (if B appears on a's buddy list, a will appear on B's friends list).
// Each person's friend relationship does not exceed m, m <= 3000.
// If there are two people who share the same number of friends as user, the smaller number is considered the most likely person to know.
// If user and all strangers have no common friends, return -1.


public class Solution {
    /**
     * @param friends: people's friends
     * @param user: the user's id
     * @return: the person who most likely to know
     */
    public int recommendFriends(int[][] friends, int user) {
        // Write your code here
        if (friends == null || friends.length == 0 || friends[0].length == 0) {
            return -1;
        }

        // return mytry(friends, user);

        return method2(friends, user);
    }

    private int method2(int[][] friends, int user) {
        int people = friends.length;
        // only get user's friends
        Set<Integer> set = new HashSet<>();
        for (int f : friends[user]) {
            set.add(f);
        }
        // no friend to recommend
        if (set.size() == people - 1) {
            return -1;
        }

        int result = -1;
        int max = 0; // common friends
        for (int i = 0; i < people; i++) {
            if (i == user || set.contains(i)) {
                // it's user or user's friend
                continue;
            }
            int count = 0;
            for (int f : friends[i]) {
                if (set.contains(f)) {
                    // it's a common friend
                    count++;
                }
            }
            // no common friend
            if (count == 0) {
                continue;
            }
            if (count > max) {
                max = count;
                result = i;
            } else if (count == max && i < result) {
                result = i;
            }
        }

        return result;
    }

    private int mytry(int[][] friends, int user) {
        int people = friends.length;
        // construct adjacent list
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < people; i++) {
            adj.add(new HashSet<Integer>());
        }
        for (int i = 0; i < people; i++) {
            for (int num : friends[i]) {
                adj.get(i).add(num);
            }
        }
        // no friend to recommend
        if (adj.get(user).size() == people - 1) {
            return -1;
        }

        PriorityQueue<Person> pq = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.commonFriend == o2.commonFriend) {
                    return Integer.compare(o1.name, o2.name);
                } else {
                    return Integer.compare(o2.commonFriend, o1.commonFriend);
                }
            }
        });

        for (int i = 0; i < people; i++) {
            if (i == user) {
                continue;
            }
            if (!adj.get(user).contains(i)) {
                // current i is not user's friend
                int commonFriend = 0;
                // to find their common friends
                if (adj.get(user).size() < adj.get(i).size()) {
                    commonFriend = getCommonFriend(adj, user, i);
                } else {
                    commonFriend = getCommonFriend(adj, i, user);
                }
                // attention: when they do have common friends, put into pq
                if (commonFriend != 0) {
                    Person person = new Person(i, commonFriend);
                    pq.offer(person);
                }

            }
        }

        // 一定要记住用了 pq 要查看有没有最后里面没有答案的情况
        if (pq.isEmpty()) {
            return -1;
        } else {
            return pq.peek().name;
        }
    }
    private int getCommonFriend(List<Set<Integer>> adj, int a, int b) {
        int count = 0;
        for (int num : adj.get(a)) {
            if (adj.get(b).contains(num)) {
                count++;
            }
        }
        return count;
    }
    private class Person {
        private int name;
        private int commonFriend;
        public Person(int name, int commonFriend) {
            this.name = name;
            this.commonFriend = commonFriend;
        }
    }
}
