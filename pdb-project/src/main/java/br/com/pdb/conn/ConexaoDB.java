package br.com.pdb.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
	
	Connection con = null;
	private static final String DRIVER = "org.postgresql.Driver";


	public Connection open() throws SQLException {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdb_project", "pdb", "pdb");
            if (con != null) {
                return con;
            } else {
                System.out.println("Não foi possível conectar com o banco");
                return null;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
            return null;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e);
            e.printStackTrace();
            return null;
        }
	}

	public void close() throws SQLException {
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			throw e;
		}
	}

}
