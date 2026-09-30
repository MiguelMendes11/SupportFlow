# SupportFlow

## Sobre o projeto

O SupportFlow é um aplicativo Android para gerenciamento de chamados de suporte. Com ele é possível cadastrar, listar, visualizar os detalhes e excluir chamados.

Cada chamado possui as seguintes informações:

- título;
- cliente;
- prioridade (Alta, Média, Baixa);
- status (Pendente, Em andamento, Concluído);
- descrição opcional.

O aplicativo guarda os dados localmente no dispositivo, sem depender de internet ou de serviços externos.

## Funcionalidades

- Listagem dos chamados em cartões, com prioridade e status em destaque;
- Cadastro de novo chamado por meio de formulário;
- Validação do formulário: título e cliente são obrigatórios, e o botão de salvar fica desabilitado enquanto estiverem vazios;
- Visualização dos detalhes do chamado;
- Exclusão do chamado com diálogo de confirmação;
- Persistência local dos dados com Room;
- Estados de interface de carregamento, conteúdo, lista vazia e erro (com opção de tentar novamente);
- Navegação entre as telas de lista, formulário e detalhes.

O projeto também mantém a implementação em Android Views/XML correspondente à primeira etapa acadêmica, com `MainActivity`, `DetalheActivity`, layouts em XML, RecyclerView e ViewBinding.

## Tecnologias utilizadas

- Kotlin 2.2.10;
- Android SDK (minSdk 24, compileSdk e targetSdk 37);
- Jetpack Compose (Compose BOM 2026.08.00);
- Material 3;
- Navigation 3 (1.2.0), com `kotlinx.serialization` para as chaves de navegação;
- ViewModel (`lifecycle-viewmodel`);
- StateFlow e Kotlin Coroutines;
- Room (2.8.5) com KSP;
- Lifecycle Runtime Compose (coleta de estado com `collectAsStateWithLifecycle`);
- ViewBinding, RecyclerView e ConstraintLayout, referentes à etapa Android Views/XML.

## Arquitetura e organização

A aplicação segue um fluxo simples entre camadas:

**UI (Compose) → ViewModel → Repository → Room/DAO**

- A `ChamadosUiState` é uma interface selada com os estados `Loading`, `Content`, `Empty` e `Error`. A tela apenas renderiza o estado recebido, sem precisar saber de onde ele veio.
- O `ChamadosViewModel` expõe esse estado como `StateFlow`, observando o repositório de forma reativa: quando o banco muda, a lista é atualizada sozinha.
- A camada de dados é separada da interface por meio da interface `ChamadoRepository`, implementada por `ChamadoRepositoryImpl`, que conversa com o `ChamadoDao` do Room.
- A navegação é feita com Navigation 3, usando uma pilha de telas (`Lista`, `Formulario` e `Detalhe`) montada no `ChamadosNavGraph`.

Assim, a UI não acessa o banco diretamente: ela conversa com o ViewModel, que conversa com o repositório, que por fim acessa o Room.

## Como executar o projeto

1. Clone o repositório do projeto;
2. Abra a pasta clonada no Android Studio;
3. Aguarde a sincronização do Gradle terminar;
4. Escolha um emulador Android ou um dispositivo físico compatível;
5. Clique no botão **Run** para executar o módulo `app`.

Não há necessidade de configurar API, arquivo `.env`, senha ou banco externo: o projeto usa persistência local e não contém secrets.

Requisitos verificados no projeto:

| Item | Valor |
| --- | --- |
| Gradle (wrapper) | 9.5.0 |
| Android Gradle Plugin | 9.3.3 |
| Kotlin | 2.2.10 |
| compileSdk / targetSdk | 37 |
| minSdk | 24 |
| JDK | 17 |

## Fluxo principal

1. Abrir o aplicativo;
2. Visualizar a lista de chamados;
3. Criar um novo chamado pelo botão "Novo chamado";
4. Preencher e salvar o formulário;
5. Ver o chamado recém-criado na lista;
6. Abrir o chamado para ver os detalhes;
7. Excluir o chamado, se desejado (com confirmação).

Os dados são armazenados localmente pelo Room e permanecem após reiniciar o aplicativo.

## Etapas acadêmicas

**Etapa 1 — Android Views/XML**

Implementação com layouts XML, RecyclerView, Intent explícita entre `MainActivity` e `DetalheActivity`, ViewBinding e dados iniciais em mock. Essa implementação continua no projeto, junto com os arquivos de mock da primeira versão.

**Etapa 2 — Jetpack Compose**

Implementação com Jetpack Compose, Navigation 3, ViewModel com `StateFlow`, `ChamadosUiState`, Repository, Coroutines e Room. A Activity de inicialização do aplicativo é a que carrega o gráfico de navegação em Compose.

## Observações

- Não são necessárias chaves de API;
- Não é necessário servidor externo ou conexão com a internet;
- O banco do Room é criado localmente na primeira execução do aplicativo.
