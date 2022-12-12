package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO {
    
    private Connection connection;
    
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/examen?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    /* Completar consultas */
    static final String INSERT_QUERY ="INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `dni`) VALUES (NULL, ?, ?, ?);";
    static final String LIST_QUERY="SELECT * from usuario;";
    static final String GET_BY_DNI="SELECT * from usuario where dni = ?;";
    
    
    public void connect(){
        try {
            
            /* completar código de conexión */
           connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(UsuarioDAO.class.getName()).info("Conexión establecida con exito");
        }catch(Exception ex){
            System.out.println("Error al conectar a la base de datos");
            System.out.println("ex");
        }     
    }
  
    public void close(){
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");     
        }
    }
    
    public void save(Usuario user){
        
      try (var pst = connection.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)) {
          
          pst.setString(1, user.getNombre());
          //System.out.println(user.getNombre());
          pst.setString(2, user.getApellidos());
           //System.out.println(user.getApellidos());
          pst.setString(3, user.getDni());
           //System.out.println(user.getDni());
           if (pst.executeUpdate() > 0) {

                var keys = pst.getGeneratedKeys();
                keys.next();
            } 
      } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("completado");

    }

    public ArrayList<Usuario> list(){
        
        var salida = new ArrayList<Usuario>(0);
        try (var pst = connection.prepareStatement(LIST_QUERY)) {
            ResultSet resultSet = pst.executeQuery();
             while (resultSet.next()) {
               var user = new Usuario();
               
               user.setId(resultSet.getLong("id"));
               user.setNombre(resultSet.getString("nombre"));
               user.setApellidos(resultSet.getString("apellidos"));
               user.setDni(resultSet.getString("dni"));
                salida.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return salida;
    }
    
    public Usuario getByDNI(String dni){

        Usuario salida = new Usuario();
        
        try (var pst = connection.prepareStatement(GET_BY_DNI)) {

             pst.setString(1, dni);
             
              ResultSet resultSet = pst.executeQuery();
              while (resultSet.next()) {
              salida.setId(resultSet.getLong("id"));
              salida.setNombre(resultSet.getString("nombre"));
              salida.setApellidos(resultSet.getString("apellidos"));
              salida.setDni(resultSet.getString("dni"));
              }
         }
        catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return salida;
    }    
}
