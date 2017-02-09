package marcelosantana.me.cloudnote.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Automobi-dev on 25/03/2016.
 */
public class Note implements Parcelable {
    private Long id;
    private String title;
    private String message;
    private String datecreated;
    private boolean isTrash;

    public Note(Long id, String title, String message, String datecreated, boolean isTrash) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.datecreated = datecreated;
        this.isTrash = isTrash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public boolean isTrash() {
        return isTrash;
    }

    public void setTrash(boolean trash) {
        isTrash = trash;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.message);
        dest.writeString(this.datecreated);
        dest.writeByte(isTrash ? (byte) 1 : (byte) 0);
    }

    protected Note(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.message = in.readString();
        this.datecreated = in.readString();
        this.isTrash = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
