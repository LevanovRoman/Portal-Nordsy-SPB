SELECT * FROM fors_store.etl_ppu;

SELECT * FROM fors_store.OBJ136991;

SELECT *
FROM fors_store.etl_ppu
WHERE EXTRACT(YEAR FROM TO_DATE(createdate, 'YYYY-MM-DD')) = 2024 AND isexecuted=1
ORDER BY TO_DATE(createdate, 'YYYY-MM-DD') ASC;

SELECT title, numberanddate, department, prepperson, regdate, state, isexecuted, field21, field22
FROM fors_store.etl_ppu
WHERE EXTRACT(YEAR FROM TO_DATE(createdate, 'YYYY-MM-DD')) >= 2024
ORDER BY TO_DATE(createdate, 'YYYY-MM-DD') ASC;

SELECT COUNT(*) FROM fors_store.etl_ppu WHERE field22 ILIKE 'Бытовые улучшения';
-- ------------------------------------------------------------------------------
SELECT COUNT(*) FROM suggestion WHERE category ILIKE 'Бытовые улучшения';
SELECT COUNT(*) FROM suggestion WHERE category ILIKE 'Информационные / IT-процессы';
SELECT COUNT(*) FROM suggestion WHERE category ILIKE 'Охрана труда';
SELECT COUNT(*) FROM suggestion WHERE category ILIKE 'Производственные процессы и технология';
SELECT COUNT(*) FROM suggestion WHERE category ILIKE 'Эргономика';

SELECT COUNT(*) FROM suggestion;

-- зарегистрировано
SELECT COUNT(*) FROM suggestion WHERE suggestion.number_and_date_registration IS NOT NULL
                                  AND suggestion.number_and_date_registration <> '';
-- согласовано
SELECT COUNT(*) FROM suggestion WHERE agreed ILIKE 'Согласован';
-- внедрено
SELECT COUNT(*) FROM suggestion WHERE implemented ILIKE '1';

-- зарегистрировано для отдела
SELECT COUNT(*) FROM suggestion WHERE suggestion.number_and_date_registration IS NOT NULL
        AND suggestion.number_and_date_registration <> '' AND department LIKE '415';

INSERT INTO suggestion_director (position, implemented, inclusion,  registered, total) VALUES
                                                                                           ('Заместитель генерального директора по управлению персоналом и административным вопросам', 10, 10, 10, 10),
                                                                                           ('Заместитель генерального директора по экономике и финансам', 10, 10, 10, 10),
                                                                                           ('Главный инженер', 10, 10, 10, 10),
                                                                                           ('Директор по производству', 10, 10, 10, 10),
                                                                                           ('Директор по материально-техническому обеспечению и логистике', 10, 10, 10, 10),
                                                                                           ('Директор по качеству и развитию производственной системы', 10, 10, 10, 10),
                                                                                           ('Заместитель генерального директора по правовому обеспечению', 10, 10, 10, 10);

TRUNCATE TABLE suggestion_director RESTART IDENTITY CASCADE;

INSERT INTO suggestion_departments (suggestion_director_id, departments) VALUES
                                                                             (1, '508'), (1, '447'), (1, '420'), (1, '419'), (1, '402'), (1, '428'),
                                                                             (2, '512'), (2, '476'), (2, '516'), (2, '406'), (2, '482'), (2, '426'), (2, '487'),
                                                                             (3, '503'), (3, '531'), (3, '405'), (3, '407'), (3, '511'), (3, '415'), (3, '414'),
                                                                             (3, '423'), (3, '530'), (3, '427'), (3, '431'), (3, '430'), (3, '416'), (3, '434'),
                                                                             (3, '510'), (3, '425'), (3, '411'), (3, '024'), (3, '421'), (3, '535'), (3, '477'),
                                                                             (3, '470'), (3, '461'), (3, '467'),
                                                                             (4, '504'), (4, '543'), (4, '524'), (4, '413'), (4, '036'), (4, '020'), (4, '013'),
                                                                             (4, '047'), (4, '040'), (4, '035'), (4, '041'), (4, '525'), (4, '453'), (4, '439'),
                                                                             (5, '505'), (5, '016'), (5, '432'), (5, '443'), (5, '454'),
                                                                             (6, '506'), (6, '445'), (6, '488'), (6, '408'), (6, '436'),
                                                                             (7, '513'), (7, '438'), (7, '465'), (7, '451');

SELECT COUNT(*) FROM person;
INSERT INTO suggestion_director (position) VALUES
            ('Заместитель генерального директора по управлению персоналом и административным вопросам'),
            ('Заместитель генерального директора по экономике и финансам'),
            ('Главный инженер'),
            ('Директор по производству'),
            ('Директор по материально-техническому обеспечению и логистике'),
            ('Директор по качеству и развитию производственной системы'),
            ('Заместитель генерального директора по правовому обеспечению');

TRUNCATE TABLE suggestion_director RESTART IDENTITY CASCADE;

INSERT INTO suggestion_departments (suggestion_director_id, departments) VALUES
                                                                             (1, '508'), (1, '447'), (1, '420'), (1, '419'), (1, '402'), (1, '428'),
                                                                             (2, '512'), (2, '476'), (2, '516'), (2, '406'), (2, '482'), (2, '426'), (2, '487'),
                                                                             (3, '503'), (3, '531'), (3, '405'), (3, '407'), (3, '511'), (3, '415'), (3, '414'),
                                                                              (3, '423'), (3, '530'), (3, '427'), (3, '431'), (3, '430'), (3, '416'), (3, '434'),
                                                                             (3, '510'), (3, '425'), (3, '411'), (3, '024'), (3, '421'), (3, '535'), (3, '477'),
                                                                             (3, '470'), (3, '461'), (3, '467'),
                                                                             (4, '504'), (4, '543'), (4, '524'), (4, '413'), (4, '036'), (4, '020'), (4, '013'),
                                                                             (4, '047'), (4, '040'), (4, '035'), (4, '041'), (4, '525'), (4, '453'), (4, '439'),
                                                                             (5, '505'), (5, '016'), (5, '432'), (5, '443'), (5, '454'),
                                                                             (6, '506'), (6, '445'), (6, '488'), (6, '408'), (6, '436'),
                                                                             (7, '513'), (7, '438'), (7, '465'), (7, '451');

SELECT COUNT(*) FROM person;
