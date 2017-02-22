package controlador;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import modelo.modelo;
import vista.vista;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class controlador implements ActionListener {

    private final vista vista;
    private final modelo consultas;

    String usuario = "";

    public controlador(vista v) {
        vista = v;
        this.consultas = new modelo();
    }


    public enum AccionMVC {

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
        btnDeletTarea

    }

    public void iniciar() {

        //propiedades de los paneles principales
        //logo de las ventanas
        Toolkit t = Toolkit.getDefaultToolkit();
        //vista.setIconImage(t.getImage(getClass().getResourcer("/imagenes/logo.png")));

        //modificamos el titulo de las ventanas de la aplicacion
        vista.pack();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.setTitle("Incidencias Carmona");

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
    }

    //acciones de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())) {

            //botones login
            case btnAceptLogin:
                if (consultas.existeUsuario(vista.txtUserLogin.getText(), vista.txtPassLogin.getText())) {
                    if (consultas.tipoUsuario(vista.txtUserLogin.getText()) == 1){
                    JOptionPane.showMessageDialog(vista, "Logueo con exito");
                    vista.panelLogin.setVisible(false);
                    vista.panelAdmin.setVisible(true);
                    vista.panelEmple.setVisible(false);
                    } else if (consultas.tipoUsuario(vista.txtUserLogin.getText()) == 0){
                    JOptionPane.showMessageDialog(vista, "Logueo con exito");
                    vista.panelLogin.setVisible(false);
                    vista.panelAdmin.setVisible(false);
                    vista.panelEmple.setVisible(true);
                    } else if (consultas.tipoUsuario(vista.txtUserLogin.getText()) == -1){
                        JOptionPane.showMessageDialog(vista, "Ese usuario no tiene tipo");
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Error de email o contraseña");
                }
                break;
            case btnSalirLogin:
                System.exit(0);
                break;
/*
            //botones panel usuario
            case btnAddUser:
                consultas.existeUsuario();
                if (consultas.existeUsuario() == 0) {
                    consultas.insertarUsuario()
                } else {
                    System.out.println("Ya existe el usuario");
                }
                break;
            case btnModUser:
                consultas.actualizarUsuario();
                break;
            case btnDeletUser:
                consultas.eliminarUsuario();
                break;

            //botones panel empleado
            case btnAddEmple:
                consultas.existeEmpleado();
                if (consultas.existeEmpleado() == 0) {
                    consultas.insertarEmpleado()
                } else {
                    System.out.println("Ya existe el empleado");
                }
                break;
            case btnModEmple:
                consultas.actualizarEmpleado();
                break;
            case btnDeletEmple:
                consultas.eliminarUsuario();
                break;

            //botones panel horario
            case btnAddTarea:
                consultas.insertarTarea();
                break;
            case btnModTarea:
                consultas.actualizarTarea();
                break;
            case btnDeletTarea:
                consultas.eliminarTarea();

            //botones panel incidencia
            case btnAddInciden:
                consultas.insertarIncidencia();
                break;
            case btnModInciden:
                consultas.actualizarIncidencia();
                break;
            case btnDeletInciden:
                consultas.eliminarIncidencia();
                break;

        }*/
    }
/*
    //mouseClicked
    @Override
    public void mouseClicked(MouseEvent e) {

        //tabla usuarios
        if (vista.tablaUsuarios.getSelectedRow() > -1) {
            int usuario = vista.tablaUsuarios.rowAtPoint(e.getPoint());
            if (usuario > -1) {
                try {
                    String userTabla = String.valueOf(vista.tablaUsuarios.getValueAt(usuario, 0));

                    Object[] datosUsuario = modelo.getDatosUsuario(userTabla);

                    vista.txtEmailUser.setText(datosUsuario[1].toString());
                    vista.txtPassUser.setText(datosUsuario[2].toString());
                    vista.txtTipoUser.setText(datosUsuario[3].toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al obtener los datos de la tupla de la tabla.\n\n" + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un elemento de la tabla.\n\n");
            }
        }

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
}
}
