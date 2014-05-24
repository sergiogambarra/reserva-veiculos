use srv;

insert into servidor 
values ('1234567', 'admin', '123456', 'admin1@gmail.com', '1',
 'm', '2010/10/09','00000000000', '0000000000', 'ssp/rs',
 'porto alegre', 'rs', 'brasileiro', 'cas', '(51)2324-2324', '(43)3434-3434',
 '1', '12345678911', '1', 'teste admin'),
 ('7654321', 'servidor1', '123456', 'serv1@restinga.ifrs.edu.br', '0',
 'f', '1980/02/23', '111.111.111-11', '1111111111', 'stj/rs',
 'alvorada', 'rs', 'brasileiro', 'sol', '(51)1111-1111', '(52)2222-2222',
'1', '11111111111', '1', 'teste servidor 1'),
 ('7654322', 'servidor2', '123456', 'serv2@restinga.ifrs.edu.br', '0',
 'm', '1985/10/04', '222.222.222-02', '2222222222', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43)3333-3333', '(46)4444-4444',
 '0', null, '1', 'teste servidor 2');

INSERT INTO veiculo VALUES 
('car1111', '2014', 'fiat', 'siena 1.0', 'gasolina', 
'12345678900', '5', '1', '2014/12/12', '2014/12/24'),
('car2222', '2013', 'gm', 'corsa sedan', 'gnv',
'12345678901', '5', '0', null, null),
('car3333', '2012', 'ford', 'f-100', 'diesel',
'12345678902', '3', '1', '2014/10/20', '2014/10/21');

insert into destino values
	  (null, 'OUTROS')
	, (null, 'IFRS CAMPUS RESTINGA')
	, (null, 'IFRS CAMPUS PORTO ALEGRE')
	, (null, 'IFRS CAMPUS RIO GRANDE')
	, (null, 'IFRS CAMPUS BENTO GONÇALVES')
	, (null, 'IFRS CAMPUS FARROUPILHA')
	, (null, 'IFRS CAMPUS SERTÃO')
	, (null, 'IFRS CAMPUS OSÓRIO')
	, (null, 'IFRS CAMPUS VIAMÃO')
	, (null, 'IFRS CAMPUS ALVORADA')
	, (null, 'IFRS REITORIA');