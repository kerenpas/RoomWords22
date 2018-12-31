package kerenzur.com.roomwords.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "places_table")
public class Places {


    public Places(@NonNull String mName, @NonNull double mLat, @NonNull double mLng, @NonNull String mAddress, String mPhoto, boolean mIsLastSearch, boolean mIsFavorite) {
        this.mName = mName;
        this.mLat = mLat;
        this.mLng = mLng;
        this.mAddress = mAddress;
        this.mPhoto = mPhoto;
        this.mIsLastSearch = mIsLastSearch;
        this.mIsFavorite = mIsFavorite;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private long ID;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "lat")
    private double mLat;


    @NonNull
    @ColumnInfo(name = "lng")
    private double mLng;


    @NonNull
    @ColumnInfo(name = "address")
    private String mAddress;



    @ColumnInfo(name = "photo")
    private String mPhoto;

    @ColumnInfo(name = "isLastSearch")
    private boolean mIsLastSearch;

    @ColumnInfo(name = "isFavorite")
    private boolean mIsFavorite;


    @NonNull
    public long getID() {
        return ID;
    }

    public void setID(@NonNull long ID) {
        this.ID = ID;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    @NonNull
    public double getLat() {
        return mLat;
    }

    public void setLat(@NonNull double mLat) {
        this.mLat = mLat;
    }

    @NonNull
    public double getLng() {
        return mLng;
    }

    public void setLng(@NonNull double mLng) {
        this.mLng = mLng;
    }

    @NonNull
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(@NonNull String mAddress) {
        this.mAddress = mAddress;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }

    public boolean isIsLastSearch() {
        return mIsLastSearch;
    }

    public void setIsLastSearch(boolean mIsLastSearch) {
        this.mIsLastSearch = mIsLastSearch;
    }

    public boolean isIsFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(boolean mIsFavorite) {
        this.mIsFavorite = mIsFavorite;
    }
}