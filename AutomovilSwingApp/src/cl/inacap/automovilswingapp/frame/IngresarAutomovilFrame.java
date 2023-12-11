package cl.inacap.automovilswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.inacap.automovilswingappmodelo.dao.AutomovilDAO;
import cl.inacap.automovilswingappmodelo.dto.Automovil;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class IngresarAutomovilFrame extends JInternalFrame { //Esta clase hereda de JInternalFrame (Son los Frames que se muestran cuando se ejecuta el codigo de los JMenuItem).
	private JTextField textFieldPatente;
	private JTextField textFieldNombreDeContacto;
	private JComboBox <String> comboBoxTipoDeAtencion; //A los JComboBox siempre hay que asignarles el tipo de dato que van a mostrar, y al momento de crear el objeto deben tener un <>.
	private JSpinner spinnerKilometraje;
	private JList listTipoDeMotor;

	//Proceso de creación del frame.
	public IngresarAutomovilFrame() {
		setTitle("Ingresar Automovil"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		setClosable(true);
		
		//Todo este código se ejecuta antes de que se abra el JInternalFrame (Momento ideal para cargar el JComboBox).
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) { //Aca se crea el listener del JComboBox llamado (Tipo de Atencion).
				String[] tipoDeAtencion = {"Lavado Completo", "Lavado Básico"}; //Este Array se puede reemplazar por un DAO.
			
				//Proceso de agregación de items al JComboBox.
				for (int i = 0; i < tipoDeAtencion.length; i++) { //Aca se recorre el Array llamado asignaturas.
					comboBoxTipoDeAtencion.addItem(tipoDeAtencion[i]); //Aca se agregan los items al JComboBox.
				}
			}
		});
		
		//Este es el frame que se muestra cuando se escucha el JMenuItem llamado (Ingresar Automovil).
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(51, 30, 80, 14);
		getContentPane().add(lblPatente);
		
		JLabel lblKilometraje = new JLabel("Kilometraje");
		lblKilometraje.setBounds(51, 55, 80, 14);
		getContentPane().add(lblKilometraje);
		
		JLabel lblNombreDeContacto = new JLabel("Nombre de contacto");
		lblNombreDeContacto.setBounds(51, 80, 118, 14);
		getContentPane().add(lblNombreDeContacto);
		
		JLabel lblTipoDeAtencion = new JLabel("Tipo de Atenci\u00F3n");
		lblTipoDeAtencion.setBounds(51, 105, 118, 14);
		getContentPane().add(lblTipoDeAtencion);
		
		JLabel lblTipoDeMotor = new JLabel("Tipo de Motor");
		lblTipoDeMotor.setBounds(51, 130, 118, 14);
		getContentPane().add(lblTipoDeMotor);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this::ingresarAutomovil); //Aca se añade el Listener del JButton llamado (Agregar) el cual llama al método llamado ingresarAutomovil().
		btnAgregar.setBounds(295, 197, 89, 23);
		getContentPane().add(btnAgregar);
		
		textFieldPatente = new JTextField();
		textFieldPatente.setBounds(173, 27, 86, 20);
		getContentPane().add(textFieldPatente);
		textFieldPatente.setColumns(10);
		
		textFieldNombreDeContacto = new JTextField();
		textFieldNombreDeContacto.setBounds(173, 77, 86, 20);
		getContentPane().add(textFieldNombreDeContacto);
		textFieldNombreDeContacto.setColumns(10);
		
		comboBoxTipoDeAtencion = new JComboBox<>(); //Si al JComboBox se le añadio un tipo de dato, a la creación del objeto hay que añadirle un <>.
		comboBoxTipoDeAtencion.setBounds(173, 102, 211, 20);
		getContentPane().add(comboBoxTipoDeAtencion);
		
		spinnerKilometraje = new JSpinner(); //Creación del JSpinner llamado (Kilometraje).
		spinnerKilometraje.setBounds(173, 52, 86, 20);
		getContentPane().add(spinnerKilometraje);
		
		listTipoDeMotor = new JList();
		listTipoDeMotor.setBounds(168, 130, 216, 62);
		getContentPane().add(listTipoDeMotor);
		DefaultListModel listTipoDeMotorModelo = new DefaultListModel(); //Se crea un objeto de tipo DefaultListModel.
		listTipoDeMotorModelo.addElement("Petrolero");
		listTipoDeMotorModelo.addElement("Bencinero");
		listTipoDeMotorModelo.addElement("Eléctrico");
		listTipoDeMotor.setModel(listTipoDeMotorModelo); //Aca se añaden todos los textos al componente JList.
	}
	
	//Código a ejecutar del Listener del componente JButton llamado (Agregar).
	private void ingresarAutomovil(ActionEvent e) {
		String tipoDeMotor = "";
		
		List<String> errores = new ArrayList<String>(); //Aca se crea una lista, la cual almacenará todos los posible errores cometidos por el usuario.
		
		String patente = this.textFieldPatente.getText().trim(); //Aca se almacena lo que el usuario ingresa en el componente, similar a un Scanner.
		if (patente.isEmpty() == true) {
			errores.add("- Tiene que ingresar una patente");
		}
		
		int kilometraje = (int)this.spinnerKilometraje.getValue(); //En esta variable se almacena el valor que retorna el JSpinner (Es necesario hacer una refundición de dato).
		if (kilometraje <= 30) {
			errores.add("- El kilometraje es 30 o menor");
		}
		
		String nombreDeContacto = this.textFieldNombreDeContacto.getText().trim(); //Aca se almacena lo que el usuario ingresa en el componente, similar a un Scanner.
		if (nombreDeContacto.isEmpty() == true) {
			errores.add("- Tiene que ingresar un nombre de contacto");
		}
		
		if (this.listTipoDeMotor.getSelectedValue() == null) {
			errores.add("- No seleccionó el tipo de motor");
		}
		else {
			tipoDeMotor = this.listTipoDeMotor.getSelectedValue().toString(); //En esta variable se almacena el tipo de motor que esta seleccionado en el JList (Se debe llamar a toString ya que este método por defecto retorna un Object).
		}
		
		if (errores.size() > 0) { //Si la lista tiene errores (no está vacia), se ejecuta el código.
			String mensaje = ""; //Aca se crea el mensaje de errores.
			for (int i = 0; i < errores.size(); i++) {
				mensaje+="\n" + errores.get(i); //En esta instrucción se concatenan todos los errores en la variable.
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE); //El primer parametro siempre debe ser null para que el mensaje se centre, en el segundo parametro va el mensaje a mostrar,en el tercer parametro va el titulo de la ventana y en el cuarto parametro va el tipo de error.
		}	
		//Proceso de creación del objeto de tipo Automovil (Solo se ejecuta si no existen errores).
		else {
			Automovil au = new Automovil();
			au.setId(new AutomovilDAO().automoviles.size() - 1);
			au.setKilometraje(kilometraje);
			au.setNombreDeContacto(nombreDeContacto);
			au.setPatente(patente);
			au.setTipoDeAtencion((String)comboBoxTipoDeAtencion.getSelectedItem()); //Tiene que refundirse el dato a tipo String porque el atributo del JComboBox lo declaramos como String.
			au.setTipoDeMotor(tipoDeMotor);
			
			AutomovilDAO daoAutomovil = new AutomovilDAO();
			daoAutomovil.save(au); //Aca se añade el automovil a la lista.
			JOptionPane.showMessageDialog(null, "Automovil ingresado"); //Esta instrucción muestra un mensaje cuando el automovil es ingresado.
			this.dispose(); //Esta instrucción cierra la ventana (JInternal Frame llamado (Ingresar Automovil)).
		}
	}
}
