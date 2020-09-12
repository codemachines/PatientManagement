package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.Nurse;

@Dao
public interface NurseDao {

    @Insert
    void insertDetails(Nurse data);

    @Query("SELECT * FROM nurse WHERE email =:email and password= :password")
    Nurse getNurse(String email,String password);

    @Query("SELECT nurseId FROM nurse WHERE email =:email and password= :password")
    Nurse getNurseID(String email,String password);

    // @Query("SELECT firstname FROM nurse WHERE nurseId= :nurseId")
    //LiveData<Nurse> getNurseName(int nurseId);

    @Insert
    void insert(Nurse nurse);

    @Query("select * from Nurse")
    LiveData<List<Nurse>> getDetails();

    @Update
    void update(Nurse nurse);

    @Query("delete from Nurse")
    void deleteAllData();
}
