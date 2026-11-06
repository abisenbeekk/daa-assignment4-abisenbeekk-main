### DAA Assignment4 - Report and Defense (50 points)

### Problem 1 - Counting Bits **(5 points)**.

**Approach:**
This problem is solved using Dynamic Programming combined with Bitwise Operations. We create an array `ans` of length `n + 1`. To find the number of `1`s in the binary representation of any number `i`, we use the following formula:
`ans[i] = ans[i >> 1] + (i & 1)`

Where:
* `i >> 1` — this is equivalent to dividing `i` by 2 (right bit shift). It gives us the count of `1`s in the half of `i` (which has already been calculated).
* `i & 1` — this checks the least significant bit (LSB) of `i`. It returns `1` if the number is odd, and `0` if it is even.

Thus, the calculation for each number takes O(1) time, resulting in an overall time complexity of O(n) for the algorithm.

**Complexity:**
* **Time Complexity:** O(n) — we iterate through a single loop from 1 to n.
* **Space Complexity:** O(n) — an additional array of length n+1 is used to store the results (as required by the problem constraints).


### Problem 2 - Jump Game **(5 points)**.

**Approach:**
The problem is solved using a Greedy algorithm. The goal is to find the minimum number of jumps. We maintain two variables: `currentEnd`, which represents the furthest index we can reach with the current number of jumps, and `farthest`, which represents the furthest index we can reach from all the indices we have visited so far.

We iterate through the array. For every index `i`, we update `farthest` by taking the maximum of its current value and `i + nums[i]`. When the iteration reaches `currentEnd` (meaning we have explored all possibilities from the current jump), we must make another jump. We increment the `jumps` counter and update `currentEnd` to `farthest`. We stop when `currentEnd` reaches or exceeds the last index.

**Complexity:**
* **Time Complexity:** O(n) — we iterate through the array exactly once. It is highly efficient.
* **Space Complexity:** O(1) — we only use a few constant integer variables (`jumps`, `currentEnd`, `farthest`).


### Problem 3 - Min Cost Climbing Stairs **(5 points)**.

**Approach:**
This problem is solved using Dynamic Programming with Space Optimization. The idea is that the minimum cost to reach the `i`-th step depends only on the minimum cost of reaching the two previous steps (`i-1` and `i-2`).
The recurrence relation is: `costToReach[i] = min(costToReach[i-1] + cost[i-1], costToReach[i-2] + cost[i-2])`.
Instead of creating an array of size `n`, we can just keep track of the last two minimum costs using two variables (`prev1` and `prev2`), because we only ever need the previous two values to calculate the next one. We initialize both to 0 since we can start at step 0 or step 1 for free. The final answer will be the cost to reach step `n` (the top).

**Complexity:**
* **Time Complexity:** O(n) — we iterate through the `cost` array exactly once.
* **Space Complexity:** O(1) — we only use two integer variables (`prev1` and `prev2`) to store the last two costs, which is optimal.


### Problem 4 - Longest Increasing Subsequence **(5 points)**.

**Approach:**
To solve the "Follow up" requirement of achieving O(n log n) time complexity, I used the Patience Sorting algorithm combined with Binary Search.
We maintain an array `dp`, where `dp[i]` stores the smallest possible tail value of an increasing subsequence of length `i+1`.
For each number in the input array, we use `Arrays.binarySearch()` to find the position where this number should be placed in `dp`. If the number is larger than all elements in `dp`, it extends the longest subsequence (we increment `len`). If it's smaller, it replaces the first element in `dp` that is greater than or equal to it. This ensures that we always keep the smallest possible tail for any given subsequence length, maximizing the chance to extend it later.

**Complexity:**
* **Time Complexity:** O(n log n) — we iterate through the array (n times), and for each element, we perform a binary search (log n) on the `dp` array. This is much faster than the standard O(n^2) Dynamic Programming approach.
* **Space Complexity:** O(n) — we use an additional `dp` array of size n.




### Problem 5 - Longest Common Subsequence **(5 points)**.

**Approach:**
This problem is solved using 2D Dynamic Programming. We create a 2D array `dp` of size `(m+1) x (n+1)`, where `m` and `n` are the lengths of `text1` and `text2` respectively.
`dp[i][j]` represents the length of the Longest Common Subsequence (LCS) for the prefixes `text1[0...i-1]` and `text2[0...j-1]`.

We iterate through both strings. For each character pair:
1. If the characters match (`text1.charAt(i-1) == text2.charAt(j-1)`), it means we can extend the LCS found so far by 1. So, `dp[i][j] = dp[i-1][j-1] + 1`.
2. If the characters do not match, the LCS length will be the maximum of excluding the current character from `text1` (`dp[i-1][j]`) or excluding it from `text2` (`dp[i][j-1]`). So, `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`.

The final answer is stored in `dp[m][n]`.

**Complexity:**
* **Time Complexity:** O(m * n) — we fill a 2D table of size m x n, where each cell is computed in O(1) time.
* **Space Complexity:** O(m * n) — for the 2D `dp` array. (Note: This can be optimized to O(min(m, n)) using a 1D array, but the 2D approach is standard and within the constraints).



### Problem 6 - Edit Distance **(5 points)**.

**Approach:**
This problem is solved using 2D Dynamic Programming. We create a 2D table `dp` of size `(m+1) x (n+1)`, where `m` and `n` are the lengths of `word1` and `word2`. The value `dp[i][j]` represents the minimum number of operations required to convert the first `i` characters of `word1` into the first `j` characters of `word2`.

**Base Cases:**
- `dp[i][0] = i`: To convert a string of length `i` to an empty string, we need `i` delete operations.
- `dp[0][j] = j`: To convert an empty string to a string of length `j`, we need `j` insert operations.

**Recurrence Relation:**
For each character pair `word1[i-1]` and `word2[j-1]`:
1. If they match, no operation is needed: `dp[i][j] = dp[i-1][j-1]`.
2. If they don't match, we consider the minimum of three operations and add 1:
    - **Replace:** `dp[i-1][j-1] + 1`
    - **Delete:** `dp[i-1][j] + 1` (delete from word1)
    - **Insert:** `dp[i][j-1] + 1` (insert into word1)

   `dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])`

The final answer is at `dp[m][n]`.

**Complexity:**
* **Time Complexity:** O(m * n) — we fill a 2D table of size m x n.
* **Space Complexity:** O(m * n) — for the DP table. (Can be optimized to O(min(m, n)) using two 1D arrays).



### Problem 7 - Longest Palindromic Subsequence **(5 points)**.

**Approach:**
This problem is solved using 2D Dynamic Programming. We create a 2D array `dp` of size `n x n`, where `n` is the length of string `s`. `dp[i][j]` represents the length of the Longest Palindromic Subsequence (LPS) in the substring `s[i...j]`.

**Base Case:**
Every single character is a palindrome of length 1. Therefore, for all `i`, `dp[i][i] = 1`.

**Recurrence Relation:**
We build the table by increasing the length of substrings. For a substring `s[i...j]`:
1. If the first and last characters match (`s.charAt(i) == s.charAt(j)`), they can form the boundaries of a palindrome. Thus, the length increases by 2 plus the LPS of the inner substring: `dp[i][j] = dp[i+1][j-1] + 2`. (Special case: if length is 2, `dp[i][j] = 2`).
2. If the characters do not match, the LPS length is the maximum of the LPS when we exclude the first character (`dp[i+1][j]`) or exclude the last character (`dp[i][j-1]`). Thus, `dp[i][j] = max(dp[i+1][j], dp[i][j-1])`.

The final answer is stored in `dp[0][n-1]`, which represents the entire string.

**Complexity:**
* **Time Complexity:** O(n^2) — we fill a 2D table of size n x n, and each cell takes O(1) time.
* **Space Complexity:** O(n^2) — for the 2D `dp` array.


### Problem 8 - Palindromic Substrings **(5 points)**.

**Approach:**
This problem is efficiently solved using the "Expand Around Center" technique. A palindrome is symmetric around its center. Therefore, a palindromic substring can have either:
1. An odd length (e.g., "aba"), where the center is a single character.
2. An even length (e.g., "abba"), where the center is between two characters.

We iterate through each character in the string and treat it as a potential center. For each index `i`, we do two expansions:
- `expandAroundCenter(s, i, i)` for odd-length palindromes.
- `expandAroundCenter(s, i, i + 1)` for even-length palindromes.

The helper method `expandAroundCenter` expands outwards from the given `left` and `right` pointers as long as the characters match and we are within the string bounds. It returns the total count of palindromes found from that specific center.

**Complexity:**
* **Time Complexity:** O(n^2) — In the worst-case scenario (e.g., a string of identical characters like "aaaaa"), expanding from each of the `n` centers takes O(n) time. So, the total time is O(n^2).
* **Space Complexity:** O(1) — We only use a few integer variables (`count`, `left`, `right`) for the expansion. No additional data structures or recursion stacks are used.



### Problem 9 - Maximum Subarray **(5 points)**.

**Approach:**
The primary solution implemented is **Kadane's Algorithm**, which is an optimal Dynamic Programming approach for this problem.
The idea is to iterate through the array while maintaining two variables:
1. `currentMax`: The maximum sum of a subarray ending at the current index `i`.
2. `maxSoFar`: The maximum sum found anywhere in the array so far.

For each element `nums[i]`, we decide whether to add it to the existing subarray (`currentMax + nums[i]`) or start a new subarray from the current element (`nums[i]`). We take the maximum of these two choices. Then, we update `maxSoFar` with the maximum of itself and `currentMax`.

*(Note on Follow-up: For the Divide and Conquer approach, we would split the array into two halves. The maximum subarray must lie entirely in the left half, entirely in the right half, or cross the midpoint. We would recursively find the max in left and right, and calculate the max crossing subarray in O(n) time, resulting in an O(n log n) total time. However, Kadane's algorithm is strictly better with O(n) time).*

**Complexity:**
* **Time Complexity:** O(n) — We iterate through the array exactly once, making it a single-pass algorithm.
* **Space Complexity:** O(1) — We only use two integer variables to store the sums, requiring constant extra space.


### Problem 10 - House Robber **(5 points)**.

**Approach:**
This problem is solved using Dynamic Programming with Space Optimization. The core constraint is that we cannot rob two adjacent houses. Therefore, for any given house `i`, we have two choices:
1. **Rob house `i`**: We add the money of house `i` to the maximum loot we could get from houses up to `i-2` (since `i-1` must be skipped).
2. **Do not rob house `i`**: We take the maximum loot we could get from houses up to `i-1`.

The recurrence relation is: `maxLoot[i] = max(maxLoot[i-1], maxLoot[i-2] + nums[i])`.

Since calculating the loot for house `i` only requires the results from `i-1` and `i-2`, we do not need a full DP array. Instead, we use two variables (`prev1` and `prev2`) to keep track of the last two maximum values, reducing the space complexity to O(1).

**Complexity:**
* **Time Complexity:** O(n) — We iterate through the `nums` array exactly once.
* **Space Complexity:** O(1) — We only use two integer variables (`prev1` and `prev2`) to store the previous states, making it highly memory-efficient.

