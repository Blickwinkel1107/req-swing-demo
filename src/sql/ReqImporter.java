package sql;

import java.io.File;
import java.sql.*;

/**
 * created by yx 将需求文本写入数据库
 */

public class ReqImporter {
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite::resource:sql/test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			File root = new File("data/sample/AquaLush_Requirement_Origin");
			File[] fileList = root.listFiles();
			for (File file : fileList) {
				// System.out.println(file.getName());
				String fileName = file.getName();
				String sql = "INSERT INTO reqList " + "VALUES (\'" + fileName + "\', 'Normal', 0);";
				stmt.executeUpdate(sql);
			}

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}
}