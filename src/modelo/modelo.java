/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import database.conexion;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author F
 */
public class modelo extends conexion {

    public modelo() {
    }

    /**
     * Metodo que sirve para insertar una nueva empresa
     *
     * @param c CIF de la empresa
     * @param nom nombre de la empresa
     * @param dir direccion de la empresa
     * @param tel telefono de la empresa
     * @param l logo de la empresa
     * @return true o false
     */
    public boolean insertarEmpresa(String c, String nom, String dir, String tel, File l) {
        try {
            String q = "INSERT INTO empresa set CIF='" + c + "', nombre='" + nom + "', direccion='" + dir + "', telefono='" + tel + "', logo = ? ";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            FileInputStream fis = new FileInputStream(l);
            ps.setBinaryStream(1, fis, l.length());
            ps.execute(); // Ejecuto la consulta 
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        } catch (FileNotFoundException f) {
            //System.err.print(f.getMessage());
        }

        return false;
    }

    public Object[] getEmpresa() {
        Object[] a = new Object[5];
        Blob b;
        try {
            String q = "select CIF, nombre, direccion, telefono, logo from empresa";
            PreparedStatement cs = getConexion().prepareStatement(q);
            ResultSet rs = cs.executeQuery();
            rs.next();
            a[0] = rs.getString(1);
            a[1] = rs.getString(2);
            a[2] = rs.getString(3);
            a[3] = rs.getString(4);
            b = rs.getBlob(5);
            //convierte el blob en Image
            byte[] dat = b.getBytes(1, (int) b.length());
            BufferedImage img = null;
            img = ImageIO.read(new ByteArrayInputStream(dat));
            Image e = img;
            //crea el fichero de la imagen
            a[4] = e;
        } catch (Exception e) {
            //System.err.println(e.getMessage());
        }
        return a;
    }

    /**
     * Metodo que sirve para actualizar los datos de una empresa existente
     *
     * @param c CIF de la empresa
     * @param nom nombre de la empresa
     * @param dir direccion de la empresa
     * @param tel telefono de la empresa
     * @param l logo de la empresa
     * @return true o false
     */
    public boolean actualizarEmpresa(String c, String nom, String dir, String tel, String l) {
        try {
            String q = "UPDATE empresa set CIF='" + c + "', nombre='" + nom + "', direccion='" + dir + "', telefono='" + tel + "', logo='" + l + "' WHERE CIF='" + c + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo que sirve para saber si el empleado con el dni que se esta
     * metiendo existe o no
     *
     * @param d dni del empleado
     * @return true o false
     */
    public boolean existeEmpleado(String d) {
        String s = "";
        try {
            String q = "select dni from empleado where dni='" + d + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ResultSet res = ps.executeQuery(); //Ejecuto la query
            res.next();
            s = res.getString("dni"); //Con el result set recojo el resultado de la query
            res.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
            return false;
        }
    }

    public int tipoUsuario(String mail) {
        int s = -1;
        try {
            String q = "select tipo_usuario from usuario where email='" + mail + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ResultSet res = ps.executeQuery(); //Ejecuto la query
            res.next();
            s = res.getInt("tipo_usuario"); //Con el result set recojo el resultado de la query
            res.close();

        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return s;
    }

    /**
     * Metodo parar insertar un nuevo empleado
     *
     * @param d dni del empleado
     * @param nom nombre del empleado
     * @param ape apellidos del empleado
     * @param pues puesto del empleado
     * @param tel telefono del empleado
     * @param f foto del empleado
     * @param mail email del empleado
     * @return
     */
    public boolean insertarEmpleado(String d, String nom, String ape, String pues, String tel, String mail) {
        try {
            String q = "insert into empleado set dni='" + d + "', nombre='" + nom + "', apellidos='" + ape + "', puesto='" + pues + "', telefono='" + tel + "', email='" + mail + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para actualizar un empleado ya existente
     *
     * @param d dni del empleado
     * @param nom nombre del empleado
     * @param ape apellidos del empleado
     * @param pues puesto del empleado
     * @param tel telefono del empleado
     * @param f foto del empleado
     * @param mail email del empleado
     * @return true o false
     */
    public boolean actualizarEmpleado(String d, String nom, String ape, String pues, String tel, String mail) {
        try {
            String q = "update empleado set dni='" + d + "', nombre='" + nom + "', apellidos='" + ape + "', puesto='" + pues + "', telefono='" + tel + "',email='" + mail + "' where dni='" + d + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para eliminar un empleado existente
     *
     * @param d dni del empleado
     * @return true o false
     */
    public boolean eliminarEmpleado(String d) {
        try {
            String q = "delete from empleado where dni='" + d + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para mostrar en una tabla los datos de los empleados
     *
     * @return true o false
     */
    public DefaultTableModel verEmpleados() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        int total = 0;
        String[] columnas = {"Dni", "Nombre", "Apellidos", "Puesto", "Telefono", "Email"};
        try {
            String q = "SELECT count(*) as total FROM empleado";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            res.next();
            total = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        Object data[][] = new String[total][columnas.length]; //Sacamos un array para el nombre de las columnas y otro para los datos de las filas
        try {
            String q = "SELECT dni, nombre, apellidos, puesto, telefono, email FROM empleado";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("dni");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("apellidos");
                data[i][3] = res.getString("puesto");
                data[i][4] = res.getString("telefono");
                data[i][5] = res.getString("email");
                i++;
            }
            dtm.setDataVector(data, columnas); // Le decimos a la tabla que use el modelo de datos que hemos creado
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        return dtm;
    }

    /**
     * Metodo para mostrar los empleados existentes en un combobox
     *
     * @return true o false
     */
    public DefaultComboBoxModel cbEmpleados() {
        DefaultComboBoxModel com = new DefaultComboBoxModel();
        try {
            String q = "SELECT nombre, puesto, email FROM empleado";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                com.addElement(res.getString("nombre") + "-" + res.getString("puesto") + "-" + res.getString("email")); // te añade al combobox el resultado de la query
            }
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        return com;
    }

    /**
     * Metodo para comprobar si el email introducido del usuario existe
     *
     * @param mail Email del usuario
     * @return true o false
     */
    public boolean existeUsuario(String mail, String p) {
        String s = "";
        try {
            String q = "select email from usuario where email='" + mail + "' and pass='" + p + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ResultSet res = ps.executeQuery(); //Ejecuto la query
            res.next();
            s = res.getString("email"); //Con el result set recojo el resultado de la query
            res.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
            return false;
        }
    }

    public boolean existeUsuarioSinPass(String mail) {
        String s = "";
        try {
            String q = "select email from usuario where email='" + mail + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ResultSet res = ps.executeQuery(); //Ejecuto la query
            res.next();
            s = res.getString("email"); //Con el result set recojo el resultado de la query
            res.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
            return false;
        }
    }

    /*
     * Metodo para comprobar que la contraseña de un email en concreto es
     * correcta
     *
     * @param p Contraseña del usuario
     * @return true o false
     */
    /**
     * Metodo para insertar un nuevo usuario
     *
     * @param tipo Tipo de usuario, 0 administrador, 1 empleado
     * @param p password del usuario
     * @param mail Email de inicio de sesion del usuario
     * @return true o false
     */
    public boolean insertarUsuario(String tipo, String p, String mail) {
        try {
            String q = "insert into usuario set tipo_usuario=" + tipo + ", pass='" + p + "', email='" + mail + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
            //e.printStackTrace();
        }
        return false;
    }

    /**
     * Metodo para actualizar un usuario existente
     *
     * @param tipo Tipo de usuario, 0 administrador, 1 empleado
     * @param p password del usuario
     * @param mail Email de inicio de sesion del usuario
     * @return true o false
     */
    public boolean actualizarUsuario(String tipo, String p, String mail) {
        try {
            String q = "update usuario set tipo_usuario=" + tipo + ", pass='" + p + "' where email='" + mail + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para eliminar un usuario existente
     *
     * @param mail Email del usuario
     * @return true o false
     */
    public boolean eliminarUsuario(String mail) {
        try {
            String q = "delete from usuario where email='" + mail + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;
    }

    /**
     * Tabla para mostrar todos los usuarios existentes
     *
     * @return true o false
     */
    public DefaultTableModel verUsuarios() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        int total = 0;
        String[] columnas = {"Tipo de usuario", "Password", "Email"};
        try {
            String q = "SELECT count(*) as total FROM usuario";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            res.next();
            total = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        Object data[][] = new String[total][columnas.length]; //Sacamos un array para el nombre de las columnas y otro para los datos de las filas
        try {
            String q = "SELECT tipo_usuario, pass, email FROM usuario";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("tipo_usuario");
                data[i][1] = res.getString("pass");
                data[i][2] = res.getString("email");
                i++;
            }
            dtm.setDataVector(data, columnas); // Le decimos a la tabla que use el modelo de datos que hemos creado
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        return dtm;
    }

    /**
     * Metodo para insertar una nueva tarea
     *
     * @param id id de la tarea
     * @param fec fecha para la tarea
     * @param d dni del empleado que realizara la tarea
     * @param z zona en la que se realizara la tarea
     * @param tramo tramo horario en el que se realizara la tarea
     * @param tar Tarea a realizar
     * @return true o false
     */
    public boolean insertarTarea(String fec, String d, String z, String tramo, String tar) {
        try {
            String q = "insert into planning set fecha='" + fec + "', dni='" + d + "', zona='" + z + "', tramo_horario='" + tramo + "', tarea='" + tar + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para actualizar los datos de una tarea existente
     *
     * @param id id de la tarea
     * @param fec fecha para la tarea
     * @param d dni del empleado que realizara la tarea
     * @param z zona en la que se realizara la tarea
     * @param tramo tramo horario en el que se realizara la tarea
     * @param tar Tarea a realizar
     * @return true o false
     */
    public boolean ActualizarTarea(String id, String fec, String d, String z, String tramo, String tar) {
        try {
            String q = "update planning set fecha='" + fec + "', dni='" + d + "', zona='" + z + "', tramo_horario='" + tramo + "', tarea='" + tar + "' where id_tarea=" + id + "";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para eliminar una tarea existente
     *
     * @param id id de la tarea
     * @return true o false
     */
    public boolean eliminarTarea(String id) {
        try {
            String q = "delete from planning where id_tarea=" + id + "";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para mostrar en una tabla todas las tareas existentes
     *
     * @return true o false
     */
    public DefaultTableModel verTareas() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        int total = 0;
        String[] columnas = {"id_tarea", "fecha", "dni", "zona", "tramo_horario", "tarea"};
        try {
            String q = "SELECT count(*) as total FROM planning";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            res.next();
            total = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        Object data[][] = new String[total][columnas.length]; //Sacamos un array para el nombre de las columnas y otro para los datos de las filas
        try {
            String q = "SELECT id_tarea, fecha, dni, zona, tramo_horario, tarea FROM planning";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("id_tarea");
                data[i][1] = res.getString("fecha");
                data[i][2] = res.getString("dni");
                data[i][3] = res.getString("zona");
                data[i][4] = res.getString("tramo_horario");
                data[i][5] = res.getString("tarea");
                i++;
            }
            dtm.setDataVector(data, columnas); // Le decimos a la tabla que use el modelo de datos que hemos creado
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        return dtm;
    }

    /**
     * Metodo para recoger el dni y el nombre de todos los empleados existentes
     *
     * @return true o false
     */
    public DefaultComboBoxModel cbDniEmpleados() {
        DefaultComboBoxModel com = new DefaultComboBoxModel();
        try {
            String q = "SELECT dni, nombre FROM empleado";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                com.addElement(res.getString("dni") + "-" + res.getString("nombre")); // te añade al combobox el resultado de la query
            }
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        return com;
    }

    /**
     * Metodo para insertar una nueva incidencia
     *
     * @param nom Nombre de la incidencia
     * @param tip tipo de incidencia
     * @param img imagen de la incidencia
     * @param fec_suc fecha en la que sucede la incidencia
     * @param fec_res fecha en la que se resuleve la incidencia
     * @param desc Descripcion de la incidencia
     * @param loc localizacion de la incidencia
     * @param est Estado de la incidencia
     * @param prior Prioridad de la incidencia
     * @param mail Email del trabajador al que se le va a asignar la incidencia
     * @return true o false
     */
    public boolean insertarIncidencia(String nom, String tip, String fec_suc, String fec_res, String desc, String loc, String est, String prior, String mail) {
        try {
            String q = "insert into incidencia set nombre='" + nom + "', tipo='" + tip + "', fecha_suceso='" + fec_suc + "', fecha_resolucion='" + fec_res + "', descripcion='" + desc + "', localizacion='" + loc + "', estado='" + est + "', prioridad='" + prior + "', email='" + mail + "'";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para actualizar una incidencia
     *
     * @param nom Nombre de la incidencia
     * @param tip tipo de incidencia
     * @param img imagen de la incidencia
     * @param fec_suc fecha en la que sucede la incidencia
     * @param fec_res fecha en la que se resuleve la incidencia
     * @param desc Descripcion de la incidencia
     * @param loc localizacion de la incidencia
     * @param est Estado de la incidencia
     * @param prior Prioridad de la incidencia
     * @param mail Email del trabajador al que se le va a asignar la incidencia
     * @return true o false
     */
    public boolean actualizarIncidencia(String cod, String nom, String tip, String fec_suc, String fec_res, String desc, String loc, String est, String prior, String mail) {
        try {
            String q = "update incidencia set nombre='" + nom + "', tipo='" + tip + "', fecha_suceso='" + fec_suc + "', fecha_resolucion='" + fec_res + "', descripcion='" + desc + "', localizacion='" + loc + "', estado='" + est + "', prioridad='" + prior + "', email='" + mail + "' where codigo=" + cod + "";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ps.execute(); // Ejecuto la consulta
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para eliminar una incidencia
     *
     * @param cod codigo de la incidencia
     * @return true o false
     */
    public boolean eliminarIncidencia(String cod) {
        try {
            String q = "delete from incidencia where codigo=" + cod + "";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;

    }

    /**
     * Metodo para listar en una tabla todas las existencias existentes
     *
     * @return true o false
     */
    public DefaultTableModel listarIncidencias() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        int total = 0;
        String[] columnas = {"codigo", "nombre", "tipo", "fecha_suceso", "fecha_resolucion", "descripcion", "localizacion", "estado", "prioridad", "email"};
        try {
            String q = "SELECT count(*) as total FROM incidencia";
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            res.next();
            total = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        Object data[][] = new String[total][columnas.length]; //Sacamos un array para el nombre de las columnas y otro para los datos de las filas
        try {
            String q = "SELECT codigo, nombre, tipo, fecha_suceso, fecha_resolucion, descripcion, localizacion, estado, prioridad, email FROM incidencia";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("tipo");
                data[i][3] = res.getString("fecha_suceso");
                data[i][4] = res.getString("fecha_resolucion");
                data[i][5] = res.getString("descripcion");
                data[i][6] = res.getString("localizacion");
                data[i][7] = res.getString("estado");
                data[i][8] = res.getString("prioridad");
                data[i][9] = res.getString("email");
                i++;
            }
            dtm.setDataVector(data, columnas); // Le decimos a la tabla que use el modelo de datos que hemos creado
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return dtm;

    }

    /**
     * Metodo para comprobar si la empresa existe
     *
     * @return
     */
    public boolean existeEmpresa() {
        String s = "";
        try {
            String q = "select * from empresa";
            PreparedStatement ps = this.getConexion().prepareStatement(q); //preparo la consulta
            ResultSet res = ps.executeQuery(); //Ejecuto la query
            res.next();
            s = res.getString(1);
            res.close();
            return true;
        } catch (SQLException e) {
            //System.err.print(e.getMessage());
        }
        return false;
    }

    public DefaultComboBoxModel cbEmailEmpleado() {
        DefaultComboBoxModel com = new DefaultComboBoxModel();
        try {
            String q = "SELECT email FROM usuario";
            PreparedStatement ps = this.getConexion().prepareStatement(q);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                com.addElement(res.getString("email")); // te añade al combobox el resultado de la query
            }
            res.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());

        }
        return com;
    }

    public DefaultTableModel tablaBusqueda(String nom) { //tabla de busqueda de jugador por su dni//
        DefaultTableModel tablaBusqueda = new DefaultTableModel();
        int fila = 0;
        String[] nombre = {"DNI", "Nombre", "Apellidos", "Puesto", "Teléfono", "Email"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(dni) as total FROM empleado where nombre = '" + nom + "'");
            ResultSet res = pstm.executeQuery();
            res.next();
            fila = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[fila][6];
        try {
            PreparedStatement ps = this.getConexion().prepareStatement("select dni, nombre, apellidos, puesto, telefono, email from empleado where nombre ='" + nom + "'");
            ResultSet res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("dni");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("apellidos");
                data[i][3] = res.getString("puesto");
                data[i][4] = res.getString("telefono");
                data[i][5] = res.getString("email");
                i++;
            }
            res.close();
            tablaBusqueda.setDataVector(data, nombre);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablaBusqueda;

    }

}
