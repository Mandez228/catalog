insert into categories(category_name)
values ('Процессоры'),
       ('Мониторы');

insert into characteristics(characteristics_name, characteristics_categories)
values ('Производитель', 1),
       ('Сокет', 1),
       ('Максимальный объём ОЗУ', 1),
       ('Тактовая частота', 1),
       ('Производитель', 2),
       ('Диагональ', 2),
       ('Матрица', 2),
       ('Материал подставки', 2);

insert into product_name(name, product_category)
values ('Intel Core I9 9900', 1),
       ('AMD Ryzen 7 3700X', 1),
       ('Samsung MZ23U255', 2),
       ('AOC L215U266', 2);

insert into product_values(values, values_product, values_characteristics)
    values ('Intel', 1, 1),
       ('1151', 1, 2),
       ('128Gb', 1, 3),
       ('4.8MHz', 1, 4),
       ('AMD', 1, 1),
       ('AM4', 1, 2),
       ('128Gb', 1, 3),
       ('4.6MHz', 1, 4),
       ('Samsung', 2, 5),
       ('23"', 2, 6),
       ('TN-Film', 2, 7),
       ('Пластик', 2, 8),
       ('AOC', 2, 5),
       ('21.5"', 2, 6),
       ('AH-IPS', 2, 7),
       ('Аллюминий', 2, 8);