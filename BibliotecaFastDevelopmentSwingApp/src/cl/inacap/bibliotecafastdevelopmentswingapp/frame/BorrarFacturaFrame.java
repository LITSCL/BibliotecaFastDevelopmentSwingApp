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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.FacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Factura;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarFacturaFrame extends JInternalFrame {
	private JComboBox<String> comboBoxFactura;
	private JButton btnBorrarFactura;
	private List<Factura> facturas=new ArrayList<Factura>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarFacturaFrame frame = new BorrarFacturaFrame();
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
	public BorrarFacturaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				facturas = new FacturaDAO().getAll();
				
				if (facturas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay facturas registradas en el sistema");
					dispose();
				}
				else {
					for (Factura fa : facturas) {
						comboBoxFactura.addItem(fa.toString());
					}
				}
			}
		});
		setTitle("Borrar Factura");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar la facutura que desea borrar");
		lblNewLabel.setBounds(221, 33, 293, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxFactura = new JComboBox<String>();
		comboBoxFactura.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxFactura);
		
		btnBorrarFactura = new JButton("Borrar");
		btnBorrarFactura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarFactura.setIcon(new ImageIcon(BorrarFacturaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarFactura.setBounds(289, 89, 115, 23);
		btnBorrarFactura.addActionListener(e -> borrarFactura(e));
		getContentPane().add(btnBorrarFactura);

	}
	
	private void borrarFactura(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		Factura facturaSeleccionada = facturas.get(this.comboBoxFactura.getSelectedIndex());
		
		new FacturaDAO().delete(facturaSeleccionada);
		
		JOptionPane.showMessageDialog(null, "Factura borrada exitosamente");
		dispose();
	}

}
