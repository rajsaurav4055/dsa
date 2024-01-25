class TimeMap {
    
    HashMap<String,List<Pair<String, Integer>>> map;
    
    public TimeMap() {
        map= new HashMap<>();
    }
    
    //if the map doesn't contain a key, put the key and they add the pair
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key))map.put(key,new ArrayList<>());
        map.get(key).add(new Pair(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key))return "";
        List<Pair<String, Integer>> list=map.get(key);
        return binarySearch(list,timestamp);
    }

    public String binarySearch(List<Pair<String, Integer>> list, int timestamp){
        int start=0;
        int end=list.size()-1;
        int tempts=0;
        while(start < end){
            int mid= (start + end + 1)/2; //this logic is really important so that it is  biased to the right for even numbers and prevents infinite loop
            if(list.get(mid).getValue() <=timestamp){
                start=mid;
            }else{
                end= mid - 1;
            } 
                
        }
        return list.get(start).getValue() <= timestamp?list.get(start).getKey():"";
    }
}

//TC: O(logn) for search , set is O(1)
//SC: O(n)

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
