import oracle.jdbc.internal.OracleTypes;

import java.sql.*;

public class PLSQL {
    private Connection conn;

    public PLSQL() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        conn = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:xe", "student", "STUDENT");
    }

    public String selectieUser(String username) throws SQLException {
        CallableStatement cstmt = conn.prepareCall("{? = call selectieUtilizator (?, ?, ?)}");
        cstmt.registerOutParameter (1, Types.VARCHAR);
        cstmt.setString(2, username);
        cstmt.setInt(3, 10);
        cstmt.setInt(4, 10);
        cstmt.execute();
        return cstmt.getString(1);
    }

    public boolean insertUser(String username, String password) throws SQLException {
        boolean temp = false;
        CallableStatement cstmt = conn.prepareCall("{? = call insertUser (?, ?)}");
        cstmt.setString(2, username);
        cstmt.setString(3, password);
        cstmt.registerOutParameter (1, OracleTypes.BOOLEAN);
        cstmt.executeUpdate();
        return cstmt.getBoolean(1);
    }

    public void updatePoints(String username1, String username2) throws SQLException {
        boolean temp = false;
        CallableStatement cstmt = conn.prepareCall("{call puncteDupaMeci (?, ?, ?)}");
        cstmt.setString(1, username1);
        cstmt.setString(2, username2);
        cstmt.registerOutParameter (1, OracleTypes.BOOLEAN);
        cstmt.executeUpdate();
    }

    public void updateStatusJucator(String username, Integer numarStatus) throws SQLException {
        CallableStatement cstmt = conn.prepareCall("{call updateStatusJucator (?, ?)}");
        cstmt.setString(1, username);
        cstmt.setInt(2, numarStatus);
        cstmt.executeUpdate();
    }

    public void topN(Integer number) throws SQLException {
        CallableStatement cstmt = conn.prepareCall("{call top (?)}");
        cstmt.setInt(1, number);
        cstmt.executeUpdate();
    }

    public void intervalPoints(Integer number1, Integer number2) throws SQLException {
        CallableStatement cstmt = conn.prepareCall("{call intervalPoints (?, ?)}");
        cstmt.setInt(1, number1);
        cstmt.setInt(2, number2);
        cstmt.executeUpdate();
    }
}
