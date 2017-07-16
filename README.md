Repositório de desenvolvimento do projeto CARONAS CWI 2017-1

Descrição:
O conjunto de arquivos contidos no repositório, trata-se de um projeto em desenvolvimento que visa diminuir o número de carros que se deslocam diariamente até a empresa CWI Software através do compartilhamento de vagas não utilizadas ao realizar a ida e vinda para a empresa.
O projeto foi desenvolvido pelos estudantes Aléxia de Jesus Dorneles Pereira, Christopher da Rosa Michel e Deórdines Tomazi, tendo como Product Owner o bacharel em sistemas de informação Carlos Henrique Nonnemacher e como líder técnico o bacharel em sistemas de informação Éverton Zanatta.

Requisitos:
É necessário ter no ambiente uma IDE para executar o projeto e utilizar a webAPI, banco de dados ORACLE e alguma plataforma para colocar no ar o servidor da aplicação, como NodeJS ou PowerShell.

Primeiros passos:
Para o banco de dados Oracle
-Logar em um perfil com permissão que criar um novo usuário;
-Utilizar o comando "Create user caronas identified by caronas" para criar o usuário;
-Utilizar o comando "grant connect, resource, create view to caronas" para dar as permissões ao usuário;
-Criar uma nova conexão utilizando o usuário caronas.

Para a webAPI
-Abrir o projeto caronas, que se encontra na pasta backend, em alguma IDE com suporte para java e executar o projeto.

Para a aplicação
-Colocar no ar um servidor a partir da pasta frontend.

Modo de uso:
Ao entrar na aplicação, o usuário será direcionado para a tela de login social, podendo optar por google ou facebook.
Após logar, haverá quatro telas para interação: cadastrar rotina, buscar carona, minhas rotinas, meus grupos.
Ao logar pela primeira vez, o usuário será direcionado para cadastrar uma nova rotina, para que possa usufruir das demais funcionalidades da aplicação. Nessa tela, haverá dois modos de cadastro. Cadastrar como passageiro, na qual o objetivo é buscar pessoas que possam lhe conceder carona ou cadastrar como motorista, na qual o objetivo é o usuário ceder vagas disponíveis em seu carro para pessoas que estão próximas ao seu trajeto, em função de seu destino final ser coincidente.
Após isso, caso cadastrada uma rotina como passageiro, o usuário poderá acessar a tela de buscar carona e pesquisar por possíveis pessoas que passam próximas ao seu ponto de partida e dispostas a lhe darem carona.
Caso cadastrada uma rotina como motorista, o usuário automáticamente estará criando um grupo para posteriormente estar recebendo solicitações de pessoas que desejam ocupar uma das vagas que o usuário disponibilizou, em outras palavras, pertencer ao seu grupo.
