package kerenzur.com.roomwords.network;

import android.graphics.Movie;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kerenzur.com.roomwords.data.Places;
import kerenzur.com.roomwords.interfaaaces.IPlacesDataReceived;
import kerenzur.com.roomwords.models.LocationModel;
import kerenzur.com.roomwords.repository.PlaceRepository;
import kerenzur.com.roomwords.ui.NearByApplication;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetWorkDataProvider {


    public void getPlacesByLocation(double lat , double lng, IPlacesDataReceived resultListner_)
    {

       //go get data from google API
        // take time....
        //more time...
        //Data received -> resultListner_

        GetPlacesByLocationAsyncTask getPlacesByLocationAsyncTask = new GetPlacesByLocationAsyncTask(resultListner_);
        getPlacesByLocationAsyncTask.execute(lat,lng);

    }




    private class GetPlacesByLocationAsyncTask extends AsyncTask < Double , Integer, IPlacesDataReceived>

    {
        private ArrayList<LocationModel> mLocationModels;

        private IPlacesDataReceived mIPlacesDataReceived;

        public GetPlacesByLocationAsyncTask(IPlacesDataReceived iPlacesDataReceived)
        {
            mIPlacesDataReceived = iPlacesDataReceived;
        }

        @Override
        protected IPlacesDataReceived doInBackground(Double... doubles) {
            OkHttpClient client = new OkHttpClient();
            //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=32.029252,%2034.7425159&radius=1500&key=AIzaSyDc7oNJ8FQZc6xmDVEvj-vewU5-ohnlwR0
            String urlQuery = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+doubles[0] +"," +doubles[1]+ "&radius=1500&key=AIzaSyDc7oNJ8FQZc6xmDVEvj-vewU5-ohnlwR0";
            Request request = new Request.Builder()
                    .url(urlQuery)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!response.isSuccessful())
                try {
                    throw new IOException("Unexpected code " + response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            try {
                mLocationModels =   getLocationListFromJson(response.body().string());
                PlaceRepository placeRepository = new PlaceRepository(NearByApplication.getApplication());
                ArrayList <Places> listPlaces = new ArrayList<>();
                for(LocationModel locationModel: mLocationModels)
                {

                    Places place = new Places(locationModel.getName(),locationModel.getGeometry().getLocation().getLat(),locationModel.getGeometry().getLocation().getLng(),locationModel.getVicinity(),"",false,true );
                    listPlaces.add(place);
                }

                placeRepository.insert(listPlaces);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return mIPlacesDataReceived;
        }

        private ArrayList<LocationModel> getLocationListFromJson(String jsonResponse) {
            List<LocationModel> stuLocationData = new ArrayList<LocationModel>();
            Gson gson = new GsonBuilder().create();
            LocationResponse response = gson.fromJson(jsonResponse, LocationResponse.class);
            stuLocationData = response.results;
            ArrayList<LocationModel> arrList = new ArrayList<>();
            arrList.addAll(stuLocationData);
            return arrList;
        }

        @Override
        protected void onPostExecute(IPlacesDataReceived iPlacesDataReceived_) {
            iPlacesDataReceived_.onPlacesDataReived(mLocationModels);
        }



        public class LocationResponse {

            private List<LocationModel> results;

            // public constructor is necessary for collections
            public LocationResponse() {
                results = new ArrayList<LocationModel>();
            }

        }

    }
}
