package data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Patient;
import models.Test;

@Database(entities = {Patient.class, Test.class},version = 3,exportSchema = true)
public abstract class PatientDatabase extends RoomDatabase {

    private static volatile PatientDatabase INSTANCE;
    private static final String DATABASE_NAME="PatientDB";
    public abstract PatientDao patientDao();
    public abstract TestDao testDao();

    //
    public static synchronized PatientDatabase getInstance(Context context){
        if(INSTANCE==null) {
            //Create database object
            synchronized (PatientDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            PatientDatabase.class, "App_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
