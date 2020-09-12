package repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import data.TestDao;
import data.TestDatabase;
import models.Test;

public class TestRepository {

    private final TestDao testDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Test>> testList;
    private LiveData<List<Test>> selectedTest;


    public TestRepository(Context context) {
        //creating a database object
        TestDatabase db = TestDatabase.getDatabase(context);

        //creating an interface object
        testDao = db.testDao();

        //calling interface method
        testList = testDao.getAllTests();

    }
    // returns query results as LiveData object
    public LiveData<List<Test>> getAllTest() {
        return testList;
    }
    public LiveData<List<Test>> getPatientTest(int patientId){
        return testDao.getPatientTest(patientId);
    }
    public LiveData<Test> getSelectedTest(int testId)
    {
        return testDao.getSelectedTest(testId);
    }
    //inserts a Test asynchronously
    public void insert(Test test) {
        insertAsync(test);
    }

    // returns a insert result as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Test test) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testDao.insertDetails(test);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
