package Google - Previously asked;

class Solution {

    class RoomComparator implements Comparator<long[]>{
        public int compare(long[] r1, long[] r2){
            
            //If the end time of two rooms are same then compare the room number
            if(r1[1] == r2[1]){
                return Long.compare(r1[0],r2[0]);
            }

            //else compare the end time of the meetings
            return Long.compare(r1[1],r2[1]);
        }
    }
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        int[] numberOfMeetings =new int[n];
        //minHeap stores endtime of all the current ongoing meetings , the rooms that they are in
        PriorityQueue<long[]> minHeap = new PriorityQueue<>(n, new RoomComparator());
        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        
        //Populate the freeRooms pq 
        for(int i=0; i<n; i++){
            freeRooms.add(i);
        }

        int result=n;//this tracks the lowest room number
        int maxMeetingCount= 0;//this tracks the maximum count

        for(int i=0; i < meetings.length; i++){

            //Pehle check kro ki aage badhne se pehle koi room khali kiya jaa skta hai ya nahi.
            //Toh basically check kro ki abhi jo meeting ko process krne wale ho uska start time is same as the end time of any meeting in the heap. Agr hai toh uss meeting room ko khali kro taaki next meeting add ho skte in the below if else block
            while(!minHeap.isEmpty() && minHeap.peek()[1] <= (long)meetings[i][0]){
                long[] next= minHeap.poll();
                freeRooms.add((int)next[0]);
            }
            
            //With each meeting, check if there are any free rooms. If there are, then use the room with the smallest number.
            if(!freeRooms.isEmpty()){
                int availableRoom = freeRooms.poll();
                minHeap.add(new long[]{(long)availableRoom,(long)meetings[i][1]});
                numberOfMeetings[availableRoom]++;
                
                //The proper usage of || here is important. I ignored it and started getting errors because of this
                if(numberOfMeetings[availableRoom] > maxMeetingCount || (numberOfMeetings[availableRoom] == maxMeetingCount && result > availableRoom)){
                    maxMeetingCount = numberOfMeetings[availableRoom];
                    result = availableRoom;
                }
            }else{ //Otherwise, assign the meeting to the room whose meeting will end the soonest.
                long[] next= minHeap.poll();
                long nextAvailableTime = next[1];
                int nextAvailableRoom = (int)next[0];
                minHeap.add(new long[]{nextAvailableRoom, nextAvailableTime + (long)(meetings[i][1] - meetings[i][0])});
                numberOfMeetings[nextAvailableRoom]++;
                //updating maxMeetingCount and result variable
                if(numberOfMeetings[nextAvailableRoom] > maxMeetingCount || (numberOfMeetings[nextAvailableRoom] == maxMeetingCount && result > nextAvailableRoom)){
                    maxMeetingCount = numberOfMeetings[nextAvailableRoom];
                    result = nextAvailableRoom;
                }
            }
        }
        return result;
    }
}