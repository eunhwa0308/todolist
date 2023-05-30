package likelion.todolist.service;

import likelion.todolist.entity.ToDo;
import likelion.todolist.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    @Transactional
    public void saveTodo(String content){
        ToDo todo = new ToDo();
        todo.setContent(content);
        todo.setCompleted(false);
        toDoRepository.save(todo);
    }
    //update 코드
    @Transactional
    public ToDo updateTodo(Long todoId, String content, Boolean completed){
        ToDo findTodo = toDoRepository.findOne(todoId);
        findTodo.setContent(content);
        findTodo.setCompleted(completed);
        toDoRepository.save(findTodo);
        return findTodo;

    }

    //delete 코드
    @Transactional
    public void deleteTodo(Long id){
        ToDo findToDo = toDoRepository.findOne(id);
        if(findToDo == null)
            throw new IllegalArgumentException("해당 아이템이 없습니다.");
        toDoRepository.delete(findToDo);
    }

    @Transactional
    public ToDo findOne(Long id){
        return toDoRepository.findOne(id);
    }

    @Transactional
    public List<ToDo> findToDos(){
        return toDoRepository.findAll();
    }

}
