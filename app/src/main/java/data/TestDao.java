package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import models.Test;

@Dao
public interface TestDao {

    @Insert
    void insertDetails(Test data);

    @Query("select * from TestDetails")
    LiveData<List<Test>> getAllTests();

    @Query("delete from TestDetails")
    void deleteAllData();

    @Query("select * from TESTDETAILS where TestId = :testId")
    LiveData<Test> getSelectedTest(int testId);

    @Query("select * from TestDetails where PatientId = :patientId")
    LiveData<List<Test>> getPatientTest(int patientId);
}
