# 🚚 Delivery Tracer

O **Delivery Tracer** é uma API REST desenvolvida em **Java + Spring Boot** para gerenciar entregas, clientes e motoristas.  
O objetivo é simular um sistema de rastreamento de entregas do mundo real, aplicando boas práticas de programação e arquitetura.

---

## ✨ Funcionalidades
- Cadastro e gerenciamento de **Clientes**
- Cadastro e gerenciamento de **Motoristas**
- Registro e rastreamento de **Entregas**
- Atualização de status da entrega (`PENDENTE`, `EM ANDAMENTO`, `CONCLUÍDA`, `CANCELADA`)
- Busca de entrega por **código de rastreamento**
- Paginação e filtros avançados
- Validações com **Bean Validation**

---

## 🛠️ Tecnologias utilizadas
- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA / Hibernate**
- **Bean Validation**
- **MapStruct**
- **PostgreSQL** (ou outro banco relacional)
- **Maven**

---

## 🚀 Como rodar o projeto localmente

### 1. Pré-requisitos
Antes de começar, você precisa ter instalado na sua máquina:
- [Java 17+]
- [Maven]  
- [PostgreSQL](ou outro banco configurado)

---

### 2. Clonar o repositório
```bash
git clone https://github.com/SEU-USUARIO/delivery-tracer.git
cd delivery-tracer

---

### 3. Criar um banco de dados
Crie um banco no PostgreSQL, por exemplo:
CREATE DATABASE delivery_tracer;

---

### 4. Rodar a aplicação
mvn spring-boot:run
