package com.sample.archcompo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.sample.archcompo.Dao.StudentDao;
import com.sample.archcompo.Database.AppDatabase;
import com.sample.archcompo.model.Student;

import java.util.List;

public class Repository {

    private StudentDao mStudentDao;
    private LiveData<List<Student>> mAllStudents;


    public Repository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        mStudentDao = database.studentDao();
    }

//    public LiveData<PagedList<AnimalEntity>> getAllAnimals(PagedList.Config config) {
//        DataSource.Factory<Integer, AnimalEntity> factory = mAnimalDao.getAllAnimalsPaged();
//
//        return new LivePagedListBuilder<>(factory, config)
//                .build();
//    }


    public LiveData<PagedList<Student>> getAllStudents(PagedList.Config config) {
        DataSource.Factory<Integer,Student> factory = mStudentDao.getAllStudents();
        return new LivePagedListBuilder<>(factory,config)
                .build();
    }


    public void insert(Student student) {
        new insertAsyncTask(mStudentDao).execute(student);
    }

    private static class insertAsyncTask extends AsyncTask<Student,Void,Void> {

        private StudentDao mDao;

        public insertAsyncTask(StudentDao mStudentDao) {

            mDao = mStudentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            mDao.insert(students[0]);
            return null;
        }
    }


    public void delete(Student student){
        new deleteAsyncTask(mStudentDao).execute(student);
    }

    private class deleteAsyncTask extends AsyncTask<Student,Void,Void>{

        private StudentDao mDao;

        public deleteAsyncTask(StudentDao mStudentDao) {
            mDao = mStudentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            mDao.delete(students[0]);
            return null;
        }
    }

    public void update(Student student){
        new  updateAsyncTask(mStudentDao).execute(student);
    }

    private class updateAsyncTask extends AsyncTask<Student,Void,Void>{

        private StudentDao mDao;

        public updateAsyncTask(StudentDao mStudentDao) {
            mDao = mStudentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            mDao.update(students[0]);
            return null;
        }
    }
}
