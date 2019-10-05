package com.sample.archcompo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sample.archcompo.Adapter.StudentAdapter;
import com.sample.archcompo.ViewModel.StudentViewModel;
import com.sample.archcompo.model.Student;

import java.util.ArrayList;
import java.util.List;

import static com.sample.archcompo.AppConstants.ACTIVITY_REQUEST_CODE;
import static com.sample.archcompo.AppConstants.INTENT_DATA;

public class MainActivity extends AppCompatActivity implements StudentAdapter.ListItemClickListener {
//    public class MainActivity extends AppCompatActivity implements StudentAdapter.ListItemClickListener {

    private RecyclerView mRecycleView;
    private StudentViewModel mViewModel;
    private ArrayList<Student> mStudents;


    private int mStudentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Stetho.initializeWithDefaults(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
            }
        });

//
        final StudentAdapter mAdapter = new StudentAdapter(this, this);
        mRecycleView = findViewById(R.id.recycleView);
        mViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        mViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                mAdapter.setStudents(students);
            }
        });

        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
// Add the functionality to swipe items in the
// recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Student myWord = mAdapter.getStudentAtPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting " +
                                myWord.getName(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mViewModel.delete(myWord);
                    }
                });

        helper.attachToRecyclerView(mRecycleView);

//        mAdapter.onAdapterClickListener(new StudentAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Student student) {
//
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            if (data.hasExtra(INTENT_DATA)) {
                mViewModel.update((Student) data.getParcelableExtra(INTENT_DATA));
            } else {

                String name = data.getStringExtra(AppConstants.INTENT_NAME);
                String email = data.getStringExtra(AppConstants.INTENT_EMAIL);
                String country = data.getStringExtra(AppConstants.INTENT_COUNTRY);
                Student student = new Student();
                student.setName(name);
                student.setEmail(email);
                student.setCountry(country);
                mViewModel.insert(student);
            }
        }


    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onListItemClick (Student student){

            Intent intent = new Intent(this, AddStudentActivity.class);
            intent.putExtra(INTENT_DATA, student);
            startActivityForResult(intent, ACTIVITY_REQUEST_CODE);

        }

    }
