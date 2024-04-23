package piikki;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import piikki.GUIWrapper.CustomQueryResult;

public class DBWrapper {

	public static String dbServer = "127.0.0.1:3306";

	public static String dbUser = "";

	public static String dbPass = "";

	public static String dbName = "";

	private static Statement stmt = null;

	private static Connection conn = null;

	private int showLog = 1000;

	private enum TableName {
		Users, Products, Purchases, Adminlog, Stuff
		// , Pricelist
	}

	public DBWrapper() {
		// init();
	}

	// public void close() {
	//		
	// try {
	// stmt.close();
	// conn.close();
	// } catch (SQLException e) {
	// System.out.println("Exception in close: " + e);
	// }
	//
	// }

	public void init() {
		if (conn == null)
			createConn();
		dropTables();
		createTables();
		insertBasic();
	}

	protected void dropTables() {
		for (TableName name : TableName.values()) {
			execute("drop table if exists " + name.name() + ";");
			// System.out.println("name: " + name.name());
		}
	}

	private void createConn() {
		// System.out.println("test "+dbPass);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + dbServer + "/"
					+ dbName + "?user=" + dbUser + "&password=" + dbPass);
			stmt = conn.createStatement();
			stmt.setEscapeProcessing(false);
		} catch (SQLException e) {
			System.out.println("Exception in createConn: " + e);
			sqlError(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public boolean execute(String query) {
		if (conn == null)
			createConn();
		// System.out.println(query);
		try {
			String query1 = query.replaceAll("\\\\", "\\\\\\\\");
			stmt.executeUpdate(query1);
			if (GUIController.isDev) {
				System.out.println("executed: "+query1);
			}
			return true;
		} catch (SQLException e) {
			System.err.println("Exception in execute: " + e);
			sqlError(e);
			return false;
		}
	}

	public Vector getUsers() {
		if (conn == null)
			createConn();
		Vector<User> users = new Vector<User>();
		try {
			ResultSet rs = stmt.executeQuery("select * from Users;");
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setDetails(rs.getString("details"));
				user.setDiscount(rs.getBoolean("discount"));
				user.setBalance(rs.getDouble("balance"));
				users.add(user);
			}
		} catch (SQLException e) {
			System.err.println("Exception in getUsers: " + e);
			sqlError(e);
			return null;
		}
		return users;
	}

	public Vector<Product> getProducts() {
		if (conn == null)
			createConn();
		Vector<Product> products = new Vector<Product>();
		try {
			ResultSet rs = stmt
					.executeQuery("select * from Products order by name;");
			while (rs.next()) {
				Product prod = new Product();
				prod.setName(rs.getString("name"));
				prod.setPrice(rs.getDouble("price"));
				prod.setNumber(rs.getInt("number"));
				products.add(prod);
			}
		} catch (SQLException e) {
			System.err.println("Exception in getProducts: " + e);
			sqlError(e);
			return null;
		}
		return products;
	}

	private void insertBasic() {
		if (conn == null)
			createConn();
		try {
			stmt.execute("insert into Products values(1, 'Olut', 1.3);");
			stmt
					.execute("insert into Products values(2, 'Oluttölkki (Tallinna)', 1.0);");
			stmt
					.execute("insert into Products values(3, 'Siideri (0,5l)', 2.6);");
			stmt.execute("insert into Products values(4, 'Shotti', 1.0);");
			stmt.execute("insert into Stuff values('piikkibackup', 0.2);");

		} catch (SQLException e) {
			System.err.println("Exception: " + e);
		}
	}

	private void createTables() {

		try {
			String[] creates = getCreates();
			File file = new File("create_tables.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (int a = 0; a < creates.length; a++) {
				stmt.execute(creates[a]);
				writer.append(creates[a] + "\n");
			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

	}

	public String getBackUpPathAndDiscount() {
		if (conn == null)
			createConn();
		try {
			ResultSet rs = stmt.executeQuery("select * from Stuff;");
			if (rs.next()) {
				try {
					Common.discount = rs.getDouble("discount");

				} catch (SQLException e) {
					// no discount column, versions before 1.35
				}
				return rs.getString("backup");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			sqlError(e);
		}
		return null;
	}

	public void getOrderLog() {
		if (conn == null)
			createConn();
		try {
			ResultSet rs = stmt
					.executeQuery("select * from (select * from (select * from Purchases order by date desc) as a limit "
							+ showLog + ") as b order by date;");
			while (rs.next()) {
				GUIWrapper.updateOrderLog(rs.getTimestamp("date"), rs
						.getString("name"), rs.getString("product"), rs
						.getDouble("price"));
			}
		} catch (SQLException e) {
			System.err.println("Exception in getOrderLog: " + e);
			sqlError(e);
		}
	}

	public void getAdminLog() {
		if (conn == null)
			createConn();
		try {
			ResultSet rs = stmt
					.executeQuery("select * from (select * from (select * from Adminlog order by date desc) as a limit "
							+ showLog + ") as b order by date;");
			while (rs.next()) {
				GUIWrapper.updateAdminLog(rs.getTimestamp("date"), rs
						.getString("action"), rs.getString("actionValue"), rs
						.getString("target"));
			}
		} catch (SQLException e) {
			System.err.println("Exception in getAdminLog: " + e);
			sqlError(e);
		}
	}

	public void addAdminLog(String action, String actionValue, String target) {
		String query = "insert into Adminlog values (?, ?, ?, ?);";
		Calendar cal = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setTimestamp(1, timestamp);
			stmt.setString(2, action);
			stmt.setString(3, actionValue);
			stmt.setString(4, target);
			stmt.executeUpdate();
			GUIWrapper.updateAdminLog(timestamp, action, actionValue, target);
		} catch (SQLException e) {
			e.printStackTrace();
			sqlError(e);
		}
	}

	public void addOrderLog(Vector sells, User user) {
		String query = "insert into Purchases values (?, ?, ?, ?);";
		Calendar cal = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			double totalAmount = 0.0;
			String product;
			double price;

			for (int a = 0; a < sells.size() - 1; a++) {
				Vector sell = (Vector) sells.get(a);
				int amount = Integer.parseInt(sell.get(2).toString());
				while (amount != 0) {

					product = sell.get(0).toString();
					price = Double.parseDouble(sell.get(1).toString());

					// if (user.getDiscount()) price -= Common.discount;
					price = Common.getNum(price);

					stmt.setTimestamp(1, timestamp);
					stmt.setString(2, user.getName());
					stmt.setString(3, product);
					stmt.setDouble(4, price);

					GUIWrapper.updateOrderLog(timestamp, user.getName(),
							product, price);
					stmt.executeUpdate();

					totalAmount += price;
					amount--;
				}
			}
			double amount2 = Double.parseDouble(sells.lastElement().toString());
			totalAmount = Common.getNum(totalAmount);

			stmt.setTimestamp(1, timestamp);
			stmt.setString(2, user.getName());
			stmt.setString(3, "Muu");
			stmt.setDouble(4, Common.getNum(amount2 - totalAmount));

			if (amount2 - totalAmount > 0) {
				GUIWrapper.updateOrderLog(timestamp, user.getName(), "Muu",
						Common.getNum(amount2 - totalAmount));
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			sqlError(e);
		}
	}

	public List<Analysis.StatsData> getStats(String product) {
		List<Analysis.StatsData> results = new Vector<Analysis.StatsData>();
		String query;
		if (product == "")
			query = "select date_format(date, '%Y-%m-%d') as date_mod, sum(price) as price_mod from Purchases group by date_format(date, '%Y-%m-%d');";
		else
			query = "select date_format(date, '%Y-%m-%d') as date_mod, count(price) as price_mod from Purchases where product in (select name from Products where name like '"
					+ product + "') group by date_format(date, '%Y-%m-%d');";

		if (conn == null)
			createConn();
		try {
			ResultSet rs = stmt.executeQuery(query);
			Analysis anal = new Analysis();
			while (rs.next()) {
				Analysis.StatsData data = anal.new StatsData();
				data.setDate(rs.getDate("date_mod"));
				data.setPrice(rs.getDouble("price_mod"));
				results.add(data);
			}
		} catch (SQLException e) {
			System.err.println("Exception in getS: " + e);
			sqlError(e);
		}
		return results;
	}
	
	public void removeLogs() {
		if (conn == null)
			createConn();
		String query1 = "DELETE FROM Adminlog";
		String query2 = "DELETE FROM Purchases";
		try {
			stmt.execute(query1);
			stmt.execute(query2);
		} catch (SQLException e) {
			e.printStackTrace();
			sqlError(e);
		}
	}

	private void sqlError(SQLException e) {

		JOptionPane
				.showMessageDialog(
						null,
						"Virhe tietokannan käytössä. Kone on luultavasti sekoittanut tietokannan (taas). \n"
								+ "Kanta on nyt toivottavasti korjattu. Mahdollisesti viimeisin muutos tietokantaan ei jäänyt voimaan, tarkista \n"
								+ "mahdollisuuksien mukaan. Käynnistä uusiksi. \n\nVirhe: "
								+ e);
		repairTables();
		GUIController.quit();
	}

	public void repairTables() {
		for (TableName name : TableName.values()) {
			try {
				stmt.executeUpdate("repair table " + name.name() + ";");
			} catch (SQLException e) {
				System.err.println("Exception in repair: " + e);
				JOptionPane.showMessageDialog(null,
						"Taulujen korjaaminen epäonnistui. Jotain on "
								+ "vituillaan tai sitten tietokantayhteys ei vaan toimi.");
			}

		}
	}
	
	

	private String[] getCreates() {

		String[] result = {
				"create table Users ( name varchar(50) NOT NULL UNIQUE, "
						+ "details varchar(100), "
						+ "discount boolean NOT NULL, balance double) ",

				"create table Purchases ( " + "date Timestamp NOT NULL, "
						+ "name varchar(50) NOT NULL, "
						+ "product varchar(50) NOT NULL, "
						+ "price double NOT NULL) ",

				"create table Adminlog ( " + "date Timestamp NOT NULL, "
						+ "action varchar(50) NOT NULL, "
						+ "actionValue varchar(50), "
						+ "target varchar(50) NOT NULL) ",

				"create table Products ( number int NOT NULL, "
						+ "name varchar(40) NOT NULL, "
						+ "price double NOT NULL)",

				"create table Stuff ( backup varchar(100), "
						+ "discount double);"

		// "create table Pricelist (name varchar(40) NOT NULL,"
		// + "price double);"

		};
		return result;
	}

	
	public String[][] getBalanceStats() {
		if (conn == null)
			createConn();
		String[][] result = null;
		try {
			ResultSet rs = stmt.executeQuery("select * from Users order by Balance;");
			result = new String[Common.usersVector.size()][2];
			int a = 0;
			while (rs.next()) {
				 result[a][0] = rs.getString("name");
				 result[a][1] = rs.getString("balance");
				 a++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			sqlError(e);
		}
		return result;
	}

	

}
