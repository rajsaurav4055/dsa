package Trie;

class WordDictionary {
    static class Node{
        Node[] links=new Node[26];
        boolean flag=false;
        public boolean containsKey(char ch){
            return links[ch - 'a']!=null;
        }
        public boolean matchKey(char ch){
            return links[ch - 'a']!=null || ch=='.';
        }

        public Node get(char ch){
            return links[ch - 'a'];
        }

        public void put(char ch, Node node){
            links[ch - 'a']=node;
        }

        public void setEnd(){
            flag=true;
        }


    }


    Node root;
    public WordDictionary() {
        root=new Node();
    }
    
    //O(n)
    public void addWord(String word) {
        Node node=root;
        for(int i=0; i<word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i), new Node());
            }
            node=node.get(word.charAt(i));
        }
        node.setEnd();
    }
    
    //this will have to be recursion as whenever we have a '.' we have to go through the links array of that node completely 
    //and search in all the possible paths

    //O(n^2)
    public boolean search(String word) {
        return searchUtil(word, 0, root);
    }

    private boolean searchUtil(String word, int index, Node node){
        for(int i=index; i<word.length(); i++){
            char ch=word.charAt(i);
            if(ch == '.'){
                for(Node temp : node.links){
                    if(temp!=null && searchUtil(word, i+1, temp)){
                        return true;
                    }
                }
                return false;
            }else{
                if(!node.containsKey(ch)){
                    return false;
                }
                node=node.get(ch);
            }
        }
        return node.flag;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */


 //TC: O(n) + O(n^2)
 //SC: O(n)
