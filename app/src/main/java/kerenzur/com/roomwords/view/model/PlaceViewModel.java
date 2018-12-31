package kerenzur.com.roomwords.view.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import kerenzur.com.roomwords.data.Places;
import kerenzur.com.roomwords.repository.PlaceRepository;

public class PlaceViewModel extends AndroidViewModel {

    private PlaceRepository mRepository;

    private LiveData<List<Places>> mAllPlaces;

    public PlaceViewModel(Application application) {
        super(application);
        mRepository = new PlaceRepository(application);
        mAllPlaces = mRepository.getAllPlaces();
    }

    public LiveData<List<Places>> getAllPlaces() { return mAllPlaces; }

    public void insert(Places word) { mRepository.insert(word); }
}