import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        try (InputStream inputStream = Main.class.getResourceAsStream("Schools.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://192.168.2.91/windsor",
                    "root",
                    ""
            )) {

                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO schools(x_coord, y_coord, fid, name, address, board) VALUES (?, ?, ?, ?, ?, ?)"
                );

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
            }


        }
    }
}
