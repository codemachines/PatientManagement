package data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Nurse;

@Database(entities = {Nurse.class},version = 1,exportSchema = false)
public abstract class NurseDatabase extends RoomDatabase {

    public abstract NurseDao nurseDao();

    private static NurseDatabase INSTANCE;

    public static NurseDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (NurseDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                            context, NurseDatabase.class, "DATABASE_NURSE")
                            .fallbackToDestructiveMigration()
                            .build();

                }

            }

        }

        return INSTANCE;

    }
}
