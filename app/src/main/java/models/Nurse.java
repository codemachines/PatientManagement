package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Nurse")
public class Nurse {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nurseId")
    private int nurseId;

    @ColumnInfo(name = "FirstName")
    private String firstname;

    @ColumnInfo(name = "LastName")
    private String lastname;

    @ColumnInfo(name = "Department")
    private String department;

    private String email;

    @ColumnInfo(name = "Password")
    private String password;


    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return firstname + " " + lastname;
    }
}
