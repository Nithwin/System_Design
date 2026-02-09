package SystemDesign.StudentAttendance;
class Student {
    int studentId;
    String name;
    String section;
    String password;

    public Student(int studentId, String name, String section, String password) {
        this.studentId = studentId;
        this.name = name;
        this.section = section;
        this.password = password;
    }

    public boolean login(int id, String pass) {
        return this.studentId == id && this.password.equals(pass);
    }
}
