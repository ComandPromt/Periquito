package utils;
import java.awt.Color;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import javax.swing.Action;

import javax.swing.ActionMap;

import javax.swing.Icon;

import javax.swing.ImageIcon;

import javax.swing.InputMap;

import javax.swing.JButton;

import javax.swing.JComponent;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.KeyStroke;

public class MultiplesFuentesDeEvento {

    public static void main(String [] args) {

        Ventana ventana=new Ventana();

        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

class Ventana extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ventana() {

        setTitle("Implementacion de M,F");
        setSize(500,500);
        setLocationRelativeTo(null);

        add(new Lamina());

    }

}

class Lamina extends JPanel {


 private class AccionColor extends AbstractAction{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccionColor(String nombre,Icon icono,Color color_boton) {

        putValue(Action.NAME,nombre);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("prueba "+getValue(Action.NAME));

      }

    }

}