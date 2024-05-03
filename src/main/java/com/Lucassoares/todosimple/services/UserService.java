package com.Lucassoares.todosimple.services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Lucassoares.todosimple.models.User;
import com.Lucassoares.todosimple.repositories.TaskRepository;
import com.Lucassoares.todosimple.repositories.UserRepository;



@Service
public class UserService {
    @Autowired//utiliza as anotações do springboot para instancia e fazer as conexões entre as classes
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

public User findById(Long id){//crio o metodo como eu quiser
Optional/*vou receber um dado, mas não coloca nulo não, mas pode deixar vazio*/<User> user = this.userRepository.findById(id);
    return user.orElseThrow(() -> new RuntimeException(//exeção enquanto ta rodando
   "Usuário não encontrado! Id: "+ id + ", Tipo: " + User.class.getName()
   //eu retorno se tiver preenchido, se tiver vazio, eu trato uma exceção;
));
}

@Transactional//salvar no banco--ou salva tudo ou salva nada... Cria um cashe tbm... mas pesa um pouco
    public User create(User obj){
        obj.setId(null);//zera pra passar um novo, pra n dar merda
        obj = this.userRepository.save(obj);//salva 
        this.taskRepository.saveAll(obj.getTasks());
        return obj;

    }

  
    
    
}









