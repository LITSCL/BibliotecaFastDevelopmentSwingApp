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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoFacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoFactura;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarMetodoPagoFacturaFrame extends JInternalFrame {
	private JButton btnBorrarMetodoPagoFactura;
	private JComboBox<String> comboBoxMetodoPagoFactura;
	private List<MetodoPagoFactura> metodoPagoFacturas = new ArrayList<MetodoPagoFactura>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarMetodoPagoFacturaFrame frame = new BorrarMetodoPagoFacturaFrame();
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
	public BorrarMetodoPagoFacturaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				metodoPagoFacturas = new MetodoPagoFacturaDAO().getAll();
				
				if (metodoPagoFacturas.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "No hay m�todos de pago registrados en el sistema");
					dispose();
				}
				else {
					for (MetodoPagoFactura mpf : metodoPagoFacturas) {
						comboBoxMetodoPagoFactura.addItem(mpf.toString());
					}
				}	
			}
		});
		setTitle("Borrar M\u00E9todo de Pago");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el m\u00E9todo de pago que desea borrar");
		lblNewLabel.setBounds(203, 33, 272, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxMetodoPagoFactura = new JComboBox<String>();
		comboBoxMetodoPagoFactura.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxMetodoPagoFactura);
		
		btnBorrarMetodoPagoFactura = new JButton("Borrar");
		btnBorrarMetodoPagoFactura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarMetodoPagoFactura.setIcon(new ImageIcon(BorrarMetodoPagoFacturaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarMetodoPagoFactura.addActionListener(e -> borrarMetodoDePagoFactura(e));
		btnBorrarMetodoPagoFactura.setBounds(289, 89, 115, 23);
		getContentPane().add(btnBorrarMetodoPagoFactura);

	}

	private void borrarMetodoDePagoFactura(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		MetodoPagoFactura metodoPagoFacturaSeleccionado = metodoPagoFacturas.get(this.comboBoxMetodoPagoFactura.getSelectedIndex());
		MetodoPagoFacturaDAO daoMetodoPagoFactura = new MetodoPagoFacturaDAO();
		if (daoMetodoPagoFactura.delete(metodoPagoFacturaSeleccionado) == true) {
			JOptionPane.showMessageDialog(null, "M�todo de pago borrado exitosamente");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "El m�todo de pago no se pudo borrar, ya que esta siendo usado en una factura", "Error DB", JOptionPane.ERROR_MESSAGE);
		}
	}

}
