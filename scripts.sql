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
    ('Участок изготовления узлов, фундаментов и деталей машиностроительной части', 1);

INSERT INTO criterion (name) VALUES ('Общая оценка'), ('ОТ/ПБ'), ('5С'), ('ТРМ'), ('КПСЦ'), ('СВМ');

INSERT INTO area_criterion (area_id, criterion_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
                                                          (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6),
                                                          (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6),
                                                          (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6),
                                                          (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6),
                                                          (6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 6),
                                                          (7, 1), (7, 2), (7, 3), (7, 4), (7, 5), (7, 6),
                                                          (8, 1), (8, 2), (8, 3), (8, 4), (8, 5), (8, 6),
                                                          (9, 1), (9, 2), (9, 3), (9, 4), (9, 5), (9, 6);

INSERT INTO week (number) VALUES (31), (32), (33), (34);

INSERT INTO ac_week (area_criterion_id, week_id, result) VALUES (1, 1, 0), (1, 2, 0), (1, 3, 0), (1, 4, 0),
                                                        (2, 1, 0), (2, 2, 0), (2, 3, 0), (2, 4, 0),
                                                        (3, 1, 0), (3, 2, 0), (3, 3, 0), (3, 4, 0),
                                                        (4, 1, 0), (4, 2, 0), (4, 3, 0), (4, 4, 0),
                                                        (5, 1, 0), (5, 2, 0), (5, 3, 0), (5, 4, 0),
                                                        (6, 1, 0), (6, 2, 0), (6, 3, 0), (6, 4, 0),
                                                        (7, 1, 28), (7, 2, 42), (7, 3, 46), (7, 4, 46),
                                                        (8, 1, 40), (8, 2, 60), (8, 3, 80), (8, 4, 80),
                                                        (9, 1, 0), (9, 2, 0), (9, 3, 0), (9, 4, 0),
                                                        (10, 1, 0), (10, 2, 0), (10, 3, 0), (10, 4, 0),
                                                        (11, 1, 50), (11, 2, 75), (11, 3, 75), (11, 4, 75),
                                                        (12, 1, 50), (12, 2, 75), (12, 3, 75), (12, 4, 75);


select ca.id, d.number, a.name, criterion.name from criterion
                                                 join area_criterion ca on criterion.id = ca.criterion_id
                                                 join public.area a on a.id = ca.area_id
                                                 join public.department d on a.department_id = d.id;

select ca.id, ac.id, d.number, a.name, c.name, w.number from week w
                                                                 full join  ac_week ac on w.id = ac.week_id
                                                                 full join area_criterion ca on ac.area_criterion_id = ca.id
                                                                 full join criterion c on ca.criterion_id = c.id
                                                                 full join public.area a on a.id = ca.area_id
                                                                 full join public.department d on a.department_id = d.id;
