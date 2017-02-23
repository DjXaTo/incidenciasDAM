package controlador;

import java.awt.Image;
import java.awt.event.ActionListener;
import modelo.modelo;
import vista.vista;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import static java.lang.Character.isLetter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import sun.net.www.content.image.png;

public class controlador implements ActionListener, MouseListener {

    File f = null;
    private final vista vista;
    private final modelo consultas;
    private JFileChooser fcLogo = new JFileChooser();

    FileNameExtensionFilter imagen = new FileNameExtensionFilter("Imagen(*.jpg),(*.png)", "jpg", "png");

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
        btnSeleFotoEmpleAdmin,
        btnBuscarEmpleAdmin,
        //panel incidencias

        btnAddInciden,
        btnModInciden,
        btnDeletInciden,
        btnSeleFotoIncidencia,
        //panel horarios

        btnAddTarea,
        btnModTarea,
        btnDeletTarea,
        //tablas
        tablaUsuario,
        tablaPlanning,
        tablaIncidencia,
        tablaEmpleado

    }

    public void iniciar() {

        //propiedades de los paneles principales
        //logo de las ventanas
        Object[] s = consultas.getEmpresa();
        vista.setIconImage((Image) s[4]);
        fcLogo.setFileFilter(imagen);
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
        vista.cbEmailEmpleAdmin.setModel(consultas.cbEmailEmpleado());

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

        vista.btnBuscarEmpleAdmin.setActionCommand("btnBuscarEmpleAdmin");
        vista.btnBuscarEmpleAdmin.addActionListener(this);

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

        vista.tablaIncidencias.addMouseListener(this);
        vista.tablaIncidencias.setName("tablaIncidencia");

        vista.tablaEmpleados.addMouseListener(this);
        vista.tablaEmpleados.setName("tablaEmpleado");

    }

    //acciones de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())) {

            //botones empresa
            case btnSelecLogo:
                fcLogo.setVisible(true);
                fcLogo.setSize(804, 502);
                if (fcLogo.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
                    f = fcLogo.getSelectedFile();

                }
                break;

            case btnAceptarEmpresa:
                if (soloNumeros(vista.txtCifEmpresa.getText()) && soloNumeros(vista.txtTelEmpresa.getText()) && soloLetras(vista.txtNombreEmpresa.getText())){
                consultas.insertarEmpresa(vista.txtCifEmpresa.getText(), vista.txtNombreEmpresa.getText(), vista.txtDirEmpresa.getText(),
                        vista.txtTelEmpresa.getText(), f);
                JOptionPane.showMessageDialog(vista, "Se ha insertado la empresa correctamente.");
                vista.panelLogin.setVisible(true);
                vista.panelAdmin.setVisible(false);
                vista.panelEmple.setVisible(false);
                vista.panelEmpresa.setVisible(false);
                Object[] s = consultas.getEmpresa();
                vista.setIconImage((Image) s[4]);
                } else {
                    JOptionPane.showMessageDialog(vista, "Datos incorrectos");
                } 
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
                    consultas.insertarEmpleado(vista.txtDniEmple.getText(), vista.txtNomEmple.getText(),
                            vista.txtApeEmple.getText(), vista.txtPuestoEmple.getText(), vista.txtTlfnoEmple.getText(),
                            vista.cbEmailEmpleAdmin.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(vista, "El empleado se inserto correctamente");
                    vista.tablaEmpleados.setModel(consultas.verEmpleados());
                    vista.txtDniEmple.setText("");
                    vista.txtNomEmple.setText("");
                    vista.txtApeEmple.setText("");
                    vista.txtPuestoEmple.setText("");
                    vista.txtTlfnoEmple.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "El empleado ya existe");
                }
                break;

            case btnModEmple:
                int fila2 = vista.tablaEmpleados.getSelectedRow();
                if (fila2 > -1) {
                    consultas.actualizarEmpleado(vista.txtDniEmple.getText(), vista.txtNomEmple.getText(),
                            vista.txtApeEmple.getText(), vista.txtPuestoEmple.getText(), vista.txtTlfnoEmple.getText(),
                            vista.cbEmailEmpleAdmin.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(vista, "El empleado se actualizo correctamente");
                    vista.tablaEmpleados.setModel(consultas.verEmpleados());
                    vista.txtDniEmple.setText("");
                    vista.txtNomEmple.setText("");
                    vista.txtApeEmple.setText("");
                    vista.txtPuestoEmple.setText("");
                    vista.txtTlfnoEmple.setText("");
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
                } else {
                    JOptionPane.showMessageDialog(vista, "No se ha seleccionado ninguna fila");
                }

                break;

            case btnSeleFotoEmpleAdmin:
                fcLogo.setVisible(true);
                fcLogo.setSize(804, 502);
                if (fcLogo.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
                    f = fcLogo.getSelectedFile();

                }
                break;

            case btnBuscarEmpleAdmin:
                consultas.tablaBusqueda(vista.txtBusqEmpleado.getText());
                vista.tablaEmpleados.setModel(consultas.tablaBusqueda(vista.txtBusqEmpleado.getText()));
                break;

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
                String fechaSuc = new SimpleDateFormat("yyyy-MM-dd").format(this.vista.fechaSucesoAdmin.getDate());
                String fechaRes = new SimpleDateFormat("yyyy-MM-dd").format(this.vista.fechaSucesoAdmin.getDate());
                if (consultas.insertarIncidencia(vista.txtNomIncidencia.getText(), vista.txtTipoIncidencia.getText(), fechaSuc,
                        fechaRes, vista.txtDescripcionIncide.getText(), vista.txtLocalizacionInciden.getText(),
                        vista.txtEstadoInciden.getText(), vista.txtPrioridadIncide.getText(),
                        vista.cbEmpleIncidencias.getSelectedItem().toString().split("-")[2])) {
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
                String fechaSuc1 = new SimpleDateFormat("yyyy-MM-dd").format(this.vista.fechaSucesoAdmin.getDate());
                String fechaRes1 = new SimpleDateFormat("yyyy-MM-dd").format(this.vista.fechaResolucionAdmin.getDate());
                if (fila4 > -1) {
                    consultas.actualizarIncidencia(vista.txtCodIncidencia.getText(), vista.txtNomIncidencia.getText(),
                            vista.txtTipoIncidencia.getText(), fechaSuc1, fechaRes1, vista.txtDescripcionIncide.getText(),
                            vista.txtLocalizacionInciden.getText(), vista.txtEstadoInciden.getText(), vista.txtPrioridadIncide.getText(),
                            vista.cbEmpleIncidencias.getSelectedItem().toString().split("-")[2]);
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

            case btnSeleFotoIncidencia:
                fcLogo.setVisible(true);
                fcLogo.setSize(804, 502);
                if (fcLogo.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
                    f = fcLogo.getSelectedFile();

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

            //tabla empleados
            case tablaIncidencia:
                fila = this.vista.tablaIncidencias.rowAtPoint(e.getPoint());
                String entrada1 = String.valueOf(vista.tablaIncidencias.getValueAt(fila, 3));
                String entrada2 = String.valueOf(vista.tablaIncidencias.getValueAt(fila, 4));
                SimpleDateFormat parseador1 = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha1 = null;
                Date fecha2 = null;
                try {
                    fecha1 = parseador1.parse(entrada1);
                    fecha2 = parseador1.parse(entrada2);
                } catch (ParseException ex) {
                    Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                SimpleDateFormat formateador1 = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada1 = formateador1.format(fecha1);
                String fechaFormateada2 = formateador1.format(fecha2);
                if (fila > -1) {
                    this.vista.txtCodIncidencia.setText(String.valueOf(this.vista.tablaIncidencias.getValueAt(fila, 0)));
                    this.vista.txtNomIncidencia.setText(String.valueOf(this.vista.tablaIncidencias.getValueAt(fila, 1)));
                    this.vista.txtTipoIncidencia.setText(String.valueOf(this.vista.tablaIncidencias.getValueAt(fila, 2)));
                    this.vista.fechaSucesoAdmin.setDate(fecha1);
                    this.vista.fechaResolucionAdmin.setDate(fecha2);
                    this.vista.txtDescripcionIncide.setText(String.valueOf(this.vista.tablaIncidencias.getValueAt(fila, 5)));
                    this.vista.txtLocalizacionInciden.setText(String.valueOf(this.vista.tablaIncidencias.getValueAt(fila, 6)));
                    this.vista.txtEstadoInciden.setText(String.valueOf(this.vista.tablaIncidencias.getValueAt(fila, 7)));
                    this.vista.txtPrioridadIncide.setText(String.valueOf(this.vista.tablaIncidencias.getValueAt(fila, 8)));
                    this.vista.cbEmpleIncidencias.setSelectedItem(vista.tablaIncidencias.getValueAt(fila, 9));
                }
                break;

            case tablaEmpleado:
                fila = this.vista.tablaEmpleados.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    this.vista.txtDniEmple.setText(String.valueOf(this.vista.tablaEmpleados.getValueAt(fila, 0)));
                    this.vista.txtNomEmple.setText(String.valueOf(this.vista.tablaEmpleados.getValueAt(fila, 1)));
                    this.vista.txtApeEmple.setText(String.valueOf(this.vista.tablaEmpleados.getValueAt(fila, 2)));
                    this.vista.txtPuestoEmple.setText(String.valueOf(this.vista.tablaEmpleados.getValueAt(fila, 3)));
                    this.vista.txtTlfnoEmple.setText(String.valueOf(this.vista.tablaEmpleados.getValueAt(fila, 4)));
                    this.vista.cbEmailEmpleAdmin.setSelectedItem(vista.tablaEmpleados.getValueAt(fila, 5));
                    break;
                }

        }

    }

    /**
     * Metodo para que solo acepte numeros enteros
     *
     * @param a: palabra que escribes
     * @return false si hay alguna letra
     */
    public static boolean soloNumeros(String a) {
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo para que solo acepte letras
     *
     * @param a: palabra que escribes
     * @return false si hay algun numero
     */
    public static boolean soloLetras(String a) {
        for (int i = 0; i < a.replace(" ", "").length(); i++) {
            if (!isLetter(a.replace(" ", "").charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo para introducir un dni
     *
     * @param dn dni
     * @return true si es correcto false si no
     */
    public static boolean dni(String dn) {
        return dn.length() == 9 && isLetter(dn.charAt(8)) && soloNumeros(dn.substring(1, 8));
    }

    /**
     * comprueba que no haya Strings vacios
     *
     * @param parametros strings
     * @return false si es ta vacio
     */
    /**
     * comprueba que no haya Strings vacios
     *
     * @param a
     * @param parametros strings
     * @return false si es ta vacio
     */
    public static boolean vacioString(String... a) {
        for (String a1 : a) {
            if (a1.isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
