INSERT INTO workshop (number) VALUES (16),(20),(35),(36),(40),(41),(47);

INSERT INTO inspection (date, general_score, total_score, workshop_id) VALUES
                                                   ('28.06', 'white', 'white', 1),
                                                   ('Обход не проводился', 'red', 'red', 1),
                                                   ('12.09', 'green', 'yellow', 1),
                                                   ('03.10', 'red', 'red', 2),
                                                   ('17.10', 'yellow', 'yellow', 2),
                                                   ('17.10', 'yellow', 'yellow', 3),
                                                   ('17.10', 'yellow', 'yellow', 3),
                                                   ('17.10', 'yellow', 'yellow', 4),
                                                   ('17.10', 'yellow', 'yellow', 4),
                                                   ('17.10', 'yellow', 'yellow', 5),
                                                   ('17.10', 'yellow', 'yellow', 5),
                                                   ('17.10', 'yellow', 'yellow', 6),
                                                   ('17.10', 'yellow', 'yellow', 6),
                                                   ('17.10', 'yellow', 'yellow', 7),
                                                   ('17.10', 'yellow', 'yellow', 7),
                                                   ('17.10', 'yellow', 'yellow', 7),
                                                   ('10.10', 'green', 'green', 3);

TRUNCATE TABLE inspection RESTART IDENTITY CASCADE;
TRUNCATE TABLE visit_history RESTART IDENTITY CASCADE;