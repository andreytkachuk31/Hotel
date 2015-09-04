USE hotel;

INSERT INTO users VALUES(1, 'petrov','petrov','Petrov','Petr', 3, 1);
INSERT INTO users VALUES(2, 'ivanov','ivanov','Ivanov','Ivan', 3, 1);
INSERT INTO users VALUES(3, 'sidorov','sidorov','Sidorov','Aleksandr', 3, 1);
INSERT INTO users VALUES(4, 'manager','manager','Tkachuk','Andrii', 2, 1);
INSERT INTO users VALUES(5, 'admin','admin','Geichenko','Iegor', 1, 1);

INSERT INTO user_roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_roles VALUES (2, 'ROLE_MANAGER');
INSERT INTO user_roles VALUES (3, 'ROLE_CLIENT');

INSERT INTO user_status VALUES (1, 'active');
INSERT INTO user_status VALUES (2, 'blocked');
INSERT INTO user_status VALUES (3, 'deleted');
	
INSERT INTO room_categories VALUES (1, 1);
INSERT INTO room_categories VALUES (2, 2);
INSERT INTO room_categories VALUES (3, 3);
INSERT INTO room_categories VALUES (4, 4);
INSERT INTO room_categories VALUES (5, 5);
	
INSERT INTO rooms VALUES(1,5,1,1,200,0);
INSERT INTO rooms VALUES(2,7,1,1,400,0);
INSERT INTO rooms VALUES(3,10,1,2,600,0);
INSERT INTO rooms VALUES(4,21,2,3,800,0);
INSERT INTO rooms VALUES(5,25,2,3,800,0);
INSERT INTO rooms VALUES(6,31,3,4,1000,0);
INSERT INTO rooms VALUES(7,33,3,4,1000,0);
INSERT INTO rooms VALUES(8,1,1,1,199,0);
INSERT INTO rooms VALUES(9,2,1,1,220,0);
INSERT INTO rooms VALUES(10,3,1,1,281,0);
INSERT INTO rooms VALUES(11,4,1,1,315,0);
INSERT INTO rooms VALUES(12,6,1,2,299,0);
INSERT INTO rooms VALUES(13,9,1,2,361,0);
INSERT INTO rooms VALUES(14,11,1,2,512,0);
INSERT INTO rooms VALUES(15,12,1,2,454,0);
INSERT INTO rooms VALUES(16,14,1,3,379,0);
INSERT INTO rooms VALUES(17,15,1,3,578,0);
INSERT INTO rooms VALUES(18,16,2,3,629,0);
INSERT INTO rooms VALUES(19,22,2,3,690,0);
INSERT INTO rooms VALUES(20,23,3,4,567,0);
INSERT INTO rooms VALUES(21,24,3,4,800,0);
INSERT INTO rooms VALUES(22,26,3,4,679,0);
INSERT INTO rooms VALUES(23,27,1,4,389,0);
INSERT INTO rooms VALUES(24,28,1,1,235,0);
INSERT INTO rooms VALUES(25,30,1,2,399,0);
INSERT INTO rooms VALUES(26,32,2,3,599,0);
INSERT INTO rooms VALUES(27,34,2,4,799,0);
INSERT INTO rooms VALUES(28,35,3,1,180,0);
INSERT INTO rooms VALUES(29,36,3,2,620,0);
INSERT INTO rooms VALUES(30,37,3,3,750,0);
INSERT INTO rooms VALUES(31,38,3,4,898,0);
