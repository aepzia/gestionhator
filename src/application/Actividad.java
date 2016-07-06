package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Actividad {
	private String id;
	private boolean closed;
	private String tpActivididad;
	private String monitorDNI;
	private List<String> apuntadosDNI;
	private List<Boolean> pagos;
	private String fechaIni;
	private String fechaFin;
	private String Nombre;
	private String Precio;
	private String numeroPlazas;
	
	
	public Actividad(ResultSet rs) {
		// TODO Auto-generated constructor stub
		try {
			id = rs.getObject("Id").toString();
			tpActivididad = rs.getObject("tpActividad").toString();
			monitorDNI = rs.getObject("monitorDNI").toString();
			fechaIni = rs.getObject("fechaIni").toString();
			Nombre = rs.getObject("nombre").toString();
			numeroPlazas = rs.getObject("numero_de_plazas").toString();
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
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
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
	public List<String> getApuntadosDNI() {
		return apuntadosDNI;
	}
	public void setApuntadosDNI(List<String> apuntadosDNI) {
		this.apuntadosDNI = apuntadosDNI;
	}
	public List<Boolean> getPagos() {
		return pagos;
	}
	public void setPagos(List<Boolean> pagos) {
		this.pagos = pagos;
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
}
