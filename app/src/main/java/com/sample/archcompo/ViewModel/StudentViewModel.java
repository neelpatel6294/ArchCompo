package com.sample.archcompo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.sample.archcompo.Constants;
import com.sample.archcompo.Repository;
import com.sample.archcompo.model.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<PagedList<Student>> mAllStudents;

    private final static PagedList.Config config
            = new PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.PAGE_INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(Constants.PAGE_PREFETCH_DISTANCE)
            .setEnablePlaceholders(true)
            .build();


    public StudentViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
    }

    public LiveData<PagedList<Student>> getAllStudents() {
        if (mAllStudents == null) {
            mAllStudents = mRepository.getAllStudents(config);

        }
        return mAllStudents;
    }

    public void insert(Student student) {
        mRepository.insert(student);
    }

    public void delete(Student student) {
        mRepository.delete(student);
    }

    public void update(Student student) {
        mRepository.update(student);
    }
}
