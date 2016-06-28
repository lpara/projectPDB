package br.com.pdb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.pdb.conn.ConexaoDB;

public class GenericDao {

    private Connection connection;
    private ConexaoDB conexao = new ConexaoDB();

    protected GenericDao() {

    }

    public Connection getConnection() throws SQLException {
			return conexao.open();
    }
}
