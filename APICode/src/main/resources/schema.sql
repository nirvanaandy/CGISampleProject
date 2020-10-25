drop table if exists account CASCADE 
drop table if exists account_transaction CASCADE 
drop sequence if exists hibernate_sequence
create sequence hibernate_sequence start with 1 increment by 1
create table account (account_number varchar(9) not null, account_currency varchar(3), account_name varchar(20), account_type varchar(10), account_user_id integer, available_balance decimal(19,2), balance_date timestamp, primary key (account_number))
create table account_transaction (id bigint not null, credit_amount decimal(19,2), debit_amount decimal(19,2), transaction_narrative varchar(50), transaction_type varchar(10), value_date timestamp, belonged_account varchar(9), primary key (id))
alter table account_transaction add constraint FKmjmnfh26sf8d6nvqlqi66v9jv foreign key (belonged_account) references account
