package uk.co.dekoorb.c3469162.todoapplab04;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import uk.co.dekoorb.c3469162.todoapplab04.databinding.TodoItemBinding;
import uk.co.dekoorb.c3469162.todoapplab04.model.Todo;
import uk.co.dekoorb.c3469162.todoapplab04.model.TodoModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoFragment extends Fragment {

    public static final String EXTRA_TODO_UUID = "todo_id";

    private Todo mTodo;
    private TodoItemBinding mBinding;

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
            mTodo = TodoModel.get(getContext()).getTodo(todoId);
        } else {
            mTodo = new Todo();
            TodoModel.get(getContext()).addTodo(mTodo);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = TodoItemBinding.inflate(inflater, container, false);
//        mBinding.setHandler(new TodoHandler());
        mBinding.setTodo(mTodo);
        return mBinding.getRoot();
    }
}
