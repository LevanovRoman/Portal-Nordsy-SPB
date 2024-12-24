INSERT INTO inspection (date, department, general_score, total_score) VALUES
                                                   ('28.06', 20, 'white', 'white'),
                                                   ('Обход не проводился', 35, 'red', 'red'),
                                                   ('12.09', 36, 'green', 'yellow'),
                                                   ('03.10', 40, 'red', 'red'),
                                                   ('17.10', 41, 'yellow', 'yellow'),
                                                   ('10.10', 47, 'green', 'green');

TRUNCATE TABLE inspection RESTART IDENTITY CASCADE;
TRUNCATE TABLE visit_history RESTART IDENTITY CASCADE;