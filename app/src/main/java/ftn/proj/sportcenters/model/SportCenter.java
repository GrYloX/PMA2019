package ftn.proj.sportcenters.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SportCenter implements Parcelable {

    private int id;
    private String name;
    private String address;
    private int image;

    public SportCenter(int id, String name, String address, int image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public SportCenter(){

    }

    protected SportCenter(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        image = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(address);
        out.writeInt(image);
    }
}
