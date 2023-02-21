package com.example.web;

import com.example.model.Todo;
import com.example.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    //create todos
    @RequestMapping(
            method = RequestMethod.POST,
            value="/com/todos"
    )
    public void saveTodo(@RequestBody Todo todo){
        todoRepository.save(todo);

    }

    // get all todos
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/com/todos/{accId}"
    )
    public List<Todo> todoList (@PathVariable(name = "accId")int accId){
        List<Todo> todoList = todoRepository.findAll(accId);
        return todoList;
    }

//    // get completed todos
//    @RequestMapping(
//            method = RequestMethod.GET,
//            value = "/com/todos/{accId}/completed"
//    )
//    public List<Todo> getCompletedTodos(@PathVariable(name = "accId")int accId){
//        List<Todo> todoList = todoRepository.findAll(accId);
//
//        return todoList.stream().filter(todo -> todo.isCompleted()).collect(Collectors.toList());
//    }





}
