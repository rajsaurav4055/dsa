class Trie {
    static class Node{
        Node[] links=new Node[26];
        boolean flag=false;
        
        public boolean containsKey(char ch){
            return links[ch - 'a']!=null;
        }

        public void put(char ch, Node node){
            links[ch - 'a']=node;
        }

        public Node get(char ch){
            return links[ch - 'a'];
        }

        public void setEnd(){
            flag=true;
        }

        public boolean isEnd(){
            return flag;
        }       
    }

    private Node root;

    public Trie() {
        root=new Node();
    }
    
    public void insert(String word) {
        Node node=root;
        for(int i=0; i<word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i), new Node());
            }
            node=node.get(word.charAt(i));
        }
        node.setEnd();
    }
    
    public boolean search(String word) {
        Node node=root;
        for(int i=0; i<word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                return false;
            }
            node= node.get(word.charAt(i));
        }
        return node.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        Node node=root;
        for(int i=0; i<prefix.length(); i++){
            if(!node.containsKey(prefix.charAt(i))){
                return false;
            }
            node=node.get(prefix.charAt(i));
        }
        return true;
    }

    public static void main(String args[]){
        Trie trie=new Trie();
        trie.insert("apple")
        trie.search("apple")
        trie.search("app")
        trie.startsWith("app")
        trie.insert("app")
        trie.search("app")

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

 //TC:O(N) + O(N) + O(N): insert + search + prefixsearch
 //SC: O(N)