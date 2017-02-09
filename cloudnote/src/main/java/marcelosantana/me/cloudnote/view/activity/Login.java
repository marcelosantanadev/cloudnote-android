package marcelosantana.me.cloudnote.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import marcelosantana.me.cloudnote.R;
import marcelosantana.me.cloudnote.databinding.LoginActivityBinding;


public class Login extends AppCompatActivity {
    LoginActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
    }
}

