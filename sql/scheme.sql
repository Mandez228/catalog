create table categories
(
    id serial8,
    category_name varchar not null,
    primary key (id)
);

create table characteristics
(
    id serial8,
    characteristics_name varchar not null,
    characteristics_categories bigint not null,
    primary key (id),
    foreign key (characteristics_categories) references categories (id)
);

create table product_name
(
    id serial8,
    name varchar not null,
    product_category bigint not null,
    primary key (id),
    foreign key (product_category) references categories (id)
);

create table product_values
(
    id serial8,
    values varchar not null,
    values_product bigint not null,
    values_characteristics bigint not null,
    primary key (id),
    foreign key (values_product) references product_name (id),
    foreign key (values_characteristics) references characteristics (id)
);