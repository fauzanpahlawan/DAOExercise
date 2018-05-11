package com.example.fauza.daoexercise.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fauza.daoexercise.R;
import com.example.fauza.daoexercise.database.PeopleDatabase;
import com.example.fauza.daoexercise.entity.People;
import com.example.fauza.daoexercise.util.DatabaseWorker;
import com.example.fauza.daoexercise.util.IOnPostExecute;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IOnPostExecute {
    private final String TAG = MainActivity.class.getSimpleName();
    private final Context context = MainActivity.this;

    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.tv_data)
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new DatabaseWorker(context, this).execute();
    }

    @OnClick(R.id.bt_add)
    public void bt_add() {
        People people = new People();
        people.setFirstName(etFirstName.getText().toString());
        people.setLastName(etLastName.getText().toString());
        new DatabaseWorker(context, this).execute(people);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
        PeopleDatabase.destroyInstance();
    }

    @Override
    public void passData(List<People> peoples) {
        //if peoples not null show data
        if (peoples != null) {
            tvData.setText("");
            for (People people1 : peoples) {
                Log.v(TAG, people1.getFirstName() + " " + people1.getLastName() + "\n");
                tvData.append(people1.getFirstName() + " " + people1.getLastName() + "\n");
            }
        } else {
            //if data null a.k.a data already exist shows warning
            Toast.makeText(context, "Data already exist", Toast.LENGTH_SHORT).show();
        }
    }
}
