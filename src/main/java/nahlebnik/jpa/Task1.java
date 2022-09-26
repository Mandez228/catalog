package nahlebnik.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class Task1 {
    public static void main(String[] args) throws Exception{
        String name = "Андрей";
        String surname = "Одинцов";

        // Если запись с именем из переменной name и фамилией из переменной surname есть в таблице users необходимо
        // вывести "Запись найдена в базе данных", в обратном случае "Запись не найдена в базе данных".

        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/pseudo_shop", "postgres", "1488"
        );
        Statement statement = connection.createStatement();
        String query = "select * from users";
        boolean logic = false;
        if (statement.execute(query)){
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                if (resultSet.getString("name").equals(name) && resultSet.getString("surname").equals(surname)){
                   logic = true;
                }
            }
        }
        if (logic){
            System.out.println("Запись найдена в базе данных");
        }
        else System.out.println("Запись не найдена в базе данных");
    }
}
