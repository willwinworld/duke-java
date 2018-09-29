import java.sql.*;

public class update {

//        static final String DB_URL = "jdbc:mysql://192.168.1.110:33312/alphai?&useSSL=false";
//
//        static final String USER = "root";
//        static final String PASS = "alphai_news_mysql_passwd";

        private static Connection getConn(){
            String url = "jdbc:mysql://192.168.1.110:33312/alphai?&username=root&pass=alphai_news_mysql_passwd&useSSL=false";
            Connection conn = null;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url);
                if (conn != null){
                    System.out.println("Connected");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return conn;
        }

        private static void insert(){
            Connection conn = getConn();
            String sql = "insert into cluster (object_id, url, title, html, content, category_name, ) values(?, ?, ?)";
            try{
                PreparedStatement statement = conn.prepareStatement(sql);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }



        public static void main(String[] args){
            Connection conn = null;
            Statement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cluster LIMIT 10");
            while (rs.next()){
                System.out.println(rs.getString("object_id"));
            }

            conn.isValid(10);
        }catch (Exception e){
            e.printStackTrace();
        }
        }
}
