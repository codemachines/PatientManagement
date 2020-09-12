package viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import models.Nurse;
import repository.NurseRepository;

public class LoginviewModel extends AndroidViewModel {

    private NurseRepository repository;
    private LiveData<List<Nurse>> getAllData;
    private LiveData<Integer> insertResult;

    public LoginviewModel(@NonNull Application application) {
        super(application);

        repository = new NurseRepository(application);
        getAllData = repository.getAllData();
        insertResult = repository.getInsertResult();
    }

    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public void insert(Nurse data) {
        repository.insertData(data);
    }

    public LiveData<List<Nurse>> getGetAllData() {
        return getAllData;
    }
}
