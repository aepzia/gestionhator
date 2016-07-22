package application;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import javafx.scene.image.Image;

public class Socio implements Comparator {
	private static final Object[] String = null;
	private String nSocio;
	private String DNI;
	private boolean pensionista;
	private String izena;
	private String abizena;
	private String fechaNacimiento;
	private String tpSocio;
	private String cuentaCorriente;
	private String sexo;
	private String foto;
	private String helbidea;
	private String tel1;
	private String tel2;
	private String telEmergencia;
	private String email;
	private String fechaAlta;
	private String fechaBaja;
	private String motivoBaja;
	private String comentarios;
	private String LOPD;
	private String cp;
	private String localidad;
	private String provincia;

	public List<String> actividades;
	public List<Boolean> pagos;
	public boolean baja;
	public boolean cuotaActualPago;
	
	Socio(ResultSet rs) throws SQLException{
		setnSocio(rs.getObject("nSocio").toString());
		setDNI(rs.getObject("DNI").toString());
		setPensionista(rs.getBoolean("pensionista"));
		setIzena(rs.getObject("nombre").toString());
		setAbizena(rs.getObject("apellido").toString());
		setFechaNacimiento(rs.getObject("fechaNacimiento").toString());
		setTpSocio(rs.getObject("tpSocio").toString());
		setSexo(rs.getObject("sexo").toString());
		setHelbidea(rs.getObject("direccion").toString());
		setTel1(rs.getObject("tel1").toString());
		setTel2(rs.getObject("tel2").toString());
		setTelEmergencia(rs.getObject("telContacto").toString());
		setEmail(rs.getObject("email").toString());
		setFechaAlta(rs.getObject("fechaAlta").toString());
		setFoto(rs.getObject("foto").toString());		
		setCuentaCorriente(rs.getObject("cuenta_corriente").toString());
		setComentarios(rs.getObject("otras_observaciones").toString());
		setLOPD(rs.getObject("proteccion_de_datos").toString());
		setFechaBaja(rs.getObject("fechaBaja").toString());
		setMotivoBaja(rs.getObject("motivoBaja").toString());
		setCp(rs.getObject("cp").toString());
		setLocalidad(rs.getObject("localidad").toString());
		setProvincia(rs.getObject("provincia").toString());
	}
	@Override
	public String toString(){
		String[] fecha = getFechaNacimiento().split("-");
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		//System.out.println(fecha[2]);
		int añoNacimiento = Integer.parseInt(fecha[0]);
		int edad = currentYear -añoNacimiento;
		return getnSocio() + " " + getIzena()+" "+ getAbizena()+" "+getFechaAlta()+" "+edad+" años/urte";
	}
	public String lortuInformazioa(){
		return getIzena()+" "+getAbizena()+"\n"+"NAN/DNI: " +getDNI()+"\n"+"Znb/Nº " +
	getnSocio()+"\n"+"Tel.: "+getTel1()+" " + getTel2()+
	"\nAltako data / Fecha de Alta: "+getFechaAlta()+
	"\nJaiotze data / Fecha de Nacimiento: "+getFechaNacimiento();
	}
	
	
	
	public String getnSocio() {
		return nSocio;
	}
	public void setnSocio(String nSocio) {
		this.nSocio = nSocio;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public boolean isPensionista() {
		return pensionista;
	}
	public void setPensionista(boolean b) {
		this.pensionista = b;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getAbizena() {
		return abizena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String string) {
		this.fechaNacimiento = string;
	}
	public String getTpSocio() {
		return tpSocio;
	}
	public void setTpSocio(String tpSocio) {
		this.tpSocio = tpSocio;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getHelbidea() {
		return helbidea;
	}
	public void setHelbidea(String helbidea) {
		this.helbidea = helbidea;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTelEmergencia() {
		return telEmergencia;
	}
	public void setTelEmergencia(String telEmergencia) {
		this.telEmergencia = telEmergencia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String string) {
		this.fechaAlta = string;
	}
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getMotivoBaja() {
		return motivoBaja;
	}
	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}
	public List<String> getActividades() {
		return actividades;
	}
	public void setActividades(List<String> actividades) {
		this.actividades = actividades;
	}
	public List<Boolean> getPagos() {
		return pagos;
	}
	public void setPagos(List<Boolean> pagos) {
		this.pagos = pagos;
	}
	public boolean isBaja() {
		return baja;
	}
	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	public String getCuentaCorriente() {
		return cuentaCorriente;
	}
	public void setCuentaCorriente(String cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getLOPD() {
		return LOPD;
	}
	public void setLOPD(String lOPD) {
		LOPD = lOPD;
	}
	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
		
}
