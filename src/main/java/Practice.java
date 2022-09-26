import nahlebnik.jpa.Filter;
import nahlebnik.jpa.entity.Category;
import nahlebnik.jpa.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class Practice {
    private static final Scanner IN = new Scanner(System.in);
    public static void main(String[] args) {
        String categoryName = "Процессоры";


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Category> productTypedQuery = manager.createQuery(
                "select c from Category c where c.name = :categoryName", Category.class
        );
        productTypedQuery.setParameter("categoryName", categoryName);
        List<Category> category1 = productTypedQuery.getResultList();
        System.out.println("Введите название категории: ");
        String next_Cat = IN.nextLine();
        if (next_Cat.equals(categoryName)) {
            System.out.println("Такая запись есть в базе данных");
           }
        else System.out.println("Такой записи нет в базе данных");


    }
}
