// SchoolSystem.java

import java.util.List;
import java.util.ArrayList;

class School {
    private List<Department> departments;

    public School(String name) {
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department dept) {
        departments.add(dept);
    }

    public List<Department> getDepartments() {
        return departments;
    }
}

class Department {
    private String name;
    private List<Teacher> teachers;
    private MasterTeacher masterTeacher;
    private List<Office> offices;

    public Department(String name) {
        this.name = name;
        this.teachers = new ArrayList<>();
        this.offices = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void assignMasterTeacher(MasterTeacher master) {
        this.masterTeacher = master;
        addTeacher(master); // MasterTeacher is also a Teacher
    }

    public void addOffice(Office office) {
        offices.add(office);
    }

    public String getName() {
        return name;
    }

    public MasterTeacher getMasterTeacher() {
        return masterTeacher;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Office> getOffices() {
        return offices;
    }
}

class Teacher {
    protected String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class MasterTeacher extends Teacher {
    public MasterTeacher(String name) {
        super(name);
    }

    public void manageDepartment(Department dept) {
        System.out.println(name + " is managing the " + dept.getName() + " department.");
    }
}

class Office {
    private String roomNumber;
    private boolean isFacultyRoom;

    public Office(String roomNumber, boolean isFacultyRoom) {
        this.roomNumber = roomNumber;
        this.isFacultyRoom = isFacultyRoom;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public boolean isFacultyRoom() {
        return isFacultyRoom;
    }
}

public class SchoolSystem {
    public static void main(String[] args) {
        School school = new School("Sunrise Academy");

        Department science = new Department("Science");
        MasterTeacher mt = new MasterTeacher("Ms. Rivera");
        science.assignMasterTeacher(mt);

        science.addTeacher(new Teacher("Mr. Cruz"));
        science.addTeacher(new Teacher("Ms. Santos"));

        science.addOffice(new Office("Room 101", false));
        science.addOffice(new Office("Room 102", true)); // Faculty room

        school.addDepartment(science);

        // Output
        for (Department dept : school.getDepartments()) {
            System.out.println("Department: " + dept.getName());
            System.out.println("Master Teacher: " + dept.getMasterTeacher().getName());
            System.out.println("Teachers:");
            for (Teacher t : dept.getTeachers()) {
                System.out.println(" - " + t.getName());
            }
            System.out.println("Offices:");
            for (Office o : dept.getOffices()) {
                System.out.println(" - " + o.getRoomNumber() + (o.isFacultyRoom() ? " (Faculty Room)" : ""));
            }
            dept.getMasterTeacher().manageDepartment(dept);
            System.out.println();
        }
    }
}
