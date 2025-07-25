

<img src="https://github.com/gitdaniellopes/Movie-App/assets/26637908/6ad756db-2ac7-49de-ae46-cea420221dfa" width="1920">

# 🎬 MovieApp

MovieApp é um aplicativo Android desenvolvido com **Kotlin** que permite aos usuários explorar filmes, visualizar detalhes e gerenciar favoritos. O projeto adota as melhores práticas de arquitetura e desenvolvimento Android moderno, com foco em escalabilidade, performance e qualidade de código.

## 📱 Tecnologias Utilizadas

- **Kotlin** – Linguagem oficial para desenvolvimento Android.
- **Retrofit** – Para consumo de APIs RESTful.
- **Room** – Banco de dados local para persistência de dados.
- **MVVM** – Arquitetura que separa responsabilidades e melhora a testabilidade.
- **Jetpack Components** – Incluindo LiveData, ViewModel e Navigation.
- **JUnit & Mockito** – Para testes unitários e de instrumentação.

## 🛠️ Funcionalidades

- Buscar e listar filmes por categorias.
- Exibir detalhes de cada filme, como sinopse, elenco e nota.
- Marcar/desmarcar filmes como favoritos (salvos localmente via Room).
- Layout adaptado para diferentes tamanhos de tela.
- Aplicação testada para garantir estabilidade e confiabilidade.

## 🧪 Testes

O MovieApp enfatiza a importância dos testes automatizados. Foram implementados:

- Testes unitários dos ViewModels e UseCases.
- Testes de integração com o Room Database.
- Mock de chamadas de API com Retrofit e Mockito.

## 📦 Estrutura do Projeto

movieapp/
│
├── data/ # Camada de dados
│ ├── local/ # Banco de dados local (Room)
│ │ ├── dao/ # Data Access Objects
│ │ └── entities/ # Entidades do banco de dados
│ ├── remote/ # Comunicação com API (Retrofit)
│ │ ├── api/ # Interfaces de serviços Retrofit
│ │ └── responses/ # Modelos de resposta da API
│ └── repository/ # Implementações dos repositórios
│
├── domain/ # Camada de domínio
│ ├── model/ # Modelos de domínio
│ ├── repository/ # Interfaces dos repositórios
│ └── usecase/ # Casos de uso
│
├── presentation/ # Camada de apresentação (UI)
│ ├── ui/ # Fragments, Activities e Composables
│ │ └── screens/ # Telas do app organizadas por feature
│ ├── viewmodel/ # ViewModels
│ └── adapter/ # Adapters (RecyclerView, etc.)
│
├── di/ # Injeção de dependências (Hilt ou Koin)
│
├── utils/ # Classes utilitárias, extensões etc.
│
├── test/ # Testes unitários
│
├── androidTest/ # Testes instrumentados (UI tests)
│
└── build.gradle # Configuração do projeto


## 🚀 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/movieapp.git

Abra o projeto no Android Studio.

Configure a chave de API para o serviço de filmes (ex: TMDb).

Execute o app em um dispositivo físico ou emulador.

## 📄 Licença

Este projeto está licenciado sob a MIT License.

Desenvolvido com 💙 por Estefânia Pinheiro
