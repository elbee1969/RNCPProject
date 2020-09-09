-- decoded password = $2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi
-- encoded password = password

INSERT INTO user (id, account_non_expired, firstname, lastname, password, username, email, account_non_locked, credentials_non_expired, enabled, album_id)
	VALUES (1,'T','Charles','DARWIN','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','darwin','charles.darwin@gamil.com','T','T','T',1),
	(2,'T','Albert','EINSTEIN','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','einstein','albert.einstein@gamil.com','T','T','T',2),
    (3,'T','William','SHAKESPEARE','$2a$10$VdQQpPC9NPCf4AF96aedIOpQ6/5NYoyBzX4a.ncqyanBcS5joxNWi','shakespeare','william.shakespeare@gamil.com','T','T','T',3);
	
INSERT INTO role (id, code, default_role) VALUES (1,'ROLE_USER','T'), (2,'ROLE_ADMIN','F');

INSERT INTO user_role (user_id, role_id) VALUES (1,1),(3,1),(2,2),(3,2);

INSERT INTO album (id, user_id) VALUES (1,1),(2,2),(3,3);
