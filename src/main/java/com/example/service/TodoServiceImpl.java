package com.example.service;

import com.example.model.Account;
import com.example.model.Todo;
import com.example.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;
    Account account;

//    public TodoServiceImpl(TodoRepository todoRepository){
//        this.todoRepository = todoRepository;
//    }

    @Override
    public void setAccount(Account account) {
        this.account =account;
    }

    @Override
    public void addTodo(String title) {
        Todo newTodo = new Todo(title,false);
        newTodo.setAccount(account);
        todoRepository.save(newTodo);
    }

    @Override
    public void editTodo(int id, String newTitle) {

    }

    @Override
    public void deleteTodo(int id) {

    }

    @Override
    public void completeTodo(int id) {

    }

    @Override
    public void completeAll() {

    }

    @Override
    public void clearCompleted() {

    }

    @Override
    public List<Todo> getTodos() {
        return null;
    }
}
