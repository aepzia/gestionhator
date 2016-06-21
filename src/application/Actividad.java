package application;

import java.util.Date;
import java.util.List;

public class Actividad {
	private int id;
	private boolean closed;
	private String tpActivididad;
	private String monitorDNI;
	private List<String> apuntadosDNI;
	private List<Boolean> pagos;
	private Date fechaIni;
	private Date fechaFin;
	private String Nombre;
	private int Precio;
	
	
	@Override
	public String toString(){
		return getNombre()+" "+ getTpActivididad()+" "+ getFechaIni();
	}
	public String getInformation(){
		return "Izena / Nombre: "+getNombre() +
				"\nHasiera eguna / Fecha de inicio: "+getFechaIni() +
				"\nBukatze eguna / Fecha de Finalizacion: "+getFechaFin();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getPrecio() {
		return Precio;
	}
	public void setPrecio(int precio) {
		Precio = precio;
	}
}
