package nahlebnik.jpa;

import nahlebnik.jpa.entity.Category;
import nahlebnik.jpa.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ApplicationLesson2 {
    public static void main(String[] args) {
        // JPA - стандарт, описывающий взаимодействие Java с реляционными базами данных по принципу ORM. JPA решает несколько изъянов
        // JDBC, такие как привязка к определённому диалекту, взаимодействие с данными через неудобный для использования ResultSet
        // (тип определяется в момент обращения) и т.д.

        // ORM (Object Relational Mapping) - система объектно - реляционного сопоставления, основной целью которой является преобразование
        // табличных данных к Java объектам и наоборот.

        // table users        -> ORM -> class User
        // id serial8         -> ORM -> Long id
        // first_name varchar -> ORM -> String firstName
        // birthdate date     -> ORM -> LocalDate birthdate

        // Т.к. JPA это просто стандарт, написанный на бумаге (т.е. мы не можем взять и использовать JPA, т.к. это не конкретный
        // рабочий прототип), необходимо использовать сторонние реализации этого стандарта.

        // Реализации JPA:
        // 1. Hibernate;
        // 2. EclipseLink;
        // 3. ...;

        // persistence.xml - основной конфигурационный файл стандарта JPA, который хранит в себе блоки с параметрами подключения.
        // Данный файл конфигурации должен находиться в папке с названием META-INF, которая в свою очередь должна попасть
        // в итоговую сборку проекта.

        // EntityManagerFactory - объект, основной задачей которого является открытие нового соединения с базой данных, и
        // поддержание его в открытом виде с целью получения объектов EntityManager.

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");

        // EntityManager - объект, позволяющий производить манипуляции с сущностями (создавать, изменять, удалять и выбирать).
        // Сущность - Java класс объекты, которому взаимодействуют с базой данных т.е. являются отражением таблиц в JPA.
        EntityManager manager = factory.createEntityManager();

        // 'manager.find(Class<T> aClass, Object o) : T' - производит поиск сущности типа из параметра 'aClass' со значением
        // первичного ключа из параметра 'o'

        // Category.class -> Class<Category>
        // Product.class -> Class<Product>

        //select * from categories where id = 1
        Category category = manager.find(Category.class,1L);


        // select * from categories join products on products.category_id = categories.id where id = 1
        List<Product> products = category.getProducts();

        // В базе данных для взаимодействия с ней используется язык специального
        // назначения SQL. Язык SQL может иметь различные особенности для
        // разных баз данных.

        // JPQL - язык из стандарта JPA предназначенный для написания запросов и взаимодействия с сущностями.
        // JPQL это универсальный язык для работы с базой данных в рамках стандарта JPA.

        // JPQL -> ORM -> MySQL Dialect-> MySQL
        // JPQL -> ORM -> MySQL Dialect-> Oracle DB
        // JPQL -> ORM -> MySQL Dialect-> Microsoft SQL Server

        // SQL -> select * from products
        // JPQL -> select p from Product p

        // SQL -> select * from products where price >= 100000 and price <= 200000
        // JPQL -> select p from Product p where p.price between 100000 and 200000

        // 'TypedQuery<X>' - объект, предназначенный для выполнения запросов с четко определенным результатом.
        // Тип результата указывается в аргументе 'X'. Применяется для выполнения запросов на выборку данных,
        // т.е. select.

        // 'entityManager.createQuery(String s, Class<T> clazz) : TypedQuery<T>'
        // производит создание нового объекта 'TypedQuery' на осн. JPQL запроса из параметра 's' и с типом
        // результата из параметра 'clazz'.

        /*TypedQuery<Product> categoryTypedQuery = manager.createQuery(
                "select p from Product p where p.price between 10000 and 15000", Product.class
        );
        List<Product> products1 = categoryTypedQuery.getResultList();
        for (Product product : products1){
            System.out.println(product.getName());
        }*/

        // Параметры запроса - условные обозначения в запросе на место которых в дальнейшем должна быть подставлена
        // конкретная информация.
        // Параметры в JPQL бывают двух видов:
        // 1. Порядковые - каждый параметр должен быть пронумерован числом начиная от единицы.
        // Такие параметры создаются при помощи вопросит. знака.
        // 2. Именованные - каждый параметр должен иметь своё название при условии, что это название соответствует правилам
        // составления идентификатора. Такие параметры создаются при помощи двоеточия.

        // "select * from products where category_name = ?1"

        // setParameter(1, "Процессоры")

        /*TypedQuery<Product> productTypedQuery = manager.createQuery(
                "select p from Product p where p.price between ?1 and ?2", Product.class
        );
        productTypedQuery.setParameter(1, 10000);
        productTypedQuery.setParameter(2, 15000);
        List<Product> products2 = productTypedQuery.getResultList();
        for (Product product : products2){
            System.out.println(product.getName());
        }*/

        TypedQuery<Product> productTypedQuery = manager.createQuery(
                "select p from Product p where p.price between :minPrice and :maxPrice", Product.class
        );
        productTypedQuery.setParameter("minPrice", 10000);
        productTypedQuery.setParameter("maxPrice", 15000);
        List<Product> products2 = productTypedQuery.getResultList();
        for (Product product : products2){
            System.out.println(product.getName());
        }
    }
}
