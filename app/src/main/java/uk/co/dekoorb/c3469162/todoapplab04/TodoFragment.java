package uk.co.dekoorb.c3469162.todoapplab04;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

import uk.co.dekoorb.c3469162.todoapplab04.model.Todo;
import uk.co.dekoorb.c3469162.todoapplab04.model.TodoModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoFragment extends Fragment {

    private static final String TAG = "TodoFragment";

    public static final String EXTRA_TODO_UUID = "todo_id";

    private EditText mTodoTitle;
    private EditText mTodoDetail;
    private CheckBox mTodoComplete;
    private Button mButtonDate;

    private Todo mTodo;

    public static TodoFragment newInstance(UUID todoId) {
        TodoFragment todoFragment = new TodoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_TODO_UUID, todoId);
        todoFragment.setArguments(bundle);
        return todoFragment;
    }

    public TodoFragment() {
        // required empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID todoId = null;
        Bundle args = getArguments();

        if (args != null) {
            todoId = (UUID) args.getSerializable(EXTRA_TODO_UUID);
        }

        if (todoId != null) {
            Log.d(TAG, "onCreate: EXITING TODO");
            mTodo = TodoModel.get(getContext()).getTodo(todoId);
            Log.d(TAG, "onCreate: " + todoId + " -> " + mTodo);
        } else {
            Log.d(TAG, "onCreate: NEW TODO");
            mTodo = new Todo();
            TodoModel.get(getContext()).addTodo(mTodo);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        mTodoTitle = (EditText) view.findViewById(R.id.todo_title);
        mTodoDetail = (EditText) view.findViewById(R.id.todo_detail);
        mTodoComplete = (CheckBox) view.findViewById(R.id.todo_complete);
        mButtonDate = (Button) view.findViewById(R.id.btn_todo_date);

        mTodoTitle.setText(mTodo.getTitle());
        mTodoDetail.setText(mTodo.getDetail());
        mTodoComplete.setChecked(mTodo.isComplete());
        mButtonDate.setText(mTodo.getDate().toString());

        setupListeners();

        return view;
    }

    private void setupListeners() {
        mTodoTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTodo.setTitle(charSequence.toString());
                Log.d(TAG, "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        mTodoDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTodo.setDetail(charSequence.toString());
                Log.d(TAG, "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        mTodoComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean complete) {
                mTodo.setComplete(complete);
            }
        });

        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTodo.setDate(new Date());
                mButtonDate.setText(mTodo.getDate().toString());
            }
        });
    }

}
