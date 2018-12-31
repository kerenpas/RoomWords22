package kerenzur.com.roomwords.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import kerenzur.com.roomwords.data.Places;
import kerenzur.com.roomwords.data.PlacesDao;
import kerenzur.com.roomwords.data.PlacesRoomDatabase;

public class PlaceRepository {

    private PlacesDao mPLacesDao;
    private LiveData<List<Places>> mAllPlaces;

    public PlaceRepository(Application application) {
        PlacesRoomDatabase db = PlacesRoomDatabase.getDatabase(application);
        mPLacesDao = db.placesDao();
        mAllPlaces = mPLacesDao.getAllPlaces();
    }




    public LiveData<List<Places>> getAllPlaces() {
        return mAllPlaces;
    }

    public void insert (Places place_) {
        new insertAsyncTask(mPLacesDao).execute(place_);
    }

    public void insert (List<Places> placeList_) {
        for(Places p :placeList_) {
           insert(p);
        }
    }


    private static class insertAsyncTask extends AsyncTask<Places, Void, Void> {

        private PlacesDao mAsyncTaskDao;

        insertAsyncTask(PlacesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Places... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
