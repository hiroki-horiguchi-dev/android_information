/*
 * @lc app=leetcode id=703 lang=java
 *
 * [703] Kth Largest Element in a Stream
 */

// @lc code=start
class KthLargest {
    private int element;
    private ArrayList<Integer> array;

    public KthLargest(int k, int[] nums) {
        element = k;
        array = new ArrayList<>();
        for (int num : nums) {
            array.add(num);
        }
    }

    public int add(int val) {
        array.add(val);
        Collections.sort(array);
        return array.get(array.size() - element);
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
// @lc code=end

