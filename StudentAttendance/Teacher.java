package SystemDesign.StudentAttendance;
class Teacher {
    int teacherId;
    String name;
    String section;
    String password;

    public Teacher(int teacherId, String name, String section, String password) {
        this.teacherId = teacherId;
        this.name = name;
        this.section = section;
        this.password = password;
    }

    public boolean login(int id, String pass) {
        return this.teacherId == id && this.password.equals(pass);
    }
}
