public class Grade implements Comparable<Grade> {
    private int id; 
    private int studentId;
    private String courseName;
    private double grade;

    public Grade(int id, int studentId, String courseName, double grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public int compareTo(Grade other) {
        return this.courseName.compareTo(other.courseName);
    }
}
