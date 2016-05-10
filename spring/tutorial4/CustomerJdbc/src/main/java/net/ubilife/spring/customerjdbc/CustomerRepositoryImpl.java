package net.ubilife.spring.customerjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private JdbcOperations jdbc;
	
	private static final String SQL_INSERT = "insert into customer (firstname, lastname) values (?, ?)";
	private static final String SQL_UPDATE = "update customer set firstname=?, lastname=? where id=?";
	private static final String SQL_FIND_ONE = "select * from customer where id = ?";
	private static final String SQL_FIND_ALL = "select * from customer order by lastname";
	private static final String SQL_DELETE_ONE = "delete from customer where id = ?";

	
	@Override
	public Customer findOne(long id) {
		return jdbc.queryForObject(SQL_FIND_ONE, new CustomerRowMapper(), id);
	}

	@Override
	public Customer save(final Customer cust) {
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		int rows = jdbc.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id"});
				
				ps.setString(1,  cust.getFirstName());
				ps.setString(2, cust.getLastName());
				
				return ps;
			}
		}, holder);
		
		if(rows == 1) {	// success, so apply ID to the customer object
			cust.setId((Long)holder.getKey());
			return cust;
		}
		
		return null;
		
	}

	@Override
	public List<Customer> findAll() {
		return jdbc.query(SQL_FIND_ALL, new CustomerRowMapper());
	}

	@Override
	public int update(Customer cust) {
		return jdbc.update(SQL_UPDATE, cust.getFirstName(), cust.getLastName(), cust.getId());
	}

	@Override
	public int delete(Customer cust) {
		return jdbc.update(SQL_DELETE_ONE, cust.getId());
	}

	private class CustomerRowMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int row) throws SQLException {
			
			return new Customer(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname")); 
			
		}
		
	}
}
