# ProjetoJava-Desktop-Swing
Primeiro projeto que fiz para meu portfólio Java Backend, é uma aplicação desktop usando Swing para gerenciamento de uma loja.

Criei um banco de dados para a loja, todas as interações do sistema com o banco de dados são feitas atraves de Procedures ou Views, assim não teria como quem usa o sistema saber o nome real de nada presente no banco de dados, fiz triggers que monitoram cada loggin e operação do funcionario e uma Trigger que toda vez que um pedido for feito a quantidade de produtos restantes vai ser atualizada.


Dependendo de quem loga(Operador ou Gerente) as funcionalidades disponiveis diferem.

Funcionalidades Operador:

Cadastrar Cliente - Cadastra os dados do cliente no banco de dados da loja.
Cadastrar Produto - Cadastra uma lista de Produtos no banco de dados, podem ser varios ou apenas 1.
Consultar Pedido - Mostra na tela os dados do pedido escolhido, tem a opção de mostrar todos os pedidos.
Encomendar Pedido - Sobe um novo pedido no banco de dados com os dados passados, com o status PENDENTE e sem Data Final.
Subir Pedido - Sobe um pedido no banco de dados, já fechado, usado para compras feitas no local.


Funcionalidades gerente:
Cadastrar Funcionario - Após contratar um novo funcionario usar esta função para cadastra-lo no banco de dados da loja.
Cadastrar Usuario do Sistema - Cria um usuario e senha para o usuario poder usar o sistema da loja.
Emitir Relatório - Emite um relatório mostrando todos os pedidos feitos por mes e ano e o total faturado naquele mes.
