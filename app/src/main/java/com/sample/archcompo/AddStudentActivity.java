package com.sample.archcompo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.archcompo.model.Student;

import static com.sample.archcompo.AppConstants.INTENT_COUNTRY;
import static com.sample.archcompo.AppConstants.INTENT_DATA;
import static com.sample.archcompo.AppConstants.INTENT_EMAIL;
import static com.sample.archcompo.AppConstants.INTENT_NAME;

public class AddStudentActivity extends AppCompatActivity {

//    public static final String INTENT_TASK = "Intent_data";
//
//    public static final String EXTRA_REPLY = "com.sample.archcompo.Reply";
//    public static final String NAME_EXTRA = "name1";
//    public static final String EMAIL_EXTRA = "email1";
//    public static final String COUNTRY_EXTRA = "country1";
//    public static final String STUDENT_EXTRA = "id";


    private EditText mName;
    private EditText mEmail;
    private EditText mCountry;
    private Button mButton;

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mName = findViewById(R.id.edit_Name);
        mEmail = findViewById(R.id.edit_Email);
        mCountry = findViewById(R.id.edit_Country);
        mButton = findViewById(R.id.submit_button);

//        student = (Student) getIntent().getSerializableExtra(INTENT_DATA);

        student = getIntent().getParcelableExtra(INTENT_DATA);
//        Intent intent = getIntent();
//        if (intent.hasExtra(STUDENT_EXTRA)) {
//            String name = intent.getStringExtra(NAME_EXTRA);
//            String email = intent.getStringExtra(EMAIL_EXTRA);
//            String country = intent.getStringExtra(COUNTRY_EXTRA);

        if (student == null) {

            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        } else {

            if (student.getName() != null && !student.getName().isEmpty()) {
                mName.setText(student.getName());

            }
            if (student.getEmail() != null && !student.getEmail().isEmpty()) {
                mEmail.setText(student.getEmail());

            }
            if (student.getCountry() != null && !student.getCountry().isEmpty()) {
                mCountry.setText(student.getCountry());

            }
//        }
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();

                if (student != null) {

                    student.setName(mName.getText().toString());
                    student.setEmail(mEmail.getText().toString());
                    student.setCountry(mCountry.getText().toString());
                    intent.putExtra(INTENT_DATA,student);


                } else {
                    intent.putExtra(INTENT_NAME, mName.getText().toString());
                    intent.putExtra(INTENT_EMAIL, mEmail.getText().toString());
                    intent.putExtra(INTENT_COUNTRY, mCountry.getText().toString());

                }

                setResult(Activity.RESULT_OK, intent);
                finish();

//                if (TextUtils.isEmpty(mName.getText())) {
//                    setResult(RESULT_CANCELED, intent);
//
//                    Toast.makeText(AddStudentActivity.this, "Empty Data", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    student.setName(mName.getText().toString());
//                    student.setEmail(mEmail.getText().toString());
//                    student.setCountry(mCountry.getText().toString());
//                    intent.putExtra(INTENT_TASK, String.valueOf(student));
//                    setResult(RESULT_OK, intent);
//                    finish();
//
//
//                }

            }
        });
    }
}
