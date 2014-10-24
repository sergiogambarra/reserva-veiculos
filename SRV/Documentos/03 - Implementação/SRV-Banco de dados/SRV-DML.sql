
use srv;


insert into servidor 
values ('1234567', 'admin', '123456', 'admin1@gmail.com', '1',
 'm', '2010/10/09','00000000000', '0000000000', 'ssp/rs',
 'porto alegre', 'rs', 'brasileiro', 'cas', '(51) 2324-2324', '(43) 3434-3434',
 '1', '12345678911', '1', 'teste admin'),
 ('7654321', 'servidor0', '123456', 'serv0@restinga.ifrs.edu.br', '0',
 'f', '1980/02/23', '111.111.111-11', '1111111111', 'stj/rs',
 'alvorada', 'rs', 'brasileiro', 'sol', '(51) 1111-1111', '(52) 2222-2222',
'1', '00000000000', '1', 'teste servidor 0'),
 ('1111111', 'servidor1', '123456', 'serv2@restinga.ifrs.edu.br', '1',
 'm', '1985/10/04', '222.222.222-02', '1111111111', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43) 3333-3333', '(46) 4444-4444',
 '0', null, '1', 'teste servidor 1'),
 ('2222222', 'servidor2', '123456', 'serv2@restinga.ifrs.edu.br', '2',
 'm', '1985/10/04', '222.222.222-02', '2222222222', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43) 3333-3333', '(46) 4444-4444',
 '0', null, '1', 'teste servidor 2'),
 ('3333333', 'servidor3', '123456', 'serv2@restinga.ifrs.edu.br', '0',
 'm', '1985/10/04', '222.222.222-02', '1111111111', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43) 3333-3333', '(46) 4444-4444',
 '1', null, '1', 'teste servidor 3'),
 ('4444444', 'servidor4', '123456', 'serv2@restinga.ifrs.edu.br', '0',
 'm', '1985/10/04', '222.222.222-02', '1111111111', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43) 3333-3333', '(46) 4444-4444',
 '0', null, '0', 'teste servidor 4'),
 ('5555555', 'servidor5', '123456', 'serv2@restinga.ifrs.edu.br', '0',
 'm', '1985/10/04', '222.222.222-02', '1111111111', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43) 3333-3333', '(46) 4444-4444',
 '0', null, '1', 'teste servidor 5'),
 ('6666666', 'servidor6', '123456', 'serv2@restinga.ifrs.edu.br', '0',
 'm', '1985/10/04', '222.222.222-02', '1111111111', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43) 3333-3333', '(46) 4444-4444',
 '0', null, '0', 'teste servidor 6'),
 ('7777777', 'servidor7', '123456', 'serv2@restinga.ifrs.edu.br', '0',
 'm', '1985/10/04', '222.222.222-02', '1111111111', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43) 3333-3333', '(46) 4444-4444',
 '0', null, '1', 'teste servidor 7'),
 ('8888888', 'servidor8', '123456', 'serv2@restinga.ifrs.edu.br', '0',
 'm', '1985/10/04', '222.222.222-02', '1111111111', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43) 3333-3333', '(46) 4444-4444',
 '0', null, '0', 'teste servidor 8');

INSERT INTO veiculo VALUES 
('CAR-1111', '2011', 'fiat', 'siena 1.0', 'g', 
'12345678900', '5', '1', '2014/12/12', '2014/12/24'),
('CAR-3333', '2013', 'gm', 'corsa sedan', 'n',
'12345678901', '5', '0', null, null),
('CAR-4444', '2014', 'honda', 'civic', 'g',
'12345678901', '5', '0', null, null),
('CAR-5555', '2015', 'toyota', 'corolla', 'g',
'12345678901', '5', '0', null, null),
('CAR-6666', '2006', 'fiat', 'palio', 'n',
'12345678901', '5', '0', null, null),
('CAR-7777', '2007', 'honda', 'fitc', 'n',
'12345678901', '5', '0', null, null),
('CAR-8888', '2008', 'volkswagen', 'gol', 'c',
'12345678901', '5', '0', null, null),
('CAR-9999', '2009', 'ford', 'fiesta', 'c',
'12345678901', '5', '0', null, null),
('CAR-1000', '2010', 'chevrolet', 'celta', 'n',
'12345678901', '5', '0', null, null),
('CAR-1222', '2012', 'toyota', 'hilux', 'g',
'12345678901', '5', '0', null, null),
('CAR-1333', '2013', 'ford', 'ecosport', 'g',
'12345678901', '5', '0', null, null),
('CAR-1444', '2014', 'fiat', 'uno', 'c',
'12345678901', '5', '0', null, null),
('CAR-1555', '2015', 'fiat', 'uno', 'n',
'12345678901', '5', '0', null, null),
('CAR-1666', '2006', 'fiat', 'palio', 'c',
'12345678901', '5', '0', null, null),
('CAR-1777', '2007', 'gm', 'corsa sedan', 'n',
'12345678901', '5', '0', null, null),
('CAR-1888', '2008', 'ford', 'f-100', 'c',
'12345678902', '3', '1', '2014/10/20', '2014/10/21');


INSERT INTO destino 
values 
	  (null, "Outro Destino")
	, (null, "IFRS Campus Porto Alegre")
	, (null, "IFRS Campus Bento Gonçalves")
	, (null, "IFRS Campus Rio Grande")
	, (null, "IFRS Reitoria")
  , (null, "Em Manutenção");

INSERT INTO `srv`.`reserva` (`id_reserva`, `matricula_siape`, `data_saida`, `data_retorno`, `placa`, 
`condutor`, `matricula_siape_condutor`, `ocupantes`, `id_destino`, `reserva_datetime`) 
VALUES ('1406116062', '2222222', '2014-12-30 13:00:00', '2014-12-22 07:00:00', 'CAR-9999', 
'1', '2222222', '2', '2', '2014-07-27 23:59:58'),
('1406000024', '1234567', '2014-12-28 02:00:00', '2014-12-30 02:00:00', 'CAR-1777', 
'1', '1234567', '1', '5', '2014-07-28 00:00:24'),

('1406000115', '7654321', '2014-12-28 01:00:00', '2014-12-29 01:00:00', 'CAR-1111', 
'1', '7654321', '1', '5', '2014-07-27 23:59:58'),
('1406000202', '7654321', '2014-12-29 01:00:00', '2014-12-29 05:00:00', 'CAR-1444', 
'1', '7654321', '1', '3', '2014-07-27 23:59:58'),
('1406000230', '7654321', '2014-12-01 01:00:00', '2014-12-01 02:00:00', 'CAR-1222', 
'1', '7654321', '1', '4', '2014-07-27 23:59:58'),
('1406000250', '7654321', '2014-12-02 04:00:00', '2014-12-02 05:00:00', 'CAR-1666', 
'1', '7654321', '1', '3', '2014-07-27 23:59:58'),
('1406000424', '7654321', '2014-12-03 02:00:00', '2014-12-04 02:00:00', 'CAR-1666', 
'0', '6666666', '2', '3', '2014-07-27 23:59:58'),
('1406000448', '7654321', '2014-12-12 02:00:00', '2014-12-12 03:00:00', 'CAR-1555', 
'0', '7777777', '2', '5', '2014-07-27 23:59:58'),
('1406000536', '7654321', '2014-12-26 03:00:00', '2014-12-26 04:00:00', 'CAR-1777', 
'1', '7654321', '1', '3', '2014-07-27 23:59:58'),
('1406000556', '7654321', '2014-12-18 08:00:00', '2014-12-18 09:00:00', 'CAR-7777', 
'1', '7654321', '1', '3', '2014-07-27 23:59:58'),
('1406000629', '7654321', '2014-12-20 01:00:00', '2014-12-21 02:00:00', 'CAR-1333', 
'1', '7654321', '1', '2', '2014-07-27 23:59:58'),
('1406000646', '7654321', '2014-12-22 06:00:00', '2014-12-22 07:00:00', 'CAR-1777', 
'1', '7654321', '1', '4', '2014-07-27 23:59:58'),
('1406000706', '7654321', '2014-12-30 13:00:00', '2014-12-30 14:00:00', 'CAR-1555', 
'1', '7654321', '1', '4', '2014-07-27 23:59:58'),
('1406000748', '7654321', '2014-12-31 15:00:00', '2014-12-31 15:30:00', 'CAR-1888', 
'1', '7654321', '1', '2', '2014-07-27 23:59:58'),
('1406113215', '1234567', '2014-12-27 23:30:00', '2014-12-29 13:30:00', 'CAR-1000', 
'1', '1234567', '1', '2', '2014-07-27 23:59:58'),
('1406115716', '1234567', '2014-12-30 13:30:00', '2014-12-31 14:30:00', 'CAR-1111', 
'1', '1234567', '2', '2', '2014-07-27 23:59:58'),
('1406115851', '1234567', '2014-12-30 11:30:00', '2014-12-31 14:00:00', 'CAR-1222', 
'0', '7654321', '2', '3', '2014-07-27 23:59:58'),
('1406115958', '1234567', '2014-12-29 14:00:00', '2014-12-30 16:00:00', 'CAR-1333', 
'1', '1234567', '1', '3', '2014-07-27 23:59:58'),
('1406116061', '1234567', '2014-12-29 14:00:00', '2014-12-30 16:00:00', 'CAR-1444', 
'1', '1234567', '1', '3', '2014-07-27 23:59:58');