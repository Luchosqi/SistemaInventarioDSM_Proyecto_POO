package Javaswing;

import javax.swing.*;
import java.awt.event.*;

public class Swing extends JFrame {
    public Swing () {
        super("CompraFrontera");
         setSize(400,300);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocationRelativeTo(null);

         JPanel panel = new JPanel();
         JLabel mensaje = new JLabel("seleccione");

         JRadioButton cliente = new JRadioButton("Cliente");
         JRadioButton ususario = new JRadioButton("Usuario");
         JRadioButton admin = new JRadioButton("Admin");

         ButtonGroup group = new ButtonGroup();

         group.add(cliente);
         group.add(ususario);
         group.add(admin);
         JButton boton = new JButton("continuar");
         boton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Swing swing = new Swing();
                 swing.setVisible(true);
                 dispose();
             }
         });
         panel.add(mensaje);
         panel.add(cliente);
         panel.add(ususario);
         panel.add(admin);
         panel.add(boton);
         add(panel);
    }
}
