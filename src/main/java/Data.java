import java.io.BufferedReader;
import java.sql.*;
import java.util.Arrays;

public class Data
{

    static String sqlQuery = "INSERT INTO schools(x_coord, y_coord, fid, name, address, board) VALUES (?, ?, ?, ?, ?, ?)";


    public Data(BufferedReader reader)
    {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/windsor",
                    "root",
                    "");
            System.out.print("\nDatabase connected\n");
            PreparedStatement ps = con.prepareStatement(sqlQuery);

            String row;
            while ((row = reader.readLine()) != null) {

                // skip the header line
                if (row.contains("XCoord")) {
                    continue;
                }

                String[] data = row.split(",");

                // build the sql query
                ps.setFloat(1, Float.parseFloat(data[0]));
                ps.setFloat(2, Float.parseFloat(data[1]));
                ps.setInt(3, Integer.parseInt(data[2]));
                ps.setString(4, data[3]);
                ps.setString(5, data[4]);
                ps.setString(6, data[5]);

                ps.addBatch();
            }

            System.out.println(
                    Arrays.toString(ps.executeBatch())
            );
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
