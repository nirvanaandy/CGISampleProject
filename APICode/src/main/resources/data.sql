/*
-- Query: SELECT * FROM mytestdb.account
LIMIT 0, 1000

-- Date: 2020-09-16 09:58
*/
INSERT INTO `account` (`account_number`,`account_currency`,`account_name`,`account_type`,`account_user_id`,`available_balance`,`balance_date`) VALUES ('585309209','USD','SGSavings726','Savings',123,84231.25,'2020-08-31 14:00:00.000000');
INSERT INTO `account` (`account_number`,`account_currency`,`account_name`,`account_type`,`account_user_id`,`available_balance`,`balance_date`) VALUES ('585309210','AUD','AUCurrent374','Current',123,74231.25,'2020-07-31 14:00:00.000000');
INSERT INTO `account` (`account_number`,`account_currency`,`account_name`,`account_type`,`account_user_id`,`available_balance`,`balance_date`) VALUES ('585309212','AUD','AUCurrent444','Current',123,44231.25,'2020-03-31 13:00:00.000000');
INSERT INTO `account` (`account_number`,`account_currency`,`account_name`,`account_type`,`account_user_id`,`available_balance`,`balance_date`) VALUES ('589309265','AUD','AUSaving447','Savings',123,94231.25,'2019-03-31 13:00:00.000000');
INSERT INTO `account` (`account_number`,`account_currency`,`account_name`,`account_type`,`account_user_id`,`available_balance`,`balance_date`) VALUES ('768949180','AUD','AUCurrent754','Current',123,88231.25,'2018-08-10 14:00:00.000000');
INSERT INTO `account` (`account_number`,`account_currency`,`account_name`,`account_type`,`account_user_id`,`available_balance`,`balance_date`) VALUES ('793949180','AUD','AUCurrent754','Current',123,88231.25,'2018-08-10 14:00:00.000000');
INSERT INTO `account` (`account_number`,`account_currency`,`account_name`,`account_type`,`account_user_id`,`available_balance`,`balance_date`) VALUES ('847749180','SGD','SGSaving323','Savings',123,38231.25,'2017-07-10 14:00:00.000000');
INSERT INTO `account` (`account_number`,`account_currency`,`account_name`,`account_type`,`account_user_id`,`available_balance`,`balance_date`) VALUES ('997749180','SGD','SGCurrent323','Current',123,78231.25,'2017-03-16 13:00:00.000000');

/*
-- Query: SELECT * FROM mytestdb.account_transaction
LIMIT 0, 1000

-- Date: 2020-09-16 09:58
*/
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (18,0.00,112.12,'Memo content','Debit','2020-08-01 14:00:00.000000','589309265');
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (26,0.00,212.12,'Memo content','Debit','2018-08-01 14:00:00.000000','585309209');
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (27,272.13,0.00,'Memo content','Credit','2016-09-01 14:00:00.000000','585309209');
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (28,1272.13,0.00,'Memo content','Credit','2017-10-01 13:00:00.000000','585309209');
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (29,0.00,2390.12,'Memo content','Debit','2019-11-01 13:00:00.000000','585309209');
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (30,0.00,233.10,'Memo content1','Debit','2019-09-02 00:00:00.000000','585309209');
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (31,444.14,0.00,'Memo Cotent2','Credit','2019-03-01 00:00:00.000000','585309209');
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (32,111.32,0.00,'Memo Content32','Credit','2012-05-01 00:00:00.000000','585309209');
INSERT INTO `account_transaction` (`id`,`credit_amount`,`debit_amount`,`transaction_narrative`,`transaction_type`,`value_date`,`belonged_account`) VALUES (33,0.00,23.42,'Memo Content33','Debit','2013-06-02 00:00:00.000000','585309209');
