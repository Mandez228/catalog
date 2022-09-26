package nahlebnik.jpa;

import nahlebnik.jpa.entity.Category;
import nahlebnik.jpa.entity.CharValues;
import nahlebnik.jpa.entity.Characteristics;
import nahlebnik.jpa.entity.Product;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final Scanner IN = new Scanner(System.in);
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println("- Создание [1]");
            System.out.println("- Редактирование [2]");
            System.out.println("- Удаление [3]");
            System.out.println("- Завершение работы [4]");

            String actionNum = IN.nextLine();
            switch (actionNum) {
                case "1" -> create();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> {
                    System.out.println("Завершение работы. Спасибо за использование программы!");
                    System.exit(0);
                }
                default -> System.out.println("Некорректный номер действия.");
            }
        }
    }
    private static void create() {
        EntityManager manager = factory.createEntityManager();
        try{
            manager.getTransaction().begin();
            TypedQuery<Category> categoryTypedQuery = manager.createQuery(
                    "select c from Category c", Category.class
            );
            Product product = new Product();
            List<Category> categories = categoryTypedQuery.getResultList();
            for(Category category : categories){
                System.out.println(category.getName() + " [" + category.getId() + "]");
            }
            System.out.println("Выберите категорию по номеру: ");
            String category_input = IN.nextLine();
            Long cat_convert = Long.parseLong(category_input);
            product.setCategory(manager.find(Category.class, cat_convert));
            System.out.println("Введите название товара: ");
            String name_input = IN.nextLine();
            product.setName(name_input);
            System.out.println("Введите цену");
            String price_input = IN.nextLine();
            Integer price_convert = Integer.parseInt(price_input);
            product.setPrice(price_convert);
            manager.persist(product);
            TypedQuery<Characteristics> characteristicsTypedQuery = manager.createQuery(
                    "select c from Characteristics c where c.category.id = :charId", Characteristics.class
            );
            characteristicsTypedQuery.setParameter("charId", cat_convert);
            List<Characteristics> characteristics = characteristicsTypedQuery.getResultList();
            for(Characteristics characteristic : characteristics){
                CharValues char1 = new CharValues();
                System.out.println("Введите информацию о характеристике: " + " [" + characteristic.getName() + "]");
                String value_input = IN.nextLine();
                char1.setValues(value_input);
                char1.setCharacteristics(characteristic);
                char1.setProduct(product);
                manager.persist(char1);
            }
            manager.getTransaction().commit();
            System.out.println("Товар успешно создан.");
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    private static void update(){
        EntityManager manager = factory.createEntityManager();

        try{
            manager.getTransaction().begin();
            System.out.println("Введите ID товара: ");
            String get_id = IN.nextLine();
            Long id_convert = Long.parseLong(get_id);
            Product product = manager.find(Product.class, id_convert);
            System.out.println("Выбранный товар: " + product.getName());
            System.out.println("Введите новое значение названия: ");
            String insert_name = IN.nextLine();
            product.setName(insert_name);
            System.out.println("Введите новое значение цены: ");
            String insert_price = IN.nextLine();
            Integer price_convert = Integer.parseInt(insert_price);
            product.setPrice(price_convert);
            TypedQuery<Characteristics> characteristicsTypedQuery = manager.createQuery(
                    "select c from Characteristics c where c.category.id = :charId", Characteristics.class
            );
            characteristicsTypedQuery.setParameter("charId", product.getCategory().getId());
            List<Characteristics> characteristics = characteristicsTypedQuery.getResultList();
            for(Characteristics characteristic : characteristics){
                System.out.println("Введите новую информацию о характеристике: " + " [" + characteristic.getName() + "]");
                String value_input = IN.nextLine();
                TypedQuery<CharValues> charValuesTypedQuery1 = manager.createQuery(
                        "select c from CharValues c where c.characteristics.id = :charId and c.product.id = :productId", CharValues.class
                );
                charValuesTypedQuery1.setParameter("charId", characteristic.getId());
                charValuesTypedQuery1.setParameter("productId", id_convert);
                List<CharValues> charValues = charValuesTypedQuery1.getResultList();
                CharValues charValues1 = charValues.get(0);
                charValues1.setValues(value_input);
            }
            manager.getTransaction().commit();
            System.out.println("Товар успешно обновлён.");
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    private static void delete(){
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            System.out.println("Введите ID товара для удаления: ");
            String get_id = IN.nextLine();
            Long id_convert = Long.parseLong(get_id);
            Product product = manager.find(Product.class, id_convert);
            if (product != null){
                Query query = manager.createQuery(
                        "delete from CharValues c where c.product.id = :cId"
                );
                query.setParameter("cId", product.getId());
                query.executeUpdate();
                Query query1 = manager.createQuery(
                        "delete from Product p where p.id = :pId"
                );
                query1.setParameter("pId", product.getId());
                query1.executeUpdate();
            }
            manager.getTransaction().commit();
            System.out.println("Товар успешно удалён.");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
