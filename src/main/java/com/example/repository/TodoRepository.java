package com.example.repository;

import com.example.model.Todo;

import java.util.List;

public interface TodoRepository {

    void save (Todo todo);

    void update(int id, String title);

    void update(int id, boolean completed);

    void delete(int id);

    List<Todo> findAll(int userId);
}
