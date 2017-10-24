create table categories(id int primary key, name varchar(255))
insert into categories values(1, 'Pizza')
insert into categories values(2, 'Pasta')
insert into categories values(3, 'Desert')
create table customers(id int primary key, name varchar(255), address varchar(255), phone varchar(255), city varchar(255))
insert into customers values(1, 'John D', 'Johns address', '099229922', 'New York');
insert into customers values(2, 'Jane D', 'Janes address', '088118811', 'New York');
insert into customers values(3, 'Tom S', 'Toms address', '088338833', 'Los Angeles');
insert into customers values(4, 'Tim S', 'Tims address', '088121212', 'Los Angeles');
insert into customers values(5, 'Dave P', 'Daves address', '088445511' , 'New York');
insert into customers values(6, 'Jean M', 'Jeans address', '012983333', 'New York');
insert into customers values(7, 'Monique N', 'Moniques address', '033778822', 'Los Angeles');
create table products(id int primary key, name varchar(255), category_id int)
insert into products values(1, 'Pizza carbonara', 1)
insert into products values(2, 'Pizza quatro stagioni', 1)
insert into products values(3, 'Pizza with no cheese', 1)
insert into products values(4, 'Spaghetti carbonara', 2)
insert into products values(5, 'Penne carbonara', 2)
insert into products values(6, 'Muffin', 3)
insert into products values(7, 'Cake', 3)	
create table orders(id int primary key, order_date date, customer_id int, product_id int)	