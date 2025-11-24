CREATE DATABASE IF NOT EXISTS pub_system
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE pub_system;


CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    contato VARCHAR(100)
);

CREATE TABLE Mesa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL UNIQUE,
    capacidade INT NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE Ingrediente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    unidade_medida VARCHAR(20) NOT NULL,
    estoque_atual DECIMAL(10, 2)
);


CREATE TABLE Bebida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    preco DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    descricao TEXT
);

CREATE TABLE ReceitaBebida (
    id_bebida INT NOT NULL,
    id_ingrediente INT NOT NULL,
    quantidade_necessaria DECIMAL(10, 2),
    PRIMARY KEY (id_bebida, id_ingrediente),
    FOREIGN KEY (id_bebida) REFERENCES Bebida(id) ON DELETE CASCADE,
    FOREIGN KEY (id_ingrediente) REFERENCES Ingrediente(id) ON DELETE RESTRICT
);


CREATE TABLE Evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data DATE NOT NULL,
    hora_inicio TIME NOT NULL, -- TIME em MySQL
    atracoes_musicais TEXT
);


CREATE TABLE BebidasDoEvento (
    id_evento INT NOT NULL,
    id_bebida INT NOT NULL,
    preco_promocional DECIMAL(10, 2),
    is_destaque BOOLEAN,
    PRIMARY KEY (id_evento, id_bebida),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE,
    FOREIGN KEY (id_bebida) REFERENCES Bebida(id) ON DELETE RESTRICT
);


CREATE TABLE Promocao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    tipo_desconto VARCHAR(20) NOT NULL,
    valor_desconto DECIMAL(5, 2)
);


CREATE TABLE Comanda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_mesa INT,
    data_abertura DATETIME, -- DATETIME em MySQL
    data_fechamento DATETIME, -- DATETIME em MySQL
    status VARCHAR(20) NOT NULL,
    valor_subtotal DECIMAL(10, 2),
    valor_desconto DECIMAL(10, 2),
    valor_total DECIMAL(10, 2),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id) ON DELETE SET NULL,
    FOREIGN KEY (id_mesa) REFERENCES Mesa(id) ON DELETE SET NULL
);

CREATE TABLE ItemComanda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_comanda INT NOT NULL,
    id_bebida INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario_registro DECIMAL(10, 2),
    valor_item DECIMAL(10, 2),
    UNIQUE KEY uk_itemcomanda (id_comanda, id_bebida),
    FOREIGN KEY (id_comanda) REFERENCES Comanda(id) ON DELETE CASCADE,
    FOREIGN KEY (id_bebida) REFERENCES Bebida(id) ON DELETE RESTRICT
);

CREATE TABLE PromocaoAplicada (
    id_comanda INT NOT NULL,
    id_promocao INT NOT NULL,
    data_aplicacao DATETIME, -- DATETIME em MySQL
    valor_desconto_aplicado DECIMAL(10, 2),
    PRIMARY KEY (id_comanda, id_promocao),
    FOREIGN KEY (id_comanda) REFERENCES Comanda(id) ON DELETE CASCADE,
    FOREIGN KEY (id_promocao) REFERENCES Promocao(id) ON DELETE RESTRICT
);




