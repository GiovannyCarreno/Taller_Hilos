package Logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.Scanner;

public class Eps {
    private Scanner scaner;
    private int turno;
    private boolean salir;
    private boolean isRunning;
    private ArrayDeque<Cliente> cola;
    private boolean[] module;
    private DefaultListModel<String> lista;
    private JLabel[] lblModuloEstado;
    private JLabel turnoActual, usuario, modulo;

    public Eps(DefaultListModel<String> lista, JLabel[] lblModuloStado, int turno,ArrayDeque<Cliente> cola, JLabel turnoActual, JLabel usuario, JLabel modulo){
        this.scaner = new Scanner(System.in);
        this.cola = cola;
        this.salir = true;
        this.turno = 1;
        this.module = new boolean[3];
        this.isRunning = false;
        this.lista = lista;
        this.lblModuloEstado = lblModuloStado;
        this.turno = turno;
        this.turnoActual = turnoActual;
        this.usuario = usuario;
        this.modulo = modulo;
    }

    public void addAllElements(){
        lista.removeAllElements();
        for (Cliente cliente:cola){
            lista.addElement("Nombre: " + cliente.getNombre() +
                    "  --  Documento: " + cliente.getNumDocumento() +
                    "  --  Servicio: " + cliente.getServicio() +
                    "  --  Turno: " + cliente.getTurno());
        }
    }

    public void assingModule(){
        if (cola.isEmpty()){
            isRunning= false;
        }else {
            if(module[0] == false){
                module[0] = true;
                usuario.setBackground(Color.GREEN);
                usuario.setText("   Usuario    ------>    " + cola.getFirst().getNombre());
                modulo.setBackground(Color.GREEN);
                modulo.setText("      Asignado al modulo 1");
                turnoActual.setText("  Turno Actual      -->       " + cola.poll().getTurno());
                addAllElements();
                lblModuloEstado[0].setText("Modulo 1 ocupado");
                lblModuloEstado[0].setBackground(Color.RED);
                new Thread(() -> {
                    for (int i = 0; i <= 10; i++){
                        try{
                            Thread.sleep(new Random().nextLong(2000)+1000);
                        }catch (InterruptedException e){
                            throw  new RuntimeException(e);
                        }
                        if(i == 10){
                            module[0] = false;
                            lblModuloEstado[0].setText("Modulo 1 libre");
                            lblModuloEstado[0].setBackground(Color.green);
                            assingModule();
                        }
                    }
                }).start();
            } else if (module[1] == false) {
                module[1] = true;
                usuario.setBackground(Color.GREEN);
                usuario.setText("   Usuario    ------>    " + cola.getFirst().getNombre());
                modulo.setBackground(Color.GREEN);
                modulo.setText("      Asignado al modulo 2");
                turnoActual.setText("  Turno Actual      -->       " + cola.poll().getTurno());
                addAllElements();
                lblModuloEstado[1].setText("Modulo 2 ocupado");
                lblModuloEstado[1].setBackground(Color.RED);
                new Thread(() -> {
                    for (int i = 0; i <= 30; i++){
                        try{
                            Thread.sleep(new Random().nextLong(2000)+1000);
                        }catch (InterruptedException e){
                            throw  new RuntimeException(e);
                        }
                        if(i == 30){
                            module[1] = false;
                            lblModuloEstado[1].setText("Modulo 2 libre");
                            lblModuloEstado[1].setBackground(Color.green);
                            assingModule();
                        }
                    }
                }).start();
            } else if (module[2] == false) {
                module[2] = true;
                usuario.setBackground(Color.GREEN);
                usuario.setText("   Usuario    ------>    " + cola.getFirst().getNombre());
                modulo.setBackground(Color.GREEN);
                modulo.setText("      Asignado al modulo 3");
                turnoActual.setText("Turno Actual      -->       " + cola.poll().getTurno());
                addAllElements();
                lblModuloEstado[2].setText("Modulo 3 ocupado");
                lblModuloEstado[2].setBackground(Color.RED);
                new Thread(() -> {
                    for (int i = 0; i <= 20; i++){
                        try{
                            Thread.sleep(new Random().nextLong(2000)+1000);
                        }catch (InterruptedException e){
                            throw  new RuntimeException(e);
                        }
                        if(i == 20){
                            module[2] = false;
                            lblModuloEstado[2].setText("Modulo 3 libre");
                            lblModuloEstado[2].setBackground(Color.green);
                            assingModule();
                        }
                    }
                }).start();
            }
        }
    }
}