package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TestDetails")
public class Test {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TestId")
    private int testId;

    @ColumnInfo(name = "PatientId")
    private int patientId;

    @ColumnInfo(name = "NurseId")
    private int nurseId;

    @ColumnInfo(name = "BPL")
    private String BPL;
    @ColumnInfo(name = "BPH")
    private String BPH;

    @ColumnInfo(name = "PH")
    private String PH;

    @ColumnInfo(name = "Temperature")
    private String temperature;

    @ColumnInfo(name = "Bloodgroup")
    private String bloodgroup;

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }



    public Test() {

    }

    public Test(int patientId, int nurseId, String BPL, String BPH, String PH, String temperature) {
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.BPL = BPL;
        this.BPH = BPH;
        this.PH = PH;
        this.temperature = temperature;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getBPL() {
        return BPL;
    }

    public void setBPL(String BPL) {
        this.BPL = BPL;
    }

    public String getBPH() {
        return BPH;
    }

    public void setBPH(String BPH) {
        this.BPH = BPH;
    }

    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
