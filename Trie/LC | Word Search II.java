package Trie;


//revision needed for sure
class Solution {
    private static int COLS;
    private static int ROWS;
    private Trie currentTrie;
    class Trie{
        public HashMap<Character, Trie> links;
        public boolean flag;
        public int refs=0; //maybe to check wether there are references after this or not

        public Trie(){
            links=new HashMap<>();
        }

        public void addWord(String word){
            currentTrie=this;
            currentTrie.refs += 1;
            for(int i=0; i<word.length(); i++){
                if(!currentTrie.links.containsKey(word.charAt(i))){
                    currentTrie.links.put(word.charAt(i), new Trie());
                }
                currentTrie=currentTrie.links.get(word.charAt(i));
                currentTrie.refs += 1;//note
            }
            currentTrie.flag=true;
        }

        public void removeWord(String word){
            currentTrie=this;
            currentTrie.refs -= 1;
            for(int i=0; i<word.length();i++){
                if(currentTrie.links.containsKey(word.charAt(i))){
                    currentTrie=currentTrie.links.get(word.charAt(i));
                    currentTrie.refs -= 1;
                }
            }
        }

    }
    public List<String> findWords(char[][] board, String[] words) {
        Trie root=new Trie();
        for(String word : words){
            root.addWord(word);
        }

        ROWS=board.length;
        COLS=board[0].length;

        HashSet<String> res=new HashSet<>();
        HashSet<String> visit=new HashSet<>();

        for(int r=0; r< ROWS; r++){
            for(int c=0; c < COLS; c++){
                dfs(r, c, root, "", res, visit, board, root);
            }
        }

        return new ArrayList<>(res);
    }

    public void dfs(int r, int c, Trie node, String word, HashSet<String> res, 
    HashSet<String> visit, char[][] board, Trie root){
        if(r < 0|| c<0|| r==ROWS|| c==COLS|| !node.links.containsKey(board[r][c])|| 
         node.links.get(board[r][c]).refs < 1 || visit.contains(r + "-" + c) )return; 

        visit.add(r + "-" + c);
        node=node.links.get(board[r][c]);
        word += board[r][c];
        if(node.flag){
            res.add(word);
            node.flag=false;
            root.removeWord(word);
        }

        dfs(r+1, c, node, word, res, visit, board, root);
        dfs(r-1, c, node, word, res, visit, board, root);
        dfs(r, c+1, node, word, res, visit, board, root);
        dfs(r, c-1, node, word, res, visit, board, root);
        visit.remove(r + "-" + c);

    }
}

//O(m*n* (4*3^L-1)) where L is the length of the max length of words and m=rows and n=columns : See leetcode for more explanation

