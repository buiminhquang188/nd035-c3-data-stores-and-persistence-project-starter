USE critter;

INSERT INTO pet_type(id, type)
VALUES (1, 'CAT'),
       (2, 'DOG'),
       (3, 'LIZARD'),
       (4, 'BIRD'),
       (5, 'FISH'),
       (6, 'SNAKE'),
       (7, 'OTHER');

INSERT INTO role(id, name)
VALUES (1, 'CUSTOMER'),
       (2, 'EMPLOYEE');

INSERT INTO skill(id, name)
VALUES (1, 'PETTING'),
       (2, 'WALKING'),
       (3, 'FEEDING'),
       (4, 'MEDICATING'),
       (5, 'SHAVING');

INSERT INTO operation_time(id, day_of_week)
VALUES (1, 'MONDAY'),
       (2, 'TUESDAY'),
       (3, 'WEDNESDAY'),
       (4, 'THURSDAY'),
       (5, 'FRIDAY'),
       (6, 'SATURDAY'),
       (7, 'SUNDAY');

INSERT INTO user (name, phone_number, notes, role_id)
VALUES ('Clark', '09091232211', 'Hello Clark', 1);
INSERT INTO user (name, phone_number, notes, role_id)
VALUES ('Bruce', '99221122331', 'Hello Bruce', 2);
INSERT INTO user (name, phone_number, notes, role_id)
VALUES ('Walker', null, null, 2);


INSERT INTO pet (id, name, age, birth_date, gender, notes, pet_type_id, user_id)
VALUES (1, 'Powder', 1, '2020-10-08', 'MALE', 'Hello Powder', 2, 1),
       (2, 'Bell', 1, '2023-10-01', 'FEMALE', 'Hello Bell', 1, 1);

INSERT INTO schedule (id, date)
VALUES (1, '2019-12-21 17:00:00'),
       (2, '2019-12-16 17:00:00');

INSERT INTO schedule_employee (schedule_id, user_id)
VALUES (1, 2),
       (2, 2),
       (1, 3),
       (2, 3);

INSERT INTO schedule_pet (pet_id, schedule_id)
VALUES (1, 1),
       (2, 1),
       (1, 2),
       (2, 2);

INSERT INTO schedule_skill (schedule_id, skill_id)
VALUES (2, 1),
       (2, 3),
       (1, 5);

INSERT INTO user_operation_time (user_id, operation_time_id)
VALUES (2, 1),
       (2, 2),
       (2, 7),
       (3, 1),
       (3, 2),
       (3, 7);

INSERT INTO user_skill (user_id, skill_id)
VALUES (3, 3),
       (3, 1),
       (2, 2),
       (2, 1);