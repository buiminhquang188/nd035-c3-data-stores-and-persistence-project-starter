CREATE TABLE pet
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255) NULL,
    age         TINYINT NULL,
    birth_date  DATE NULL,
    gender      VARCHAR(255) NULL,
    notes       VARCHAR(255) NULL,
    pet_type_id INT NULL,
    user_id     BIGINT NULL,
    CONSTRAINT pk_pet PRIMARY KEY (id)
);

CREATE TABLE pet_type
(
    id   INT AUTO_INCREMENT NOT NULL,
    type VARCHAR(255) NULL,
    CONSTRAINT pk_pet_type PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE schedule
(
    start_time DATETIME NULL,
    end_time   DATETIME NULL,
    pet_id     BIGINT NOT NULL,
    owner_id   BIGINT NOT NULL,
    CONSTRAINT pk_schedule PRIMARY KEY (pet_id, owner_id)
);

CREATE TABLE skill
(
    id   INT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_skill PRIMARY KEY (id)
);

CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255) NULL,
    phone_number VARCHAR(255) NULL,
    notes        VARCHAR(255) NULL,
    role_id      INT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_skill
(
    user_id  BIGINT NOT NULL,
    skill_id INT    NOT NULL,
    CONSTRAINT pk_user_skill PRIMARY KEY (user_id, skill_id)
);

CREATE TABLE operation_time
(
    id          INT AUTO_INCREMENT NOT NULL,
    day_of_week VARCHAR(255) NULL,
    CONSTRAINT pk_operation_time PRIMARY KEY (id)
);

CREATE TABLE user_operation_time
(
    user_id           BIGINT NOT NULL,
    operation_time_id INT    NOT NULL,
    CONSTRAINT pk_user_operation_time PRIMARY KEY (user_id, operation_time_id)
);

ALTER TABLE pet_type
    ADD CONSTRAINT uc_pet_type_type UNIQUE (type);

ALTER TABLE pet
    ADD CONSTRAINT FK_PET_ON_PET_TYPE FOREIGN KEY (pet_type_id) REFERENCES pet_type (id);

ALTER TABLE pet
    ADD CONSTRAINT FK_PET_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_OWNER FOREIGN KEY (owner_id) REFERENCES user (id);

ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_PET FOREIGN KEY (pet_id) REFERENCES pet (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_ROLE FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE user_skill
    ADD CONSTRAINT FK_USER_SKILL_ON_SKILL FOREIGN KEY (skill_id) REFERENCES skill (id);

ALTER TABLE user_skill
    ADD CONSTRAINT FK_USER_SKILL_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_operation_time
    ADD CONSTRAINT FK_USER_OPERATION_TIME_ON_OPERATION_TIME FOREIGN KEY (operation_time_id) REFERENCES operation_time (id);

ALTER TABLE user_operation_time
    ADD CONSTRAINT FK_USER_OPERATION_TIME_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);
