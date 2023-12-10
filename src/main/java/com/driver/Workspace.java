package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        if(calendar.size()<2)return calendar.size();
        Collections.sort(calendar, Comparator.comparing(Meeting::getEndTime));
        LocalTime prevMeetEnd = calendar.get(0).getStartTime();
        int count = 1;
        for(int i=1;i< calendar.size();i++){
            LocalTime st = calendar.get(i).getStartTime();
            LocalTime end = calendar.get(i).getStartTime();
            if(st.isBefore(prevMeetEnd)){
                LocalTime newEnd = end.isBefore(prevMeetEnd)?end:prevMeetEnd;
                prevMeetEnd = newEnd;
            }else count++;
        }
        return count;
    }
}
