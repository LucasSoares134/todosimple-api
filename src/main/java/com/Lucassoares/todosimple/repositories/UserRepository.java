package com.Lucassoares.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Lucassoares.todosimple.models.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{//forneceço um contrato que extense o jpa, ontem ja tem mt metodo pronto
   
}