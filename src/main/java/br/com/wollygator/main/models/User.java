package br.com.wollygator.main.models;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import br.com.wollygator.main.models.enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(min = 3, max = 30)
    private String name;
    @NotNull
    @Email(message = "Insira um email válido")
    @Length(min = 3, max = 30)
    private String email;
    @NotNull
    @Length(max = 20)
    private String password;
    // Associação 1-1 de Usuario e Carteira. Vai ser criada coluna carteira_id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carteira_id")
    private Carteira carteira;
    private TipoUsuario tipoUsuario;
    @CreationTimestamp
    private Instant createdAt;

    public User() {
    }

    public User(Long id, String name, String email, String password, Carteira carteira, TipoUsuario tipoUsuario,
            Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.carteira = carteira;
        this.tipoUsuario = tipoUsuario;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
