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

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoBoletaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoFacturaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoBoleta;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoFactura;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrarMetodoPagoBoletaFrame extends JInternalFrame {
	private JButton btnBorrarMetodoPagoBoleta;
	private JComboBox<String> comboBoxMetodoPagoBoleta;
	private List<MetodoPagoBoleta> metodoPagoBoletas = new ArrayList<MetodoPagoBoleta>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarMetodoPagoBoletaFrame frame = new BorrarMetodoPagoBoletaFrame();
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
	public BorrarMetodoPagoBoletaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				metodoPagoBoletas = new MetodoPagoBoletaDAO().getAll();
				
				if (metodoPagoBoletas.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "No hay métodos de pago registrados en el sistema");
					dispose();
				}
				else {
					for (MetodoPagoBoleta mpb : metodoPagoBoletas) {
						comboBoxMetodoPagoBoleta.addItem(mpb.toString());
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
		
		comboBoxMetodoPagoBoleta = new JComboBox<String>();
		comboBoxMetodoPagoBoleta.setBounds(10, 58, 664, 20);
		getContentPane().add(comboBoxMetodoPagoBoleta);
		
		btnBorrarMetodoPagoBoleta = new JButton("Borrar");
		btnBorrarMetodoPagoBoleta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnBorrarMetodoPagoBoleta.setIcon(new ImageIcon(BorrarMetodoPagoBoletaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/del.png")));
		btnBorrarMetodoPagoBoleta.addActionListener(e -> borrarMetodoDePagoBoleta(e));
		btnBorrarMetodoPagoBoleta.setBounds(289, 89, 115, 23);
		getContentPane().add(btnBorrarMetodoPagoBoleta);

	}

	private void borrarMetodoDePagoBoleta(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		MetodoPagoBoleta metodoPagoBoletaSeleccionado = metodoPagoBoletas.get(this.comboBoxMetodoPagoBoleta.getSelectedIndex());
		MetodoPagoBoletaDAO daoMetodoPagoBoleta = new MetodoPagoBoletaDAO();
		if (daoMetodoPagoBoleta.delete(metodoPagoBoletaSeleccionado) == true) {
			JOptionPane.showMessageDialog(null, "Método de pago borrado exitosamente");
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "El método de pago no se pudo borrar, ya que esta siendo usado en una boleta","Error DB", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
