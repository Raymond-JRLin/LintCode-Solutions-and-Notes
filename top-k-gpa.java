// 1571. Top K GPA
// Description
// Given a List, each element in the list represents a student's StudentId and GPA. Return the StudentId and GPA of the top K GPA,in the original order.
//
// 1.if k > the number of students, Return all student information.
// 2.Both StudentId and GPA are String.
// 3.The GPA between two students is different
//
// Have you met this question in a real interview?
// Example
// Given：
//
// List = [["001","4.53"],["002","4.87"],["003","4.99"]]
// k = 2
// Return:
//
// [["002","4.87"],["003","4.99"]]


public class Solution {
    /**
     * @param list: the information of studnet
     * @param k:
     * @return: return a list
     */
    public List<List<String>> topKgpa(List<List<String>> list, int k) {
        // Write your code here
        if (list == null || list.size() == 0) {
            return Collections.emptyList();
        }
        if (k <= 0) {
            return Collections.emptyList();
        }
        if (k >= list.size()) {
            return list;
        }

        // return mytry(list, k);

        return method2(list, k);
    }

    private List<List<String>> method2(List<List<String>> list, int k) {
        // or we can use quick select to get the kth largest GPA, and then get top K
        // average O(N) time and O(N) space
        int n = list.size();
        double[] nums = new double[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Double.valueOf(list.get(i).get(1));
        }
        // 注意这里是 n - k， 因为 quick select 得到的是从小到大的排序
        double kthGPA = quickSelect(nums, n - k, 0, n - 1);
        List<List<String>> result = new ArrayList<>();
        for (List<String> l : list) {
            if (Double.valueOf(l.get(1)) >= kthGPA) {
                result.add(l);
            }
        }
        return result;
    }
    private double quickSelect(double[] nums, int k, int start, int end) {
        while (start < end) {
            int pivot = partition(nums, start, end); // pivot is index
            if (pivot < k) {
                start = pivot + 1;
            } else if (pivot > k) {
                end = pivot - 1;
            } else {
                return nums[pivot];
            }
        }
        return nums[start];
    }
    private int partition(double[] nums, int start, int end) {
        int pivot = start;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) {
                start++;
            }
            while (start <= end && nums[end] > nums[pivot]) {
                end--;
            }
            if (start > end) {
                break;
            }
            swap(nums, start, end);
        }
        swap(nums, pivot, end);
        return end;
    }
    private void swap(double[] nums, int start, int end) {
        double temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    private List<List<String>> mytry(List<List<String>> list, int k) {
        // PQ - min heap
        // O(NlogK) time and O(k) space
        PriorityQueue<Student> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.gpa.compareTo(o2.gpa);
        });
        for (List<String> l : list) {
            if (l.size() != 2) {
                continue;
            }
            Student curr = new Student(l.get(0), l.get(1));
            pq.offer(curr);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Student> temp = new ArrayList<>();
        while (!pq.isEmpty()) {
            temp.add(pq.poll());
        }
        Collections.sort(temp, (o1, o2) -> {
            return o1.id.compareTo(o2.id);
        });

        List<List<String>> result = new ArrayList<>();
        for (Student stu : temp) {
            result.add(Arrays.asList(stu.id, stu.gpa));
        }
        return result;
    }
    private class Student {
        private String id;
        private String gpa;

        public Student(String id, String gpa) {
            this.id = id;
            this.gpa = gpa;
        }
    }
}
