public class Appointment {
    private int appointmentId;
    private String patientName;
    private String doctorName;
    private String date;
    private String time;
    private String status;
    private String roomNumber;

    public Appointment(int appointmentId, String patientName, String doctorName, String date,
                       String time, String status, String roomNumber) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.status = status;
        this.roomNumber = roomNumber;
    }

    public Appointment() {
        this(0, "Unknown", "Unknown", "2025-01-01", "09:00", "Scheduled", "Room 101");
    }

    // Геттеры
    public int getAppointmentId() { return appointmentId; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getStatus() { return status; }
    public String getRoomNumber() { return roomNumber; }

    // Сеттеры
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setStatus(String status) { this.status = status; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    // Методы
    public void reschedule(String newDate, String newTime) {
        String oldDateTime = date + " " + time;
        this.date = newDate;
        this.time = newTime;
        System.out.println("Appointment #" + appointmentId + " rescheduled");
        System.out.println("From: " + oldDateTime);
        System.out.println("To: " + newDate + " " + newTime);
    }

    public void complete() {
        if (status.equals("Scheduled") || status.equals("In-progress")) {
            status = "Completed";
            System.out.println("Appointment #" + appointmentId + " completed.");
        }
    }

    public void cancel() {
        if (status.equals("Scheduled")) {
            status = "Cancelled";
            System.out.println("Appointment #" + appointmentId + " cancelled.");
        }
    }

    public boolean isScheduled() {
        return status.equals("Scheduled");
    }

    @Override
    public String toString() {
        return "Appointment #" + appointmentId +
                "\n  Patient: " + patientName + " | Doctor: " + doctorName +
                "\n  Date/Time: " + date + " at " + time +
                "\n  Status: " + status + " | Room: " + roomNumber;
    }
}