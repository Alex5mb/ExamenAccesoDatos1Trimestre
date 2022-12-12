package dao;

import java.util.ArrayList;
import java.util.HashSet;
import models.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {
    
    private static SessionFactory sessionFactory;
    
    static{
        var sf = new Configuration().configure().buildSessionFactory();
        try(Session s = sf.openSession()){
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch(Exception ex){
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
        public static SessionFactory getSessionFactory(){
        return  sessionFactory;
    }
    
    public void saveLibro( Libro e ){
        
        var libro = new Libro();
        /* Guarda un libro con todos sus ejemplares en la base de datos */
        
        libro.setAutor(e.getAutor());
        libro.setTitulo(e.getTitulo());
                
        try (Session s = BibliotecaDAO.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(libro);
            t.commit();
        }
    }
  
    public HashSet<Libro> findByEstado(String estado){
        
        HashSet<Libro> salida = new HashSet<Libro>();
        ArrayList<Libro> listado;
        /* 
         Devuelve el conjunto de libros que tenga el estado indicado      
        */
         try (Session s = BibliotecaDAO.getSessionFactory().openSession()) {
            
              Query consulta = s.createQuery("from Ejemplar where Estado = :estado");
              consulta.setParameter("estado", estado);
              listado = (ArrayList<Libro>) consulta.list();          
         }
       
         
        return salida;
        
    }
    
    public void printInfo(){
        
        /* 
          Muestra por consola todos los libros de la biblioteca y el número
          de ejemplares disponibles de cada uno.
          
          Debe imprimirlo de esta manera:
        
          Biblioteca
          ----------
          Como aprender java en 24h. (3)
          Como ser buena persona (1)
          Aprobando exámenes fácilmente (5)
          ...
        
        */
         try (Session s = BibliotecaDAO.getSessionFactory().openSession()) {
         
         }
        
        
        
        
        
        
        
        
        
        
        System.out.println("Método printInfo no implementado");
        
    }
    
}
