package application;

public class Monitor {
	private String empresa;
	private String direccion ;
	private String telEmpresa; 
	private String profesional; 
	private String telProfesional; 
	private String DNI ;
	private String CIF ;
	
	
	@Override public String toString() {
		return profesional+" "+empresa.toUpperCase();
	}
	
	public String getInformacionEmpresa() {
		return empresa.toUpperCase()+
				"\nTel.:" +telEmpresa+
				"\nCIF" +CIF
				+"\nDirecion: "+direccion;
	}
	public String getInformacionProfesional() {
		return profesional.toUpperCase()+
				"\nTel.:" +telProfesional+
				"\nDNI" +DNI;
	}
	
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelEmpresa() {
		return telEmpresa;
	}
	public void setTelEmpresa(String telEmpresa) {
		this.telEmpresa = telEmpresa;
	}
	public String getProfesional() {
		return profesional;
	}
	public void setProfesional(String profesional) {
		this.profesional = profesional;
	}
	public String getTelProfesional() {
		return telProfesional;
	}
	public void setTelProfesional(String telProfesional) {
		this.telProfesional = telProfesional;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getCIF() {
		return CIF;
	}
	public void setCIF(String cIF) {
		CIF = cIF;
	}
	
}
