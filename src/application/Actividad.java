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
	private boolean lunes;
	private boolean martes;
	private boolean miercoles;
	private boolean jueves;
	private boolean viernes;
	private boolean sabado;
	private boolean domingo;
	private String lugar;
	private String incluye;
	private String nAutobus;
	private String PAutobus;
	String pagatua;

	
	
	public Actividad(ResultSet rs) {
		// TODO Auto-generated constructor stub
		try {
			id = rs.getObject("Id").toString();
			tpActivididad = rs.getObject("tpActividad").toString();
			fechaIni = rs.getObject("fechaIni").toString();
			monitorDNI = rs.getObject("monitorDNI").toString();
			fechaFin = rs.getObject("fechaFin").toString();
			setHoraIni(rs.getObject("horaIni").toString());
			setHoraFin(rs.getObject("horaFin").toString());
			Nombre = rs.getObject("nombre").toString();
			setHoraIni(rs.getObject("horaIni").toString());
			numeroPlazas = rs.getObject("numero_de_plazas").toString();
			numeroDisponibles = (rs.getObject("numero_disponibles").toString());
			
			if(tpActivididad == "Preventiva / Prebentziozkoa"){
				monitorDNI = rs.getObject("monitorDNI").toString();
				fechaFin = rs.getObject("fechaFin").toString();
				setHoraFin(rs.getObject("horaFin").toString());
				setLunes(rs.getBoolean("lunes"));
				setMartes(rs.getBoolean("martes"));
				setMiercoles(rs.getBoolean("miercoles"));
				setJueves(rs.getBoolean("jueves"));
				setViernes(rs.getBoolean("viernes"));
				setSabado(rs.getBoolean("sabado"));
				setDomingo(rs.getBoolean("domingo"));
			}else if(tpActivididad == "Soocio-recreativa / gizarte-jolasa"){
				setFechaFin(rs.getObject("fechaFin").toString());
				setHoraFin(rs.getObject("horaFin").toString());
				setLugar(rs.getObject("lugar").toString());
				setIncluye(rs.getObject("incluye").toString());
			}else if(tpActivididad == "Cultural / Kulturala"){
				setFechaFin(rs.getObject("fechaFin").toString());
				setHoraFin(rs.getObject("horaFin").toString());
				setLugar(rs.getObject("lugar").toString());
				setnAutobus(rs.getObject("NAutobus").toString());
				setPAutobus(rs.getObject("PAutobus").toString());
			}
			

			Precio = rs.getObject("precio").toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	@Override
	public String toString(){
		return getNombre()+" "+ getTpActivididad()+" "+ getFechaIni() +" plazas:"+getNumeroDisponibles() ;
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
	public boolean isLunes() {
		return lunes;
	}
	public void setLunes(boolean lunes) {
		this.lunes = lunes;
	}
	public boolean isMartes() {
		return martes;
	}
	public void setMartes(boolean martes) {
		this.martes = martes;
	}
	public boolean isMiercoles() {
		return miercoles;
	}
	public void setMiercoles(boolean miercoles) {
		this.miercoles = miercoles;
	}
	public boolean isJueves() {
		return jueves;
	}
	public void setJueves(boolean jueves) {
		this.jueves = jueves;
	}
	public boolean isViernes() {
		return viernes;
	}
	public void setViernes(boolean viernes) {
		this.viernes = viernes;
	}
	public boolean isSabado() {
		return sabado;
	}
	public void setSabado(boolean sabado) {
		this.sabado = sabado;
	}
	public boolean isDomingo() {
		return domingo;
	}
	public void setDomingo(boolean domingo) {
		this.domingo = domingo;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getIncluye() {
		return incluye;
	}
	public void setIncluye(String incluye) {
		this.incluye = incluye;
	}
	public String getnAutobus() {
		return nAutobus;
	}
	public void setnAutobus(String nAutobus) {
		this.nAutobus = nAutobus;
	}
	public String getPAutobus() {
		return PAutobus;
	}
	public void setPAutobus(String pAutobus) {
		PAutobus = pAutobus;
	}
}
