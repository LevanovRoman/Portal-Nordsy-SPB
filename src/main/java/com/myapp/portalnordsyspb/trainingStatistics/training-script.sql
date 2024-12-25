INSERT INTO weekday (name) VALUES ('Понедельник'), ('Вторник'),
                                   ('Среда'), ('Четверг'),
                                  ('Пятница');

INSERT INTO instructor (fio) VALUES ('Гордеев М.С.'), ('Колесник Я.В.'),
                                  ('Нагуманова А.Ф.'), ('Варданян И.И.'),
                                  ('Алексеева П.В.');

INSERT INTO period (interval, month, number) VALUES ('01.07 - 05.07', 'Июль', 27),
                                                    ('08.07 - 12.07', 'Июль', 28),
                                                    ('15.07 - 19.07', 'Июль', 29),
                                                    ('22.07 - 26.07', 'Июль', 30),
                                                    ('29.07 - 02.08', 'Июль', 31),
                                                    ('05.08 - 09.08', 'Август', 32),
                                                    ('12.08 - 16.08', 'Август', 33);

INSERT INTO period (interval, month, number) VALUES ('01.07 - 05.07', 'Июль', 34),
                                                    ('08.07 - 12.07', 'Июль', 35),
                                                    ('15.07 - 19.07', 'Июль', 36),
                                                    ('22.07 - 26.07', 'Июль', 37),
                                                    ('29.07 - 02.08', 'Июль', 38),
                                                    ('05.08 - 09.08', 'Август', 39),
                                                    ('05.08 - 09.08', 'Август', 40),
                                                    ('05.08 - 09.08', 'Август', 41),
                                                    ('05.08 - 09.08', 'Август', 42),
                                                    ('12.08 - 16.08', 'Август', 43);

INSERT INTO direction (hours, name, remark) VALUES (2, 'Основы ПС', ''),
                                                         (1, 'СВМ', ''),
                                                         (1, 'Зона красных ярлыков', ''),
                                                         (4, 'Судостроение 4.0', 'в разработке'),
                                                         (2, 'ТРМ', 'в разработке'),
                                                         (1, 'Быстрая переналадка', 'перспективная'),
                                                         (1.5, 'ППД', '');

INSERT INTO direction_instructor (direction_id, instructor_id) VALUES (1, 1), (2, 1), (3, 1), (4, 1), (4, 2), (5, 1),
                                                                      (6, 1), (7, 1), (7, 3);

INSERT INTO unit(direction_id, period_id, weekday_id) VALUES
                                (1, 1, 1), (1, 1, 2), (1, 1, 3), (1, 1, 4), (1, 1, 5),
                                (1, 2, 1), (1, 2, 2), (1, 2, 3), (1, 2, 4), (1, 2, 5),
                                (1, 3, 1), (1, 3, 2), (1, 3, 3), (1, 3, 4), (1, 3, 5),
                                (1, 4, 1), (1, 4, 2), (1, 4, 3), (1, 4, 4), (1, 4, 5),
                                (1, 5, 1), (1, 5, 2), (1, 5, 3), (1, 5, 4), (1, 5, 5),
                                (1, 6, 1), (1, 6, 2), (1, 6, 3), (1, 6, 4), (1, 6, 5),
                                (1, 7, 1), (1, 7, 2), (1, 7, 3), (1, 7, 4), (1, 7, 5);

TRUNCATE TABLE unit RESTART IDENTITY CASCADE;
TRUNCATE TABLE unit_details RESTART IDENTITY CASCADE;
TRUNCATE TABLE unit_values RESTART IDENTITY CASCADE;
TRUNCATE TABLE details_persons RESTART IDENTITY CASCADE;

TRUNCATE TABLE direction RESTART IDENTITY CASCADE;
TRUNCATE TABLE direction_instructor RESTART IDENTITY CASCADE;
TRUNCATE TABLE instructor RESTART IDENTITY CASCADE;

INSERT INTO unit_values(unit_id, values) VALUES (1, 40),(2, 40),(3, 40),(3, 50),(4, 40),(5, 40),(6, 40),(7, 40),
                                                (8, 40),(9, 40),(10, 40),(11, 40),(12, 40),(13, 40),(14, 40),
                                                (15, 40),(16, 40),(16, 50),(17, 40),(18, 40),(19, 40),(20, 40),(21, 40),
                                                (22, 40),(23, 40),(24, 40),(25, 40),(26, 40),(27, 40),(28, 40),
                                                (29, 40),(30, 40),(31, 40),(32, 40),(33, 40),(33, 70),(34, 40),(35, 40);

INSERT INTO unit_details(date, unit_id)
   VALUES ('2024-11-21', 6);

INSERT INTO details_persons(unit_details_id, persons) VALUES (2, '10101,Николаев И.И,Руководитель'),
                                                             (2, '10102,Петров И.И,Руководитель'),
                                                             (2, '10103,Сидоров И.И,Руководитель');

SELECT unit_id FROM unit_details ud JOIN details_persons dp on ud.id = dp.unit_details_id WHERE dp.persons LIKE '10103%';

SELECT * FROM unit JOIN public.unit_details ud on unit.id = ud.unit_id JOIN public.details_persons dp on ud.id = dp.unit_details_id
         WHERE direction_id=1 AND period_id=1 AND weekday_id=1 AND dp.persons LIKE '10103%';