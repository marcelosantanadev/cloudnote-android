package marcelosantana.me.cloudnote.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import marcelosantana.me.cloudnote.R;
import marcelosantana.me.cloudnote.view.fragment.NotesFragment;

public class MainActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_activity);
        addNotesFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_github:
                openGithubRepository();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openGithubRepository() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://github.com/marcelosantanadev"));
        startActivity(intent);
    }

    private void addNotesFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new NotesFragment())
                .commit();
    }

}
