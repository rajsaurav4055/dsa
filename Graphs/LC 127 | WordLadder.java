package Graphs;


//Questions to be asked
//Will the length of all the words be same as we are just changing one of the letters of the words ?
//Will all the letters be in lowercase or uppercase -> we might need to take care of this while coding ?
//Will all the words in the wordList be unique?
//Will beginWord be equal to endWord by any chance ?

public class WordLadderSolution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        //create adjacency list-> Better to use map to use adjacency list
        Map<String, List<String>> adjList=new HashMap<>();
        wordList.add(beginWord);//List me aise add krte hai
        for(String word: wordList){ //TC: n*m
            StringBuilder string= null;
            for(int i=0; i < word.length(); i++){
                string=new StringBuilder(word);
                string.setCharAt(i,'*');
                List<String> wordlist= adjList.getOrDefault(string.toString(),new ArrayList<String>());
                wordlist.add(word);
                adjList.put(string.toString(), wordlist);//Map me aise add krte -> same as C++
            }  
        }

        //Now after adjaceny list creation implement BFS
        Queue<String> queue=new LinkedList<>();
        queue.offer(beginWord);
        int step=1;
        Set<String> visitedset=new HashSet<>();
        visitedset.add(beginWord);
        StringBuilder string= null;
        while(!queue.isEmpty()){ //n*m*m
            step++;
            int size=queue.size();//this is necessary because we can't directly do for(int i=0; i < queue.size(); i++) because the size of the queue is changing inside the loop
            for(int i=0; i < size; i++){
                String str= queue.poll();

                for(int j=0; j< str.length(); j++){
                    string=new StringBuilder(str);
                    string.setCharAt(j,'*');

                    for(String pattern : adjList.get(string.toString())){
                        if(pattern.equals(endWord))return step;
                        if(visitedset.contains(pattern))continue;
                        queue.offer(pattern);
                        visitedset.add(pattern);
                    }
                }
            }
        }
        return 0;
        
    }
    //TC: given 'n' words and 'm' is the length of each word it is n*m^2
    //SC: adjList= N* m^2
    
}
