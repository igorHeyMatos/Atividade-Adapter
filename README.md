# 📱 Sistema de Gerenciamento de Mídias Sociais

## 📋 Sobre o Projeto

Este projeto implementa um **sistema unificado** para o gerenciamento de múltiplas plataformas de mídia social utilizando o padrão de projeto **Adapter**.  
Com ele, é possível **publicar conteúdo**, **agendar postagens**, **obter estatísticas** e **deletar publicações** de forma **padronizada**, centralizando a comunicação entre diferentes redes.

---

## 🏗️ Arquitetura e Funcionamento

### 🔌 Padrão Adapter

O sistema aplica o **padrão Adapter** para transformar as APIs específicas de cada plataforma (Instagram, Twitter, LinkedIn, TikTok) em uma **interface comum**, permitindo que o código cliente (usuário) interaja com todas elas de forma unificada e consistente.

---

### 🧩 Componentes Principais

#### **1. 🎯 Interface Base – `GerenciadorMidiaSocial`**

Define o contrato para todas as plataformas, padronizando operações essenciais:

- `publicarConteudo()` → Publica conteúdo imediatamente  
- `agendarPublicacao()` → Agenda uma publicação para data e hora futuras  
- `obterEstatisticas()` → Retorna métricas de engajamento (curtidas, visualizações etc.)  
- `deletarPublicacao()` → Remove uma publicação pelo seu ID  
- `getNomePlataforma()` → Retorna o nome da plataforma  

---

#### **2. 🔄 Adapters Implementados**

Cada rede social possui sua própria implementação do adapter, responsável por traduzir as chamadas genéricas para o formato específico da API da plataforma:

- **📷 `InstagramAdapter`**  
- **🐦 `TwitterAdapter`**  
- **💼 `LinkedInAdapter`**  
- **🎵 `TikTokAdapter`**

---

#### **3. 🏭 Factory – `SocialMediaFactory`**

Responsável por **gerenciar e instanciar adapters** dinamicamente, conforme as configurações de habilitação de cada plataforma.

Funções principais:
- Habilitar ou desabilitar redes sociais;
- Criar instâncias específicas de adapters;
- Validar plataformas disponíveis no sistema.

---

#### **4. ⚙️ Serviço Central – `SistemaGerenciamentoMidiaSocial`**

Classe que **orquestra todas as operações**, coordenando as ações de publicação, agendamento, obtenção de estatísticas e deleção entre múltiplas plataformas.

Funções principais:
- Publicar em uma ou todas as plataformas simultaneamente;  
- Agendar postagens com data/hora específicas;  
- Consultar métricas de engajamento;  
- Excluir publicações por ID.

---

#### **5. 🎮 Classe Consumidora – `ConsumidorSistemaMidiaSocial`**

Simula o uso do sistema por meio de exemplos práticos.  
Nesta classe, são demonstradas todas as funcionalidades:

- **Publicação em todas as plataformas**  
- **Agendamento de publicação no Instagram**  
- **Deleção de uma publicação do Twitter**  
- **Consulta de estatísticas de engajamento**
