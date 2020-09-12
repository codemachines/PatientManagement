package repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import data.NurseDao;
import data.NurseDatabase;
import models.Nurse;

public class NurseRepository {

    private NurseDao nurseDao;
    private LiveData<List<Nurse>> allData;
    LiveData<Nurse> getNurseName;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();

    public NurseRepository(Application application) {

        NurseDatabase db = NurseDatabase.getDatabase(application);
        nurseDao = db.nurseDao();
        allData = nurseDao.getDetails();


    }

    public void deleteData() {
        nurseDao.deleteAllData();
    }

    public LiveData<List<Nurse>> getAllData() {
        return allData;
    }

    public void insertData(Nurse data) {
        try {
            new NurseInsert(nurseDao).execute(data);
            insertResult.postValue(1);
        } catch (Exception e) {
            insertResult.postValue(0);
        }
    }

    public LiveData<Integer> getInsertResult(){
        return insertResult;
    }


    private static class NurseInsert extends AsyncTask<Nurse, Void, Void> {

        private NurseDao nurseDao1;

        private NurseInsert(NurseDao loginDao) {

            this.nurseDao1 = loginDao;

        }

        @Override
        protected Void doInBackground(Nurse... data) {

            for(int i=0; i<data.length; i++){
                nurseDao1.insertDetails(data[i]);
            }
            return null;

        }

    }
}
