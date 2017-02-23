package controlador;

import java.awt.event.ActionListener;
import modelo.modelo;
import vista.vista;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class controlador implements ActionListener, MouseListener {

    private final vista vista;
    private final modelo consultas;

    String usuario = "";

    public controlador(vista v) {
        vista = v;
        this.consultas = new modelo();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public enum AccionMVC {

        //botones empresa
        btnSelecLogo,
        btnAceptarEmpresa,
        btnCancelarEmpresa,
        //botones menú superior
        labelUser,
        labelEmple,
        labelIncidencia,
        labelHorario,
        labelSalir,
        labelInforme,
        //panel login

        btnAceptLogin,
        btnSalirLogin,
        //panel usuarios

        btnAddUser,
        btnModUser,
        btnDeletUser,
        //panel empleados

        btnAddEmple,
        btnModEmple,
        btnDeletEmple,
        //panel incidencias

        btnAddInciden,
        btnModInciden,
        btnDeletInciden,
        //panel horarios

        btnAddTarea,
        btnModTarea,
        btnDeletTarea,
        //tablas
        tablaUsuario,
        tablaPlanning

    }

    public void iniciar() {

        //propiedades de los paneles principales
        //logo de las ventanas
        Toolkit t = Toolkit.getDefaultToolkit();
        //vista.setIconImage(t.getImage(getClass().getResourcer("/imagenes/logo.png")));

        if (consultas.existeEmpresa() == false) {
            vista.panelEmpresa.setVisible(true);
            vista.panelAdmin.setVisible(false);
            vista.panelEmple.setVisible(false);
            vista.panelLogin.setVisible(false);
        } else {
            vista.panelEmpresa.setVisible(false);
            vista.panelAdmin.setVisible(false);
            vista.panelEmple.setVisible(false);
            vista.panelLogin.setVisible(true);
        }
        //modificamos el titulo de las ventanas de la aplicacion
        vista.pack();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.setTitle("Incidencias Carmona");
        vista.tablaEmpleados.setModel(consultas.verEmpleados());
        vista.tablaHorarios.setModel(consultas.verTareas());
        vista.tablaIncidencias.setModel(consultas.listarIncidencias());
        vista.tablaIncidencias2.setModel(consultas.listarIncidencias());
        vista.tablaTareas.setModel(consultas.verTareas());
        vista.tablaUsuarios.setModel(consultas.verUsuarios());
        vista.cbDniTareas.setModel(consultas.cbDniEmpleados());
        vista.cbDniTarea.setModel(consultas.cbDniEmpleados());
        vista.cbEmpleIncidencias.setModel(consultas.cbEmpleados());
        vista.cbIncidenEmple.setModel(consultas.cbEmpleados());

        //ajusgamos el tamaño de las imagenes a sus contenedores (labels)
        /* ejemplo
		
		int logoEmpresaW = vista.logoEmpresa.getWidth();
		int logoEmpresaH = vista.logo-getHeight)=;
		ImageIcon logoEmpresaIcon = new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoEmpresa.png"));
		
		Image logoEmpresaImg = logoEmpresaIcon.getImage();
		Image logoEmpresaNewImg = logoEmpresaImg.getScaledInstance(logoEmpresaW, logoEmpresaH, java.awt.Image.SCALE_SMOOTH);
		logoEmpresaIcon = new ImageIcon(logoEmpresaNewImg);
		vista.logoEmpresa.setIcon(logoEmpresaIcon);
		
         */
        //controlamos los mouselistener de los labels
        //panel login
        /*
        vista.btnAceptLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ((consultas.existeUsuario(vista.txtUserLogin.getText())) = true) {
                    if (consultas.validarPassword(vista.txtUserLogin.getText())) {
                        JOptionPane.showMessageDialog(null, "Sesión iniciada");
                        vista.panelLogin.setVisible(false);
                        vista.panelAdmin.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email incorrecto");
                }
            }
        };
         */
        vista.txtUserLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    vista.txtPassLogin.requestFocus();
                }
            }
        });
        /*
        vista.txtPassLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    iniciarSesion();
                }
            }
        });
         */
 /*
        vista.btnSalirLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        
        //panel buttons
        vista.labelUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.panelButtons.setVisible(true);
                vista.panelInicio.setVisible(false);
                vista.panelLogin.setVisible(false);
                vista.panelUsuarios.setVisible(true);
                vista.panelEmpleados.setVisible(false);
                vista.panelIncidencias.setVisible(false);
                vista.panelHorarios.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                vista.labelUser.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                vista.labelUser.setBorder(null);
            }
        };

        vista.labelEmple.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.panelButtons.setVisible(true);
                vista.panelInicio.setVisible(false);
                vista.panelLogin.setVisible(false);
                vista.panelUsuarios.setVisible(false);
                vista.panelEmpleados.setVisible(true);
                vista.panelIncidencias.setVisible(false);
                vista.panelHorarios.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                vista.labelEmple.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                vista.labelEmple.setBorder(null);
            }
        };

        vista.labelIncidencia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.panelButtons.setVisible(true);
                vista.panelInicio.setVisible(false);
                vista.panelLogin.setVisible(false);
                vista.panelUsuarios.setVisible(false);
                vista.panelEmpleados.setVisible(false);
                vista.panelIncidencias.setVisible(true);
                vista.panelHorarios.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                vista.labelIncidencia.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                vista.labelIncidencia.setBorder(null);
            }
        };

        vista.labelHorario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.panelButtons.setVisible(true);
                vista.panelInicio.setVisible(false);
                vista.panelLogin.setVisible(false);
                vista.panelUsuarios.setVisible(false);
                vista.panelEmpleados.setVisible(false);
                vista.panelIncidencias.setVisible(false);
                vista.panelHorarios.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                vista.labelHorario.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                vista.labelHorario.setBorder(null);
            }
        };

        vista.labelSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.panelButtons.setVisible(false);
                vista.panelInicio.setVisible(false);
                vista.panelLogin.setVisible(true);
                vista.panelUsuarios.setVisible(false);
                vista.panelEmpleados.setVisible(false);
                vista.panelIncidencias.setVisible(false);
                vista.panelHorarios.setVisible(false);
                usuario = "";
                vista.txtUserLoginb.setText("");
                vista.txtPassLogin.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                vista.labelSalir.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                vista.labelSalir.setBorder(null);
            }
        };

        vista.labelInforme.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.panelButtons.setVisible(true);
                vista.panelInicio.setVisible(true);
                vista.panelLogin.setVisible(false);
                vista.panelUsuarios.setVisible(false);
                vista.panelEmpleados.setVisible(false);
                vista.panelIncidencias.setVisible(false);
                vista.panelHorarios.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                vista.labelInforme.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                vista.labelInforme.setBorder(null);
            }
        };
         */

        //controlamos los botones de la aplicacion
        //panel empresa
        vista.btnSelecLogo.setActionCommand("btnSelecLogo");
        vista.btnSelecLogo.addActionListener(this);

        vista.btnAceptarEmpresa.setActionCommand("btnAceptarEmpresa");
        vista.btnAceptarEmpresa.addActionListener(this);

        vista.btnCancelarEmpresa.setActionCommand("btnCancelarEmpresa");
        vista.btnCancelarEmpresa.addActionListener(this);

        //panel login
        vista.btnAceptLogin.setActionCommand("btnAceptLogin");
        vista.btnAceptLogin.addActionListener(this);

        vista.btnSalirLogin.setActionCommand("btnSalirLogin");
        vista.btnSalirLogin.addActionListener(this);

        //panel usuario
        vista.btnAddUser.setActionCommand("btnAddUser");
        vista.btnAddUser.addActionListener(this);

        vista.btnModUser.setActionCommand("btnModUser");
        vista.btnModUser.addActionListener(this);

        vista.btnDeletUser.setActionCommand("btnDeletUser");
        vista.btnDeletUser.addActionListener(this);

        //panel empleados
        vista.btnAddEmple.setActionCommand("btnAddEmple");
        vista.btnAddEmple.addActionListener(this);

        vista.btnModEmple.setActionCommand("btnModEmple");
        vista.btnModEmple.addActionListener(this);

        vista.btnDeletEmple.setActionCommand("btnDeletEmple");
        vista.btnDeletEmple.addActionListener(this);

        //panel incidencias
        vista.btnAddInciden.setActionCommand("btnAddInciden");
        vista.btnAddInciden.addActionListener(this);

        vista.btnModInciden.setActionCommand("btnModInciden");
        vista.btnModInciden.addActionListener(this);

        vista.btnDeletIncide.setActionCommand("btnDeletInciden");
        vista.btnDeletIncide.addActionListener(this);

        //panel horarios
        vista.btnAddTarea.setActionCommand("btnAddTarea");
        vista.btnAddTarea.addActionListener(this);

        vista.btnModTarea.setActionCommand("btnModTarea");
        vista.btnModTarea.addActionListener(this);

        vista.btnDeletTarea.setActionCommand("btnDeletTarea");
        vista.btnDeletTarea.addActionListener(this);

        vista.tablaUsuarios.addMouseListener(this);
        vista.tablaUsuarios.setName("tablaUsuario");

        vista.tablaHorarios.addMouseListener(this);
        vista.tablaHorarios.setName("tablaPlanning");
    }

    //acciones de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())) {

            //botones empresa
            case btnSelecLogo:
                vista.frameFC.setVisible(true);
                vista.frameFC.setSize(804, 502);
                break;

            case btnAceptarEmpresa:
                consultas.insertarEmpresa(vista.txtCifEmpresa.getText(), vista.txtNombreEmpresa.getText(), vista.txtDirEmpresa.getText(),
                        vista.txtTelEmpresa.getText(), vista.fcLogo.getSelectedFile());
                JOptionPane.showMessageDialog(vista, "Se ha insertado la empresa correctamente.");
                vista.panelLogin.setVisible(true);
                vista.panelAdmin.setVisible(false);
                vista.panelEmple.setVisible(false);
                vista.panelEmpresa.setVisible(false);
                break;

            case btnCancelarEmpresa:
                System.exit(0);
                break;

            //botones login
            case btnAceptLogin:
                if (consultas.existeUsuario(vista.txtUserLogin.getText(), vista.txtPassLogin.getText())) {
                    if (consultas.tipoUsuario(vista.txtUserLogin.getText()) == 0) {
                        JOptionPane.showMessageDialog(vista, "Logueo con exito");
                        vista.panelLogin.setVisible(false);
                        vista.panelAdmin.setVisible(true);
                        vista.panelEmple.setVisible(false);
                    } else if (consultas.tipoUsuario(vista.txtUserLogin.getText()) == 1) {
                        JOptionPane.showMessageDialog(vista, "Logueo con exito");
                        vista.panelLogin.setVisible(false);
                        vista.panelAdmin.setVisible(false);
                        vista.panelEmple.setVisible(true);
                    } else if (consultas.tipoUsuario(vista.txtUserLogin.getText()) == -1) {
                        JOptionPane.showMessageDialog(vista, "Ese usuario no tiene tipo");
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Error de email o contraseña");
                }
                break;
            case btnSalirLogin:
                System.exit(0);
                break;

            //botones panel usuario
            case btnAddUser:
                if (consultas.existeUsuarioSinPass(vista.txtEmailUser.getText()) == false) {
                    String tipoU = "";
                    if (vista.cbTipoUsuario.getSelectedIndex() == 0) {
                        tipoU = "0";
                    } else if (vista.cbTipoUsuario.getSelectedIndex() == 1) {
                        tipoU = "1";
                    }
                    consultas.insertarUsuario(tipoU, vista.txtPassUser.getText(), vista.txtEmailUser.getText());
                    vista.tablaUsuarios.setModel(consultas.verUsuarios());
                    vista.txtPassUser.setText("");
                    vista.txtEmailUser.setText("");
                } else {
                    System.out.println("Ya existe el usuario");
                }
                break;
            case btnModUser:
                if (consultas.existeUsuarioSinPass(vista.txtEmailUser.getText()) == true) {
                    consultas.actualizarUsuario(String.valueOf(vista.cbTipoUsuario.getSelectedIndex()), vista.txtPassUser.getText(), vista.txtEmailUser.getText());
                    vista.tablaUsuarios.setModel(consultas.verUsuarios());
                    vista.txtPassUser.setText("");
                    vista.txtEmailUser.setText("");
                    vista.txtEmailUser.setEditable(true);
                } else {
                    System.out.println("No existe el usuario");
                }
                break;
            case btnDeletUser:
                if (consultas.existeUsuarioSinPass(vista.txtEmailUser.getText()) == true) {
                    consultas.eliminarUsuario(vista.txtEmailUser.getText());
                    vista.tablaUsuarios.setModel(consultas.verUsuarios());
                    vista.txtPassUser.setText("");
                    vista.txtEmailUser.setText("");
                    vista.txtEmailUser.setEditable(true);
                } else {
                    System.out.println("No existe el usuario");
                }
                break;
//
//            //botones panel empleado
            case btnAddEmple:
                if (consultas.existeEmpleado(vista.txtDniEmple.getText()) == false) {
                    consultas.insertarEmpleado(vista.txtDniEmple.getText(), vista.txtNomEmple.getText(), vista.txtApeEmple.getText(), vista.txtPuestoEmple.getText(), vista.txtTlfnoEmple.getText(), usuario, vista.txtEmailEmple.getText());
                    JOptionPane.showMessageDialog(vista, "El empleado se inserto correctamente");
                    vista.tablaEmpleados.setModel(consultas.verEmpleados());
                    vista.txtDniEmple.setText("");
                    vista.txtNomEmple.setText("");
                    vista.txtApeEmple.setText("");
                    vista.txtPuestoEmple.setText("");
                    vista.txtTlfnoEmple.setText("");
                    vista.txtEmailEmple.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "El empleado ya existe");
                }
                break;

            case btnModEmple:
                int fila2 = vista.tablaEmpleados.getSelectedRow();
                if (fila2 > -1) {
                    consultas.actualizarEmpleado(vista.txtDniEmple.getText(), vista.txtNomEmple.getText(), vista.txtApeEmple.getText(), vista.txtPuestoEmple.getText(), vista.txtTlfnoEmple.getText(), usuario, vista.txtEmailEmple.getText());
                    JOptionPane.showMessageDialog(vista, "El empleado se actualizo correctamente");
                    vista.tablaEmpleados.setModel(consultas.verEmpleados());
                    vista.txtDniEmple.setText("");
                    vista.txtNomEmple.setText("");
                    vista.txtApeEmple.setText("");
                    vista.txtPuestoEmple.setText("");
                    vista.txtTlfnoEmple.setText("");
                    vista.txtEmailEmple.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se ha seleccionado ninguna fila");
                }

                break;

            case btnDeletEmple:
                int fila3 = vista.tablaEmpleados.getSelectedRow();
                if (fila3 > -1) {
                    consultas.eliminarEmpleado(vista.txtDniEmple.getText());
                    JOptionPane.showMessageDialog(vista, "El empleado se elimino correctamente");
                    vista.tablaEmpleados.setModel(consultas.verEmpleados());
                    vista.txtDniEmple.setText("");
                    vista.txtNomEmple.setText("");
                    vista.txtApeEmple.setText("");
                    vista.txtPuestoEmple.setText("");
                    vista.txtTlfnoEmple.setText("");
                    vista.txtEmailEmple.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se ha seleccionado ninguna fila");
                }

                break;
//
//            //botones panel horario
            case btnAddTarea:
                String fecha = new SimpleDateFormat("yyyy-MM-dd").format(this.vista.dcFechaHorarioAdmin.getDate());
                if (consultas.insertarTarea(fecha, vista.cbDniTarea.getSelectedItem().toString().split("-")[0], vista.txtZonaTarea.getText(), vista.txtTramoHorario.getText(), vista.txtTarea.getText())) {
                    JOptionPane.showMessageDialog(vista, "La tarea se inserto correctamente");
                    vista.tablaHorarios.setModel(consultas.verTareas());
                    vista.txtIdTarea.setText("");
                    vista.dcFechaHorarioAdmin.setDate(null);
                    vista.txtZonaTarea.setText("");
                    vista.txtTramoHorario.setText("");
                    vista.txtTarea.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "Ocurrio un error al insertar la tarea");
                }
                break;

            case btnModTarea:
                int fila = vista.tablaHorarios.getSelectedRow();
                if (fila > -1) {
                    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(this.vista.dcFechaHorarioAdmin.getDate());
                    consultas.ActualizarTarea(vista.txtIdTarea.getText(), fecha2, vista.cbDniTarea.getSelectedItem().toString().split("-")[0], vista.txtZonaTarea.getText(), vista.txtTramoHorario.getText(), vista.txtTarea.getText());
                    JOptionPane.showMessageDialog(vista, "Se ha actualizado la tarea con exito");
                    vista.tablaHorarios.setModel(consultas.verTareas());
                    vista.txtIdTarea.setText("");
                    vista.dcFechaHorarioAdmin.setDate(null);
                    vista.txtZonaTarea.setText("");
                    vista.txtTramoHorario.setText("");
                    vista.txtTarea.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se ha seleccionado ninguna fila");
                }
                break;

            case btnDeletTarea:
                int fila1 = vista.tablaHorarios.getSelectedRow();
                if (fila1 > -1) {
                    String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(this.vista.dcFechaHorarioAdmin.getDate());
                    consultas.eliminarTarea(vista.txtIdTarea.getText());
                    JOptionPane.showMessageDialog(vista, "Se ha eliminado la tarea correctamente");
                    vista.tablaHorarios.setModel(consultas.verTareas());
                    vista.txtIdTarea.setText("");
                    vista.dcFechaHorarioAdmin.setDate(null);
                    vista.txtZonaTarea.setText("");
                    vista.txtTramoHorario.setText("");
                    vista.txtTarea.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se ha seleccionado ninguna fila");
                }
                break;
//            //botones panel incidencia
            case btnAddInciden:
                if (consultas.insertarIncidencia(vista.txtNomIncidencia.getText(), vista.txtTipoIncidencia.getText(), usuario, usuario, usuario, vista.txtDescripcionIncide.getText(), vista.txtLocalizacionInciden.getText(), vista.txtEstadoInciden.getText(), vista.txtPrioridadIncide.getText(), vista.cbEmpleIncidencias.getSelectedItem().toString().split("-")[2])) {
                    JOptionPane.showMessageDialog(vista, "Se inserto la incidencia con exito");
                    vista.tablaIncidencias.setModel(consultas.listarIncidencias());
                    vista.txtCodIncidencia.setText("");
                    vista.txtNomIncidencia.setText("");
                    vista.txtTipoIncidencia.setText("");
                    vista.txtDescripcionIncide.setText("");
                    vista.txtLocalizacionInciden.setText("");
                    vista.txtEstadoInciden.setText("");
                    vista.txtPrioridadIncide.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "Ha ocurrido un error en la insercion");
                }
                break;

            case btnModInciden:
                int fila4 = vista.tablaIncidencias.getSelectedRow();
                if (fila4 > -1) {
                    consultas.actualizarIncidencia(vista.txtCodIncidencia.getText(), vista.txtNomIncidencia.getText(), vista.txtTipoIncidencia.getText(), usuario, usuario, usuario, vista.txtDescripcionIncide.getText(), vista.txtLocalizacionInciden.getText(), vista.txtEstadoInciden.getText(), vista.txtPrioridadIncide.getText(), vista.cbEmpleIncidencias.getSelectedItem().toString().split("-")[2]);
                    JOptionPane.showMessageDialog(vista, "Se actualizo la incidencia con exito");
                    vista.tablaIncidencias.setModel(consultas.listarIncidencias());
                    vista.txtCodIncidencia.setText("");
                    vista.txtNomIncidencia.setText("");
                    vista.txtTipoIncidencia.setText("");
                    vista.txtDescripcionIncide.setText("");
                    vista.txtLocalizacionInciden.setText("");
                    vista.txtEstadoInciden.setText("");
                    vista.txtPrioridadIncide.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se ha seleccionado ninguna fila");
                }
                break;

            case btnDeletInciden:
                int fila5 = vista.tablaIncidencias.getSelectedRow();
                if (fila5 > -1) {
                    consultas.eliminarIncidencia(vista.txtCodIncidencia.getText());
                    JOptionPane.showMessageDialog(vista, "Se elimino la incidencia con exito");
                    vista.tablaIncidencias.setModel(consultas.listarIncidencias());
                    vista.txtCodIncidencia.setText("");
                    vista.txtNomIncidencia.setText("");
                    vista.txtTipoIncidencia.setText("");
                    vista.txtDescripcionIncide.setText("");
                    vista.txtLocalizacionInciden.setText("");
                    vista.txtEstadoInciden.setText("");
                    vista.txtPrioridadIncide.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se ha seleccionado ninguna fila");
                }
                break;

        }
    }

    //mouseClicked
    public void mouseClicked(MouseEvent e) {

        //tabla usuarios
        int fila;
        switch (controlador.AccionMVC.valueOf(e.getComponent().getName())) {

            case tablaUsuario:
                fila = this.vista.tablaUsuarios.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    this.vista.txtEmailUser.setText(String.valueOf(this.vista.tablaUsuarios.getValueAt(fila, 2)));
                    this.vista.txtPassUser.setText(String.valueOf(this.vista.tablaUsuarios.getValueAt(fila, 1)));
                    if (vista.tablaUsuarios.getValueAt(fila, 0).equals("0")) {
                        this.vista.cbTipoUsuario.setSelectedIndex(0);
                    } else {
                        this.vista.cbTipoUsuario.setSelectedIndex(1);
                    }
                }
                vista.txtEmailUser.setEditable(false);
                break;

            case tablaPlanning:
                //String fecha = new SimpleDateFormat("yyyy-MM-dd").format(this.vista.dcFechaHorarioAdmin.getDate());
                fila = this.vista.tablaHorarios.rowAtPoint(e.getPoint());
                String entrada = String.valueOf(vista.tablaHorarios.getValueAt(fila, 1));
                SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = null;
                try {
                    fecha = parseador.parse(entrada);
                } catch (ParseException ex) {
                    Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = formateador.format(fecha);

                if (fila > -1) {
                    this.vista.txtIdTarea.setText(String.valueOf(this.vista.tablaHorarios.getValueAt(fila, 0)));
                    this.vista.dcFechaHorarioAdmin.setDate(fecha);
                    this.vista.cbDniTarea.setSelectedItem(vista.tablaHorarios.getValueAt(fila, 2));
                    this.vista.txtZonaTarea.setText(String.valueOf(vista.tablaHorarios.getValueAt(fila, 3)));
                    this.vista.txtTramoHorario.setText(String.valueOf(vista.tablaHorarios.getValueAt(fila, 4)));
                    this.vista.txtTarea.setText(String.valueOf(vista.tablaHorarios.getValueAt(fila, 5)));
                }
                break;
        }
    }
    /*
        //tabla empleados
        if (vista.tablaEmpleados.getSelectedRow() > -1) {
            int empleado = vista.tablaUsuarios.rowAtPoint(e.getPoint());
            if (empleado > -1) {
                try {
                    String empleTabla = String.valueOf(vista.tablaEmpleados.getValueAt(empleado, 0));

                    Object[] datosEmple = modelo.getDatosEmpleado(empleTabla);

                    vista.txtDniEmple.setText(datosEmple[1].toString());
                    vista.txtNomEmple.setText(datosEmple[2].toString());
                    vista.txtApeEmple.setText(datosEmple[3].toString());
                    vista.txtPuestoEmple.setText(datosEmple[4].toString());
                    vista.txtTlfnoEmple.setText(datosEmple[5].toString());
                    vista.txtEmailEmple.setText(datosEmple[6].toString());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al obtener los datos de la tupla de la tabla.\n\n" + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un elemento de la tabla.\n\n");
            }
        }

        //tabla incidencias
        if (vista.tablaIncidencias.getSelectedRow() > -1) {
            int incidencia = vista.tablaIncidencias.rowAtPoint(e.getPoint());
            if (incidencia > -1) {
                try {
                    String inciTabla = String.valueOf(vista.tablaIncidencias.getValueAt(incidencia, 0));

                    Object[] datosInci = modelo.listarIncidencias(inciTabla);

                    vista.txtCodIncidencia.setText(datosInci[1].toString());
                    vista.txtNomIncidencia.setText(datosInci[2].toString());
                    vista.txtTipoIncidencia.setText(datosInci[3].toString());
                    vista.txtDescripcionIncidencia.setText(datosInci[4].toString());
                    vista.txtLocalizacionIncidencia.setText(datosInci[5].toString());
                    vista.txtEstadoIncidencia.setText(datosInci[6].toString());
                    vista.txtPrioridadIncidencia.setText(datosInci[7].toString());
                    vista.txtEmailIncidencia.setText(datosInci[8].toString());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al obtener los datos de la tupla de la tabla.\n\n" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
     */
 /*
    public void iniciarModelo() {

        vista.tablaUsuario.setModel(consultas.tablaUsuarios());
        vista.tablaUsuario.getTableHeader().setReorderingAllowed(false);
        vista.tablaUsuario.getTableHeader().setResizingAllowed(false);

        vista.tablaEmpleado.setModel(consultas.tablaEmpleados());
        vista.tablaEmpleado.getTableHeader().setReorderingAllowed(false);
        vista.tablaHorario.getTableHeader().setResizingAllowed(false);

        vista.tablaHorario.setModel(consiltas.tablaHorario();
        vista.tablaHorario.getTableHeader().setReorderingAllowed(false);
        vista.tablaHorario.getTableHeader().setResizingAllowed(false);

        vista.tablaIncidencia.setModel(consultas.tablaIncidencia();
        vista.tablaIncidencia.getTableHeader().setReorderingAllowed(false);
        vista.tablaIncidencia.getTableHeader().setResizingAllowed(false);

    }

    //DEFINIMOS LA CONFIGURACIÓN DEL PROGRAMA AL INICIAR SESIÓN COMO TRABAJADOR
    public void inicioDeSesionDeTrabajador() {
        vista.tablaCesta.setModel(modelo.tablaCestaClienteVacia());
        vista.principal.pack();
        vista.principal.setLocationRelativeTo(null);
        vista.principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vista.principal.setVisible(true);
        cargarImagenesPrincipal();
        int tiempo = modelo.getTiempo(usuario);
        //RESUMIMOS EL HILO EN CASO DE HABER DESLOGEADO PARA QUE SIGA CONTANDO EL TIEMPO DE TRABAJO
        a.resumir();
        //EJECUTAMOS EL HILO PARA ACUMULAR EL TIEMPO DE TRABAJO DEL TRABAJADOR
        a.run(tiempo);
    }

    //DEFINIMOS LA CONFIGURACIÓN DEL PROGRAMA AL INCIAR SESIÓN COMO ADMINISTRADOR
    public void inicioDeSesionDeAdministrador() {
        vista.principalAdmin.pack();
        vista.principalAdmin.setLocationRelativeTo(null);
        vista.principalAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vista.principalAdmin.setVisible(true);
        cargarImagenesPrincipalAdmin();
        //SOLO EN CASO DE QUE EL USUARIO SEA UN ADMINISTRADOR, EJECUTAMOS EL HILO QUE COMPRUEBE LOS AVISOS
        c.resumir();
        c.run();
    }

    public void abrirPrograma() {
        String bd = vista.txtNombreBD.getText();
        String usu = vista.txtUsuBD.getText();
        String pass = vista.txtPassBD.getText();
        String ip = vista.txtIPBD.getText();
        String port = vista.txtPortBD.getText();

        vista.configuracionDB.setVisible(false);
        vista.cargando.pack();
        vista.cargando.setLocationRelativeTo(null);
        vista.cargando.setVisible(true);

        modelo = new Modelo(bd, usu, pass, ip, port);
        iniciarModelo();
    }

    //INICIAMOS LOS MÉTODOS RELACIONADOS CON EL MODELO Y ESTE MÉTODO SERÁ UTILIZADO TRAS LA CONEXIÓN A LA BASE DE DATOS
    public void iniciarModelo() {
        //LE AJUSTAMOS A LA TABLA DE PEDIDOS EL RENDERIZADOR MENCIONADO ANTERIORMENTE
        //ESTO SERVIRÁ PARA DARLE UN ASPECTO LIGERAMENTE MODIFICADO A LA TABLA EN CUESTIÓN
        render = new TablaRenderizador();
        vista.tablaPedidos.setDefaultRenderer(String.class, render);
        vista.tablaPedidos.setModel(modelo.tablaProductosHistorialVacia());
        vista.tablaPedidos1.setModel(modelo.tablaProductosHistorialVacia());
        vista.tablaPedidos.getTableHeader()
                .setReorderingAllowed(false);
        vista.tablaPedidos.getTableHeader()
                .setResizingAllowed(false);

        vista.tablaProductosProvee.setDefaultRenderer(String.class, render);
        vista.tablaProductosProvee.setModel(modelo.tablaProductosProveedoresVacia());
        vista.tablaProductosProvee.getTableHeader()
                .setReorderingAllowed(false);
        vista.tablaProductosProvee.getTableHeader()
                .setResizingAllowed(false);
        vista.tablaTrabajadores.getTableHeader()
                .setReorderingAllowed(false);
        vista.tablaTrabajadores.getTableHeader()
                .setResizingAllowed(false);
    }


     */
    /**
     * comprueba que no haya Strings vacios
     *
     * @param parametros strings
     * @return false si es ta vacio
     */

}

//    /**
//     * comprueba que no haya Strings vacios
//     *
//     * @param a
//     * @param parametros strings
//     * @return false si es ta vacio
//     */
//    public static boolean vacioString(String... a) {
//        for (String a1 : a) {
//            if (a1.isEmpty()) {
//                return false;
//            }
//        }
//        return true;
//    }
