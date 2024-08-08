/*
 * @lc app=leetcode id=347 lang=kotlin
 *
 * [347] Top K Frequent Elements
 */

// @lc code=start
class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val frequencyMap = mutableMapOf<Int, Int>()

        // 各要素の出現回数をカウント
        for (num in nums) {
            frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1
        }

        // 出現回数が多い順に要素をソートして上位 k 個を取得
        val topKFrequentElements = frequencyMap.entries
            .sortedByDescending { it.value }
            .take(k)
            .map { it.key }
            .toIntArray()

        return topKFrequentElements 
    }
}
// @lc code=end