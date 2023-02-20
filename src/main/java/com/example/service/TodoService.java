package com.example.service;

import com.example.model.Account;
import com.example.model.Todo;

import java.util.List;

public interface TodoService {

    void setAccount(Account user);

    void addTodo(String title);

    void editTodo(int id, String newTitle);

    void deleteTodo(int id);

    void completeTodo(int id);

    void completeAll();

    void clearCompleted();

    List<Todo> getTodos();

}
