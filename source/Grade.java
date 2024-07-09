class Grade {
    private int studentId;
    private String courseName;
    private double grade;

    public Grade(int studentId, String courseName, double grade) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
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
}