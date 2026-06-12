/*
Ticket Booking System

A movie theater has:

Total Seats = 5

Create:

Multiple booking threads
Requirements
Each thread tries to book seats.
If seats are available, booking succeeds.
Otherwise print:
Booking Failed

Concepts Tested
Threads
Shared Resource
Synchronized Method

Instructions - 
Create class Theater

    Variable:
        totalSeats = 5

    synchronized method bookSeat(int seats)

        If totalSeats >= seats

            Print:
                ThreadName + " Booking Successful"

            totalSeats = totalSeats - seats

            Print remaining seats

        Else

            Print:
                ThreadName + " Booking Failed"


Create class BookingThread extends Thread

    Theater theater
    int seatsRequired

    Constructor receives:
        theater
        seatsRequired

    run()

        theater.bookSeat(seatsRequired)


Main Method

    Create Theater object

    Create Thread1 -> wants 2 seats
    Create Thread2 -> wants 2 seats
    Create Thread3 -> wants 2 seats

    Start Thread1
    Start Thread2
    Start Thread3


Possible Output

    Thread1 Booking Successful
    Remaining Seats = 3

    Thread2 Booking Successful
    Remaining Seats = 1

    Thread3 Booking Failed
*/
class Theater {

    private int totalSeats = 5;

    public synchronized void bookSeat(int seats) {

        if (totalSeats >= seats) {

            System.out.println(Thread.currentThread().getName()
                    + " Booking Successful");

            totalSeats = totalSeats - seats;

            System.out.println("Remaining Seats = " + totalSeats);
        } else {

            System.out.println(Thread.currentThread().getName()
                    + " Booking Failed");
        }
    }
}

class BookingThread extends Thread {

    private Theater theater;
    private int seatsRequired;

    public BookingThread(Theater theater, int seatsRequired, String name) {
        this.theater = theater;
        this.seatsRequired = seatsRequired;
        setName(name);
    }

    @Override
    public void run() {
        theater.bookSeat(seatsRequired);
    }
}

public class Main {

    public static void main(String[] args) {

        Theater theater = new Theater();

        BookingThread t1 = new BookingThread(theater, 2, "Thread1");
        BookingThread t2 = new BookingThread(theater, 2, "Thread2");
        BookingThread t3 = new BookingThread(theater, 2, "Thread3");

        t1.start();
        t2.start();
        t3.start();
    }
}
