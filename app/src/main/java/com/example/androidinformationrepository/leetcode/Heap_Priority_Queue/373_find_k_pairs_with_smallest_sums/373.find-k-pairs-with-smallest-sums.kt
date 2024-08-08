/*
 * @lc app=leetcode id=373 lang=kotlin
 *
 * [373] Find K Pairs with Smallest Sums
 */

// @lc code=start
class Solution {
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {

        val mutableList = mutableListOf<Triple<Int, Int, Int>>()

        for (num1 in nums1) {
            for (num2 in nums2) {
                mutableList.add(Triple(i + j, i, j)) 
            } 
        }

        mutableList.sortBy { it.first }

        return pairs.take(k.coerceAtMost(pairs.size)).map { listOf(it.second, it.third) }

    }
}
// @lc code=end

