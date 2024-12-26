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

