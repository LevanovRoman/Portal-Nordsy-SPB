package com.myapp.portalnordsyspb.getDataFromDb;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TempService {

    private static final String POSTGRES_URL =
            "jdbc:postgresql://172.16.15.35:5499/sv_corporate_site_db";
    private static final String POSTGRES_USERNAME = "postgres";
    private static final String POSTGRES_PASSWORD = "admin232";

    private final JdbcTemplate jdbcTemplate;

    public void getDataFromDb() throws SQLException {
        String query = "SELECT * FROM phone_book";

        try (Connection connection = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USERNAME, POSTGRES_PASSWORD);
                Statement statement = connection.createStatement())
              {try (ResultSet resultSet = statement.executeQuery(query)) {
                      // Выводим содержимое ResultSet построчно
                      while (resultSet.next()) {
                          for (int i = 1; i <= 3; i++) {
                              System.out.print("*" + resultSet.getString(i) + "*" + "\t");
                          }
                          System.out.println();
                      }
                  }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void getDataFromDb() {
//        try (Connection connection = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USERNAME, POSTGRES_PASSWORD)) {
//            System.out.println("Connected to the database successfully!");
//            // Вывод данных из таблицы с помощью JdbcTemplate
//            printTableData();
//        } catch (SQLException e) {
//            System.err.println("Failed to connect to the database!");
//            e.printStackTrace();
//        }
//    }

    private void printTableData() {
//        String query = "SELECT tab_n, full_name_io, appoint_name FROM persons_cand WHERE card_id < 50";
        String query = "SELECT * FROM phone_book";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        // Выводим данные в консоль
        rows.forEach(System.out::println);
    }

}
