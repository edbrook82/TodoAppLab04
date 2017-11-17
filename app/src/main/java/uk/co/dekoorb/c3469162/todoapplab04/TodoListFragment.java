package uk.co.dekoorb.c3469162.todoapplab04;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.dekoorb.c3469162.todoapplab04.model.Todo;
import uk.co.dekoorb.c3469162.todoapplab04.model.TodoModel;

/**
 * Created by c3469162 on 10/11/2017.
 */

public class TodoListFragment extends Fragment {
    private RecyclerView mTodoRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        mTodoRecyclerView = (RecyclerView) view.findViewById(R.id.todo_recycler_view);
        mTodoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Todo> todos = TodoModel.get(getActivity()).getTodos();

        updateUI(todos);

        return view;
    }

    private void updateUI(@Nullable List<Todo> todos){
        TodoAdapter mTodoAdapter = new TodoAdapter(todos);
        mTodoRecyclerView.setAdapter(mTodoAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Todo> todos = new ArrayList<>();
        TodoModel todoModel = TodoModel.get(getContext());
        todos = todoModel.getTodos();
        updateUI(todos);
    }

    public class TodoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Todo mTodo;
        private TextView mTextViewTitle;
        private TextView mTextViewDate;

        public TodoHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.fragment_todo_list_item, parent, false));

            itemView.setOnClickListener(this);

            mTextViewTitle = (TextView) itemView.findViewById(R.id.todo_title);
            mTextViewDate = (TextView) itemView.findViewById(R.id.todo_date);

        }

        @Override
        public void onClick(View view) {
            Intent intent = TodoActivity.newIntent(getActivity(), mTodo.getId());
            startActivity(intent);
        }

        public void bind(Todo todo){
            mTodo = todo;
            mTextViewTitle.setText(mTodo.getTitle());
            mTextViewDate.setText(mTodo.getDate().toString());
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
            return new TodoHolder(layoutInflater, parent);
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
}
