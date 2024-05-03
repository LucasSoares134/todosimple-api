package com.Lucassoares.todosimple.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.Lucassoares.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    //Optional<Task> findById(Long id);// se não existir coloca que ta vazio.

    List<Task> findByUser_Id(Long id);//entra dentro de user e pega o id, dentro de user

/*   @Query(value = "SELECT t FROM Task t where t.user.id = :id")
    List<Task> findByUserId(@Param("id") Long id);//Semi-sql
 */ 
/* 
@Query(value = "SELECT * FROM task t WHERE t.user_id =: id",nativeQuery = true)
List<Task> findByUserId(@Param("id") Long id);//full sql */ 
}