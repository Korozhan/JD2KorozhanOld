package by.academy;
import java.sql.*;
import java.util.logging.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Connection connection = null;
        //URL � ���� ������� �� ���������:������������://[�����]:[�����_����]/[��] � ������_��������
        String url = "jdbc:postgresql://127.0.0.1:5432/test";
        //��� ������������ ��
        String name = "user";
        //������
        String password = "123456";
        try {
            //��������� �������
            Class.forName("org.postgresql.Driver");
            System.out.println("������� ���������");
            //������ ����������
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("���������� �����������");
            //��� ������������� SQL �������� ���������� 3 ���� ��������:
            //1.Statement: ������������ ��� ������� ������� ��� ����������
            Statement statement = null;

            statement = connection.createStatement();
            //�������� ������
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM users where id >2 and id <10");
            //result ��� ��������� �� ������ ������ � �������
            //����� ������� ������ �� ����� ������������ 
            //����� next() , � ������� �������� ��������� � ���������� ��������
            System.out.println("������� statement");
            while (result1.next()) {
                System.out.println("����� � ������� #" + result1.getRow()
                        + "\t ����� � ���� #" + result1.getInt("id")
                        + "\t" + result1.getString("username"));
            }
            // �������� ������
            statement.executeUpdate(
                    "INSERT INTO users(username) values('name')");
            //�������� ������
            statement.executeUpdate(
                    "UPDATE users SET username = 'admin' where id = 1");
        } catch (Exception ex) {
            //������� �������� �������� ���������
            Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
