package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.DistribuidorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Distribuidor;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarDistribuidorFrame extends JInternalFrame {
	private JComboBox<String>comboBoxBorrarDistribuidor;
	private JButton btnBorrar;
	private List<Distribuidor> distribuidores = new ArrayList<Distribuidor>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarDistribuidorFrame frame = new BorrarDistribuidorFrame();
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
	public BorrarDistribuidorFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				DistribuidorDAO daoDistribuidor = new DistribuidorDAO();
				distribuidores = daoDistribuidor.getAll();
				
				if (distribuidores.size() < 1) {
					JOptionPane.showMessageDialog(null, "No hay distribuidores registrados en el sistema");
					dispose();
				}
				else {
					for (Distribuidor di : distribuidores) {
						comboBoxBorrarDistribuidor.addItem(di.toString());
					}
				}		
			}
		});
		setTitle("Borrar Distribuidor");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el distribuidor que desea borrar");
		lblNewLabel.setBounds(226, 33, 270, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxBorrarDistribuidor = new JComboBox<String>();
		comboBoxBorrarDistribuidor.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxBorrarDistribuidor);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrar.setIcon(new ImageIcon(BorrarDistribuidorFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrar.addActionListener(e -> borrarDistribuidor(e));
		btnBorrar.setBounds(289, 89, 115, 23);
		getContentPane().add(btnBorrar);

	}

	private void borrarDistribuidor(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		int indiceSeleccionado = comboBoxBorrarDistribuidor.getSelectedIndex();
		
		DistribuidorDAO daoDistribuidor = new DistribuidorDAO();
		distribuidores = daoDistribuidor.getAll();
		Distribuidor distribuidorSeleccionado = distribuidores.get(indiceSeleccionado);
		
		daoDistribuidor.delete(distribuidorSeleccionado);
		
		dispose();	
	}

}
