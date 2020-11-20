package datoslmpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import datos.MovementDao;
import dominio.Account;
import dominio.LoanState;
import dominio.Movement;
import dominio.MovementType;

public class MovementDaoImpl implements MovementDao {

	private ConnectionDB cn;

	public MovementDaoImpl() {
	}

	@Override
	public List<Movement> getAll() {
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<Movement> list = new ArrayList<Movement>();
		try {

			ResultSet rs = cn.query(
					"SELECT m.id,m.accountNumber,m.movementDate,m.detail,m.amount,m.MovementTypeId,mt.descriptions,m.status "
							+ "FROM movements as m INNER JOIN MovementType as mt ON mt.id=m.MovementTypeId WHERE m.status = 1");
			while (rs.next()) {
				Movement mov = new Movement();
				mov.setId(rs.getInt("movement.id"));
				mov.setAccountNumber(rs.getInt("movement.accountNumber"));
				mov.setMovementDate(rs.getDate("m.movementDate").toLocalDate());
				mov.setDetail(rs.getString("m.detail"));
				mov.setAmount(rs.getFloat("m.amount"));
				mov.setStatus(rs.getBoolean("m.status"));

				MovementType mt = new MovementType();
				mt.setId(rs.getInt("m.MovementTypeId"));
				mt.setDescription(rs.getString("mt.descriptions"));

				mov.setMovementType(mt);
				list.add(mov);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return list;
	}

	@Override
	public ArrayList<Movement> getAllByAccount(int accountNumber) {
		cn = new ConnectionDB();
		cn.Open();
		ArrayList<Movement> list = new ArrayList<Movement>();
		try {
			String query = "SELECT m.id,m.accountNumber,m.movementDate,m.detail,m.amount,m.MovementTypeId,mt.descriptions,m.status";
			query += " FROM movements as m INNER JOIN MovementType as mt ON mt.id=m.MovementTypeId WHERE m.status = 1";
			query += " AND accountNumber = " + accountNumber;
			query += " ORDER BY m.movementDate desc";
			ResultSet rs = cn.query(query);
			while (rs.next()) {
				Movement mov = new Movement();
				mov.setId(rs.getInt("m.id"));
				mov.setAccountNumber(rs.getInt("m.accountNumber"));
				mov.setMovementDate(rs.getDate("m.movementDate").toLocalDate());
				mov.setDetail(rs.getString("m.detail"));
				mov.setAmount(rs.getFloat("m.amount"));
				mov.setStatus(rs.getBoolean("m.status"));

				MovementType mt = new MovementType();
				mt.setId(rs.getInt("m.MovementTypeId"));
				mt.setDescription(rs.getString("mt.descriptions"));

				mov.setMovementType(mt);
				list.add(mov);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return list;
	}

	@Override
	public float obtenerSaldo(int accountNumber) {
		float saldo = 0;
		cn = new ConnectionDB();
		cn.Open();
		try {
			String query = "SELECT SUM(amount) as Saldo";
			query += " FROM movements WHERE status = 1";
			query += " AND accountNumber = " + accountNumber;
			ResultSet rs = cn.query(query);
			while (rs.next()) {
				saldo = rs.getFloat("Saldo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return saldo;
	}

	@Override
	public boolean insert(Movement movement) {
		boolean status = true;

		cn = new ConnectionDB();
		cn.Open();

		String query = "INSERT INTO movements (accountNumber,movementDate,detail,amount,MovementTypeId,status) VALUES"
				+ "('" + movement.getAccountNumber() + "','" + movement.getMovementDate() + "','" + movement.getDetail()
				+ "'," + movement.getAmount() + "," + movement.getMovementType().getId() + ",1)";

		try {
			status = cn.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return status;
	}

}
