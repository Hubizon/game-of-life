package com.example.hubert.gameoflife.Work;

import com.example.hubert.gameoflife.Education.Subject;

public class Job {
    private String name;
    private int salary;
    private int salaryNo2;
    private int salaryNo3;
    private int markRatio;
    private String subjectNameNeededToWork;
    private int additionalSkillsRes;

    public Job(String name, int salary, int salaryNo2, int salaryNo3, int markRatio, String subjectNameNeededToWork, int additionalSkillsRes)
    {
        this.name = name;
        this.salary = salary;
        this.salaryNo2 = salaryNo2;
        this.salaryNo3 = salaryNo3;
        this.markRatio = markRatio;
        this.subjectNameNeededToWork = subjectNameNeededToWork;
        this.additionalSkillsRes = additionalSkillsRes;
    }

    public String getName() { return name; }
    public int getSalary() { return salary; }
    public int getSalaryNo2() { return salaryNo2; }
    public int getSalaryNo3() { return salaryNo3; }
    public int getMarkRatio() { return markRatio; }
    public String getSubjectNameNeededToWork() { return subjectNameNeededToWork; }
    public int getAdditionalSkillsRes() { return additionalSkillsRes; }
}
