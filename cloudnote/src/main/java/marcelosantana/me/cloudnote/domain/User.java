package marcelosantana.me.cloudnote.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

public class User implements Parcelable {

    private long _id;
    private long facebook_id;
    private String token;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private String image_url;

    public User(long _id, long facebook_id, String token, String username, String email, String name, String lastname, String image_url) {
        this._id = _id;
        this.facebook_id = facebook_id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.image_url = image_url;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(long facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this._id);
        dest.writeLong(this.facebook_id);
        dest.writeString(this.token);
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeString(this.name);
        dest.writeString(this.lastname);
        dest.writeString(this.image_url);
    }

    protected User(Parcel in) {
        this._id = in.readLong();
        this.facebook_id = in.readLong();
        this.token = in.readString();
        this.username = in.readString();
        this.email = in.readString();
        this.name = in.readString();
        this.lastname = in.readString();
        this.image_url = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
