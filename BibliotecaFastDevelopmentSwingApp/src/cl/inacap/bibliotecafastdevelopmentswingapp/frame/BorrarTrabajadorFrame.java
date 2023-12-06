package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Trabajador;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarTrabajadorFrame extends JInternalFrame {
	private JComboBox<String> comboBoxBorrarTrabajador;
	private List<Trabajador> trabajadores = new ArrayList<Trabajador>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarTrabajadorFrame frame = new BorrarTrabajadorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BorrarTrabajadorFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				trabajadores = new TrabajadorDAO().getAll();
				
				if (trabajadores.isEmpty() == false) {
					for (Trabajador tr : trabajadores) {
						comboBoxBorrarTrabajador.addItem(tr.toString());
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay trabajadores registrados en el sistema");
					dispose();
				}
				
				
			}
		});
		setTitle("Borrar Trabajador");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el trabajador que desea borrar");
		lblNewLabel.setBounds(225, 33, 257, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxBorrarTrabajador = new JComboBox<String>();
		comboBoxBorrarTrabajador.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxBorrarTrabajador);
		
		JButton btnBorrarTrabajador = new JButton("Borrar");
		btnBorrarTrabajador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarTrabajador.setIcon(new ImageIcon(BorrarTrabajadorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarTrabajador.addActionListener(e -> borrarTrabjador(e));
		btnBorrarTrabajador.setBounds(289, 89, 115, 23);
		getContentPane().add(btnBorrarTrabajador);

	}

	private void borrarTrabjador(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		Trabajador trabajadorSeleccionado = trabajadores.get(this.comboBoxBorrarTrabajador.getSelectedIndex());
		new TrabajadorDAO().delete(trabajadorSeleccionado);
		
		dispose();
	}
	
}
