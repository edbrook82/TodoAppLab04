package uk.co.dekoorb.c3469162.todoapplab04.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by c3469162 on 10/11/2017.
 */

public class TodoModel {
    private static final String TAG = "TodoModel";
    private static TodoModel sTodoModel;

    private ArrayList<Todo> mTodoList;

    public static TodoModel get(Context context) {
        if (sTodoModel == null) {
            sTodoModel = new TodoModel(context);
        }

        return sTodoModel;
    }

    private TodoModel(Context context) {
        mTodoList = new ArrayList<>();
        // TODO >>>> remove this : for testing purposes only!
        for (int i=0; i<3; i++) {
            Todo todo = new Todo();
            todo.setTitle("Todo_" + i);
            mTodoList.add(todo);
            Log.d(TAG, "get: " + todo.getId().toString() + " " + todo);
        }
        // TODO <<<<
    }

    public Todo getTodo(UUID todoId) {
        for (Todo todo : mTodoList) {
            if (todo.getId().equals(todoId)) {
                return todo;
            }
        }
        return null;
    }

    public ArrayList<Todo> getTodos() {
        return mTodoList;
    }

    public void addTodo(Todo todo) {
        mTodoList.add(todo);
    }
}
