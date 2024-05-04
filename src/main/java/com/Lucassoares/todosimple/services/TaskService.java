package com.Lucassoares.todosimple.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.Lucassoares.todosimple.models.User;
import com.Lucassoares.todosimple.repositories.TaskRepository;

@Service
public class TaskService {
    @Autowired
   private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
   Optional/*vou receber um dado, mas não coloca nulo não, mas pode deixar vazio*/<User> user = this.userRepository.findById(id);
    return user.orElseThrow(() -> new RuntimeException(//exeção enquanto ta rodando--- ()-> deixa colocar uma função dentro de uma função
   "Usuário não encontrado! Id: "+ id + ", Tipo: " + User.class.getName()));
   //eu retorno se tiver preenchido, se tiver vazio, eu trato uma exceção;

    }
}