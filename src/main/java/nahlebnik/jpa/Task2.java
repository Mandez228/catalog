package nahlebnik.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task2 {
    public static void main(String[] args) throws Exception{
        String name1 = "Андрей";
        String surname1 = "Одинцов";

        // Если запись с именем из переменной name и фамилией из переменной surname есть в таблице users необходимо
        // вывести "Запись найдена в базе данных", в обратном случае "Запись не найдена в базе данных".

        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/pseudo_shop", "postgres", "1488"
        );
        Statement statement = connection.createStatement();
        String query = "select * from users where name = " + "'" + name1 + "'" + " and surname = " + "'" + surname1 + "'";
        if (statement.execute(query)){
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()){
                System.out.println("Запись найдена в базе данных");
            }
            else System.out.println("Запись не найдена в базе данных");
        }
    }
}
