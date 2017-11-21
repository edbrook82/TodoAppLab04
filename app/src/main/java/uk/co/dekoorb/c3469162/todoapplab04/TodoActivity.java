package uk.co.dekoorb.c3469162.todoapplab04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

import uk.co.dekoorb.c3469162.todoapplab04.support.SingleFragmentActivity;

public class TodoActivity extends SingleFragmentActivity {

    public static final String EXTRA_TODO_UUID = "todo:uuid";

    public static Intent newIntent(Context context, UUID uuid) {
        Intent intent = new Intent(context, TodoActivity.class);
        intent.putExtra(EXTRA_TODO_UUID, uuid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
    }

    @Override
    protected Fragment getFragment() {
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_TODO_UUID);
        return TodoFragment.newInstance(uuid);
    }

    @Override
    protected int getContainerId() {
        return R.id.detail_fragment;
    }

}
