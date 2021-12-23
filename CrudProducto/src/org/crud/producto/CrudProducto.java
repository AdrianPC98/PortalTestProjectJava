/**
 * 
 */
package org.crud.producto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ing. Adrián Pastrana
 *
 */
public class CrudProducto {
	static Connection connection;
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static void connectDataBaseOracle () throws IOException, SQLException{
		try {
			Class.forName(driver).newInstance();
			System.out.println("Cargo driver : ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("Exception dirver " + e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(URL, "System","Adrian25");
			System.out.println("Conexión exitosa: Oracle11g");
		} catch (Exception e) {
			System.out.println("Exception conexión: " + e.getMessage());
		}
	}
	public static void agrgarProducto(int id, String nombre, double precio ) {
		try {
			connectDataBaseOracle();
			//parametros en java: ?, :a, :p , etc
			String sql = "INSERT INTO PRODUCTO (ID, NOMBRE, PRECIO) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setDouble(3, precio);
			ps.executeQuery();
			System.out.println("Agrego: " + id + ", " + nombre);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception agregar: " + e.getMessage());
		}
	}
	public static void actualizarProducto(String nombre, double precio ,int id ) {
		try {
			connectDataBaseOracle();
			//parametros en java: ?, :a, :p , etc
			String sql = "UPDATE PRODUCTO SET NOMBRE=?, PRECIO=? WHERE ID=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setDouble(2, precio);
			ps.setInt(3, id);
			ps.executeQuery();
			System.out.println("Actualizo: " + id +", " + nombre +", " + precio);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception actualizar: " + e.getMessage());
		}
	}
	public static void eliminarProducto(int id) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "DELETE FROM PRODUCTO WHERE ID =?";
			PreparedStatement ps =  connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			System.out.println("Eliminado: " + id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception eliminar: " + e.getMessage());
		}
	}
	public static void consultaIndividualProducto(int id) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			
			String sql = "SELECT * FROM PRODUCTO WHERE ID =?";
			PreparedStatement ps =  connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
				if (resultSet.next()) {
					System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("nombre") + ", "+ resultSet.getDouble("precio"));
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception consulta individual: " + e.getMessage());
		}
	}
	public static void consultaGeneralProducto() throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "SELECT * FROM PRODUCTO ORDER BY ID";
			PreparedStatement ps =  connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("nombre") + "," + resultSet.getDouble("precio"));
				}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception consulta general: " + e.getMessage());
		}
	}
	public static void main(String[] args) throws IOException, SQLException {
		//connectDataBaseOracle();
		//agrgarProducto(1, "Takis", 12.0);
		//actualizarProducto("Takis Morados", 15.0, 1);
		//eliminarProducto(59);
		//consultaIndividualProducto(1);
		consultaGeneralProducto();
	}
	
}
