# Nexus Commerce API

O **Nexus Commerce API** é uma solução completa e moderna para plataformas de e-commerce, projetada com arquitetura desacoplada em **Backend (Java / Spring Boot)** e **Frontend (React / Vite)**[cite: 1]. 

O sistema abrange desde a gestão de catálogo de produtos, controlo de stock e contas de utilizadores, até o ciclo de vida completo de pedidos, auditoria e avaliações de clientes[cite: 1].

---

##  Tecnologias Utilizadas

### **Backend**
- **Java 17+** / **Spring Boot**[cite: 1]
- **Spring Security** (Autenticação, autorização e gestão de acessos)[cite: 1]
- **Spring Data JPA & Hibernate** (Mapeamento objeto-relacional e persistência)
- **Maven** (Gestão de dependências e automação)[cite: 1]
- **Bean Validation** (Com validações customizadas, como validação de CPF/CNPJ)[cite: 1]

### **Frontend**
- **React**[cite: 1]
- **Vite** (Build tool e servidor de desenvolvimento de alta performance)[cite: 1]
- **JavaScript (ES6+) / JSX / CSS**[cite: 1]

---

## Roadmap & Próximas Integrações

O projeto encontra-se em desenvolvimento ativo. As seguintes funcionalidades e integrações externas estão planeadas no roadmap de desenvolvimento:

- [ ] **Integração com a API do Mercado Pago:** 
  - Processamento automatizado de pagamentos (PIX, Cartão de Crédito e Boleto).
  - Atualização do estado do pedido através de webhooks de notificação.
- [ ] **Integração com a API dos Correios:** 
  - Cálculo automático de prazos e valor de frete (SEDEX e PAC) no carrinho/checkout com base nas dimensões do produto[cite: 1].
  - Acompanhamento e rastreio de encomendas em tempo real.

---

## Estrutura do Projeto

```text
nexus_commerce_api/
│
├── backend/                  # API RESTful (Java / Spring Boot)[cite: 1]
│   ├── src/main/java/com/marcomedeiros/nexus_commerce_api/
│   │   ├── config/           # Configurações de Segurança e Testes[cite: 1]
│   │   ├── dto/              # DTOs (Access, Catalog, Sales, Social, Log)[cite: 1]
│   │   ├── model/            # Entidades JPA (User, Product, Order, Payment, etc.)[cite: 1]
│   │   ├── repository/       # Interfaces Spring Data JPA[cite: 1]
│   │   └── validation/       # Validadores customizados (@CpfOrCnpj)[cite: 1]
│   └── pom.xml               # Dependências do projeto[cite: 1]
│
└── frontend/                 # Aplicação Web (React / Vite)[cite: 1]
    ├── src/                  # Componentes e páginas React[cite: 1]
    ├── package.json          # Dependências do Node.js[cite: 1]
    └── vite.config.js        # Configurações do Vite[cite: 1]

```

---

## Módulos e Funcionalidades

* **Gestão de Acessos (`access`):** Registo de utilizadores, gestão de perfis (`Role`), endereços e dados pessoais com validação de documentos (`@CpfOrCnpj`).


* **Catálogo de Produtos (`catalog`):** Organização por categorias, especificações técnicas, peso/dimensões e controlo de stock em tempo real (`Product`, `Category`, `DimensionsProduct`, `Stock`).


* **Gestão de Vendas (`sales`):** Criação e acompanhamento de encomendas, cálculo de itens, aplicação de cupões de desconto e gestão de métodos de pagamento (`Order`, `OrderItem`, `Coupon`, `Payment`).


* **Avaliações e Social (`social`):** Sistema de produtos favoritos, avaliações de clientes (`Reviews`) e suporte a fotos anexadas às avaliações (`ReviewPhoto`).


* **Auditoria (`log`):** Módulo de log para rastreabilidade de ações executadas no sistema (`AuditLog`).



---

## Como Executar o Projeto

### **Pré-requisitos**

* **JDK 17** ou superior
* **Node.js** (v18+) e **npm**
* **Git**

---

### Inicializar o Backend (Spring Boot)

1. Entre no diretório do backend:
```bash
cd backend

```


2. Execute a aplicação via Maven Wrapper:
```bash
./mvnw spring-boot:run

```


*(No Windows, utilize: `mvnw.cmd spring-boot:run`)*

3. O servidor backend ficará ativo em: `http://localhost:8080`

---

### Inicializar o Frontend (React + Vite)

1. Entre no diretório do frontend:
```bash
cd frontend

```


2. Instale as dependências:
```bash
npm install

```


3. Inicie o servidor de desenvolvimento:
```bash
npm run dev

```


4. Aceda à aplicação no seu navegador (geralmente em `http://localhost:5173`).

---

## Licença

Este projeto está sob a licença MIT. Consulte o ficheiro `LICENSE` para mais detalhes.

```