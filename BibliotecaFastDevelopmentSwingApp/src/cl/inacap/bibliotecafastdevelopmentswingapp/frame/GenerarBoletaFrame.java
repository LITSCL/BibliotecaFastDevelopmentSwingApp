package cl.inacap.bibliotecafastdevelopmentswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.BoletaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.ClienteDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.MetodoPagoBoletaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.TrabajadorDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dao.VentaDAO;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Boleta;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Cliente;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.MetodoPagoBoleta;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Trabajador;
import cl.inacap.bibliotecafastdevelopmentswingappmodelo.dto.Venta;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.FechaGringaUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.StartAudioUtil;
import cl.inacap.bibliotecafastdevelopmentswingapp.util.TiempoUtil;

import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GenerarBoletaFrame extends JInternalFrame {
	private JTextField textFieldFolio;
	private JTextField textFieldHoraDeVenta;
	private JComboBox<String> comboBoxTrabajador;
	private JComboBox<String> comboBoxMetodoDePago;
	private JComboBox<String> comboBoxCliente;
	List<Trabajador> trabajadores = new ArrayList<Trabajador>();
	List<MetodoPagoBoleta> metodoPagoBoletas = new ArrayList<MetodoPagoBoleta>();
	List<Venta> ventas = new ArrayList<Venta>();
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<Boleta> boletas = new ArrayList<Boleta>();
	private JLabel lblPrecioNeto;
	private JLabel lblPrecioConIVA;
	private JComboBox<Venta> comboBoxVenta;
	private JDateChooser dateChooserFecha;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarBoletaFrame frame = new GenerarBoletaFrame();
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
	public GenerarBoletaFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				trabajadores = new TrabajadorDAO().getAll();
				metodoPagoBoletas = new MetodoPagoBoletaDAO().getAll();
				clientes = new ClienteDAO().getAll();
				ventas = new VentaDAO().getAll();
				boletas = new BoletaDAO().getAll();
				
				if (trabajadores.isEmpty() || metodoPagoBoletas.isEmpty() || clientes.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe haber un trabajador, un m�todo de pago y un cliente como m�nimo registrados en el sistema para poder generar una boleta");
					dispose();
				}
				else {
					for (Trabajador tr : trabajadores) {
						comboBoxTrabajador.addItem(tr.getRut());
					}
					for (MetodoPagoBoleta mpb : metodoPagoBoletas) {
						comboBoxMetodoDePago.addItem(mpb.getCodigo());
					}
					for (Cliente cl : clientes) {
						comboBoxCliente.addItem(cl.getRut());
					}
					
				}
				
				int trueFalse = 0;
				int contadorIteraciones = 0;
				
				for (Venta ve : ventas) {
					if (boletas.isEmpty() == true) {
						comboBoxVenta.addItem(ve);
					}
					else {
						trueFalse = 0;
						contadorIteraciones = 0;
						for (Boleta bo : boletas) {
							contadorIteraciones++;
							if (ve.getId() == bo.getVentaFK()) {
								trueFalse = 1;
								
							}
							if (trueFalse == 0 && contadorIteraciones == boletas.size()) {
								comboBoxVenta.addItem(ve);
			
							}
						}	
					}		
				}		
			}
		});
		
		setTitle("Generar Boleta");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Folio");
		lblNewLabel.setBounds(10, 33, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio neto");
		lblNewLabel_1.setBounds(10, 58, 126, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio con IVA");
		lblNewLabel_2.setBounds(10, 83, 126, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Costo del IVA");
		lblNewLabel_3.setBounds(10, 108, 99, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha de venta");
		lblNewLabel_4.setBounds(10, 133, 126, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Hora de venta");
		lblNewLabel_5.setBounds(10, 158, 111, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Trabajador");
		lblNewLabel_6.setBounds(10, 183, 99, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("M\u00E9todo de pago");
		lblNewLabel_7.setBounds(10, 208, 111, 14);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Cliente");
		lblNewLabel_8.setBounds(10, 258, 126, 14);
		getContentPane().add(lblNewLabel_8);
		
		JButton btnGenerarBoleta = new JButton("Generar");
		btnGenerarBoleta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				StartAudioUtil sau = new StartAudioUtil();
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnGenerarBoleta.setIcon(new ImageIcon(GenerarBoletaFrame.class.getResource("/cl/inacap/bibliotecafastdevelopmentswingapp/image/add.png")));
		btnGenerarBoleta.addActionListener(e -> generarBoleta(e));
		btnGenerarBoleta.setBounds(559, 381, 115, 23);
		getContentPane().add(btnGenerarBoleta);
		
		textFieldFolio = new JTextField();
		textFieldFolio.setBounds(101, 30, 86, 20);
		getContentPane().add(textFieldFolio);
		textFieldFolio.setColumns(10);
		
		textFieldHoraDeVenta = new JTextField();
		textFieldHoraDeVenta.setBounds(101, 155, 86, 20);
		getContentPane().add(textFieldHoraDeVenta);
		textFieldHoraDeVenta.setColumns(10);
		
		comboBoxTrabajador = new JComboBox<String>();
		comboBoxTrabajador.setBounds(101, 180, 208, 20);
		getContentPane().add(comboBoxTrabajador);
		
		comboBoxMetodoDePago = new JComboBox<String>();
		comboBoxMetodoDePago.setBounds(101, 205, 86, 20);
		getContentPane().add(comboBoxMetodoDePago);
		
		comboBoxCliente = new JComboBox<String>();
		comboBoxCliente.setBounds(101, 255, 208, 20);
		getContentPane().add(comboBoxCliente);
		
		JLabel lblNewLabel_10 = new JLabel("(23:44:48)");
		lblNewLabel_10.setBounds(197, 158, 99, 14);
		getContentPane().add(lblNewLabel_10);
		
		lblPrecioNeto = new JLabel("");
		lblPrecioNeto.setBounds(101, 58, 208, 14);
		getContentPane().add(lblPrecioNeto);
		
		lblPrecioConIVA = new JLabel("");
		lblPrecioConIVA.setBounds(101, 83, 208, 14);
		getContentPane().add(lblPrecioConIVA);
		
		JLabel label_1 = new JLabel("19%");
		label_1.setBounds(101, 108, 35, 14);
		getContentPane().add(label_1);
		
		JLabel lblNewLabel_11 = new JLabel("Venta");
		lblNewLabel_11.setBounds(10, 233, 46, 14);
		getContentPane().add(lblNewLabel_11);
		
		comboBoxVenta = new JComboBox<Venta>();
		comboBoxVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Venta ventaSeleccionada = (Venta)comboBoxVenta.getSelectedItem();
				Double precioNeto = ventaSeleccionada.getPrecio();
				lblPrecioNeto.setText(Double.toString(precioNeto));
				lblPrecioConIVA.setText(Double.toString(precioNeto * 1.19));
			}
		});
		comboBoxVenta.setBounds(101, 230, 86, 20);
		getContentPane().add(comboBoxVenta);
		
		dateChooserFecha = new JDateChooser();
		dateChooserFecha.setBounds(101, 127, 115, 20);
		getContentPane().add(dateChooserFecha);

	}

	private void generarBoleta(ActionEvent e) {
		StartAudioUtil sau = new StartAudioUtil();
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String folio = this.textFieldFolio.getText().trim();
		if (folio.isEmpty() ==true) {
			errores.add("Folio no valido");
		}
		
		Double precioNeto = 1.0;
		try {
			precioNeto = Double.parseDouble(this.lblPrecioNeto.getText());
		} catch (Exception ex) {
			
		}
		
		
		String fechaDeVenta= "";
		try {
			String dia = Integer.toString(dateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
			String mes = Integer.toString((dateChooserFecha.getCalendar().get(Calendar.MONTH)+1));
			String year = Integer.toString(dateChooserFecha.getCalendar().get(Calendar.YEAR));
			if (dia.length() == 1) {
				dia = "0" + dia;
			}
			if (mes.length() == 1) {
				mes = "0" + mes;
			}
			fechaDeVenta = year + "-" + mes + "-" + dia;
			
			if (new FechaGringaUtil().validarFechaGringa(fechaDeVenta) == false) {
				errores.add("Formato de fecha no valido");
			}
		} catch (Exception ex) {
			errores.add("Formato de fecha no valido");
		}
		
		
		String horaDeVenta = this.textFieldHoraDeVenta.getText().trim();
		if (new TiempoUtil().validarHora(horaDeVenta) == false) {
			errores.add("Formato de hora no valido");
		}
		
		String trabajador = (String)this.comboBoxTrabajador.getSelectedItem();
		String metodoDePago = (String)this.comboBoxMetodoDePago.getSelectedItem();
		String cliente = this.comboBoxCliente.getSelectedItem().toString();
		
		Venta ventaSeleccionada = (Venta)comboBoxVenta.getSelectedItem();
		int idVentaSeleccionada = 0;
		try {
			idVentaSeleccionada = ventaSeleccionada.getId();
		} catch (Exception ex) {
			errores.add("Ninguna venta seleccionada");
		}
		
		
		if (errores.isEmpty() == false) {
			String mensaje = "";
			for (int i=0 ; i<errores.size() ; i++) {
				mensaje+="\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validaci�n", JOptionPane.WARNING_MESSAGE);
		}
		
		if (errores.isEmpty() == true) {
			Boleta bo = new Boleta();
			bo.setFolio(folio);
			bo.setPrecioNeto(precioNeto);
			bo.setPrecioConIVA(precioNeto*1.19);
			bo.setCostoIVA(19);
			bo.setFechaDeVenta(fechaDeVenta);
			bo.setHoraDeVenta(horaDeVenta);
			bo.setTrabajadorFK(trabajador);
			bo.setMetodoDePagoBoletaFK(metodoDePago);
			bo.setClienteFK(cliente);
			bo.setVentaFK(idVentaSeleccionada);
			
			if (new BoletaDAO().save(bo)) {
				JOptionPane.showMessageDialog(null, "Boleta generada correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "El folio ya fue ingresado anteriormente, ingrese uno diferente", "Error DB", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
