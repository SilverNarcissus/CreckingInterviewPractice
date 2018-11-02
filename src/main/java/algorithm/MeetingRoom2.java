package algorithm;

//Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
//
//For example,
//Given [[0, 30],[5, 10],[15, 20]],
//return 2.

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoom2 {
    public static void main(String[] args) {
        MeetingRoom2 meetingRoom2 = new MeetingRoom2();
        int[][] input = {{0, 30},{5, 10},{15, 20}};
        System.out.println(meetingRoom2.solve(input));

    }

    public int solve(int[][] time){
        if(time.length <= 1){
            return time.length;
        }
        Arrays.sort(time, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<Integer> endTime = new PriorityQueue<>();

        int result = 0;
        for(int i = 0; i < time.length; i++){
            while(!endTime.isEmpty() && time[i][0] >= endTime.peek()){
                endTime.poll();
            }

            endTime.add(time[i][1]);
            result = Math.max(result, endTime.size());
        }

        return result;
    }


}
