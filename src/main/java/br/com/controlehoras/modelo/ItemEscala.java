package br.com.controlehoras.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "ItemEscala")
public class ItemEscala implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long codigo;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Escala escala;
    @ManyToOne
    @JoinColumn(nullable = true)
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(nullable = true)
    private Turno turno;

    public ItemEscala() {
      
    }

    @PostConstruct
    public void init() {
        turno = new Turno();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Escala getEscala() {
        return escala;
    }

    public void setEscala(Escala escala) {
        this.escala = escala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @NotNull(message = "Selecione um turno")
    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemEscala other = (ItemEscala) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemEscala{" + "codigo=" + codigo + ", escala=" + escala + ", funcionario=" + funcionario + ", turno=" + turno + '}';
    }

}
