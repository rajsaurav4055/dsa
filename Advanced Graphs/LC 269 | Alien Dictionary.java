
//Topological sort
class Solution {
    public String alienOrder(String[] words) {
        //Step 1: Create data structures and find all unique letters
        Map<Character, List<Character>> adjListMap=new HashMap<>();
        Map<Character, Integer> inDegreeMap=new HashMap<>();
        for(String word : words){
            for(char c: word.toCharArray()){
                inDegreeMap.put(c,0);
                adjListMap.put(c,new ArrayList<>());
            }
        }

        //Step 2: Find all edges and build an adjList
        for(int i=0; i< words.length - 1; i++){
            String word1= words[i];
            String word2= words[i + 1];

            //Check that word2 is not a prefix of word1
            if(word1.length() > word2.length() && word1.startsWith(word2))return "";

            //Find the first difference and insert the corresponding relation
            for(int j=0; j< Math.min(word1.length(), word2.length()); j++){
                if(word1.charAt(j) != word2.charAt(j)){
                    adjListMap.get(word1.charAt(j)).add(word2.charAt(j));
                    inDegreeMap.put(word2.charAt(j), inDegreeMap.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

            //Step 3: BFS
            StringBuilder sb=new StringBuilder(); //Note****
            Queue<Character> queue=new LinkedList<>();
            //traverse over the indegree map and insert all the elements with indegree 0 in the queue
            for(Character c: inDegreeMap.keySet()){
                if(inDegreeMap.get(c).equals(0)){
                    queue.add(c);
                }
            }

            while(!queue.isEmpty()){
                Character c= queue.remove();
                sb.append(c);//Note***
                for(Character next : adjListMap.get(c)){
                    inDegreeMap.put(next, inDegreeMap.get(next) - 1);
                    if(inDegreeMap.get(next).equals(0)){
                        queue.add(next);
                    }
                }
            }

            if(sb.length() < inDegreeMap.size())return "";
            return sb.toString(); //Note*****
        }
}
//TC: O(C) where C is the total length of all the words in the input list added together
//SC: O(U + min(U^2, N)), where U is total number of unique letters and N is the total number of strings in the input list


//Takeaways
//1. Topological sorting
//2. How to traverse over a HashMap in Java
//3. How to create a string and append to it in Java


