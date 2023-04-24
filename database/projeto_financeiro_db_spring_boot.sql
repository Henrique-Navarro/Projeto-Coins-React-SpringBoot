#CONFIGURAÇÕES
CREATE DATABASE IF NOT EXISTS projeto_financeiro_db;
USE projeto_financeiro_db;
SET SQL_SAFE_UPDATES = 0;
SHOW TABLES;

#CATEGORIAS
INSERT INTO category (id,name) VALUES
(1,'Viagem'),(2,'Investimento'),(3,'Lazer'),(4,'Planejamento'),(5,'Design'),(6, 'Desenvolvimento');
SELECT * FROM category;
DELETE FROM category;

#PROJETOS
DESCRIBE projetos;

#DELETE
DROP database projeto_financeiro_db;