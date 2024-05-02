package com.Lucassoares.todosimple.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = Task.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    public static final String TABLE_NAME = "task";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne//varias tarefas são de um usuario, não dá pra criar uma tarefa sem um usuário, mas um usuário, pode ter varias tarefas(configuravael depois)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)//
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @Size(min = 1, max = 255)
    @NotBlank
    @NonNull
    @NotEmpty
    private String description;


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true; // Verifica se é o mesmo objeto na memória
        if (!(obj instanceof Task)) return false; // Verifica se é uma instância de Task
        Task other = (Task) obj;
        return Objects.equals(this.id, other.id) && // Compara os atributos para determinar igualdade
                Objects.equals(this.user, other.user) &&
                Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode()); // Calcula o hashcode baseado no ID
        return result;
    }


}/* 
@OneToMany: Define uma relação um-para-muitos entre duas entidades. Indica que uma instância da entidade atual pode estar associada a várias instâncias da entidade de destino.
@OneToOne: Define uma relação um-para-um entre duas entidades. Indica que uma instância da entidade atual está associada a exatamente uma instância da entidade de destino.
@ManyToMany: Define uma relação muitos-para-muitos entre duas entidades. Indica que várias instâncias da entidade atual estão associadas a várias instâncias da entidade de destino e vice-versa.
@JoinColumn: Usada para especificar a coluna na tabela do banco de dados que é usada para a associação entre duas entidades. É comumente usada em conjunto com @ManyToOne e @OneToOne.
@JoinTable: Usada para especificar a tabela de junção que é usada para representar uma associação muitos-para-muitos entre duas entidades. É comumente usada em conjunto com @ManyToMany.*/