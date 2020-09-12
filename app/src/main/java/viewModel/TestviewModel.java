package viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import models.Test;
import repository.TestRepository;

public class TestviewModel extends AndroidViewModel {

    // calling repository tasks and sending the results to the Activity
    private TestRepository testRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Test>> allTest;

    public TestviewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        insertResult = testRepository.getInsertResult();
        allTest = testRepository.getAllTest();
    }
    //calls repository to insert a patient
    public void insert(Test test) {
        testRepository.insert(test);
    }

    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    //returns query results as live data object
    public LiveData<List<Test>> getAllPatient() { return allTest; }

    public LiveData<Test> getSpecificTest(int testId) { return testRepository.getSelectedTest(testId); }

    public LiveData<List<Test>> getPatientTest(int patientId){ return testRepository.getPatientTest(patientId); }
}
