/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ferreira
 */
@Entity
@Table(name = "lanche")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lanche.findAll", query = "SELECT l FROM Lanche l")
    , @NamedQuery(name = "Lanche.findById", query = "SELECT l FROM Lanche l WHERE l.id = :id")
    , @NamedQuery(name = "Lanche.findByIdLanche", query = "SELECT l FROM Lanche l WHERE l.idLanche = :idLanche")
    , @NamedQuery(name = "Lanche.findByQuantidade", query = "SELECT l FROM Lanche l WHERE l.quantidade = :quantidade")
    , @NamedQuery(name = "Lanche.findByPreco", query = "SELECT l FROM Lanche l WHERE l.preco = :preco")})
public class Lanche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_lanche")
    private Integer idLanche;
    @Column(name = "quantidade")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco")
        private Float preco;
    @JoinColumn(name = "id_ingrediente", referencedColumnName = "id")
    @ManyToOne
    private Ingrediente idIngrediente;

    public Lanche() {
    }

    public Lanche(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdLanche() {
        return idLanche;
    }

    public void setIdLanche(Integer idLanche) {
        this.idLanche = idLanche;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Ingrediente getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Ingrediente idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lanche)) {
            return false;
        }
        Lanche other = (Lanche) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Lanche[ id=" + id + " ]";
    }
    
}
