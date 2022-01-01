/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Conect.Consult;
import Library.Calendario;
import Library.FormatDecimal;
import Library.Objects;
import Library.Paginador;
import Library.Render_CheckBox;
import Library.UploadImage;
import Models.TClientes;
import Models.TReportes_clientes;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author Stiven Arboleda
 */
public class ClientsViewModel extends Consult {

    private String _accion = "insert", _mony;
    private final ArrayList<JLabel> _labels;
    private final ArrayList<JTextField> _textFields;
    private final JCheckBox _checkBoxCredito;
    private final JTable _tableClient, _tableReporte;
    private DefaultTableModel modelo1, modelo2;
    private final JSpinner _spinnerPaginas;
    private int _idCliente = 0;
    private int _reg_por_pagina = 10;
    private int _num_pag = 1;

    private Paginador<TClientes> _paginadorClientes;
    private Paginador<TClientes> _paginadorReportes;
    public int seccion;
    private final FormatDecimal _format;

    public ClientsViewModel(Object[] objects, ArrayList<JLabel> labels, ArrayList<JTextField> textFiled) {
        _labels = labels;
        _textFields = textFiled;
        _checkBoxCredito = (JCheckBox) objects[0];
        _tableClient = (JTable) objects[1];
        _spinnerPaginas = (JSpinner) objects[2];
        _tableReporte = (JTable) objects[3];
        _format = new FormatDecimal();
        reportesClientes(4);
        restart();
        restartReport();
        
    }

    // <editor-fold defaultstate="collapsed" desc="CODIGO DE REGISTRAR CLIENTE">
    /*
    * NO PUEDE ESTAR VACÍO EL CAMPO PARA EL 
    * ID, NOMBRE, APELLIDO, TELEFONO, EMAIL Y DIRECCION
     */
    public void registrarCliente() throws SQLException {
        if (_textFields.get(0).getText().equals("")) {
            _labels.get(0).setText("Ingresa el ID");
            _labels.get(0).setForeground(Color.RED);
            _textFields.get(0).requestFocus();
        } else {
            if (_textFields.get(1).getText().equals("")) {
                _labels.get(1).setText("Ingresa el NOMBRE");
                _labels.get(1).setForeground(Color.RED);
                _textFields.get(1).requestFocus();
            } else {
                if (_textFields.get(2).getText().equals("")) {
                    _labels.get(2).setText("Ingresa el APELLIDO");
                    _labels.get(2).setForeground(Color.RED);
                    _textFields.get(2).requestFocus();
                } else if (!Objects.events.isEmail(_textFields.get(3).getText())) {
                    _labels.get(3).setText("Ingresa un EMAIL valido");
                    _labels.get(3).setForeground(Color.RED);
                    _textFields.get(3).requestFocus();
                } else {
                    if (_textFields.get(4).getText().equals("")) {
                        _labels.get(4).setText("Ingresa el TELEFONO");
                        _labels.get(4).setForeground(Color.RED);
                        _textFields.get(4).requestFocus();
                    } else {
                        if (_textFields.get(5).getText().equals("")) {
                            _labels.get(5).setText("Ingresa la DIRECCION");
                            _labels.get(5).setForeground(Color.RED);
                            _textFields.get(5).requestFocus();
                        } else {
                            int count;
                            List<TClientes> listEmail = clientes().stream().filter(u -> u.getEmail().equals(_textFields.get(3).getText())).
                                    collect(Collectors.toList());
                            count = listEmail.size();

                            List<TClientes> listID = clientes().stream().filter(u -> u.getNumeroIdentidad().equals(_textFields.get(0).getText())).
                                    collect(Collectors.toList());
                            count += listID.size();
                            try {
                                switch (_accion) {
                                    case "insert":

                                        if (count == 0) {
                                            saveData();
                                        } else {
                                            if (!listEmail.isEmpty()) {
                                                _labels.get(3).setText("Email ya registrado");
                                                _labels.get(3).setForeground(Color.red);
                                                _labels.get(3).requestFocus();
                                            }
                                            if (!listID.isEmpty()) {
                                                _labels.get(0).setText("ID ya registrado");
                                                _labels.get(0).setForeground(Color.red);
                                                _labels.get(0).requestFocus();
                                            }
                                        }
                                        break;

                                    case "update":
                                        if (count == 2) {
                                            if (listEmail.get(0).getID() == _idCliente
                                                    && listID.get(0).getID() == _idCliente) {
                                                saveData();
                                            } else {
                                                if (listID.get(0).getID() != _idCliente) {
                                                    //_labels.get(0).setText("El ID ya está registrado");
                                                    JOptionPane.showMessageDialog(null, "El ID ya está registrado", "PROBLEMA DE ID", JOptionPane.WARNING_MESSAGE);
                                                    _labels.get(0).setForeground(Color.red);
                                                    _labels.get(0).requestFocus();
                                                }
                                                if (listEmail.get(0).getID() != _idCliente) {
                                                    //_labels.get(3).setText("El EMAIL ya está registrado");
                                                    JOptionPane.showMessageDialog(null, "El EMAIL ya está registrado", "PROBLEMA DE EMAIL", JOptionPane.WARNING_MESSAGE);
                                                    _labels.get(3).setForeground(Color.red);
                                                    _labels.get(3).requestFocus();
                                                }
                                            }
                                        } else {
                                            if (count == 0) {
                                                saveData();
                                            } else {
                                                if (!listID.isEmpty()) {
                                                    if (listID.get(0).getID() == _idCliente) {
                                                        saveData();
                                                    } else {
                                                        //_labels.get(0).setText("El ID ya está registrado");
                                                        JOptionPane.showMessageDialog(null, "El ID ya está registrado", "PROBLEMA DE ID", JOptionPane.WARNING_MESSAGE);
                                                        _labels.get(0).setForeground(Color.red);
                                                        _labels.get(0).requestFocus();
                                                    }
                                                }
                                                if (!listEmail.isEmpty()) {
                                                    if (listEmail.get(0).getID() == _idCliente) {
                                                        saveData();
                                                    } else {
                                                        //_labels.get(3).setText("El EMAIL ya está registrado");
                                                        JOptionPane.showMessageDialog(null, "El EMAIL ya está registrado", "PROBLEMA DE EMAIL", JOptionPane.WARNING_MESSAGE);
                                                        _labels.get(3).setForeground(Color.red);
                                                        _labels.get(3).requestFocus();
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                }
                            } catch (SQLServerException ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                    }
                }
            }
        }
    }

    private void saveData() throws SQLException {

        try {

            final QueryRunner qr = new QueryRunner(true);
            getConn().setAutoCommit(false);
            byte[] image = UploadImage.getImageByte();
            if (image == null) {
                image = Objects.uploadImage.getTransFoto(_labels.get(6));
            }
            switch (_accion) {
                case "insert":

                    String sqlCliente = "INSERT INTO tclientes(NumeroIdentidad, Nombre, Apellido, Email, Telefono, Direccion, Credito, Fecha, Imagen) VALUES (?,?,?,?,?,?,?,?,?)";

                    Object[] dataCliente = {
                        _textFields.get(0).getText(),
                        _textFields.get(1).getText(),
                        _textFields.get(2).getText(),
                        _textFields.get(3).getText(),
                        _textFields.get(4).getText(),
                        _textFields.get(5).getText(),
                        _checkBoxCredito.isSelected(),
                        new Calendario().getFecha(),
                        image,};
                    qr.insert(getConn(), sqlCliente, new ColumnListHandler(), dataCliente);

                    String sqlReport = "INSERT INTO treportes_clientes (Deuda, Mensual, Cambio, "
                            + "DeudaActual, FechaDeuda, UltimoPago, FechaPago, Ticket, FechaLimite, IdCliente) VALUES (?,?,?,?,?,?,?,?,?,?)";

                    List<TClientes> cliente = clientes();
                    Object[] dataReport = {
                        0,
                        0,
                        0,
                        0,
                        "--/--/--",
                        0,
                        "--/--/--",
                        0000000000,
                        "--/--/--",
                        cliente.get(cliente.size() - 1).getID(),};
                    qr.insert(getConn(), sqlReport, new ColumnListHandler(), dataReport);

                    String sqlIntereses = "INSERT INTO treportes_intereses_clientes"
                            + "(Intereses,Paog,Cambio,Cuota,InteresesFecha,TicketIntereses,"
                            + "IdCliente) VALUES (?,?,?,?,?,?,?)";

                    Object[] dataReportIntereses = {
                        0,
                        0,
                        0,
                        0,
                        "--/--/--",
                        "0000000000",
                        cliente.get(cliente.size() - 1).getID()

                    };
                    qr.insert(getConn(), sqlReport, new ColumnListHandler(), dataReportIntereses);
                    break;

                case "update":
                    Object[] dataCliente2 = {
                        _textFields.get(0).getText(),
                        _textFields.get(1).getText(),
                        _textFields.get(2).getText(),
                        _textFields.get(3).getText(),
                        _textFields.get(4).getText(),
                        _textFields.get(5).getText(),
                        _checkBoxCredito.isSelected(),
                        image,};

                    String sqlClient2 = "UPDATE tclientes SET NumeroIdentidad = ?, Nombre = ?, Apellido = ?, "
                            + "Email = ?, Telefono = ?, Direccion = ?, Credito = ?, Imagen = ? WHERE ID =" + _idCliente;

                    qr.update(getConn(), sqlClient2, dataCliente2);

                    break;
            }

            getConn().commit();
            restart();
        } catch (SQLException e) {
            getConn().rollback();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void searchCLient(String campo) {
        List<TClientes> clientesFilter;
        String[] titulos = {"ID", "Numero Identificación", "Nombre", "Apellido", "Email",
            "Direccion", "Telefono", "Credito", "Image"};
        modelo1 = new DefaultTableModel(null, titulos);
        int inicio = (_num_pag - 1) * _reg_por_pagina;

        if (campo.equals("")) {
            clientesFilter = clientes().stream().skip(inicio).limit(_reg_por_pagina)
                    .collect(Collectors.toList());
        } else {
            clientesFilter = clientes().stream().filter(C -> C.getNumeroIdentidad().startsWith(campo)
                    || C.getNombre().startsWith(campo) || C.getApellido().startsWith(campo)).skip(inicio).limit(_reg_por_pagina)
                    .collect(Collectors.toList());
        }
        if (!clientesFilter.isEmpty()) {
            clientesFilter.forEach(item -> {
                Object[] registros = {
                    item.getID(),
                    item.getNumeroIdentidad(),
                    item.getNombre(),
                    item.getApellido(),
                    item.getEmail(),
                    item.getDireccion(),
                    item.getTelefono(),
                    item.isCredito(),
                    item.getImagen()
                };
                modelo1.addRow(registros);
            });
            _tableClient.setModel(modelo1);
            _tableClient.setRowHeight(30);
            _tableClient.getColumnModel().getColumn(0).setMaxWidth(0);  //OCULTAR EL ID DEL REGISTRO
            _tableClient.getColumnModel().getColumn(0).setMinWidth(0);
            _tableClient.getColumnModel().getColumn(0).setPreferredWidth(0);
            _tableClient.getColumnModel().getColumn(8).setMaxWidth(0);  //OCULTAR LA IMAGEN
            _tableClient.getColumnModel().getColumn(8).setMinWidth(0);
            _tableClient.getColumnModel().getColumn(8).setPreferredWidth(0);
            _tableClient.getColumnModel().getColumn(7).setCellRenderer(new Render_CheckBox());
        }

    }

    public void getClients() {
        _accion = "update";
        int filas = _tableClient.getSelectedRow();
        _idCliente = (Integer) modelo1.getValueAt(filas, 0);
        _textFields.get(0).setText((String) modelo1.getValueAt(filas, 1));
        _textFields.get(1).setText((String) modelo1.getValueAt(filas, 2));
        _textFields.get(2).setText((String) modelo1.getValueAt(filas, 3));
        _textFields.get(3).setText((String) modelo1.getValueAt(filas, 4));
        _textFields.get(4).setText((String) modelo1.getValueAt(filas, 5));
        _textFields.get(5).setText((String) modelo1.getValueAt(filas, 6));
        _checkBoxCredito.setSelected((Boolean) modelo1.getValueAt(filas, 7));
        Objects.uploadImage.byteImage(_labels.get(6), (byte[]) modelo1.getValueAt(filas, 8));
    }

    public final void restart() {
        seccion = 1;
        _accion = "insert";
        _textFields.get(0).setText("");
        _textFields.get(1).setText("");
        _textFields.get(2).setText("");
        _textFields.get(3).setText("");
        _textFields.get(4).setText("");
        _textFields.get(5).setText("");
        _checkBoxCredito.setSelected(false);
        _checkBoxCredito.setForeground(new Color(102, 102, 102));

        //LABELS
        _labels.get(0).setText("Numero ID");
        _labels.get(0).setForeground(new Color(102, 102, 102));

        _labels.get(1).setText("Nombre");
        _labels.get(1).setForeground(new Color(102, 102, 102));

        _labels.get(2).setText("Apellido");
        _labels.get(2).setForeground(new Color(102, 102, 102));

        _labels.get(3).setText("Email");
        _labels.get(3).setForeground(new Color(102, 102, 102));

        _labels.get(4).setText("Teléfono");
        _labels.get(4).setForeground(new Color(102, 102, 102));

        _labels.get(5).setText("Dirección");
        _labels.get(5).setForeground(new Color(102, 102, 102));

        _labels.get(6).setIcon(new ImageIcon(getClass().getClassLoader().getResource("Resources/logo-google_1.png")));
        listClients = clientes();
        if (!listClients.isEmpty()) {
            _paginadorClientes = new Paginador<TClientes>(listClients, _labels.get(7), _reg_por_pagina);
        }

        SpinnerNumberModel model = new SpinnerNumberModel(
                new Integer(10), //DATO DE VISUALIZACIÓN AL INICIAR EL PROGRAMA
                new Integer(1), //LIMITE INFERIOR. NO HAY NEGATIVOS
                new Integer(50), // LIMITE SUPERIOR
                new Integer(1) //INTERVALO DE INCREMENTO-DECREMENTO
        );
        _spinnerPaginas.setModel(model);
        searchCLient("");
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="PAGOS Y REPORTES">
    
    public void searchReports(String valor) {
        List<TClientes> clientesFilter;
        String[] titulos = {"ID", "Numero Identificación", "Nombre", "Apellido", "Email",
            "Direccion", "Telefono"};
        modelo2 = new DefaultTableModel(null, titulos);
        int inicio = (_num_pag - 1) * _reg_por_pagina;

        if (valor.equals("")) {
            clientesFilter = clientes().stream().skip(inicio).limit(_reg_por_pagina)
                    .collect(Collectors.toList());
        } else {
            clientesFilter = clientes().stream().filter(C -> C.getNumeroIdentidad().startsWith(valor)
                    || C.getNombre().startsWith(valor) || C.getApellido().startsWith(valor)).skip(inicio).limit(_reg_por_pagina)
                    .collect(Collectors.toList());
        }
        if (!clientesFilter.isEmpty()) {
            clientesFilter.forEach(item -> {
                Object[] registros = {
                    item.getID(),
                    item.getNumeroIdentidad(),
                    item.getNombre(),
                    item.getApellido(),
                    item.getEmail(),
                    item.getDireccion(),
                    item.getTelefono(),};
                modelo2.addRow(registros);
            });
        }
        _tableReporte.setModel(modelo2);
        _tableReporte.setRowHeight(30);
        _tableReporte.getColumnModel().getColumn(0).setMaxWidth(0);
        _tableReporte.getColumnModel().getColumn(0).setMinWidth(0);
        _tableReporte.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    public final void restartReport() {
        listReportes = reportesClientes(4);
        //listClientReports = clientes();
        if (!listReportes.isEmpty()) {
           // _paginadorReportes = new Paginador<>(listReportes, _labels.get(7), _reg_por_pagina);
            _paginadorReportes = new Paginador<>(listClientReports, _labels.get(7), _reg_por_pagina);
        }
        searchReports("");
    }

    public void getReportClient() {
        int fila = _tableReporte.getSelectedRow();
        int idCliente = (Integer) modelo2.getValueAt(fila, 0);
        List<TReportes_clientes> clienteFilter = reportesClientes(idCliente);
        if (!clienteFilter.isEmpty()) {
            TReportes_clientes cliente = clienteFilter.get(0);
            _labels.get(8).setText(cliente.getNombre() + " " + cliente.getApellido());
            _labels.get(9).setText(_mony + _format.decimal((Double) cliente.getDeudaActual()));
            _labels.get(10).setText(_mony + _format.decimal((Double) cliente.getUltimoPago()));
            _labels.get(11).setText(cliente.getTicket());
            _labels.get(12).setText(cliente.getFechaPago());
            _labels.get(13).setText(_mony + _format.decimal((Double) cliente.getMensual()));

        }
    }

    // </editor-fold>
    
    
    private List<TClientes> listClients;
    private List<TClientes> listClientReports;
    private List<TReportes_clientes> listReportes;

    public void paginador(String metodo) {

        switch (metodo) {
            case "Primero":
                switch (seccion) {
                    case 1:
                        if (!listClients.isEmpty()) {
                            _num_pag = _paginadorClientes.primero();
                        }
                        break;
                    case 2:
                        if (!listClientReports.isEmpty()) {
                            _num_pag = _paginadorClientes.primero();
                        }
                        break;
                }
                break;
            case "Anterior":
                switch (seccion) {
                    case 1:
                        if (!listClients.isEmpty()) {
                            _num_pag = _paginadorClientes.anterior();
                        }
                        break;
                    case 2:
                        if (!listClientReports.isEmpty()) {
                            _num_pag = _paginadorClientes.anterior();
                        }
                        break;
                }
                break;
            case "Siguiente":
                switch (seccion) {
                    case 1:
                        if (!listClients.isEmpty()) {
                            _num_pag = _paginadorClientes.next();
                        }
                        break;
                    case 2:
                        if (!listClientReports.isEmpty()) {
                            _num_pag = _paginadorClientes.next();
                        }
                        break;
                }
                break;
            case "Ultimo":
                switch (seccion) {
                    case 1:
                        if (!listClients.isEmpty()) {
                            _num_pag = _paginadorClientes.ultimo();
                        }
                        break;
                    case 2:
                        if (!listClientReports.isEmpty()) {
                            _num_pag = _paginadorClientes.ultimo();
                        }
                        break;
                }
                break;
        }
        switch (seccion) {
            case 1:
                searchCLient("");
                break;
            case 2:
                searchReports("");
                break;
        }
    }

    public void registro_Paginas() {
        _num_pag = 1;
        Number caja = (Number) _spinnerPaginas.getValue();
        _reg_por_pagina = caja.intValue();
        switch (seccion) {
            case 1:
                if (!listClients.isEmpty()) {
                    _paginadorClientes = new Paginador<>(listClients, _labels.get(7), _reg_por_pagina);
                }
                searchCLient("");
                break;
            case 2:
                if (!listClientReports.isEmpty()) {
                    _paginadorReportes = new Paginador<>(listClientReports, _labels.get(7), _reg_por_pagina);
                }
                searchReports("");
                break;
        }

    }

}
