# :star2: &nbsp;&nbsp; Ecoleta - Java Backend REST API

Implementação do backend em Java para a aplicação [Ecoleta_NLW-01](https://github.com/vrfvitor/Ecoleta_NLW-01) (backend original NodeJS - link para mais informações). Neste projeto utilizo os recursos do Java 8, Maven, MySQL, SpringBoot e os módulos Spring Web e Spring Data JPA.

## :mag: &nbsp;&nbsp; Geral

Este projeto está pronto para ser utilizado pelo Front e Mobile de Ecoleta, bastando aplicar nesses, alterações mínimas como atualização da porta nos Services e configuração das interfaces para corresponder a forma como os dados são enviados por aqui. O CORS foi configurado para permitir acesso de qualquer origem.

## :computer: &nbsp;&nbsp; Features

### :inbox_tray: &nbsp;&nbsp; EndPoints e Tratamento de Arquivos

Afim de manter a aplicação simples e condizente com o que foi desenvolvido no backend Ecoleta original, neste projeto temos apenas duas endpoints e métodos que front e mobile realmente vão fazer requisições para. <br/>
Aqui destaco somente o suporte ao upload de imagem via Multpart Form-Data, que é processada pela aplicação e salva em **static/uploads** (sendo **static** uma pasta especial do Spring, configurada para acesso a arquivos estáticos de maneira pública). Dessa forma, imagens salvas na aplicação podem ser acessadas pela então outra "endpoint": **uploads/***.

### :floppy_disk: &nbsp;&nbsp; Persistência e Acesso a Dados

Valendo das facilidades providas pelo uso do Spring Data e o uso das anotações do JPA, a camada de persistência ficou bem simplificada. Configurações do DataSource estão naturalmente no application.properties.
* A aplicação foi configurada para fazer um load de dados no banco assim que a aplicação é inicializada, e as queries executadas estão no arquivo [data.sql](src/main/resources/data.sql).
* Maior parte dos métodos usados para hits ao banco são os padrões da interface JpaRepository, mas foi preciso escrever meu próprio Query Method para busca com JOIN e condicionais.

### :closed_lock_with_key: &nbsp;&nbsp; Data Transfer Objects e Validation

Utilizo o DTO pattern tanto para disponibilizar quanto para receber dados de fora da API, afim de definir o design da aplicação, garantindo mais controle do fluxo de dados, com Outputs que contêm apenas as informações essenciais para determinada view do frontend. Os Inputs ainda contam com validação, própria do Jakarta Bean Validation com anotações como `@NotNull` e `@Email` por exemplo.
