package marcelosantana.me.cloudnote.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import marcelosantana.me.cloudnote.R;
import marcelosantana.me.cloudnote.domain.Note;

public class NoteViewActivity extends AppCompatActivity {

    @Bind(R.id.progress_indicator)
    LinearLayout mProgressBar;

    @Bind(R.id.layout_offline)
    LinearLayout mOfflineLayout;

    @Bind(R.id.dateCreated)
    TextView mDateCreatedTextView;

    @Bind(R.id.message)
    EditText mMessageEditText;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.layout_noteView)
    RelativeLayout mNoteViewLayout;

    public static final String NOTE_VIEW =
            "marcelosantana.me.cloudnote.ui.activity.NoteViewActivity.NOTE_VIEW";

    private Note mNote;

    public static Intent getStartIntent(Context context, Note note) {
        Intent intent = new Intent(context, NoteViewActivity.class);
        intent.putExtra(NOTE_VIEW, note);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.note_activity_view);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        mNote = bundle.getParcelable(NOTE_VIEW);
        if (mNote == null)
            throw new IllegalArgumentException("NoteViewActivity requires a Note object.");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
