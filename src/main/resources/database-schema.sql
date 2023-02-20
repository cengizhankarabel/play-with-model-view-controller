
use todosdb;

CREATE TABLE accounts(
	id int PRIMARY KEY auto_increment,
    First_Name varchar(100) NOT NULL,
    Last_Name varchar(100) NOT NULL,
    Email_Address varchar(100) NOT NULL unique,
    Password varchar(100) NOT NULL
);

CREATE TABLE todos(
	id int PRIMARY KEY auto_increment,
    Title varchar(100) NOT NULL,
    Completed boolean not null default 0,
    account_id int REFERENCES account(id)
);


//password 12345
INSERT INTO accounts(id,First_Name,Last_Name,Email_Address,Password) values (1,'james','steve','james@steve.com','$2a$12$1TlZZ4As4duscsV7wyklqOyCTOUNFf9UcS.W7cmR/UGWQPHa.Gs8a');
INSERT INTO accounts(id,First_Name,Last_Name,Email_Address,Password) values (2,'stephen','andrew','stephen@andrew.com','$2a$12$1TlZZ4As4duscsV7wyklqOyCTOUNFf9UcS.W7cmR/UGWQPHa.Gs8a');
INSERT INTO accounts(id,First_Name,Last_Name,Email_Address,Password) values (3,'han','kbl','han@kbl.com','$2a$12$1TlZZ4As4duscsV7wyklqOyCTOUNFf9UcS.W7cmR/UGWQPHa.Gs8a');

INSERT INTO todos(id,Title,Completed,account_id) values (1,'learn java spring',false,3);
INSERT INTO todos(id,Title,Completed,account_id) values (2,'learn jdbc',false,3);
INSERT INTO todos(id,Title,Completed,account_id) values (3,'learn jpa',false,3);

INSERT INTO todos(id,Title,Completed,account_id) values (1,'make food',false,1);
INSERT INTO todos(id,Title,Completed,account_id) values (1,'go to shopping center',false,1);


INSERT INTO todos(id,Title,Completed,account_id) values (1,'buy laptop',false,2);
