
//Revision required

class Twitter {
    //to track that which tweet was made before 
    int count;
    //This is how you use a hashmap and hashset in java
    HashMap<Integer, List<int[]>> tweetmap;
    HashMap<Integer, HashSet<Integer>> followermap;

    public Twitter() {
        count = 0;
        tweetmap = new HashMap<>();
        followermap = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        tweetmap.computeIfAbsent(userId, k -> new ArrayList<>());
        tweetmap.computeIfPresent(userId, (k,v) -> {
            v.add(new int[]{count,tweetId});
            return v;
        });
        count++;
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res=new ArrayList<>();
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b) -> Integer.compare(b[0],a[0]));
        
        //It ensures that the user is present in the followerMap. If not, it initializes an entry for the user with an empty set of followees. This is done to ensure that the user's own tweets are included in their news feed.
        followermap.computeIfAbsent(userId,k->new HashSet<>());
        
        //It adds the user's own ID (userId) to their set of followees. This is also done to ensure that the user's own tweets are included in their news feed.
        followermap.get(userId).add(userId);

        //iterating over tweetmap and populating the pq with the latest tweet timestamp, tweet id and followeeid
        followermap.get(userId).forEach((followeeId)->{
            if(tweetmap.containsKey(followeeId)){
                int i= tweetmap.get(followeeId).size()-1;
                int[] tweet = tweetmap.get(followeeId).get(i);
                pq.add(new int[]{tweet[0], tweet[1], followeeId, --i});  
            }
        });

        //Iterating over pq and populating the result list
        //he loop continues until either there are no more tweets in the priority queue or the result list res reaches a size of 10.
        while(!pq.isEmpty() && res.size() < 10){
            int[] tweetdata= pq.poll();
            res.add(tweetdata[1]);

            //If there are more tweets from the same followee (indicated by tweetdata[3] being greater than  or equal to 0), it adds the next tweet from that followee back to the priority queue.
            if(tweetdata[3] >= 0){
                int[] tweet = tweetmap.get(tweetdata[2]).get(tweetdata[3]);
                pq.add(new int[]{tweet[0], tweet[1], tweetdata[2], --tweetdata[3]});
            }
        }
        return res;

    }
    
    public void follow(int followerId, int followeeId) {
        followermap.computeIfAbsent(followerId, k -> new HashSet<>());
        followermap.computeIfPresent(followerId, (k, v) -> {
            v.add(followeeId);
            return v;
        }); 
    }
    
    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> set= followermap.computeIfAbsent(followerId, k->new HashSet<>());
        if(set.contains(followeeId)){
            set.remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
