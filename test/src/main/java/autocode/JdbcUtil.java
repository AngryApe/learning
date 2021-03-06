/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package autocode;

import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @auther qiys@hzzh.com
 * @date 2018-03-15
 */
public class JdbcUtil {

    public static void parseColumns(ResultSet res, List<ColumnMeta> columns, Set<String> classes) {
        try {
            while (res.next()) {
                String columnName = res.getString("COLUMN_NAME");
                String dataType = res.getString("TYPE_NAME");
                String remarks = res.getString("REMARKS").replace("\r\n", "\t");
                ColumnMeta column = new ColumnMeta(columnName, dataType,
                        parseColumnName(columnName), remarks, jdbc2JavaType(dataType));
                columns.add(column);
                //                res.getMetaData().getColumnClassName()
                System.out.println(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static String jdbc2JavaType(String jdbcType) {
        switch (jdbcType) {
            case "CHAR":
            case "VARCHAR":
            case "LONGVARCHAR":
                return "String";
            case "NUMERIC":
            case "DECIMAL":
                return "java.math.BigDecimal";
            case "BIT":
                return "Boolean";
            case "TINYINT":
            case "SMALLINT":
            case "INTEGER":
                return "Integer";
            case "BIGINT":
                return "Long";
            case "REAL":
                return "Float";
            case "FLOAT":
            case "DOUBLE":
                return "Double";
            case "BINARY":
            case "VARBINARY":
            case "LONGVARBINARY":
                return "byte[]";
            case "DATE":
            case "TIME":
            case "TIMESTAMP":
                return "java.util.Date";
        }
        return "String";
    }

    public static String getKey(ResultSet res) {
        String key = null;
        try {
            if (res.next()) {
                key = res.getString("COLUMN_NAME"); //列名
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String parseCamelName(String name) {
        StringBuffer sb = new StringBuffer();
        sb.append(name);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        return StringUtils.capitalize(result);
    }

    public static String parseColumnName(String columnName) {
        String str = parseCamelName(columnName);
        str = str.substring(0, 1).toLowerCase() + str.substring(1);
        return str;
    }

}
