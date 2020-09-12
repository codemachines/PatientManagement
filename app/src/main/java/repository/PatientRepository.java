package repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import data.PatientDao;
import data.PatientDatabase;
import models.Patient;

public class PatientRepository {

    private final PatientDao patientDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Patient>> patientList;
    private LiveData<List<Patient>> selectedPatient;

    public PatientRepository(Context context) {
        //creating a database object
        PatientDatabase db = PatientDatabase.getInstance(context);
        //creating interface object
        patientDao = db.patientDao();
        //calling interface method
        patientList = patientDao.getAllPatient();
    }
    // returns query results as LiveData object
    public LiveData<List<Patient>> getAllPatient() {
        return patientList;
    }
    public LiveData<Patient> getSelectedPatient(int patientId) {
        return patientDao.getselectedPatient(patientId);
    }
    //inserting a Patient Data
    public void insert(Patient patient) {
        insertAsync(patient);
    }

    //Updating Patient Async task
    public void update(Patient patient) {
        updateAsync(patient);
    }

    // returns inserted results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Patient patient) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.insert(patient);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }

    private void updateAsync(final Patient patient) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.update(patient);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
