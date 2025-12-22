
public class Appointment {
    private int appointmentid;
    private String patientname;
    private String doctorname;
    private String date;
    private String time;
    private String status;
    private String roomnum;


    public Appointment(int appoimentid, String patientname, String doctorname, String date,
                       String time, String status, String roomnum) {
        this.appointmentid = appoimentid;
        this.patientname = patientname;
        this.doctorname = doctorname;
        this.date = date;
        this.time = time;
        this.status = status;
        this.roomnum = roomnum;
    }

    public Appointment() {
        this.appointmentid = 0;
        this.patientname = "unknown";
        this.doctorname = "unknown";
        this.date = "2025-01-01";
        this.time = "12:00";
        this.status = "scheduled";
        this.roomnum = "111";
    }

    public int getAppointmentid() {
        return appointmentid;
    }

    public String getPatientname() {
        return patientname;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setAppointmentid(int apploimentid) {
        this.appointmentid = apploimentid;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public void reschedule(String newdate, String newtime) {
        String olddatetime = this.date + " " + this.time;
        this.date = newdate;
        this.time = newtime;
        System.out.println("appointment" + appointmentid + "rescheduled");
        System.out.println("from" + olddatetime);
        System.out.println("to" + newdate + " " + newtime);
    }

    public void cancel() {
        if (status.equals("Scheduled")) {
            this.status = "Completed";
            System.out.println("Appointment " + appointmentid + " marked as completed.");
        } else {
            System.out.println("Cannot complete appointment. Current status: " + status);
        }
    }
}