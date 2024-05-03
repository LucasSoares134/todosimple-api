package com.Lucassoares.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Lucassoares.todosimple.models.User;



@Repository//define que isso é um repositorio
public interface UserRepository extends JpaRepository<User/*para conctar com o moledo criado*/, Long/*tipo de identificador do modelo(ID) */>{//forneceço um contrato que extense o jpa, ontem ja tem mt metodo pronto
//já possui todos os repositorios padroes criados... Mas se quiser, pode criar tbm!
}
   