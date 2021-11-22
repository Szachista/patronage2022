
INSERT INTO SUBJECT (SUBJECT_NAME) VALUES
    ('subiekt 1'),
    ('subiekt 2'),
    ('subiekt 3');

INSERT INTO PARKING_SPACE (SPACE_NUMBER, SPACE_LEVEL, FOR_DISABLED) VALUES
    (1, 0, TRUE),
    (2, 0, FALSE),
    (3, 0, FALSE),
    (1, 1, TRUE),
    (2, 1, FALSE),
    (3, 1, FALSE),
    (1, 2, TRUE),
    (2, 2, FALSE),
    (3, 2, FALSE);

INSERT INTO RESERVATION (SUBJECT_ID, SPACE_ID) VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 7);