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
        //URL ê áàçå ñîñòîèò èç ïğîòîêîëà:ïîäïğîòîêîëà://[õîñòà]:[ïîğòà_ÑÓÁÄ]/[ÁÄ] è äğóãèõ_ñâåäåíèé
        String url = "jdbc:postgresql://127.0.0.1:5432/test";
        //Èìÿ ïîëüçîâàòåëÿ ÁÄ
        String name = "user";
        //Ïàğîëü
        String password = "123456";
        try {
            //Çàãğóæàåì äğàéâåğ
            Class.forName("org.postgresql.Driver");
            System.out.println("Äğàéâåğ ïîäêëş÷åí");
            //Ñîçäà¸ì ñîåäèíåíèå
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Ñîåäèíåíèå óñòàíîâëåíî");
            //Äëÿ èñïîëüçîâàíèÿ SQL çàïğîñîâ ñóùåñòâóşò 3 òèïà îáúåêòîâ:
            //1.Statement: èñïîëüçóåòñÿ äëÿ ïğîñòûõ ñëó÷àåâ áåç ïàğàìåòğîâ
            Statement statement = null;

            statement = connection.createStatement();
            //Âûïîëíèì çàïğîñ
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM users where id >2 and id <10");
            //result ıòî óêàçàòåëü íà ïåğâóş ñòğîêó ñ âûáîğêè
            //÷òîáû âûâåñòè äàííûå ìû áóäåì èñïîëüçîâàòü 
            //ìåòîä next() , ñ ïîìîùüş êîòîğîãî ïåğåõîäèì ê ñëåäóşùåìó ıëåìåíòó
            System.out.println("Âûâîäèì statement");
            while (result1.next()) {
                System.out.println("Íîìåğ â âûáîğêå #" + result1.getRow()
                        + "\t Íîìåğ â áàçå #" + result1.getInt("id")
                        + "\t" + result1.getString("username"));
            }
            // Âñòàâèòü çàïèñü
            statement.executeUpdate(
                    "INSERT INTO users(username) values('name')");
            //Îáíîâèòü çàïèñü
            statement.executeUpdate(
                    "UPDATE users SET username = 'admin' where id = 1");
        } catch (Exception ex) {
            //âûâîäèì íàèáîëåå çíà÷èìûå ñîîáùåíèÿ
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
