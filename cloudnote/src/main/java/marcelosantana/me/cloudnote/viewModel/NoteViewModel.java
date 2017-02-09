package marcelosantana.me.cloudnote.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import marcelosantana.me.cloudnote.domain.Note;
import marcelosantana.me.cloudnote.view.activity.NoteViewActivity;

/**
 * Created by marcelosantana on 06/11/16.
 */

public class NoteViewModel extends BaseObservable {

    private Context mContext;
    private Note note;
    private Boolean isUserNotes;

    public NoteViewModel(Context context, Note note, boolean isUserNotes) {
        this.mContext = context;
        this.note = note;
        this.isUserNotes = isUserNotes;
    }

    public String getNoteTitle() {
        return note.getTitle();
    }

    public String getNoteMessage() {
        return note.getMessage();
    }

    public String getDateCreated() {
        return note.getDatecreated();
    }

    public View.OnClickListener onClickNote() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mContext.startActivity(NoteViewActivity.getStartIntent(mContext, note));
            }
        };
    }

}
