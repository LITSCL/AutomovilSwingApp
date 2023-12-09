package cl.inacap.automovilswingappmodelo.dao;

import java.util.List;
import cl.inacap.automovilswingappmodelo.dto.Automovil;

import java.util.ArrayList;

public class AutomovilDAO {
	public static List<Automovil> automoviles = new ArrayList<>();
	
	public void save(Automovil a) {
		automoviles.add(a);
	}
	
	public List<Automovil> getAll() {
		return automoviles;
	}
	
	public List<Automovil> filtrarAutomovil(String filtro) {
		List<Automovil> automovilesFiltrados = new ArrayList<>();
		
		for (Automovil a : automoviles) {
			if (filtro.equals("Petrolero") && a.getTipoDeMotor().equals("Petrolero")) {
				automovilesFiltrados.add(a);
			}
			if (filtro.equals("Bencinero") && a.getTipoDeMotor().equals("Bencinero")) {
				automovilesFiltrados.add(a);
			}
			if (filtro.equals("Eléctrico") && a.getTipoDeMotor().equals("Eléctrico")) {
				automovilesFiltrados.add(a);
			}
		}
		return automovilesFiltrados;
	}
	
	public void update(Automovil a) {
	    for (Automovil automovil : automoviles) {
	        if (automovil.getId() == a.getId()) {
	        	automovil = a;
	            break;
	        }
	    }
	}

	public void delete(Automovil a) {
		automoviles.remove(a);
	}
}
