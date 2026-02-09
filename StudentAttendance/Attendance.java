package SystemDesign.StudentAttendance;

import java.time.LocalDate;

class Attendance {
    int studentId;
    String section;
    LocalDate date;
    boolean present;

    public Attendance(int studentId, String section, boolean present) {
        this.studentId = studentId;
        this.section = section;
        this.present = present;
        this.date = LocalDate.now();
    }

    public String toString() {
        return "Student ID: " + studentId +
               " | Section: " + section +
               " | Date: " + date +
               " | Status: " + (present ? "Present" : "Absent");
    }


    
}
