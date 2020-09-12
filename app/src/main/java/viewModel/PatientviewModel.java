package viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import models.Patient;
import repository.PatientRepository;

public class PatientviewModel extends AndroidViewModel {

    // calling repository tasks and sending the results to the Activity
    private PatientRepository patientRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Patient>> allPatient;

    public PatientviewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        insertResult = patientRepository.getInsertResult();
        allPatient = patientRepository.getAllPatient();
    }

    //calls repository to insert a patient
    public void insert(Patient patient) {
        patientRepository.insert(patient);
    }

    public void update(Patient patient) {
        patientRepository.update(patient);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    public LiveData<List<Patient>> getAllPatient() { return allPatient; }

}
