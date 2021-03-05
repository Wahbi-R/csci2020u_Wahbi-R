package sample;

public class StudentRecord {
    private String studId;
    private String studName;
    private float midtermGrade;
    private float assignGrade;
    private float finalExam;
    private float finalMark;
    private String letterGrade;

    public StudentRecord(String studId, float midtermGrade, float assignGrade, float finalExam){
        this.studId = studId;
        this.midtermGrade = midtermGrade;
        this.assignGrade = assignGrade;
        this.finalExam = finalExam;

    }

    public String getStudentId(){

        return studId;
    }

    public String getStudentName(){

        return studName;
    }

    public float getMidtermGrade(){
        return midtermGrade;
    }

    public float getAssignmentGrade(){
        return assignGrade;
    }

    public float getFinalExam(){
        return finalExam;
    }

    public float getFinalMark(){
        finalMark = assignGrade*0.2f+midtermGrade*0.3f+finalExam*0.5f;
        return finalMark;
    }
    public String getLetterGrade(){
        if(finalMark < 50f) { letterGrade = "F"; }
        if(finalMark >= 50f && finalMark < 60f){ letterGrade = "D"; }
        if(finalMark >= 60f && finalMark < 70f){ letterGrade = "C"; }
        if(finalMark >= 70f && finalMark < 80f){ letterGrade = "B"; }
        if(finalMark >= 80f && finalMark < 100f){ letterGrade = "A"; }
        if(finalMark > 100f) { letterGrade = "How even"; }
        System.out.println(finalMark);
        return letterGrade;
    }
}
