# SupportFlow

Aplicativo Android para gestão de chamados de suporte, desenvolvido com **Android Views e layouts em XML**.

## Objetivo

Listar os chamados de suporte de um atendente e permitir a visualização dos detalhes de cada chamado, exercitando os fundamentos da plataforma: layouts XML, componentes de interface, navegação entre Activities e passagem de dados.

## Funcionalidades atuais

- Listagem dos chamados em cartões, com título, cliente, prioridade e status;
- Abertura da tela de detalhes ao tocar em um card;
- Exibição completa do chamado: título, cliente, descrição, prioridade e status;
- Retorno à lista pela ação "Voltar";
- Mensagem de lista vazia quando não há chamados;
- Dados 100% simulados, sem acesso a rede ou serviços externos.

## As duas telas (Android Views / XML)

| Tela | Activity | Layout |
| --- | --- | --- |
| Lista de chamados | `MainActivity` | `app/src/main/res/layout/activity_main.xml` |
| Detalhe do chamado | `DetalheActivity` | `app/src/main/res/layout/activity_detalhe.xml` |

Os layouts utilizam `ViewGroup`s clássicas (`FrameLayout`, `LinearLayout`, `ScrollView`) e componentes como `TextView`, `ImageView` e `Space`. O item da lista é definido em `app/src/main/res/layout/item_chamado.xml`.

## RecyclerView

A lista é renderizada por um `RecyclerView` com `LinearLayoutManager` e o adaptador `ChamadoAdapter`, que infla `item_chamado.xml` para cada chamado e aplica cor de destaque conforme prioridade e status.

## ViewBinding

O projeto habilita `viewBinding = true` em `app/build.gradle.kts` e todas as Activities e o adaptador acessam as views pelos bindings gerados (`ActivityMainBinding`, `ActivityDetalheBinding`, `ItemChamadoBinding`), sem `findViewById`.

## Navegação e passagem de dados

A navegação entre as telas usa **Intent explícita**:

```kotlin
val intent = Intent(this, DetalheActivity::class.java).apply {
    putExtra(DetalheActivity.EXTRA_CHAMADO, chamado)
}
startActivity(intent)
```

O objeto `Chamado` é recebido em `DetalheActivity` via `getSerializableExtra` e renderizado na tela de detalhes.

## Modelo de dados

`Chamado` é uma **data class imutável** (apenas `val`), acompanhada dos enums `Prioridade` e `Status`:

```kotlin
data class Chamado(
    val id: Int,
    val titulo: String,
    val cliente: String,
    val prioridade: Prioridade,
    val status: Status,
    val descricao: String? = null
) : Serializable
```

## Descrição opcional

`descricao` é opcional (`String?`). Na tela de detalhes, quando o valor é nulo a interface exibe o texto alternativo `R.string.sem_descricao` ("Sem descrição"). O recebimento do extra também é tratado de forma segura (`as? Chamado`), encerrando a Activity caso o dado não seja válido.

## Dados simulados

Os chamados exibidos em runtime vêm de `ChamadosMock`, um objeto com uma lista fixa de `Chamado`. `MainActivity` carrega essa lista diretamente e a repassa ao `ChamadoAdapter`. Não há chamada de API nem armazenamento local no projeto.

## Como executar

1. Clone o repositório:
   ```
   git clone <url-do-repositorio>
   ```
2. Abra a pasta clonada no **Android Studio** e aguarde a sincronização do Gradle;
3. Selecione um emulador ou dispositivo físico;
4. Clique em **Run** para instalar e executar o módulo `app`.

Para gerar o APK de depuração pela linha de comando:

```
.\gradlew.bat clean
.\gradlew.bat :app:assembleDebug
```

O APK é gerado em `app/build/outputs/apk/debug/`. Não é necessário configurar chaves, servidor, internet ou qualquer serviço externo.
