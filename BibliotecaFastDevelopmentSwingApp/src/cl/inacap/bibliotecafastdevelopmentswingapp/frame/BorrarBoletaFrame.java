package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.BoletaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Boleta;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarBoletaFrame extends JInternalFrame {
	private JComboBox<String> comboBoxBoleta;
	private JButton btnBorrarBoleta;
	private List<Boleta> boletas = new ArrayList<Boleta>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarBoletaFrame frame = new BorrarBoletaFrame();
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
	public BorrarBoletaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				boletas = new BoletaDAO().getAll();
				
				if (boletas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay boletas registradas en el sistema");
					dispose();
				}
				else {
					for (Boleta bo : boletas) {
						comboBoxBoleta.addItem(bo.toString());
					}
				}
			}
		});
		setTitle("Borrar Boleta");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar la boleta que desea borrar");
		lblNewLabel.setBounds(221, 33, 293, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxBoleta = new JComboBox<String>();
		comboBoxBoleta.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxBoleta);
		
		btnBorrarBoleta = new JButton("Borrar");
		btnBorrarBoleta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarBoleta.setIcon(new ImageIcon(BorrarBoletaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarBoleta.setBounds(289, 89, 115, 23);
		btnBorrarBoleta.addActionListener(e -> borrarBoleta(e));
		getContentPane().add(btnBorrarBoleta);

	}
	
	private void borrarBoleta(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		Boleta boletaSeleccionada = boletas.get(this.comboBoxBoleta.getSelectedIndex());
		
		new BoletaDAO().delete(boletaSeleccionada);
		
		JOptionPane.showMessageDialog(null, "Boleta borrada exitosamente");
		dispose();
	}

}
