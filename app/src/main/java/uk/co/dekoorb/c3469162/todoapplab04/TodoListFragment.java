package uk.co.dekoorb.c3469162.todoapplab04;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import uk.co.dekoorb.c3469162.todoapplab04.databinding.TodoListItemBinding;
import uk.co.dekoorb.c3469162.todoapplab04.model.Todo;
import uk.co.dekoorb.c3469162.todoapplab04.model.TodoModel;

/**
 * Created by c3469162 on 10/11/2017.
 */

public class TodoListFragment extends Fragment {
    private RecyclerView mTodoRecyclerView;

    public static TodoListFragment newInstance() {
        TodoListFragment todoListFragment = new TodoListFragment();
        return todoListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        mTodoRecyclerView = (RecyclerView) view.findViewById(R.id.todo_recycler_view);
        mTodoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Todo> todos = TodoModel.get(getActivity()).getTodos();

        setHasOptionsMenu(true);

        updateUI(todos);

        return view;
    }

    private void updateUI(@Nullable List<Todo> todos) {
        TodoAdapter mTodoAdapter = new TodoAdapter(todos);
        mTodoRecyclerView.setAdapter(mTodoAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Todo> todos;
        TodoModel todoModel = TodoModel.get(getContext());
        todos = todoModel.getTodos();
        updateUI(todos);
    }

    public class TodoHolder extends RecyclerView.ViewHolder {
        private TodoListItemBinding mBinding;

        public TodoHolder(TodoListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setHandler(new TodoItemClickHandler());
        }

        public void bind(Todo todo){
            mBinding.setTodo(todo);
            mBinding.executePendingBindings();
        }

    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoListFragment.TodoHolder> {

        private List<Todo> mTodos;

        public TodoAdapter(List<Todo> todos) {
            mTodos = todos;
        }

        @Override
        public TodoListFragment.TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            TodoListItemBinding binding = TodoListItemBinding.inflate(layoutInflater, parent, false);
            return new TodoHolder(binding);
        }

        @Override
        public void onBindViewHolder(TodoHolder holder, int position) {
            Todo todo = mTodos.get(position);
            holder.bind(todo);
        }

        @Override
        public int getItemCount() {
            if (mTodos == null) {
                return 0;
            }
            return mTodos.size();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_todo_action:
                Intent intent = TodoActivity.newIntent(getActivity(), null);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class TodoItemClickHandler {
        public void onTodoClicked(Todo todo) {
            Intent intent = TodoActivity.newIntent(getActivity(), todo.getId());
            startActivity(intent);
        }
    }
}
