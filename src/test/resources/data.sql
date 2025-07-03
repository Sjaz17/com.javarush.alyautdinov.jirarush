---------  users ----------------------
DELETE
FROM USER_ROLE;
DELETE
FROM CONTACT;
DELETE
FROM PROFILE;

DELETE
FROM ACTIVITY;
DELETE
FROM TASK_TAG;
DELETE
FROM USER_BELONG;
DELETE
FROM ATTACHMENT;
DELETE
FROM TASK;
DELETE
FROM SPRINT;
DELETE
FROM PROJECT;

DELETE
FROM MAIL_CASE;
DELETE
FROM REFERENCE;

DELETE
FROM USERS;

INSERT INTO USERS (ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DISPLAY_NAME)
VALUES (1, 'user@gmail.com', '{noop}password', 'userFirstName', 'userLastName', 'userDisplayName'),
       (2, 'admin@gmail.com', '{noop}admin', 'adminFirstName', 'adminLastName', 'adminDisplayName'),
       (3, 'guest@gmail.com', '{noop}guest', 'guestFirstName', 'guestLastName', 'guestDisplayName'),
       (4, 'manager@gmail.com', '{noop}manager', 'managerFirstName', 'managerLastName', 'managerDisplayName');

INSERT INTO USER_ROLE (USER_ID, ROLE)
VALUES (1, 0),
       (2, 0),
       (2, 1),
       (4, 2);

INSERT INTO PROFILE (ID, LAST_FAILED_LOGIN, LAST_LOGIN, MAIL_NOTIFICATIONS)
VALUES (1, NULL, NULL, 49),
       (2, NULL, NULL, 14);

-- ВАЖНОЕ ИСПРАВЛЕНИЕ ЗДЕСЬ:


INSERT INTO PROJECT (ID, code, title, description, type_code, parent_id)
VALUES (1, 'PR1', 'PROJECT-1', 'test project 1', 'task_tracker', NULL),
       (2, 'PR2', 'PROJECT-2', 'test project 2', 'task_tracker', 1);

INSERT INTO SPRINT (ID, status_code, startpoint, endpoint, code, project_id)
VALUES (1, 'finished', '2023-05-01 08:05:10', '2023-05-07 17:10:01', 'SP-1.001', 1),
       (2, 'active', '2023-05-01 08:06:00', NULL, 'SP-1.002', 1),
       (3, 'active', '2023-05-01 08:07:00', NULL, 'SP-1.003', 1),
       (4, 'planning', '2023-05-01 08:08:00', NULL, 'SP-1.004', 1),
       (5, 'active', '2023-05-10 08:06:00', NULL, 'SP-2.001', 2),
       (6, 'planning', '2023-05-10 08:07:00', NULL, 'SP-2.002', 2),
       (7, 'planning', '2023-05-10 08:08:00', NULL, 'SP-2.003', 2);

INSERT INTO TASK (ID, TITLE, TYPE_CODE, STATUS_CODE, PROJECT_ID, SPRINT_ID, STARTPOINT)
VALUES (1, 'Data', 'epic', 'in_progress', 1, 1, '2023-05-15 09:05:10'),
       (2, 'Trees', 'epic', 'in_progress', 1, 1, '2023-05-15 12:05:10'),
       (3, 'task-3', 'task', 'ready_for_test', 2, 5, '2023-06-14 09:28:10'),
       (4, 'task-4', 'task', 'ready_for_review', 2, 5, '2023-06-14 09:28:10'),
       (5, 'task-5', 'task', 'todo', 2, 5, '2023-06-14 09:28:10'),
       (6, 'task-6', 'task', 'done', 2, 5, '2023-06-14 09:28:10'),
       (7, 'task-7', 'task', 'canceled', 2, 5, '2023-06-14 09:28:10');

INSERT INTO ACTIVITY(ID, AUTHOR_ID, TASK_ID, UPDATED, COMMENT, TITLE, DESCRIPTION, ESTIMATE, TYPE_CODE, STATUS_CODE,
                     PRIORITY_CODE) -- Добавил ID
VALUES (1, 1, 1, '2023-05-15 09:05:10', NULL, 'Data', NULL, 3, 'epic', 'in_progress', 'low'),
       (2, 2, 1, '2023-05-15 12:25:10', NULL, 'Data', NULL, NULL, NULL, NULL, 'normal'),
       (3, 1, 1, '2023-05-15 14:05:10', NULL, 'Data', NULL, 4, NULL, NULL, NULL),
       (4, 1, 2, '2023-05-15 12:05:10', NULL, 'Trees', 'Trees desc', 4, 'epic', 'in_progress', 'normal');

INSERT INTO USER_BELONG (ID, OBJECT_ID, OBJECT_TYPE, USER_ID, USER_TYPE_CODE, STARTPOINT, ENDPOINT)
VALUES
    (101, 1, 2, 2, 'task_developer', '2023-06-14 08:35:10', '2023-06-14 08:55:00'),
    (102, 1, 2, 2, 'task_reviewer', '2023-06-14 09:35:10', NULL),
    (103, 1, 2, 1, 'task_developer', '2023-06-12 11:40:00', '2023-06-12 12:35:00'),
    (105, 1, 2, 1, 'task_tester', '2023-06-14 15:20:00', NULL),
    (106, 2, 2, 2, 'task_developer', '2023-06-08 07:10:00', NULL),
    (107, 2, 2, 1, 'task_developer', '2023-06-09 14:48:00', NULL),
    (108, 2, 2, 1, 'task_tester', '2023-06-10 16:37:00', NULL);
ALTER TABLE SPRINT ALTER COLUMN ID RESTART WITH 8;
ALTER TABLE PROJECT ALTER COLUMN ID RESTART WITH 3;
ALTER TABLE USERS ALTER COLUMN ID RESTART WITH 5;