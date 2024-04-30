package com.Lucassoares.todosimple.models;
//Camada de modelo no banco, desse usuário
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
//Consiedrar que vai ser uma entidade no banco
@Table (name = User.TABLE_NAME)
//para criar tabelas
//poderia por somente name = "user". foi criado uma constante e chamada, para força a realmente ficar desse jeito
public class User {
    public interface CreateUser{

    }
    //criadas, para na hora da criação, verificar se é not null ou vazia
    public interface UpdateUser{

    }
  
        public static final String TABLE_NAME = "user";
    @Id
    //Criação dos atrivutos
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    //padrão de autoincrmente do mysql
    //documentação: https://www.devmedia.com.br/jpa-como-usar-a-anotacao-generatedvalue/38592    
    @Column(name = "id", unique = true)
    // para não duplicar o valor
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true )
    //anotações pra gerar "um padrão de acordo" no banco de dados
    @NotNull(groupe = CreateUser.class)//ao criar um usuario n pode ser nulo
    //não pode ser nulo
    @NotEmpty(groupe = CreateUser.class)//ao criar n pode ser vazio
    //não pode por String vazia
    @Size(groupe = CreateUser.class, min = 2,max = 1)// ao criar usuario tem que ta nos parametros
    //valor em tempo real do programa, sem dar erro(na inserção)
    private String username;
    @JsonProperty(access = Access.WRITE_ONLY)//senha somente escrita, pra não retornar a senha pro usuário
    @Column (name = "password", length = 60, nullable = false)
    @NotNull(groupe = {CreateUser.class, UpdateUser.class})//valida ao criar e se for atualizar
    @NotEmpty(groupe = {CreateUser.class, UpdateUser.class})//valida ao criar e se for atualizar
    @Size(groupe = {CreateUser.class, UpdateUser.class}, min = 8,max = 60)
    private String password;
    

  //  private List<Task> tasks = new ArrayList<Task>();


    public User() {
    }

    public User(Long id, String username, String password, List<Task> tasks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tasks = tasks;
    }

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
@Override
public boolean equals(Object obj){
        if (obj == this)
        return true;}
}       
        if (! (obj instanceof User)){
            return false;
        }
        if (obj == null){
            return false;
        }
        User other = (User) obj;
        if(this.id != null)
            return false;
        else if (!this.id.equals(other.id))
            return false;

        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username) && objects.equals(this.password, other.password);
        // casi seja tudo igual, são tudo igual, ou seja, é uma comparação...



@Override
public int hashcode(){
    final int prime =31;
    int result = 1;
    result = prime * result + ( this.id == null ? 0 : this.id.hashcode()));
    return result;
    //caso dê problema de memoria
}


}