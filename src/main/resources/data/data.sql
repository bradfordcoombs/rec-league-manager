INSERT INTO team (name) VALUES ('Hornets');
SET @lastInsertedID = LAST_INSERTED_ID();
INSERT INTO team (name) VALUES ('Bulls');
INSERT INTO team (name) VALUES ('Bucks');
INSERT INTO team (name) VALUES ('Hawks');
INSERT INTO team (name) VALUES ('Magic');
INSERT INTO team (name) VALUES ('Knicks');
INSERT INTO team (name) VALUES ('Cletics');
INSERT INTO team (name) VALUES ('76ers');
INSERT INTO team (name) VALUES ('Lakers');
INSERT INTO team (name) VALUES ('Pacers');


INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('LaMelo', 'Ball', '15555555', 'lamelo@hornets.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Brandon', 'Miller', '15555555', 'brandon@hornets.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Cody', 'Martin', '15555555', 'cody@hornets.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Mark', 'Williams', '15555555', 'mar@hornetes.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Nick', 'Richards', '15555555', 'nrichards@hornets.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Nick', 'Smith', '15555555', 'nsmith@hornets.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Grant', 'Williams', '15555555', 'grant@hornets.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Seth', 'Curry', '15555555', 'set@hornets.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Tre', 'Mann', '15555555', 'tre@hornets.com', @lastInsertedID)
INSERT INTO player (firstName, lastName, phone, email, team_id) VALUES ('Bryce', 'McGowens', '15555555', 'bryce@hornets.com', @lastInsertedID)
