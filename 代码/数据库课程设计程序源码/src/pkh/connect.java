package pkh;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {
	
		Connection conn;
	    Statement st;

	    private static String driverStr = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    private static String connStr = "jdbc:sqlserver://localhost:1433; DatabaseName=������Դ����ϵͳ";
	    private static String dbusername = "sa";
	    private static String dbpassword = "a1029384756";
	    
	    public connect(){}
		
		/* ��ȡ���ݿ����ӵĺ���*/
		public static Connection getConnection() {
			Connection con = null;	//���������������ݿ��Connection����
			try {
				Class.forName(driverStr);// ����sql server��������
			//	System.out.println("ok");
				con = DriverManager.getConnection(
						connStr,dbusername,dbpassword);// ������������
				
			} catch (Exception e) {
				System.out.println("���ݿ�����ʧ��" + e.getMessage());
			}
			return con;	//���������������ݿ�����
		}
}