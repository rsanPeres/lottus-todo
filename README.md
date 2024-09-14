<img src="zicons/flor-de-lotus.png" alt="Flor de Lótus" width="100"/> 

<p align="center"><h1 align="center">
  Lottus To-do</p>
</h1 ><p align="center">
</p>
<p align="center">
API para listas de to-do 
<br/>

<br>
<br>

<a href="https://docs.oracle.com/en/java/">
<img src="https://img.shields.io/badge/Java-21-blueviolet">
</a>
<a href="https://www.docker.com/products/docker-desktop/">
<img src="https://img.shields.io/badge/Docker%20-🐳-brigthgreen">
</a>


<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-executando-a-api">Executando</a> •
 <a href="#-equipe-de-desenvolvimento">Equipe</a> • 
 <a href="#-contato">Contato</a> • 
 <a href="#-documentação-de-referência">Documentação</a>
</p>

## Sobre o Projeto 🦄
Esta API foi criada como um monolito mudular (Modulith Spring), baseada na estrutura proposta pelo DDD.


## Executando a API 🎲

```bash

# Clone este repositório
$ git clone git@github.com:rsanPeres/lottus-todo.git

# Tenha instalado em sua maquina o maven e o java 21
# Se puder tenha o docker instalado em sua maquina

# vá até a pasta do projeto
$ cd lottus-todo

# configure o arquivo do docker compose com a senha e usuário que desejar
# rode o docker-copose para criar um banco de dados para a aplicação
$ docker-copose up

# abra o application.yaml e fonfigure com as caracteristicas do seu banco de dados
# Configure a IDE para executar o arquivo LottusTodoCoreApplication.java presente no modulo web
# Execute a aplicação na sua IDE de preferência

# Ela inciará na porta:8780 - acesse o swagger http://localhost:8780/swagger-ui/index.html

# Se preferir, utilize essa collection para o postman
# https://api.postman.com/collections/14743252-7e86fd01-f5ab-4046-a4e7-7b300252ec9c?access_key=PMAT-01J7SAB35XFK4NWP0H9ZYFRY60

```

## Equipe de Desenvolvimento ☕️
<table>
  <tr>
    <td align="center"><a href="https://www.linkedin.com/in/rafaela-peres-2731a324b/"><img src="https://media.licdn.com/dms/image/C4E03AQHt44-mhnCsTQ/profile-displayphoto-shrink_800_800/0/1662482652839?e=1687996800&v=beta&t=mrhEvQQm_f-Hz2Q3WAdDj9ALXcNubJfLOP2iMlLLPFk" width="100px;" alt="" title="Rafaela Peres 🌟"/><br /><sub><b>Rafaela Peres</b></sub></a></td>
    </tr>
</table>

## Contato 👨‍💻
*rsan.peres@gmail.com*

### Documentação de referência
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.3/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#using.devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Modulith](https://docs.spring.io/spring-modulith/reference/)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#howto.data-initialization.migration-tool.flyway)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

