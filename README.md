markdown_content = """# Nexus Commerce API

**Aviso de Estado:** Este projeto encontra-se atualmente em fase de desenvolvimento ativo. A arquitetura, os endpoints e as funcionalidades descritas abaixo estão sujeitos a melhorias e alterações contínuas.

## Visão Geral

O Nexus Commerce API é um sistema de back-end desenvolvido em Java utilizando a framework Spring Boot. O seu principal objetivo é fornecer uma infraestrutura centralizada, escalável e modular para plataformas de comércio eletrónico, gerindo todo o ciclo de vida das operações de vendas online.

A arquitetura do projeto baseia-se em princípios de design modular, dividindo as regras de negócio em domínios específicos para facilitar a manutenção e a implementação do padrão REST. Utiliza ativamente o padrão DTO (Data Transfer Object) para encapsulamento de dados e o padrão Builder para a construção de objetos complexos.

## Tecnologias e Ferramentas

* Linguagem: Java
* Framework Principal: Spring Boot
* Acesso a Dados: Spring Data JPA / Hibernate (arquitetura baseada em Repositories)
* Segurança: Spring Security
* Gestão de Dependências e Build: Maven
* Padrões de Design: REST, DTO, Builder, MVC

## Estrutura de Módulos (Domínios de Negócio)

A aplicação está estruturada de forma modular, organizando as responsabilidades nos seguintes domínios:

### 1. Acesso e Identidade (Access)
Responsável pela gestão de autenticação, autorização e dados demográficos dos clientes.
* Entidades: User, Role, Address.
* Funcionalidades: Gestão de utilizadores, associação de papéis (roles) para controlo estrito de acessos, e gestão de múltiplos endereços (envio e faturação).
* Validações: Implementação de validadores personalizados para documentos (ex: CpfOrCnpjValidator).
* Segurança: Configurações centralizadas através da classe SecurityConfig.

### 2. Catálogo (Catalog)
Gere a montra virtual e o inventário da plataforma.
* Entidades: Product, Category, Stock, DimensionsProduct.
* Funcionalidades: Estruturação hierárquica de categorias, gestão de produtos com especificações de dimensões físicas (para cálculo de portes), e controlo rigoroso de unidades em armazém através do módulo de Stock.

### 3. Vendas e Checkout (Sales)
O motor transacional da aplicação, responsável por processar as compras.
* Entidades: Order, OrderItem, Payment, Coupon.
* Funcionalidades: Processamento de itens do carrinho, conversão de estados de encomenda (OrderStatus, através de conversores JPA dedicados), gestão de métodos de pagamento (PaymentMethod) e aplicação de regras de desconto por cupões promocionais.

### 4. Interação Social (Social)
Focado no envolvimento, confiança e fidelização do utilizador.
* Entidades: ProductReview, ReviewPhoto, ProductFavorite.
* Funcionalidades: Sistema de classificação e comentários de produtos, capacidade de anexar evidências fotográficas às avaliações, e gestão de listas de desejos (produtos favoritos).

### 5. Auditoria e Logs (Log)
Garante a rastreabilidade e segurança contínua do sistema.
* Entidades: AuditLog.
* Funcionalidades: Registo histórico e categorização de ações críticas (ActionType) realizadas na plataforma, fornecendo uma base sólida para monitorização e resolução de problemas.

## Como Executar o Projeto

### Pré-requisitos
* Java Development Kit (JDK) instalado.
* Maven instalado (ou pode utilizar o Maven Wrapper incluído na raiz do projeto).

### Passos para Execução

1. Clonar o repositório:
   Saída de código
   File generated successfully: README-v2.md

```bash
   git clone <url-do-repositorio>
   cd nexus-commerce-api
Configurar o ambiente:
Verifique o ficheiro src/main/resources/application.properties. É necessário garantir que as credenciais da base de dados e as portas do servidor estão corretas para o seu ambiente local.

Iniciar a aplicação:
Utilizando o Maven Wrapper em ambientes Linux/macOS:

    Bash
    ./mvnw spring-boot:run
    Utilizando o Maven Wrapper em ambientes Windows:

    DOS
    mvnw.cmd spring-boot:run
    Testes
    O projeto inclui um perfil de configuração específico para testes (TestConfig), isolando o ambiente de desenvolvimento do ambiente de testes. Para garantir a integridade do código, execute:

    Bash
    ./mvnw test

Autor
Marco Medeiros.
"""