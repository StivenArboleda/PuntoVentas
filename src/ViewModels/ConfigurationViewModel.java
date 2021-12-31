/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Conect.Consult;
import Models.TConfiguration;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JRadioButton;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author Stiven Arboleda
 */
public class ConfigurationViewModel extends Consult {

    public static String Mony;
    private static List<JRadioButton> _radio;

    public ConfigurationViewModel() {
        TypeMoney();
    }

    public ConfigurationViewModel(List<JRadioButton> radio) {
        _radio = radio;
        RadioEvent();
        TypeMoney();
    }

    private void RadioEvent() {

        _radio.get(0).addActionListener((ActionEvent e) -> {

            TypeMoney("COP", _radio.get(0).isSelected());

        });

        _radio.get(1).addActionListener((ActionEvent e) -> {

            TypeMoney("USD", _radio.get(1).isSelected());

        });
    }

    private String sqlConfig;

    private void TypeMoney() {

        sqlConfig = "INSERT INTO tconfiguration(TypeMoney) VALUES(?)";

        List<TConfiguration> config = config();
        final QueryRunner qr = new QueryRunner(true);

        if (config.isEmpty()) {
            Mony = "COP";
            Object[] dataConfig = {Mony};
            try {
                qr.insert(getConn(), sqlConfig, new ColumnListHandler(), dataConfig);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        } else {
            TConfiguration data = config.get(0);
            Mony = data.getTypeMoney();
            switch (Mony) {
                case "COP" ->
                    _radio.get(0).setSelected(true);
                case "USD" ->
                    _radio.get(1).setSelected(true);
            }
        }

    }

    private void TypeMoney(String typeMoney, boolean valor) {

        final QueryRunner qr = new QueryRunner(true);

        if (valor) {
            try {
                List<TConfiguration> config = config();
                if (config.isEmpty()) {
                    sqlConfig = "INSERT INTO tconfiguration(TypeMoney) VALUES(?)";
                    Mony = typeMoney;
                    Object[] dataConfig = {Mony};

                    qr.insert(getConn(), sqlConfig, new ColumnListHandler(), dataConfig);

                } else {

                    TConfiguration data = config.get(0);
                    sqlConfig = "UPDATE tconfiguration SET TypeMoney = ? WHERE ID =" + data.getID();
                   
                    if (data.getTypeMoney().equals(typeMoney)) {
                        Mony = typeMoney;
                    } else {
                        Mony = typeMoney;
                        Object[] dataConfig = {Mony};
                        qr.update(getConn(), sqlConfig, dataConfig);
                    }
                }
            } catch (SQLException e) {
            }
        }
    }

}
