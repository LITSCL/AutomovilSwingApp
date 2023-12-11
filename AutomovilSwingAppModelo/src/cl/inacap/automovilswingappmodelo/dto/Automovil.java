package cl.inacap.automovilswingappmodelo.dto;

public class Automovil {
	private int id;
	private String patente;
	private int kilometraje;
	private String nombreDeContacto;
	private String tipoDeAtencion;
	private String tipoDeMotor;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public int getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getNombreDeContacto() {
		return nombreDeContacto;
	}

	public void setNombreDeContacto(String nombreDeContacto) {
		this.nombreDeContacto = nombreDeContacto;
	}

	public String getTipoDeAtencion() {
		return tipoDeAtencion;
	}

	public void setTipoDeAtencion(String tipoDeAtencion) {
		this.tipoDeAtencion = tipoDeAtencion;
	}

	public String getTipoDeMotor() {
		return tipoDeMotor;
	}

	public void setTipoDeMotor(String tipoDeMotor) {
		this.tipoDeMotor = tipoDeMotor;
	}
	
	public String toString() {
		return "[Patente]= " + this.patente + " [Contacto]= " + this.getNombreDeContacto() + " [Kilometraje]= " + this.getKilometraje() + " [Tipo de motor]= " + this.getTipoDeMotor();
	}
}
