package kerenzur.com.roomwords.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kerenzur.com.roomwords.R;
import kerenzur.com.roomwords.data.Places;

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView placeNameTextView;

        private WordViewHolder(View itemView) {
            super(itemView);
            placeNameTextView = itemView.findViewById(R.id.textView);

        }
    }

    private final LayoutInflater mInflater;
    private List<Places> mPlacesList; // Cached copy of words

    public PlacesListAdapter(Context context) { mInflater = LayoutInflater.from(context); }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mPlacesList != null) {
            Places current = mPlacesList.get(position);
            holder.placeNameTextView.setText(current.getName());
                   } else {
            // Covers the case of data not being ready yet.
            holder.placeNameTextView.setText("No Places");
        }
    }


    public void setWords(List<Places> words){
        mPlacesList = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPlacesList != null)
            return mPlacesList.size();
        else return 0;
    }
}