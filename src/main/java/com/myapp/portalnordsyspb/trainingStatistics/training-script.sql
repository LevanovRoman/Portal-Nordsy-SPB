INSERT INTO weekday (name) VALUES ('День 1'), ('День 2'),
                                   ('День 3'), ('День 4'),
                                  ('День 5');

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

INSERT INTO direction (name, hours, remark) VALUES ('Основы ПС', 2.0, ''),
                                                         ('СВМ', 1.0, ''),
                                                         ('Зона красных ярлыков', 1.0, ''),
                                                         ('Судостроение 4.0', 1.0, 'в разработке'),
                                                         ('ТРМ', 2.0, 'в разработке'),
                                                         ('Быстрая переналадка', 1.0, 'перспективная'),
                                                         ('ППД', 1.5, '');

INSERT INTO direction_instructor (direction_id, instructor_id) VALUES (1, 1), (2, 1), (3, 1), (4, 1), (4, 2), (5, 1),
                                                                      (6, 1), (7, 1), (7, 3);

INSERT INTO unit(value, direction_id, period_id, weekday_id) VALUES
                                (40, 1, 1, 1), (40, 1, 1, 2), (40, 1, 1, 3), (40, 1, 1, 4), (40, 1, 1, 5),
                                (40, 1, 2, 1), (40, 1, 2, 2), (40, 1, 2, 3), (40, 1, 2, 4), (40, 1, 2, 5),
                                (40, 1, 3, 1), (40, 1, 3, 2), (40, 1, 3, 3), (40, 1, 3, 4), (40, 1, 3, 5),
                                (40, 1, 4, 1), (40, 1, 4, 2), (40, 1, 4, 3), (40, 1, 4, 4), (40, 1, 4, 5),
                                (40, 1, 5, 1), (40, 1, 5, 2), (40, 1, 5, 3), (40, 1, 5, 4), (40, 1, 5, 5),
                                (40, 1, 6, 1), (40, 1, 6, 2), (40, 1, 6, 3), (40, 1, 6, 4), (40, 1, 6, 5),
                                (40, 1, 7, 1), (40, 1, 7, 2), (40, 1, 7, 3), (40, 1, 7, 4), (40, 1, 7, 5);

TRUNCATE TABLE unit RESTART IDENTITY CASCADE

INSERT INTO unit_details(date, full_name, position, tab_number, unit_id)
   VALUES ('21.11.2024', 'Иванов И.И.', 'Руководитель', '10101', 1);