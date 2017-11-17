package uk.co.dekoorb.c3469162.todoapplab04;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TodoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.master_fragment);

        if (fragment == null) {
            fragment = new TodoListFragment();
            fm.beginTransaction()
                    .add(R.id.master_fragment, fragment)
                    .commit();
        }
    }
}
