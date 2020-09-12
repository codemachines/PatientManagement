package viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import models.Nurse;
import repository.NurseRepository;

public class NurseviewModel extends AndroidViewModel {

    // calling repository tasks and sending the results to the Activity
    private NurseRepository nurseRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Nurse>> allNurse;

    public NurseviewModel(@NonNull Application application) {
        super(application);
        nurseRepository = new NurseRepository(application);
        insertResult = nurseRepository.getInsertResult();
        allNurse = nurseRepository.getAllData();
    }

    //calls repository to insert a patient
    public void insert(Nurse nurse) {
        nurseRepository.insertData(nurse);
    }


    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }


    //returns query results as live data object
    LiveData<List<Nurse>> getAllPatient() { return allNurse; }
}
