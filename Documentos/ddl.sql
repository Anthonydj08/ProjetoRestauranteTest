create table pessoa (
	cod_pessoa serial,
	nome varchar(100) not null,
	cpf integer not null,
	telefone varchar(20) not null,
	usuario varchar(20) not null,
	senha varchar(20) not null,
	email varchar(100) not null,
	primary key (cod_pessoa)
);

create table cliente (
	cod_cliente integer,
	primary key (cod_cliente),
	foreign key (cod_cliente) references pessoa
);

create table funcionario (
	cod_funcionario integer,
	salario numeric(6,2) not null,
	funcao varchar(30) not null,
	num_carteira integer not null,
	primary key (cod_funcionario),
	foreign key (cod_funcionario) references pessoa
);

create table estoque (
	cod_produto serial,
	nome varchar(20) not null,
	quantidade integer not null,
	uni_medida varchar(10) not null,
	primary key (cod_produto)
);

create table produto (
	cod_produto serial,
	nome varchar(30) not null,
	preco_custo numeric(6,2) not null,
	categoria varchar(20) not null,
	primary key (cod_produto),
	foreign key (cod_produto) references estoque
);

create table cardapio (
	cod_cardapio serial,
	categoria varchar(20) not null,
	primary key (cod_cardapio)
);

create table item_cardapio (
	cod_item serial,
	cod_cardapio integer,
	preco numeric(6,2) not null,
	primary key (cod_item),
	foreign key (cod_cardapio) references cardapio
);

create table conta (
	codConta serial,
	valorTotal numeric(6,2) not null,
	status varchar(20) not null,
	primary key (codConta)

);

create table pedido (
	cod_pedido serial,
	cod_conta integer,
	cod_cliente integer,
	mesa integer not null,
	status varchar(10) not null,
	data date not null,
	primary key (cod_pedido),
	foreign key (cod_conta) references conta,
	foreign key (cod_cliente) references cliente
);

create table pedido_funcionario (
	cod_pedido integer,
	cod_funcionario integer,
	foreign key (cod_pedido) references pedido,
	foreign key (cod_funcionario) references funcionario
);

create table entrega (
	cod_entrega serial,
	cod_pedido integer,
	rua varchar(150) not null,
	numero integer not null,
	cep integer not null,
	bairro varchar(100) not null,
	referencia varchar(200),
	complemento varchar(20),
	status varchar(10) not null,
	primary key (cod_entrega),
	foreign key (cod_pedido) references pedido
);



create table item_cardapiopedido (
	cod_item integer,
	cod_pedido integer,
	quantidade integer not null,
	primary key (cod_item, codPedido),
	foreign key (cod_item) references item_cardapio,
	foreign key (cod_pedido) references pedido
);

create table pagamento (
	cod_pagamento serial,
	cod_conta integer,
	data date not null,
	primary key (cod_pagamento),
	foreign key (cod_conta) references conta
);

create table tipo_pagamento (
	cod_Tipo_pagamento serial,
	cod_pagamento integer,
	primary key (cod_Tipo_pagamento),
	foreign key (cod_pagamento) references pagamento
);

create table pagamento_dinheiro (
	cod_dinheiro serial,
	cod_Tipo_pagamento integer,
	valor numeric(6,2) not null,
	primary key (cod_dinheiro),
	foreign key (cod_Tipo_pagamento) references tipo_pagamento
);

create table pagamento_cartao (
	cod_cartao serial,
	cod_Tipo_pagamento integer,
	nome_titular varchar(100) not null,
	numero integer not null,
	data_validade date not null,
	cod_seguranca integer not null,
	primary key (codCartao),
	foreign key (cod_Tipo_pagamento) references tipo_pagamento
);


=================================
CREATE TABLE pessoa_log (
	cod_alteracao SERIAL,
	usuario VARCHAR(150) NOT NULL,
	nome VARCHAR(150) NOT NULL,
	acao VARCHAR(25) NOT NULL,
	data_alteracao TIMESTAMP NOT NULL,
	PRIMARY KEY (cod_alteracao)
);

CREATE FUNCTION valida_dados_pessoa() RETURNS TRIGGER AS $valida_dados_pessoa$
BEGIN
	IF NEW.NOME IS NULL THEN
		RAISE EXCEPTION 'Por Favor, digite o nome da pessoa!';
	END IF;

	-- Aqui iremos gravar no log o tipo de ação que foi realizada
	INSERT INTO pessoa_log (usuario, nome, acao, data_alteracao) VALUES (CURRENT_USER ,NEW.nome, TG_OP, CURRENT_TIMESTAMP);
 
	RETURN NEW;
END;
$valida_dados_pessoa$
LANGUAGE plpgsql;

CREATE TRIGGER validacao_insert AFTER INSERT OR UPDATE ON pessoa
FOR EACH ROW EXECUTE PROCEDURE valida_dados_pessoa();

==========================

CREATE FUNCTION reduz_estoque() RETURNS TRIGGER AS $reduz_estoque$
BEGIN
 	UPDATE estoque set quantidade = quantidade - new.quantidade
 	WHERE cod_estoque = NEW.cod_item;
 	RETURN NEW;
END;
$reduz_estoque$
LANGUAGE plpgsql;


CREATE TRIGGER itempedido_insert AFTER INSERT ON item_pedido
FOR EACH ROW EXECUTE PROCEDURE reduz_estoque();

================

CREATE FUNCTION status_conta() RETURNS TRIGGER AS $status_conta$
BEGIN
	IF ( new.status = 'Concluído') THEN
		UPDATE conta set status = 'Pago';
	END IF;
 
	RETURN NEW;
END;
$status_conta$
LANGUAGE plpgsql;

CREATE TRIGGER altera_status_conta AFTER INSERT OR UPDATE ON pedido
FOR EACH ROW EXECUTE PROCEDURE status_conta();


CREATE ASSERTION verifica_preco
CHECK(EXISTS(SELECT *
            	FROM item_cardapio AS ic
            	WHERE ic.Preco > (SELECT e.preco_custo
            						FROM  estoque AS e
            						WHERE e.cod_estoque = ic.cod_estoque)))

CREATE ASSERTION verifica_salario
CHECK(NOT EXISTS(SELECT *
            	FROM pessoa AS p
            	WHERE p.tipo = 'F' AND p.funcao = 'Garçon' 
            	AND p.salario > (SELECT pp.salario
            					FROM  pessoa AS pp
            					WHERE pp.tipo = 'F' AND pp.funcao = 'Gerente')
     ))