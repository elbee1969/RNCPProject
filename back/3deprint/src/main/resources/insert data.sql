USE `eprint3d`; 

/* By default mysql is in autocommit mode, put to false during the script execution*/
SET autocommit=0; 

 /* characters set for inserted datas */
SET NAMES utf8mb4;


INSERT INTO customuser (id, email, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled, firstname, lastname)
	VALUES (1,'charles.darwin@gmail.com','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','darwin','T','T','T','T','Charles','DARWIN'),
	(2,'albert.einstein@gmail.com','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','einstein','T','T','T','T','Albert','EINSTEIN'),
    (3,'william.shakespeare@gmail.com','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','shakespeare','T','T','T','T','William','SHAKESPEARE');
	
INSERT INTO role (id, code, default_role) VALUES (1,'ROLE_USER','T'), (2,'ROLE_ADMIN','F');

INSERT INTO user_role (user_id, role_id) VALUES (1,1),(3,1),(2,2),(3,2);

INSERT INTO album (id, user_id) VALUES (1,1),(2,2),(3,3);

 /* Validation of the inserted datas */
COMMIT;

/* end of the script set autocommit to true */
SET autocommit=1; 