/*
Classroom Attendance System

Teacher thread:

Takes attendance

Student thread:

Waits until attendance starts
Requirements
Students should not proceed immediately.
Students must wait.
Teacher gives signal.
Students continue after signal.

Expected Flow:

Student Waiting...

Teacher Started Attendance

Student Marked Present
Concepts Tested
wait()
notifyAll()


Instructions - 
Create Class Classroom

    Variable:
        attendanceStarted = false

    synchronized method waitForAttendance()

        While attendanceStarted is false

            Print:
                "Student Waiting..."

            wait()

        Print:
            "Student Marked Present"


    synchronized method startAttendance()

        attendanceStarted = true

        Print:
            "Teacher Started Attendance"

        notifyAll()


Create Class TeacherThread

    Classroom classroom

    run()

        classroom.startAttendance()


Create Class StudentThread

    Classroom classroom

    run()

        classroom.waitForAttendance()


Main Method

    Create Classroom object

    Create multiple Student threads

    Create Teacher thread

    Start Student threads

    Wait for a few seconds

    Start Teacher thread
Thread Coordination
Synchronization
*/
class Classroom {
    private boolean attendanceStarted = false;

    public synchronized void waitForAttendance() {
        while (!attendanceStarted) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " : Student Waiting...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()
                + " : Student Marked Present");
    }

    public synchronized void startAttendance() {
        attendanceStarted = true;

        System.out.println("Teacher Started Attendance");

        notifyAll();
    }
}

class TeacherThread extends Thread {
    Classroom classroom;

    TeacherThread(Classroom classroom) {
        this.classroom = classroom;
    }

    public void run() {
        classroom.startAttendance();
    }
}

class StudentThread extends Thread {
    Classroom classroom;

    StudentThread(Classroom classroom, String name) {
        this.classroom = classroom;
        setName(name);
    }

    public void run() {
        classroom.waitForAttendance();
    }
}

public class Main {
    public static void main(String[] args) {

        Classroom classroom = new Classroom();

        StudentThread s1 = new StudentThread(classroom, "Student-1");
        StudentThread s2 = new StudentThread(classroom, "Student-2");
        StudentThread s3 = new StudentThread(classroom, "Student-3");

        TeacherThread teacher = new TeacherThread(classroom);

        // Start students first
        s1.start();
        s2.start();
        s3.start();

        try {
            Thread.sleep(3000); // Teacher comes after 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        teacher.start();
    }
}
