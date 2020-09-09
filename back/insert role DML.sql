/* the schema  on witch to execute the script */
USE `eprintdb`; 

/* By default mysql is in autocommit mode, put to false during the script execution*/
SET autocommit=0; 

 /* characters set for inserted datas */
SET NAMES utf8mb4;
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_SUPERVISOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
/* Validation of the inserted datas */
COMMIT;

/* end of the script set autocommit to true */
SET autocommit=1; 