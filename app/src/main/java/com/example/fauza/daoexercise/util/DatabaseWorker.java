package com.example.fauza.daoexercise.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.fauza.daoexercise.database.PeopleDatabase;
import com.example.fauza.daoexercise.entity.People;

import java.lang.ref.WeakReference;
import java.util.List;

public class DatabaseWorker extends AsyncTask<People, Void, List<People>> {
    private final String TAG = DatabaseWorker.class.getSimpleName();
    private PeopleDatabase mPeopleDatabase;
    private IOnPostExecute mIOnPostExecute;

    public DatabaseWorker(Context context, IOnPostExecute iOnPostExecute) {
        WeakReference<Context> contextWeakReference = new WeakReference<>(context);
        mPeopleDatabase = PeopleDatabase.getINSTANCE(contextWeakReference.get());
        mIOnPostExecute = iOnPostExecute;
    }

    @Override
    protected List<People> doInBackground(People... peoples) {
        try {
            //check if data already exist
            if (mPeopleDatabase.getPeopleDao().getNumberOfRows(peoples[0].getFirstName(), peoples[0].getLastName()) > 0) {
                Log.v(TAG, "Data already exist");
                //if data exist return null
                return null;
            } else {
                mPeopleDatabase.getPeopleDao().insertAll(peoples);
                Log.v(TAG, "Insert Success");
            }
        } catch (Exception e) {
            Log.v(TAG, e.getMessage());
            e.printStackTrace();
        }
        return mPeopleDatabase.getPeopleDao().getAll();
    }

    @Override
    protected void onPostExecute(List<People> people) {
        if (people != null) {
            //if data not null pass the data to MainActivity
            mIOnPostExecute.passData(people);
        } else {
            //if data null pass null
            mIOnPostExecute.passData(null);
        }
    }
}
