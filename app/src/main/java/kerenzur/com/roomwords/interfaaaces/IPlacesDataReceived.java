package kerenzur.com.roomwords.interfaaaces;

import java.util.ArrayList;

import kerenzur.com.roomwords.models.LocationModel;

public interface IPlacesDataReceived {

    public void onPlacesDataReived(ArrayList<LocationModel> results_);
}
