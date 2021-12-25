insert into company (id, name)
values
       (1, 'Apple'),
       (2, 'Google');

SELECT setval('company_id_seq', (SELECT MAX(id) FROM company));
