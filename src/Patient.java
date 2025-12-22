public class Patient{
    private int patientid;
    private String fullname;
    private int age;
    private String bloodtype;
    private String diagnosis;
    private boolean hasinsurance;

    public Patient(int patientid, String fullname,int age,String bloodtype,
                    String diagnosis, boolean hasinsurance){
    this.patientid = patientid;
    this.fullname = fullname;
    this.age = age;
    this.diagnosis = diagnosis;
    this.hasinsurance = hasinsurance;
    }

    public Patient(){
        this.patientid = 0;
        this.fullname = "unknown";
        this.age = 0;
        this.bloodtype = "unknown";
        this.diagnosis = "not diagnosed";
        this.hasinsurance = false;
    }

    public int getPatientid() {
        return patientid;
    }

    public String getFullname() {
        return fullname;
    }

    public int getAge() {
        return age;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public boolean getHasinsurance(){return hasinsurance;}

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setHasinsurance(boolean hasinsurance) {
        this.hasinsurance = hasinsurance;
    }
    public boolean isMinor(){
        return age < 18;
    }

    public String getage(){
        if (age < 18) {
            return "child";
        } else if (age < 65) {
            return "adult";}
        else {
            return "senior";}
    }
}
