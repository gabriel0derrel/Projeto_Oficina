# **AutoManager** 🚗

Este repositório contém um projeto de aplicativo desktop desenvolvido em Java com PostgreSQL, criado como projeto final da disciplina **Projeto de Banco de Dados**. O sistema foi projetado para otimizar a gestão de oficinas mecânicas, permitindo o gerenciamento completo de ordens de serviço, clientes, veículos e serviços prestados.

Este projeto foi desenvolvido a partir da análise de documentos em PDF anexados no primeira diretório do projeto e entrevistas realizadas com o professor responsável.

# ✨ Principais Funcionalidades

✅ Emissão de Ordens de Serviço – Geração e exportação de O.S em PDF    
✅ Cadastro e Gerenciamento de Clientes – Adição, edição e consulta de clientes    
✅ Gestão de Veículos – Registro de veículos e histórico de serviços    
✅ Controle de Serviços – Listagem e detalhamento dos serviços prestados    
✅ Autenticação de Usuários – Controle de acesso restrito para administradores    

# 🛠️ Tecnologias Utilizadas

O projeto foi desenvolvido com as seguintes tecnologias:

- Java - Linguagem de programação principal

- Swing (Javax.swing) - Para interface gráfica

- PostgreSQL - Banco de dados relacional

- NetBeans - IDE

- OpenPDF - Para geração de arquivos PDF

# Instalação e Configuração

### 📥 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado em sua máquina:

- **Java JDK 11** ou superior
- **PostgreSQL** (com um banco de dados configurado)
- **Apache Ant**

### ⚙️ Configuração do Banco de Dados
1. Crie o banco de dados no PostgreSQL. O script necessário pode ser encontrado no arquivo:    
   [OficinaDB.sql](https://github.com/Prizrak2/Projeto_Oficina/blob/main/OficinaDB.sql)
   
   Para criar manualmente, execute:

   ```sql
   CREATE DATABASE oficina;

3. No código-fonte, localize o arquivo responsável pela conexão:    
   [ConexaoBD.java](https://github.com/Prizrak2/Projeto_Oficina/blob/main/ProjetoOficina_TrabalhoFinal/src/conexao/ConexaoBD.java)
   
   Edite os seguintes parâmetros:

   ```java
   String url = "jdbc:postgresql://localhost:5432/oficina";
   String usuario = "seu_usuario";
   String senha = "sua_senha";

### ▶️ Executando o Projeto
1. Clone o repositório:

   ```bash
   git clone https://github.com/Prizrak2/Projeto_Oficina.git

2. Acesse a pasta do projeto:

   ```bash
   cd Projeto_Oficina

3. Compile o projeto usando Ant:

   ```bash
   ant compile

4. Execute o projeto:

   ```bash
   ant run

# 📌 Observações
- O sistema foi desenvolvido para funcionar com os dados obtidos exclusivamente dos documentos em PDF fornecidos
- Caso deseje modificar a estrutura do banco de dados, ajuste os scripts SQL e revise as conexões no código

# Autores

- [Lucas Pereira Nunes](https://github.com/Prizrak2)
- [Gabriel Derrel](https://github.com/gabriel0derrel)
