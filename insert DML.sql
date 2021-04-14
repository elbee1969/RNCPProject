/* the schema  on witch to execute the script */
 -- --------------------------------------------------------
 -- Insert role into database 
 -- -------------------------------------------------------

USE `eprintdatabase`; 
 
-- By default mysql is in autocommit mode, putted to false during the script execution*/
SET autocommit=0; 

-- characters set for inserted datas */
SET NAMES utf8mb4;

-- role -> 1 for user setted to true by default, 2 for admin, setted to flase by default
INSERT INTO roles (id, code, default_role) VALUES (1,'ROLE_USER','T'), (2,'ROLE_ADMIN','F');

-- Validation of the inserted datas */
COMMIT;
INSERT INTO addresses (id) VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15);
-- Validation of the inserted datas */
COMMIT;

-- insert of 15 users
INSERT INTO custom_users (id, account_non_expired, account_non_locked, credentials_non_expired, email, enabled, firstname, lastname, password, username, address_id)
	VALUES (1,'T','T','T','laurent.berthelot1969@gmail.com','T','Laurent','BERTHELOT','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','admin',1),
    (2,'T','T','T','charles.darwin@gmail.com','T','Charles','DARWIN','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','darwin',2),
	(3,'T','T','T','albert.einstein@gmail.com','T','Albert','EINSTEIN','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','einstein',3),
    (4,'T','T','T','william.shakespeare@gmail.com','T','William','SHAKESPEARE','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','shakespeare',4),
    (5,'T','T','T','jane.doe@gmail.com','T','Jane','DOE','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','jane',5),
    (6,'T','T','T','jo.kari@gmail.com','T','Jo','KARI','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','jo',6),
    (7,'T','T','T','paul.ochon@gmail.com','T','Paul','Ochon','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','paul',7),
    (8,'T','T','T','john.atan@gmail.com','T','John','ATAN','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','john',8),
    (9,'T','T','T','marie.popins@gmail.com','T','Marie','POPINS','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','marie',9),
    (10,'T','T','T','anne.norexique@gmail.com','T','Anne','NOREXIQUE','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','anne',10),
    (11,'T','T','T','julie.beration@gmail.com','T','Julie','BERATION','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','julie',11),
    (12,'T','T','T','phil.anthrope@gmail.com','T','Phil','ANTHROPE','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','phil',12),
    (13,'T','T','T','sylvie.ticol@gmail.com','T','Sylvie','TICOL','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','sylvie',13),
    (14,'T','T','T','pierre.quiroule@gmail.com','T','Pierre','QUIROULE','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','pierre',14),
    (15,'T','T','T','tom.ates@gmail.com','T','Tom','ATES','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','tom',15);

-- Validation of the inserted datas */
COMMIT;
-- set of the roles (user 1 = admin others = customer)
INSERT INTO user_role (user_id, role_id) VALUES (1,2),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1);
-- Validation of the inserted datas */
COMMIT;
-- end of the script setted back autocommit to true */
SET autocommit=1; 
