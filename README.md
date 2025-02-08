# <img src="https://roadmap.sh/images/gifs/rocket.gif" width="25px"> Expense Tracker CLI

Aplicação com interface de linha de comando CLI(<i>Command Line Interface</i>) para gerenciamento de despesas pessoais.
<br>Projeto sugerido pelo [Roadmap.sh - **Backend Developer**](https://roadmap.sh/projects/expense-tracker).
## Features
- [x] **Adicionar Despesa:** Adiciona uma nova despesa com uma descrição e um valor.
  - [x] Cadastro da nova despesa e mensagem de retorno no terminal
  - [x] Armazenamento e Leitura da `Collection` em arquivo JSON
  - [x] Tratamento de Errors e Exceptions
  - [x] Adição de restrições de caracteres ao input `ammount`
- [x] **Atualizar Despesa:** Atualiza a descrição e valor de uma despesa a partir do ID.
- [x] **Deletar Despesa:** Remove uma despesa pelo ID.
- [x] **Listar Despesas:** Lista todas as despesas
- [ ] **Sumário Total das Despesas:** Retorna o valor total das despesas.
- [ ] **Sumário Mensal das Despesas:** Retorna o valor total das despesas de um mês específico.
- [ ] **Listar Despesas por Categoria:** Lista as despesas registradas em uma categoria específica.
  - [ ] Criar uma `enum` e registar as categorias de despesas
  - [ ] Adicionar o atributo `category` do tipo `enum` na entidade `Expense`
  - [ ] Atualizar a lógica de cadastro de despesa para o usuário atribuir uma categoria à novas despesas
  - [ ] Implementar uma função para filtrar a lista de despesas e imprimir de acordo com a categoria desejada
- [ ] **Definir Orçamento Mensal:** Permite o usuário atribuir um valor limite para despesas, recebendo um alerta quando a soma das despesas exceder o limite
- [ ] **Exportar Despesas para CSV:** Permitir o usuário exportar as lista de despesas para um arquivo CSV

## Instalação
## Instalação
1. **Clone o Repositório:**
```bash
git clone https://github.com/matheushug0/expense-tracker-cli.git
```
2. **Faça o build do projeto Maven (versão mínima recomendada: 3.6.3):**
```bash
mvn clean package install
```
3. **Execute a aplicação:**
```bash
java target/expense-tracker-cli [command]
```