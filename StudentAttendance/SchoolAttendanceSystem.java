package SystemDesign.StudentAttendance;

import java.time.LocalDate;
import java.util.*;

public class SchoolAttendanceSystem {

    static Scanner sc = new Scanner(System.in);

    static List<Student> students = new ArrayList<>();
    static List<Teacher> teachers = new ArrayList<>();
    static List<Attendance> attendanceList = new ArrayList<>();

    static Admin admin = new Admin("admin", "1234");

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== SCHOOL ATTENDANCE SYSTEM =====");
            System.out.println("1. Student Login");
            System.out.println("2. Teacher Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> studentLogin();
                case 2 -> teacherLogin();
                case 3 -> adminLogin();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }


    static void studentLogin() {
        System.out.print("Student ID: ");
        int id = sc.nextInt();
        System.out.print("Password: ");
        String pass = sc.next();

        for (Student s : students) {
            if (s.login(id, pass)) {
                studentMenu(s);
                return;
            }
        }
        System.out.println("Login Failed!");
    }

    static void studentMenu(Student s) {
        while (true) {
            System.out.println("\n1. View Attendance Report");
            System.out.println("2. Logout");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> viewAttendanceReport(s);
                case 2 -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    static void viewAttendanceReport(Student s) {
        System.out.println("\n1. Daily");
        System.out.println("2. Weekly");
        System.out.println("3. Monthly");
        int choice = sc.nextInt();

        LocalDate today = LocalDate.now();
        boolean found = false;

        for (Attendance a : attendanceList) {
            if (a.studentId == s.studentId) {

                boolean show = false;

                if (choice == 1 && a.date.equals(today))
                    show = true;
                else if (choice == 2 && a.date.isAfter(today.minusDays(7)))
                    show = true;
                else if (choice == 3 && a.date.getMonth() == today.getMonth())
                    show = true;

                if (show) {
                    System.out.println(a);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No attendance records found.");
        }
    }

    static void teacherLogin() {
        System.out.print("Teacher ID: ");
        int id = sc.nextInt();
        System.out.print("Password: ");
        String pass = sc.next();

        for (Teacher t : teachers) {
            if (t.login(id, pass)) {
                teacherMenu(t);
                return;
            }
        }
        System.out.println("Login Failed!");
    }

    static void teacherMenu(Teacher t) {
        while (true) {
            System.out.println("\n1. Mark Attendance");
            System.out.println("2. Logout");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> markAttendance(t);
                case 2 -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    static void markAttendance(Teacher t) {
        System.out.println("Marking attendance for section: " + t.section);

        for (Student s : students) {
            if (s.section.equals(t.section)) {
                System.out.print("Is " + s.name + " present? (1-Yes / 0-No): ");
                int p = sc.nextInt();
                attendanceList.add(new Attendance(s.studentId, s.section, p == 1));
            }
        }
        System.out.println("Attendance marked successfully!");
    }



    static void adminLogin() {
        System.out.print("Admin Username: ");
        String u = sc.next();
        System.out.print("Admin Password: ");
        String p = sc.next();

        if (admin.login(u, p)) {
            adminMenu();
        } else {
            System.out.println("Admin Login Failed!");
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. View Full Attendance Report");
            System.out.println("4. Logout");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> addStudent();
                case 2 -> addTeacher();
                case 3 -> {
                    if (attendanceList.isEmpty())
                        System.out.println("No attendance data.");
                    else
                        attendanceList.forEach(a -> System.out.println(a));
                }
                case 4 -> { return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    static void addStudent() {
        System.out.print("Student ID: ");
        int id = sc.nextInt();
        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("Section: ");
        String sec = sc.next();
        System.out.print("Password: ");
        String pass = sc.next();

        students.add(new Student(id, name, sec, pass));
        System.out.println("Student added successfully!");
    }

    static void addTeacher() {
        System.out.print("Teacher ID: ");
        int id = sc.nextInt();
        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("Section: ");
        String sec = sc.next();
        System.out.print("Password: ");
        String pass = sc.next();

        teachers.add(new Teacher(id, name, sec, pass));
        System.out.println("Teacher added successfully!");
    }
}
