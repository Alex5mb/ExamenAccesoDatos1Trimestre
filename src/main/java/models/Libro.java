package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;



  @Entity
  @Table (name="examen.libro")
public class Libro implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column (name ="id")
    private Long id;
    
    private String titulo;
    private String autor;
    
     @OneToMany(mappedBy="libro", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Ejemplar> ejemplar;

    /*
    Completar con los métodos y atributos que sean necesarios
    */    
    public Libro() {
    }

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + '}';
    }
    
    

    
}
