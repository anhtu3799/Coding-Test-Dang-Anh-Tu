import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportDb {
    public static void main(String[] args) {
        String csvFilePath = "\\Entrytest_Dang_Anh_Tu\\merge_result.csv";
        String dbUrl = "jdbc:postgresql://localhost:5432/TranslationProgram";
        String username = "postgres";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO translation(\n" +
                     "\tid, text, audio_url, translate_id, translate_text)\n" +
                     "\tVALUES (?, ?, ?, ?, ?);")) {

            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                line = reader.readLine();
                String[] data = line.split(","); // Adjust the delimiter based on your CSV file
                // Set the values for each column in the prepared statement
                statement.setInt(1, Integer.parseInt(data[0]));
                statement.setString(2, data[1]);
                statement.setString(3, data[2]);
                statement.setInt(4, Integer.parseInt(data[3]));
                statement.setString(5, data[4]);
                statement.executeUpdate();
            }
            connection.close();
            System.out.println("CSV data imported successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
