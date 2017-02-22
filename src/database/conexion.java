package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fali1
 */
public class conexion {
      private String db = "dam42_incidencias";
  /** usuario */
  private String user = "dam42";
  /** contraseña de MySql*/
  private String password = "0260flm4448glj";
  /** Cadena de conexion */
  private String url = "jdbc:mysql://79.148.236.236/"+db;
  /** variable para trabajar con la conexion a la base de datos */
  private Connection conn = null;
  
   /** Constructor de clase */
   public conexion(){
        this.url = "jdbc:mysql://79.148.236.236/"+this.db;
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection( this.url, this.user , this.password );         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }


   public Connection getConexion()
   {
    return this.conn;
   }
}
