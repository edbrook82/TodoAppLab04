package uk.co.dekoorb.c3469162.todoapplab04;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.co.dekoorb.c3469162.todoapplab04.support.SingleFragmentActivity;

public class TodoListActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
    }

    @Override
    protected Fragment getFragment() {
        return TodoListFragment.newInstance();
    }

    @Override
    protected int getContainerId() {
        return R.id.master_fragment;
    }
}
