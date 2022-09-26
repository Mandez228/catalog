package nahlebnik.jpa;

import nahlebnik.jpa.entity.Category;
import nahlebnik.jpa.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;


public class Filter {
    private static final Scanner IN = new Scanner(System.in);
    public static void main(String[] args) {
        /*Long categoryId = 2L;
        Integer minPrice = 0;
        Integer maxPrice = 1000000000;*/

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        /*
        Category category = manager.find(Category.class, categoryId);
        *//*productTypedQuery.setParameter("minPrice", minPrice);
        productTypedQuery.setParameter("maxPrice", maxPrice);
        productTypedQuery.setParameter("categoryId", categoryId);*//*
       *//* for(Product product : products){
            System.out.println(product.getName());
        }*//*

        System.out.println("Введите ID категории: ");
        String id_next = IN.nextLine();
        Long id_convert = Long.parseLong(id_next);
        System.out.println("Введите мин. цену: ");
        String min_next = IN.nextLine();
        Integer min_convert = Integer.parseInt(min_next);
        System.out.println("Введите макс. цену: ");
        String max_next = IN.nextLine();
        Integer max_convert = Integer.parseInt(max_next);
        TypedQuery<Product> productTypedQuery = manager.createQuery(
                "select p from Product p where p.price between :minPrice and :maxPrice and p.category.id = :categoryId", Product.class
        );
        productTypedQuery.setParameter("minPrice", min_convert);
        productTypedQuery.setParameter("maxPrice", max_convert);
        productTypedQuery.setParameter("categoryId", id_convert);
        List<Product> products = productTypedQuery.getResultList();
        for(Product product : products){
            System.out.println(product.getName());
        }
        */

        String parameter_name = "Samsung";

        TypedQuery<Product> productTypedQuery = manager.createQuery(
                "select p from Product p where p.name like :name", Product.class
        );
        productTypedQuery.setParameter("name", "%" + parameter_name + "%");
        List<Product> products = productTypedQuery.getResultList();
        for(Product product : products){
            System.out.println(product.getName());
        }

        // Транзакция - это группа запросов, собранных в один большой запрос, который отправляется на выполнение в базу данных,
        // транзакции гарантируют полное выполнение всех запросов, которые в него входят.

        // transaction -> {
        //   insert ___ -> correct
        //   update ___ -> correct
        //   update ___ -> correct
        //   update ___ ->
        // } -> error -> rollback (отмена всех изменений, которые были сделаны в рамках транзакции.)

        // transaction -> {
        //   insert ___ -> correct
        //   update ___ -> correct
        //   update ___ -> correct
        //   update ___ -> correct
        // } -> correct -> commit (подтверждение результата работы всех запросов, выполненных в рамках транзакции.)

        // Транзакция работает по принципу: либо выполняется всё, либо ничего, что и гарантирует целостность данных.
        // любое действие в рамках JPA, изменяющее данные данные (insert, update, delete) должны выполняться в рамках транзакции
        // в обязательном порядке.

        // entityManager.getTransaction() : EntityTransaction - возвращает объект транзакции, актуальной на момент вызова.

        // EntityTransaction - объект, описывающий транзакцию в базе данных.
        // -begin() : void - начинает новую транзакцию.
        // -commit() : void - отправляет на выполнение в базу все запросы, которые были добавлены в транзакцию после её создания.
        // -rollback() : void - отменяет результат работы всех запросов, которые были выполнены в рамках последней транзакции.

        // try...catch - конструкция, применяемая для отлова ошибок в определенном участке кода.
        // try {...} -> кодовый блок, проверяемый на возникновение ошибок.
        // catch {...} {...} -> кодовый блок, в котором указывается тип ошибки и реакция на её возникновение.
        // Exception - базовый класс для любых ошибок в Java программе, возникающих во время выполнения программы.
        // e.printStackTrace() - метод, выводящий информационное сообщение об ошибке.
        // entityManager.persist(Object o) : void - привязывает сущность локально к объекту EntityManager, после подтверждения
        // транзакции привязанная сущность сама отправляется в базу данных.

        try {
            manager.getTransaction().begin();

            // Добавление запросов
            Category category = manager.find(Category.class, 3L);
            category.setName("Category");
//            manager.persist(category);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}
