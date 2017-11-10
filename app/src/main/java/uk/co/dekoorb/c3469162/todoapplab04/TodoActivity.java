package uk.co.dekoorb.c3469162.todoapplab04;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class TodoActivity extends AppCompatActivity {

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
}
