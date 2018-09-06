package com.example.hubert.gameoflife.Education;

public class Subject {

    private String subjectName;
    private int subjectMark;
    private int toAnotherMark = 250;
    public boolean IsTodaysHomeworkDone = false;

    public Subject(String subjectName, int subjectMark)
    {
        this.subjectName = subjectName;
        this.subjectMark = subjectMark;
    }

    public String getSubjectName() { return subjectName; }
    public int getSubjectMark() { return subjectMark; }

    public void increaseToAnotherMark(int toIncrease) { toAnotherMark += toIncrease; }
    public void decreaseToAnotherMark(int toDecrease) { toAnotherMark -= toDecrease; }

    public void IsAnotherMark()
    {
        if(toAnotherMark >= 1000)
            if(subjectMark != 6)
            {
                subjectMark++;
                toAnotherMark = 100;
            }
        else if(toAnotherMark <= 0)
            if(subjectMark != 1)
            {
                subjectMark--;
                toAnotherMark = 900;
            }
    }
}
