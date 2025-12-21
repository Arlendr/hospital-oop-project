public class Doctor {
    private int doctorid;
    private String name;
    private String specialization;
    private int yearsOfExp;
    private boolean  isonduty;
    private int maxpatienceperday;



    public Doctor(int doctorid,String name,String specialization,int yearsOfExp,boolean  isonduty,
                  int maxpatienceperday){
        this.doctorid = doctorid;
        this.name = name;
        this.specialization = specialization;
        this.yearsOfExp = yearsOfExp;
        this.isonduty = isonduty;
        this.maxpatienceperday = maxpatienceperday;

    }

    public Doctor(){
        this.doctorid = 0;
        this.name = "unknown";
        this.specialization = "general practitioner";
        this.yearsOfExp = 0;
        this.isonduty = false;
        this.maxpatienceperday = 10;



    }

    public int getDoctorId() {return doctorid;}

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public boolean isIsonduty() {
        return isonduty;
    }

    public int getMaxpatienceperday() {
        return maxpatienceperday;
    }


    public void setDoctorid(int newDoctorId){
        this.doctorid = newDoctorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public void setIsonduty(boolean isonduty) {
        this.isonduty = isonduty;
    }

    public void setMaxpatienceperday(int maxpatienceperday) {
        this.maxpatienceperday = maxpatienceperday;
    }

    public boolean isexperienced(){
        return yearsOfExp>10;
    }
    public boolean canperformsurgery(){
        return specialization.equals("surgeon");
    }



}
