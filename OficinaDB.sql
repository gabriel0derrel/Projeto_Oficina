create table Oficina(
	email varchar(60) primary key, 
	nome varchar(60) not null, 
	ddi1 int not null,
	ddd1 int not null, 
	numeroTelefone1 int not null,
	unique(ddi1, ddd1, numeroTelefone1),
	ddi2 int null, 
	ddd2 int null, 
	numeroTelefone2 int null,
	unique(ddi2, ddd2, numeroTelefone2),
	logradouro varchar(60) not null, 
	numeroEndereco int not null, 
	cep varchar(40) not null, 
	bairro varchar(50) not null, 
	complemento varchar(50) not null, 
	cidade varchar(60) not null, 
	estado varchar(50) not null
);
create type tipoClienteEnum as enum('Pessoa Física','Pessoa Jurídica');
create table Cliente(
	idCliente serial primary key, 
	nome varchar(60) not null, 
	ddi1 int not null, 
	ddd1 int not null, 
	numeroTelefone1 int not null,
	unique(ddi1, ddd1, numeroTelefone1),
	ddi2 int null, 
	ddd2 int null, 
	numeroTelefone2 int null,
	unique(ddi2, ddd2, numeroTelefone2),
	email varchar(60) not null, 
	logradouro varchar(60) not null, 
	numeroEndereco int not null, 
	cep varchar(40) not null, 
	bairro varchar(50) not null, 
	complemento varchar(50) not null, 
	cidade varchar(50) not null, 
	estado varchar(60) not null, 
	tipoCliente tipoClienteEnum not null, 
	cpf varchar(20) null, 
	cnpj varchar(20) null, 
	contato varchar(40) null, 
	inscricaoEstadual varchar(50) null
);

create table Marca(
	idMarca serial primary key, 
	descricao varchar(100) not null
);

create table Modelo(
	idModelo serial primary key, 
	descricao varchar(100) not null, 
	idMarca int not null,
	foreign key(idMarca) references Marca(idMarca)
);

create table Acessorio(
	idAcessorio serial primary key, 
	anoFabricacao date not null, 
	descricao varchar(60) not null
);

create table Veiculo(
	placa varchar(10) primary key, 
	anoFabricacao date not null, 
	dataRegistro date not null, 
	chassi varchar(20) null, 
	patrimonio int null, 
	kilometragem int not null, 
	anoModelo date not null, 
	idModelo int not null,
	foreign key(idModelo) references Modelo(idModelo)
);

create table VeiculoAcessorio(
	placa varchar(10) not null, 
	idAcessorio int not null,
	primary key(placa, idAcessorio),
	foreign key(placa) references Veiculo(placa),
	foreign key(idAcessorio) references Acessorio(idAcessorio)
);

create table Proprietario(
	idProprietario serial primary key, 
	dataInicio date not null, 
	dataFim date null, 
	idCliente int not null, 
	placa varchar(10) not null,
	foreign key(idCLiente) references Cliente(idCliente),
	foreign key(placa) references Veiculo(placa)
);
create type estadoEnum as enum('Orçamento','Aprovado','Execução','Finalizado','Pago');
create table OrdemDeServico(
	idOrdem serial primary key,  
	estado estadoEnum not null,
	inicio date not null,
	fim date not null, 
	valorTotal money not null,
	valorPago money not null,
	diferenca money not null, 
	placa varchar(10) not null, 
	foreign key(placa) references Veiculo(placa)
);
create table Servico(
	idServico serial primary key, 
	descricao varchar(100) not null, 
	preco money not null
);

create table Funcionario(
	idFuncionario serial primary key, 
	nome varchar(50) not null, 
	email varchar(70) null, 
	ddi int not null, 
	ddd int not null, 
	numero int not null,
	unique(ddi, ddd, numero)
);

create table ItensServicos(
	idItensServico serial primary key,
	idServico int not null, 
	idOrdem int not null,
	idFuncionario int not null, 
	quantidade int not null, 
	precoUnitario money not null, 
	precoFinal money not null,
	foreign key(idServico) references Servico(idServico),
	foreign key(idOrdem) references OrdemDeServico(idOrdem),
	foreign key(idFuncionario) references Funcionario(idFuncionario)
);

create table Peca(
	idPeca int primary key, 
	descricao varchar(100) not null, 
	codigoFabricante int not null, 
	valorUnitario money not null, 
	quantidade int not null
);

create table ItensPeca(
	idItensPeca serial primary key,
	idOrdem int not null, 
	idPeca int not null,
	valorTotal money not null, 
	valorUnitario money not null, 
	quantidade int not null,
	foreign key(idOrdem) references OrdemDeServico(idOrdem),
	foreign key(idPeca) references Peca(idPeca)
);
