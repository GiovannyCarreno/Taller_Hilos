package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;

import Control.Control;
import Logic.Cliente;
import Logic.Eps;

public class MainFrame extends JFrame implements ActionListener {
    private JLabel[] lblModuloEstado;
    private JButton btnAgregar;
    private Control control;
    private Eps eps;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaPalabras;
    private JScrollPane scroll;
    private JLabel nombreLabel, documentoLabel, servicioLabel, turnoLabel, usuario, modulo;
    private JTextField nombreText, documentoText;
    private JComboBox servicioBox;
    private ArrayDeque<Cliente> cola;
    int turno;
    public MainFrame() {
        lblModuloEstado = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            lblModuloEstado[i] = new JLabel("Módulo " + (i + 1) + " libre");
            lblModuloEstado[i].setOpaque(true);
            lblModuloEstado[i].setBackground(Color.GREEN);
            lblModuloEstado[i].setHorizontalAlignment(JLabel.CENTER);
        }
        nombreLabel = new JLabel("  Nombre: ");
        documentoLabel = new JLabel("  Documento: ");
        servicioLabel = new JLabel("  Servicio:");
        turnoLabel = new JLabel("  Turno Actual      -->       0");
        turnoLabel.setBackground(Color.GREEN);
        turnoLabel.setOpaque(true);
        turnoLabel.setFont(new Font("Calibri", Font.PLAIN,22));
        nombreText = new JTextField();
        documentoText = new JTextField();
        String[] opciones = {"Afiliacion",
                "Certificado medico",
                "Cita medica",
                "Autorizacion de servicio medico",
                "Solicitud de medicamentos",
                "Consultas"};
        servicioBox = new JComboBox(opciones);
        btnAgregar = new JButton("Agregar a la Cola");
        modeloLista = new DefaultListModel<>();
        listaPalabras = new JList<>(modeloLista);
        scroll = new JScrollPane(listaPalabras);
        cola = new ArrayDeque<>();
        control = new Control();
        usuario = new JLabel();
        usuario.setOpaque(true);
        usuario.setFont(new Font("Calibri",Font.PLAIN,15));
        modulo = new JLabel();
        modulo.setOpaque(true);
        modulo.setFont(new Font("Calibri",Font.PLAIN,15));
        eps = new Eps(modeloLista,lblModuloEstado,turno,cola,turnoLabel,usuario,modulo);
        turno = 1;
    }

    public void principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        setResizable(false);
        setTitle("Modulos");
        setLocationRelativeTo(null);
        setLayout(null);

        add(mainJPanel());
        setVisible(true);
    }

    private JPanel mainJPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(0, 0, 580, 350);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(panelModulosEstado(), BorderLayout.NORTH);
        mainPanel.add(panelAgregar(), BorderLayout.CENTER);
        mainPanel.add(panelTurnos(), BorderLayout.SOUTH);

        return mainPanel;

    }

    private JPanel panelTurnos() {
        JPanel pnlTurnos = new JPanel(new BorderLayout());
        pnlTurnos.setBorder(BorderFactory.createTitledBorder("Turnos"));

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pnlTurnos.add(scroll, BorderLayout.CENTER);
        return pnlTurnos;

    }

    private JPanel panelModulosEstado() {
        JPanel pnlModulos = new JPanel(new GridLayout(1, 3));
        for (int i = 0; i < 3; i++) {
            pnlModulos.add(lblModuloEstado[i]);
        }
        pnlModulos.setBorder(BorderFactory.createTitledBorder("Módulos"));
        return pnlModulos;
    }

    private JPanel panelAgregar() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(usuario);
        inputPanel.add(modulo);
        inputPanel.add(nombreLabel);
        inputPanel.add(nombreText);
        inputPanel.add(documentoLabel);
        inputPanel.add(documentoText);
        inputPanel.add(servicioLabel);
        inputPanel.add(servicioBox);
        inputPanel.add(turnoLabel);
        btnAgregar.addActionListener(this);
        inputPanel.add(btnAgregar);
        return inputPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            if (e.getSource() == btnAgregar) {
                String nombre = nombreText.getText().trim();
                String documento = documentoText.getText().trim();
                if (!control.validarNombre(nombre)) {
                    JOptionPane.showMessageDialog(this, "El nombre debe contener solo letras", "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!control.validarDocumento(documento)) {
                    JOptionPane.showMessageDialog(this, "El documento debe contener solo números",
                            "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Cliente cliente = new Cliente();
                cliente.setNombre(nombreText.getText());
                cliente.setTurno(turno++);
                cliente.setNumDocumento(documentoText.getText());
                cliente.setServicio(servicioBox.getSelectedItem().toString());
                cola.add(cliente);
                eps.addAllElements();
                eps.assingModule();
                JOptionPane.showMessageDialog(null,"Se le ha asignado el turno " + (turno - 1));
                nombreText.setText("");
                documentoText.setText("");
            }
        } else {
            int modulo = -1;
            for (int i = 0; i < 3; i++) {
                if (e.getSource() == lblModuloEstado[i]) {
                    modulo = i;
                    break;
                }
            }
            if (modulo >= 0) {
                lblModuloEstado[modulo].setBackground(Color.RED);
                lblModuloEstado[modulo].setText("Módulo " + (modulo + 1) + " en uso");
            }
        }
    }

}