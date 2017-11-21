package uk.co.dekoorb.c3469162.todoapplab04.support;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by c3469162 on 21/11/2017.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    private static final String TAG = "SingleFragmentActivity";

    protected abstract Fragment getFragment();
    protected abstract int getContainerId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getContainerId(), getFragment())
                    .commitNow();
            Log.d(TAG, "onCreate: NEW FRAGMENT");
        }
    }
}
