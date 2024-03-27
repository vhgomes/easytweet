## Uma versão simplificada da rede social Twitter

O Twitter é uma plataforma de mídia social onde os usuários podem compartilhar pensamentos e notícias em mensagens curtas de até 280 caracteres, chamadas de "tweets". Ele é usado para expressar opiniões, divulgar informações e interagir com outros usuários em tempo real.

# Features implementadas

- [x] Registro de novos usuarios
- [x]  Login com JWT
- [x]  Sistema de Roles (Admin e Common)
- [x]  Fazer um tweet
    - [x] Deletar um tweet
    - [x] Repostar um tweet
- [x] Sistema de feed
    - [x] Pegar todos os tweets
    - [x] Paginação dos Tweets
    - [x] Mostrar os mais recentes
    - [ ] Filtrar apenas por os tweets das pessoas que o usuario segue

## Tecnologias Usadas
### Java
O projeto é construído principalmente em Java, uma linguagem de programação amplamente utilizada devido à sua eficiência e flexibilidade.
### Spring Boot
O framework Spring Boot é a espinha dorsal do backend, fornecendo uma estrutura ágil e eficiente para o desenvolvimento de aplicativos. Ele simplifica a configuração e facilita a criação de APIs RESTful.
### Docker
O Docker é empregado para criar contêineres, permitindo uma implantação consistente e escalável da aplicação em diferentes ambientes. Isso garante portabilidade e facilita a manutenção do sistema.
### MySQL
Para armazenar e gerenciar os dados, utilizamos o banco de dados relacional MySQL. Sua robustez e confiabilidade tornam-no uma escolha sólida para lidar com os requisitos de persistência de dados da aplicação.
### JWT (JSON Web Token) e Spring Boot Security
Para garantir a segurança da aplicação, implementamos o JWT em conjunto com o Spring Boot Security. Essas tecnologias fornecem uma camada de autenticação e autorização sólida, garantindo que apenas usuários autorizados tenham acesso aos recursos da aplicação.
### Data JPA
O Data JPA é utilizado para simplificar o acesso e a manipulação dos dados do banco de dados. Ele proporciona uma integração suave entre a aplicação e a camada de persistência, facilitando a implementação de operações de CRUD (Create, Read, Update, Delete) e consultas complexas.