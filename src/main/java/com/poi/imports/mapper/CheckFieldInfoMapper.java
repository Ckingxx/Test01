package com.poi.imports.mapper;

import com.poi.imports.model.CheckFieldInfo;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class CheckFieldInfoMapper {
    public static final String url = "jdbc:mysql://localhost:3306/student_massage_swing";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "caokai";
    public static Connection conn = null;
    static {
        try {
            if (conn == null) {
                Class.forName(name);
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
               this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int insertData(CheckFieldInfo c){
        String sql = "insert into check_field_info values ('"+c.getFieldId()+"','"+c.getPublishTaskId()+"','"+c.getId()+"','"+c.getFieldname()+"','"+c.getFieldtype()+
                "','"+c.getLenPrecision()+"','"+c.getLenScala()+"','"+c.getFieldformat()+"','"+c.getChecknull()+"','"+c.getCheckrepeat()+"','"+c.getCheckenum()+"','"+c.getEnumvalue()+"')";
        int rs = 0;
        try {
            Statement st = conn.createStatement();
            rs = st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
