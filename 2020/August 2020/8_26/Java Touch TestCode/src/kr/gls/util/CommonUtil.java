package kr.gls.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kr.gls.model.Config;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String MY_SQL_HOST = "jdbc:mysql://glstest.iptime.org:30000/glstech?characterEncoding=utf8&serverTimezone=UTC";
    private final String MYSQL_USER = "pi";
    private final String MYSQL_PWD = "1234";

    private Map<String, String> config_data = new HashMap<String, String>();
    private Map<Long, String> crc_data = new HashMap<Long, String>();

    public CommonUtil() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(MY_SQL_HOST, MYSQL_USER, MYSQL_PWD);
            stmt = conn.createStatement();

            String query = "SELECT " +
                    "`con`.`master_password` as `master_password`," +
                    "`con`.`gil_password` as `gil_password`," +
                    "`con`.`admin_password` as `admin_password`," +
                    "`con`.`device_addr` as `device_addr`," +
                    "`con`.`bonus1` as `bonus1`," +
                    "`con`.`bonus2` as `bonus2`," +
                    "`con`.`bonus3` as `bonus3`," +
                    "`con`.`bonus4` as `bonus4`," +
                    "`con`.`bonus5` as `bonus5`," +
                    "`con`.`bonus6` as `bonus6`," +
                    "`con`.`bonus7` as `bonus7`," +
                    "`con`.`bonus8` as `bonus8`," +
                    "`con`.`bonus9` as `bonus9`," +
                    "`con`.`bonus10` as `bonus10`," +
                    "`con`.`id` as `id`," +
                    "`con`.`card_price` as `card_price`," +
                    "`con`.`min_card_price` as `min_card_price`," +
                    "`con`.`version` as `version`," +
                    "`mg`.`manager_id` as `shop_id`," +
                    "`mg`.`encrypt` as `encrypt`," +
                    "`con`.`data_collect_state` as `data_collect_state`" +
                    "FROM config as `con` INNER JOIN `manager` as `mg` ON `con`.`manager_no` = `mg`.`no`";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String master_password = rs.getString("master_password");
                String gil_password = rs.getString("gil_password");
                String admin_password = rs.getString("admin_password");
                String device_addr = rs.getString("device_addr");
                String card_price = rs.getString("card_price");
                String min_card_price = rs.getString("min_card_price");
                String bonus1 = rs.getString("bonus1");
                String bonus2 = rs.getString("bonus2");
                String bonus3 = rs.getString("bonus3");
                String bonus4 = rs.getString("bonus4");
                String bonus5 = rs.getString("bonus5");
                String bonus6 = rs.getString("bonus6");
                String bonus7 = rs.getString("bonus7");
                String bonus8 = rs.getString("bonus8");
                String bonus9 = rs.getString("bonus9");
                String bonus10 = rs.getString("bonus10");
                String shop_id = rs.getString("id");
                String manager_id = rs.getString("shop_id");
                String version = rs.getString("version");
                String data_collect_state = rs.getString("data_collect_state");
                String encrypt = rs.getString("encrypt");

                config_data.put("master_password", master_password);
                config_data.put("gil_password", gil_password);
                config_data.put("admin_password", admin_password);
                config_data.put("bonus1", bonus1);
                config_data.put("bonus2", bonus2);
                config_data.put("bonus3", bonus3);
                config_data.put("bonus4", bonus4);
                config_data.put("bonus5", bonus5);
                config_data.put("bonus6", bonus6);
                config_data.put("bonus7", bonus7);
                config_data.put("bonus8", bonus8);
                config_data.put("bonus9", bonus9);
                config_data.put("bonus10", bonus10);
                config_data.put("card_price", card_price);
                config_data.put("min_card_price", min_card_price);
                config_data.put("shop_id", shop_id);
                config_data.put("manager_id", manager_id);
                config_data.put("version", version);
                config_data.put("data_collect_state", data_collect_state);
                config_data.put("encrypt", encrypt);
            }

            query = "SELECT * FROM `crc`";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                crc_data.put(Long.parseLong(rs.getString("no")), rs.getString("crc"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {

            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public Map<Long, String> getCrc() {
        return crc_data;
    }

    public Map<String, String> getConfig() {
        return config_data;
    }
}
