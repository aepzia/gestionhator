package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class datuBasea {
	
	 
	    /**
	     * @param args
	     */
	    public static void main(String[] args)
	    {
	        try
	        {
	            Class.forName("org.h2.Driver");
	            Connection con = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
	            Statement stmt = con.createStatement();
	            //stmt.executeUpdate( "DROP TABLE table1" );
	            stmt.executeUpdate( "CREATE TABLE socio ( nSocio varchar(50) NOT NULL,"
	            		+ " DNI varchar(50) NOT NULL PRIMARY KEY,"
	            		+ " pensionista boolean,"
	            		+ " nombre varchar(50) NOT NULL,"
	            		+ " apellido varchar(50) NOT NULL,"
	            		+ " fechaNacimiento varchar(50),"
	            		+ " tpSocio varchar(50),"
	            		+ " sexo varchar(50),"
	            		+ " direccion varchar(50),"
	            		+ " tel1 varchar(50),"
	            		+ " tel2 varchar(50),"
	            		+ " telContacto varchar(50),"
	            		+ " email varchar(50),"
	            		+ " fechaAlta varchar(50),"
	            		+ " fechaBaja varchar(50),"
	            		+ " motivoBaja varchar(200),"
	            		+ " foto varchar(200),"
	            		+ " cuenta_corriente varchar(50),"
	            		+ " otras_observaciones varchar(200),"
	            		+ " proteccion_de_datos varchar(200))" );
	            stmt.executeUpdate( "CREATE TABLE actividad ( id varchar(50)NOT NULL PRIMARY KEY,"
	            		+ " tpActividad varchar(50),"
	            		+ " monitorDNI varchar(50),"
	            		+ " fechaIni varchar(50),"
	            		+ " fechaFin varchar(50),"
	            		+ " horaIni varchar(50),"
	            		+ " horaFin varchar(50),"
	            		+ " nombre varchar(50) NOT NULL,"
	            		+ " numero_de_plazas varchar(50),"
	            		+ " precio varchar(50))");
	            stmt.executeUpdate( "CREATE TABLE asociacion ( "
	            		+ " izena varchar(50),"
	            		+ " tel1 varchar(50),"
	            		+ " tel2 varchar(50),"
	            		+ " presidente varchar(50),"
	            		+ " logo varchar(50),"
	            		+ " direccion varchar(50),"
	            		+ " cuenta_corriente varchar(50),"
	            		+ " numeracion_de_socios varchar(50),"
	            		+ " CIF varchar(50))");
	            stmt.executeUpdate( "CREATE TABLE socioActividad ( "
	            		+ " actividadId varchar(50),"
	            		+ " socioApuntado varchar(50),"
	            		+ " pagado boolean)");
	            		
	           /* ResultSet rs = stmt.executeQuery("SELECT * FROM table1");
	            while( rs.next() )
	            {
	                String name = rs.getString("user");
	                System.out.println( name );
	            }
	            stmt.close();
	            con.close();*/
	        }
	        catch( Exception e )
	        {
	            System.out.println( e.getMessage() );
	        }
	    }
	
}
