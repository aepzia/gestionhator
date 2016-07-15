package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Actividad {
	private String id;
	private String tpActivididad;
	private String monitorDNI;
	private String fechaIni;
	private String fechaFin;
	private String horaIni;
	private String horaFin;
	private String Nombre;
	private String Precio;
	private String numeroPlazas;
	private String numeroDisponibles;

	
	
	public Actividad(ResultSet rs) {
		// TODO Auto-generated constructor stub
		try {
			id = rs.getObject("Id").toString();
			tpActivididad = rs.getObject("tpActividad").toString();
			monitorDNI = rs.getObject("monitorDNI").toString();
			fechaIni = rs.getObject("fechaIni").toString();
			fechaFin = rs.getObject("fechaFin").toString();
			setHoraIni(rs.getObject("horaIni").toString());
			setHoraFin(rs.getObject("horaFin").toString());
			Nombre = rs.getObject("nombre").toString();
			numeroPlazas = rs.getObject("numero_de_plazas").toString();
			numeroDisponibles = (rs.getObject("numero_disponibles").toString());

			Precio = rs.getObject("precio").toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	@Override
	public String toString(){
		return getNombre()+" "+ getTpActivididad()+" "+ getFechaIni();
	}
	public String getInformation(){
		return "Izena / Nombre: "+getNombre() +
				"\nHasiera eguna / Fecha de inicio: "+getFechaIni() +
				"\nBukatze eguna / Fecha de Finalizacion: "+getFechaFin();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTpActivididad() {
		return tpActivididad;
	}
	public void setTpActivididad(String tpActivididad) {
		this.tpActivididad = tpActivididad;
	}
	public String getMonitorDNI() {
		return monitorDNI;
	}
	public void setMonitorDNI(String monitorDNI) {
		this.monitorDNI = monitorDNI;
	}
	
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getPrecio() {
		return Precio;
	}
	public void setPrecio(String precio) {
		Precio = precio;
	}
	public String getNumeroPlazas() {
		return numeroPlazas;
	}
	public void setNumeroPlazas(String numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getNumeroDisponibles() {
		return numeroDisponibles;
	}
	public void setNumeroDisponibles(String numeroDisponibles) {
		this.numeroDisponibles = numeroDisponibles;
	}
}
