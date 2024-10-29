INSERT INTO department (number) VALUES (16), (20), (36), (40), (41), (47);
INSERT INTO area (name, department_id)
VALUES
    ('Участок комплектации и ЗИП', 1),
    ('Участок изготовления и монтажа труб', 2),
    ('Сварочный участок', 2),
    ('Участок изоляции и гидравлических испытаний', 2),
    ('Участок сборочно-сварочный №11', 3),
    ('Участок монтажа судовой вентиляции №5', 4),
    ('Участок заготовки изоляции', 5),
    ('Участок деревообработки', 5),
    ('Участок изготовления узлов, фундаментов и деталей машиностроительной части', 6);
INSERT INTO criterion (name) VALUES ('Общая оценка'), ('ОТ/ПБ'), ('5С'), ('ТРМ'), ('КПСЦ'), ('СВМ');
INSERT INTO week (week_name) VALUES ('31 - 2024'), ('32 - 2024'), ('33 - 2024'), ('34 - 2024');
#                                                    участок, критерий, неделя, значение
INSERT INTO result (area_id, criterion_id, week_id, value) VALUES (1, 1, 1, 0), (1, 1, 2, 0), (1, 1, 3, 0), (1, 1, 4, 0),
                                                                  (1, 2, 1, 0), (1, 2, 2, 0), (1, 2, 3, 0), (1, 2, 4, 0),
                                                                  (1, 3, 1, 0), (1, 3, 2, 0), (1, 3, 3, 0), (1, 3, 4, 0),
                                                                  (1, 4, 1, 0), (1, 4, 2, 0), (1, 4, 3, 0), (1, 4, 4, 0),
                                                                  (1, 5, 1, 0), (1, 5, 2, 0), (1, 5, 3, 0), (1, 5, 4, 0),
                                                                  (1, 6, 1, 0), (1, 6, 2, 0), (1, 6, 3, 0), (1, 6, 4, 0),

                                                                  (2, 1, 1, 28), (2, 1, 2, 42), (2, 1, 3, 46), (2, 1, 4, 46),
                                                                  (2, 2, 1, 40), (2, 2, 2, 60), (2, 2, 3, 80), (2, 2, 4, 80),
                                                                  (2, 3, 1, 0), (2, 3, 2, 0), (2, 3, 3, 0), (2, 3, 4, 0),
                                                                  (2, 4, 1, 0), (2, 4, 2, 0), (2, 4, 3, 0), (2, 4, 4, 0),
                                                                  (2, 5, 1, 50), (2, 5, 2, 75), (2, 5, 3, 75), (2, 5, 4, 75),
                                                                  (2, 6, 1, 50), (2, 6, 2, 75), (2, 6, 3, 75), (2, 6, 4, 75),

                                                                  (3, 1, 1, 11), (3, 1, 2, 29), (3, 1, 3, 33), (3, 1, 4, 33),
                                                                  (3, 2, 1, 20), (3, 2, 2, 60), (3, 2, 3, 80), (3, 2, 4, 80),
                                                                  (3, 3, 1, 0), (3, 3, 2, 0), (3, 3, 3, 0), (3, 3, 4, 0),
                                                                  (3, 4, 1, 33), (3, 4, 2, 33), (3, 4, 3, 33), (3, 4, 4, 33),
                                                                  (3, 5, 1, 0), (3, 5, 2, 50), (3, 5, 3, 50), (3, 5, 4, 50),
                                                                  (3, 6, 1, 0), (3, 6, 2, 0), (3, 6, 3, 0), (3, 6, 4, 0),

                                                                  (4, 1, 1, 8), (4, 1, 2, 27), (4, 1, 3, 31), (4, 1, 4, 31),
                                                                  (4, 2, 1, 40), (4, 2, 2, 60), (4, 2, 3, 80), (4, 2, 4, 80),
                                                                  (4, 3, 1, 0), (4, 3, 2, 0), (4, 3, 3, 0), (4, 3, 4, 0),
                                                                  (4, 4, 1, 0), (4, 4, 2, 0), (4, 4, 3, 0), (4, 4, 4, 0),
                                                                  (4, 5, 1, 0), (4, 5, 2, 75), (4, 5, 3, 75), (4, 5, 4, 75),
                                                                  (4, 6, 1, 0), (4, 6, 2, 0), (4, 6, 3, 0), (4, 6, 4, 0),

                                                                  (5, 1, 1, 15), (5, 1, 2, 15), (5, 1, 3, 20), (5, 1, 4, 25),
                                                                  (5, 2, 1, 40), (5, 2, 2, 40), (5, 2, 3, 40), (5, 2, 4, 40),
                                                                  (5, 3, 1, 0), (5, 3, 2, 0), (5, 3, 3, 0), (5, 3, 4, 0),
                                                                  (5, 4, 1, 33), (5, 4, 2, 33), (5, 4, 3, 33), (5, 4, 4, 33),
                                                                  (5, 5, 1, 0), (5, 5, 2, 0), (5, 5, 3, 25), (5, 5, 4, 25),
                                                                  (5, 6, 1, 0), (5, 6, 2, 0), (5, 6, 3, 0), (5, 6, 4, 25),

                                                                  (6, 1, 1, 20), (6, 1, 2, 23), (6, 1, 3, 18), (6, 1, 4, 18),
                                                                  (6, 2, 1, 40), (6, 2, 2, 33), (6, 2, 3, 33), (6, 2, 4, 33),
                                                                  (6, 3, 1, 0), (6, 3, 2, 0), (6, 3, 3, 0), (6, 3, 4, 0),
                                                                  (6, 4, 1, 33), (6, 4, 2, 33), (6, 4, 3, 33), (6, 4, 4, 33),
                                                                  (6, 5, 1, 0), (6, 5, 2, 0), (6, 5, 3, 0), (6, 5, 4, 0),
                                                                  (6, 6, 1, 25), (6, 6, 2, 50), (6, 6, 3, 25), (6, 6, 4, 25),

                                                                  (7, 1, 1, 4), (7, 1, 2, 25), (7, 1, 3, 25), (7, 1, 4, 25),
                                                                  (7, 2, 1, 20), (7, 2, 2, 40), (7, 2, 3, 40), (7, 2, 4, 20),
                                                                  (7, 3, 1, 0), (7, 3, 2, 0), (7, 3, 3, 0), (7, 3, 4, 0),
                                                                  (7, 4, 1, 0), (7, 4, 2, 33), (7, 4, 3, 33), (7, 4, 4, 33),
                                                                  (7, 5, 1, 0), (7, 5, 2, 25), (7, 5, 3, 25), (7, 5, 4, 25),
                                                                  (7, 6, 1, 0), (7, 6, 2, 25), (7, 6, 3, 25), (7, 6, 4, 25),

                                                                  (8, 1, 1, 13), (8, 1, 2, 18), (8, 1, 3, 30), (8, 1, 4, 15),
                                                                  (8, 2, 1, 40), (8, 2, 2, 40), (8, 2, 3, 40), (8, 2, 4, 40),
                                                                  (8, 3, 1, 0), (8, 3, 2, 0), (8, 3, 3, 0), (8, 3, 4, 0),
                                                                  (8, 4, 1, 0), (8, 4, 2, 0), (8, 4, 3, 33), (8, 4, 4, 0),
                                                                  (8, 5, 1, 25), (8, 5, 2, 25), (8, 5, 3, 25), (8, 5, 4, 25),
                                                                  (8, 6, 1, 0), (8, 6, 2, 25), (8, 6, 3, 50), (8, 6, 4, 25),

                                                                  (9, 1, 1, 20), (9, 1, 2, 20), (9, 1, 3, 31), (9, 1, 4, 15),
                                                                  (9, 2, 1, 40), (9, 2, 2, 40), (9, 2, 3, 20), (9, 2, 4, 10),
                                                                  (9, 3, 1, 0), (9, 3, 2, 0), (9, 3, 3, 0), (9, 3, 4, 0),
                                                                  (9, 4, 1, 33), (9, 4, 2, 33), (9, 4, 3, 33), (9, 4, 4, 0),
                                                                  (9, 5, 1, 0), (9, 5, 2, 0), (9, 5, 3, 50), (9, 5, 4, 25),
                                                                  (9, 6, 1, 25), (9, 6, 2, 25), (9, 6, 3, 50), (9, 6, 4, 0);


--------------------------------------
INSERT INTO week (week_name) VALUES ('35 - 2024');

INSERT INTO result (area_id, criterion_id, week_id, value)
VALUES (1, 1, 7, 0), (1, 2, 7, 0), (1, 3, 7, 0), (1, 4, 7, 0),(1, 5, 7, 0), (1, 6, 7, 0),
       (2, 1, 7, 0), (2, 2, 7, 0), (2, 3, 7, 0), (2, 4, 7, 0),(2, 5, 7, 0), (2, 6, 7, 0),
       (3, 1, 7, 0), (3, 2, 7, 0), (3, 3, 7, 0), (3, 4, 7, 0),(3, 5, 7, 0), (3, 6, 7, 0),
       (4, 1, 7, 0), (4, 2, 7, 0), (4, 3, 7, 0), (4, 4, 7, 0),(4, 5, 7, 0), (4, 6, 7, 0),
       (5, 1, 7, 0), (5, 2, 7, 0), (5, 3, 7, 0), (5, 4, 7, 0),(5, 5, 7, 0), (5, 6, 7, 0),
       (6, 1, 7, 0), (6, 2, 7, 0), (6, 3, 7, 0), (6, 4, 7, 0),(6, 5, 7, 0), (6, 6, 7, 0),
       (7, 1, 7, 0), (7, 2, 7, 0), (7, 3, 7, 0), (7, 4, 7, 0),(7, 5, 7, 0), (7, 6, 7, 0),
       (8, 1, 7, 0), (8, 2, 7, 0), (8, 3, 7, 0), (8, 4, 7, 0),(8, 5, 7, 0), (8, 6, 7, 0),
       (9, 1, 7, 0), (9, 2, 7, 0), (9, 3, 7, 0), (9, 4, 7, 0),(9, 5, 7, 0), (9, 6, 7, 0);
-----------------------------------------------
select
    r1_0.id,
    r1_0.area_id,
    r1_0.criterion_id,
    r1_0.value,
    r1_0.week_id
from
    result r1_0
where
    r1_0.week_id=1 and r1_0.area_id=1 and r1_0.criterion_id=1;
-----------------------------------------------

