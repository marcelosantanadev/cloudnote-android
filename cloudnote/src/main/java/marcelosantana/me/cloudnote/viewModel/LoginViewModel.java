package marcelosantana.me.cloudnote.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import rx.Subscription;

/**
 * Created by Marcelo Santana on 14/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

public class LoginViewModel extends BaseObservable {
    private static final String TAG = "LoginViewModel";

    private Context mContext;
    private Subscription mSubscription;


    public LoginViewModel(Context context) {
        mContext = context;
    }

    public boolean getPasswordEnabled() {
        return true;
    }

    public boolean getEmailEnabled() {
        return true;
    }

    public boolean getSignInEnabled() {
        return false;
    }

    public String getEmail () {
        return "";
    }

    public String getPassword() {
        return "";
    }

    public String getSignIn() {
        return "Entrar agora";
    }

    public TextWatcher getPasswordWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public View.OnClickListener SignInClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("teste", "teste");
            }
        };

    }
}
