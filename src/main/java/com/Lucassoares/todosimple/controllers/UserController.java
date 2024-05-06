package com.Lucassoares.todosimple.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Lucassoares.todosimple.models.User;
import com.Lucassoares.todosimple.models.User.CreateUser;
import com.Lucassoares.todosimple.models.User.UpdateUser;
import com.Lucassoares.todosimple.services.UserService;

import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping("/user")// já deixa como padrão, para essa rota
@Validated//ativa aquela previalização de banco que foi criada
public class UserController {
    @Autowired
    private UserService userService;
    // localhost:8080/user/1
   
    @GetMapping("/{id}")//Informa que é uma busca, para cair nesse local//{}-informa que é uma variavel
    public ResponseEntity<User> findById(@PathVariable Long id){//Retorna entidade de resposta do tipo usuario
                                        //Informa pro java que o que ta recebendo, é realmente uma variavel
    User obj = this.userService.findById(id);
    return ResponseEntity.ok().body(obj);//Seta o padrão de resposta de mensagem de https de 200 com o ok e o que ele vai retornar.
}

@PostMapping//Inserir um dado
@Validated(CreateUser.class)
public ResponseEntity<Void> create(@Valid @RequestBody User obj){//PASSA DADO NO BODY-- CRATE E UPDATE SE PASSA SÓ NO BODY!! GET E DELETE NÃO!
this.userService.create(obj);
URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
.path("/{id}").buildAndExpand(obj.getId()).toUri();
return ResponseEntity.created(uri).build();
}

@PutMapping("/{id}")//pet quase n é mais usado
@Validated(UpdateUser.class)
public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id){
    obj.setId(id);
  this.userService.update(obj);
    return ResponseEntity.noContent().build();
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id){
  this.userService.delete(id);
  return ResponseEntity.noContent().build();

}


} 