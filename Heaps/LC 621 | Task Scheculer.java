//Greedy approach
//Total time = number of tasks + total idle slots
//Easiest approach
// class Solution {
//     public int leastInterval(char[] tasks, int n) {
//         int[] freq= new int[26];
//         for(int t:tasks){
//             freq[t - 'A']++;
//         }
//         Arrays.sort(freq);

//         //maximum frequency
//         int f_max = freq[25];
//         int idle_time= (f_max - 1)*n;

//         //Subtracting the rest of the slots filled by other characters from idle time
//         for(int i=freq.length - 2; i >= 0 && idle_time > 0; i--){
//             idle_time-= Math.min(f_max - 1, freq[i]);//We are taking the other as f_max -1 because of the case where 2 or more characters are more frequent, like for the example show in the Math approach.
//         }
//         idle_time = Math.max(0,idle_time);
//         return idle_time + tasks.length;
//     }
//     //TC: to populate freq array -> O(N). Sorting is done on a constant array of size 26 so any operation on that is in constant time. So overall O(N)
//     //SC: O(26) or O(1) for frequency array 
// }

//using Heaps
class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        int[] arr = new int[26];
        for (char c : tasks) arr[c - 'A']++;
        for (int val : arr) if (val > 0) pq.add(val);
        int time = 0;

        while ((!pq.isEmpty() || !q.isEmpty())) {
            time++;
            if (!pq.isEmpty()) {
                int val = pq.poll();
                val--;
                if (val > 0) q.add(new Pair(val, time + n));
            }

            if (!q.isEmpty() && q.peek().getValue() == time) pq.add(
                q.poll().getKey()
            );
        }
        return time;
    }
    //Same O(N) for TC and O(1) for SC
}