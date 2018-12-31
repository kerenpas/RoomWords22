package kerenzur.com.roomwords.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kerenzur.com.roomwords.R;
import kerenzur.com.roomwords.adapters.PlacesListAdapter;
import kerenzur.com.roomwords.data.Places;
import kerenzur.com.roomwords.interfaaaces.IPlacesDataReceived;
import kerenzur.com.roomwords.models.LocationModel;
import kerenzur.com.roomwords.network.NetWorkDataProvider;
import kerenzur.com.roomwords.view.model.PlaceViewModel;

public class MainActivity extends AppCompatActivity implements IPlacesDataReceived {


    private static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 665;
    private PlaceViewModel mPlacesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        NetWorkDataProvider dataProvider = new NetWorkDataProvider();
        dataProvider.getPlacesByLocation(32.146611, 34.8519761, this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mPlacesViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);





    }




    @Override
    public void onPlacesDataReived(ArrayList<LocationModel> results_) {
        // pass data result to adapter
        final PlacesListAdapter adapter = new PlacesListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.places_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlacesViewModel.getAllPlaces().observe(this, new Observer<List<Places>>() {
            @Override
            public void onChanged(@Nullable final List<Places> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });
    }
}
