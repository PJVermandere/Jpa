insert into artikels
values (null, 'Test', 1.00,1.20,'NF', 1, null, (select id from artikelgroepen a where a.naam='testGroep')),
       (null, 'Test1', 1.00,1.20,'F', null, 1, (select id from artikelgroepen a where a.naam='testGroep1')),
       (null, 'Test2', 1.00,2.00,'F', null, 2, (select id from artikelgroepen a where a.naam='testGroep2'));
insert into kortingen values ((select id from artikels where naam = 'Test1'), 5, 10.00)