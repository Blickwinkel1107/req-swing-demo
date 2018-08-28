package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SqlExecuter {
	public static void process(String sql) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Connection c = null;
				Statement stmt = null;
				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite::resource:sql/test.db");
					c.setAutoCommit(false);
					stmt = c.createStatement();
					System.out.println(sql);
					stmt.executeUpdate(sql);
					stmt.close();
					c.commit();
					c.close();
				} catch (Exception err) {
					System.err.println(err.getClass().getName() + ": " + err.getMessage());
					System.exit(0);
				}
			}
		}).start();

	}
}
