package nahlebnik.jpa;

import java.sql.*;

public class ApplicationLesson {
    public static void main(String[] args) throws Exception {
        // Maven - это система автоматизированной сборки для Java проектов.
        // Позволяет не заморачиваться с ручным подключением внешних библиотек и их конфигурацией.
        // pom.xml - конфигурационный файл для системы сборки Maven, который должен находиться в корневом каталоге проекта.
        // В данном файле конфигурации можно указать какие именно библиотеки должны использоваться проектом и версии этих библиотек должны использоваться проектом,
        // как именно должен собираться проект и т.д.

        // JDBC (Java Database Connectivity) - модуль стандартной Java, который позволяет взаимодействовать с базой данных
        // посредством написания запросов на нативном SQL и получения результата через стандартный объект ResultSet.
        // ResultSet не даёт четкого понимания о том, какие именно поля фигурируют в результате запроса, т.е. разработчик обращается
        // к полям практически вслепую.

        // Connection - объект, который хранит в себе подключение к базе данных по переданным ранее параметрам.
        // DriverManager - класс, предназначенный для получения объекта подключения к базе данных, т.е. объект Connection.
        // DriverManager.getConnection(
        // String url, String user, String password
        // ) : Connection - производит подключение к базе данных по JDBC строке подключения из параметра url от имени пользователя
        // из параметра user и пароля из параметра password. Подключение будет получено только если в classpath проекта есть
        // драйвер для работы с требуемой базой данных.
        // Для того, чтобы работать из языка программирования с любой базой данных необходима специальная прослойка в виде драйвера.
        // PostgreSQL -> PostgreSQL Java Driver -> Java
        //            -> PostgreSQL C++ Driver -> C++
        //            -> PostgreSQL PHP Driver -> PHP

        // JDBC строка подключения записывается в следующем формате:
        // jdbc:<jdbc обозначение базы данных>://<хост>:<порт>/<название базы данных>
        // jdbc обозначение бд - у каждой базы данных в рамках JDBC есть своё название, например для PostgreSQL это название
        // postgresql и т.д.
        // хост - адрес сервера либо доменное имя, на котором работает сервер базы данных.
        // порт - порт к которому привязан сервер бд.
        // название бд - название базы данных, к которой необходимо подключиться.

        // No suitable driver found - для организации подключения по переданным параметрам нет подходящего драйвера в classpath.
        // mvnrepository.org - центральное хранилище Java пакетов и библиотек, которые можно подключать к себе в проект с целью дальнейшего использования.

        Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/pseudo_shop", "postgres", "1488"
        );

        // Statement - объект, предназначенный для выполнения нативных (SQL диалект специфичный для отдельно взятой базы данных) запросов к базе данных.
        Statement statement = connection.createStatement();
        // .execute(String sql) : boolean - метод, который отправляет на выполнение запрос из параметра sql в базу данных,
        // и в случае успешного выполнения возвращает true, в ином случае - false.

        // ResultSet - объект, хранящий информацию о результате select запроса, при помощи данного объекта можно перебирать
        // результирующие записи и брать информацию по полям.
        // По умолчанию ResultSet не хранит ссылку на конкретную запись, что может приводить к ошибкам.
        // *.next() : boolean - переключается на следующую по порядку запись начиная с первой. Если переключение было успешным -
        // возвращает true, если записей больше нет - false.
            String query = "select * from users";
        if (statement.execute(query)){
            ResultSet resultSet = statement.getResultSet();
            /*resultSet.next(); // Переключается на первую запись.
            System.out.println(resultSet.getLong("id")); // Берётся id первой записи.
            System.out.println(resultSet.getString("name"));
            resultSet.next(); // Переключается на вторую запись.
            System.out.println(resultSet.getLong("id")); // Берётся id второй записи.
            System.out.println(resultSet.getString("name"));*/

           /* for (; resultSet.next(); ){
                System.out.println(resultSet.getLong("id"));
                System.out.println(resultSet.getString("name"));
                Date date = resultSet.getDate("reg_date");
                System.out.println(resultSet.getDate("reg_date"));
            };*/

            while(resultSet.next()){
                System.out.println(resultSet.getLong("id"));
                System.out.println(resultSet.getString("name"));
            }
        }
    }

}
