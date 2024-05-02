package com.Lucassoares.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
//mport lombok.Data; // Importa a anotação @Data do Lombok

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity //Consiedrar que vai ser uma entidade no banco, padrao JPA
@Table(name = User.TABLE_NAME) // Especifica o nome da tabela no banco de dados
//@Data // Adiciona automaticamente os métodos getters, setters, equals, hashCode e toString usando o Lombok
public class User {

    public static final String TABLE_NAME = "user";//Criou a constate pra força realmente o nome a ficar esse jeito no banco

    @Id
    //Seta a chave primaria no JPA: https://www.devmedia.com.br/jpa-como-usar-a-anotacao-id/38508
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração de ID automático
        //documentação: https://www.devmedia.com.br/jpa-como-usar-a-anotacao-generatedvalue/38592 
    
    @Column(name = "id", unique = true)//A restrição UNIQUE garante que todos os valores em uma coluna sejam diferentes. Ambas as restrições UNIQUE e PRIMARY KEY fornecem uma garantia de exclusividade para uma coluna ou conjunto de colunas. Uma restrição PRIMARY KEY possui automaticamente uma restrição UNIQUE.
     // Especifica o nome e as propriedades da coluna no banco de dados.....   // para não duplicar o valor
    private Long id;//criação da variavel que vai receber o atributo

    @Column(name = "username", length = 100, nullable = false, unique = true)    //anotações pra gerar "um padrão de acordo" no banco de dados
    @NotNull(groups = CreateUser.class) // Define que o campo não pode ser nulo ao criar um usuário
    @NotEmpty(groups = CreateUser.class) // Define que o campo não pode estar vazio ao criar um usuário
    @Size(groups = CreateUser.class, min = 2, max = 100) // Define o tamanho válido do campo ao criar um usuário
    //Muito usado para tratar o erro logo de começo, sem deixar cair no banco de dados e dar outro problema....
     private String username;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Define que este campo só pode ser acessado para escrita pra não retornar a senha pro usuário
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class}) // Define que o campo não pode ser nulo ao criar ou atualizar um usuário
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) // Define que o campo não pode estar vazio ao criar ou atualizar um usuário
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60) // Define o tamanho válido do campo ao criar ou atualizar um usuário
    private String password;

    public interface CreateUser {
    }
  //criadas, para na hora da criação, verificar se é not null ou vazia
    public interface UpdateUser {
    }
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();
    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }//contertutor para passar valores de forma padrão

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true; // Verifica se é o mesmo objeto na memória
        if (!(obj instanceof User)) return false; // Verifica se é uma instância de User
        User other = (User) obj;
        return Objects.equals(this.id, other.id) && // Compara os atributos para determinar igualdade
                Objects.equals(this.username, other.username) &&
                Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode()); // Calcula o hashcode baseado no ID
        return result;
    }
}

//SAPORRA CRIA NO BANCO MSM KK
//Lombok gera todos os get e setters por isso foi removido.