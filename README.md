# ***Status Code 404: El Buen Sabor***

El delivery de comidas de la ciudad “El Buen Sabor” ofrece a sus clientes una amplia variedad de bebidas y de comidas de fabricación propia. Posee un horario de atención de lunes a domingos de 20:00 a 00:00, y de sábados y domingos de 11:00 a 15:00. Los clientes tienen a disposición un menú que describe para cada una de las comidas, el nombre, los ingredientes y el precio. Los clientes realizan sus pedidos en el mostrador del local mediante una PC o pueden hacerlo en forma remota desde su casa o su celular personal. 

### Integrantes 

- [Matías Barreto](https://github.com/MatiasBarreto93) 

- [Jonathan Videla](https://github.com/jonathanvidela94)

### Wireframes Proyecto
🔗 [El Buen Sabor - Figma](https://www.figma.com/file/3y2S3mpFNwlaaWTVIAhapU/Landing-Page?t=STa59lO90YlC98Zt-6)

### Languages and Tools:

<p align="left">
    <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/></a>
    <a href="https://www.w3schools.com/css/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/css3/css3-original-wordmark.svg" alt="css3" width="40" height="40"/></a>
    <a href="https://www.w3.org/html/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/html5/html5-original-wordmark.svg" alt="html5" width="40" height="40"/></a>
    <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/javascript/javascript-original.svg" alt="javascript" width="40" height="40"/></a>
    <a href="https://reactjs.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/react/react-original-wordmark.svg" alt="react" width="40" height="40"/> </a>
    <a href="https://www.figma.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/figma/figma-icon.svg" alt="figma" width="40" height="40"/></a>
    <a href="https://nodejs.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/nodejs/nodejs-original-wordmark.svg" alt="nodejs" width="40" height="40"/></a>
    <a href="https://expressjs.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/express/express-original-wordmark.svg" alt="express" width="40" height="40"/></a>
    <a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/> </a>
    <a href="https://www.linux.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/linux/linux-original.svg" alt="linux" width="40" height="40"/></a>
</p>

# Deployment

## Configuracion de Auth0

[![name](https://upload.wikimedia.org/wikipedia/commons/thumb/5/5b/Logo_de_Auth0.svg/640px-Logo_de_Auth0.svg.png)](https://auth0.com/)

1. Antes de continuar si aun no has configurado Auth0 para ser utilizado en el Front-End porfavor dirijete a esta guia [Guia](https://github.com/MatiasBarreto93/El-Buen-Sabor-Front/tree/master)

2. Para ejecutar el proyecto es necesario crear un archivo .env en la raiz del proyecto con lo siguientes campos:

```bash
DATABASE_HOST=
DATABASE_PORT=
DATABASE_NAME=
DATABASE_USERNAME=
DATABASE_PASSWORD=

JWT_ISSUER_URI=
AUTH0_AUDIENCE=
ALLOWED_ORIGINS=*

AUTH0_DOMAIN=
AUTH0_API_CLIENT_ID=
AUTH0_API_CLIENT_SECRET=
AUTH0_API_AUDIENCE=
```
3. Con respecto a los datos que vamos a obtener de Auth0 son los mismos que en la configuracion del archivo .env en el Front-End, a exepcion de "JWT_ISSUER_URI", deberia quedar asi: "Domain" entre ("https://" y "/"). Ejemplo:
```bash
JWT_ISSUER_URI=https://dev-1kii45340wusnxas.us.auth0.com/
```

4. Continuamos con la configuracion del archivo .evn con los datos de nuestra Base de Datos MySQL. Ejemplo:
```bash
DATABASE_HOST=containers-br-east-101.railway.app
DATABASE_PORT=6841
DATABASE_NAME=buensabor_db
DATABASE_USERNAME=root
DATABASE_PASSWORD=JASJHg987JKDShhwsw
```

5. Una vez configurado el archivo .env, nos dirijimos a la carpeta "resources" y abrimos el archivo "application.properties", el cual deberia quedar de la siguiente manera:
```bash
spring.datasource.url=jdbc:mysql://${DATABASE_HOST}:${DATABASE_PORT}/${DATABASE_NAME}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql: true

spring.security.oauth2.resourceserver.jwt.issuer-uri=${JWT_ISSUER_URI}
auth0.audience=${AUTH0_AUDIENCE}
web.cors.allowed-origins=${ALLOWED_ORIGINS}
```

## Documentacion Auth0

[Auth0 Documentation](https://auth0.com/docs)

[Auth0 SPA React Quickstart](https://auth0.com/docs/quickstart/spa/react/interactive)

[Auth0 APIs](https://auth0.com/docs/get-started/auth0-overview/set-up-apis)

[Auth0 Roles](https://auth0.com/docs/manage-users/access-control/configure-core-rbac/roles)

[Authentication and Authorization Flows](https://auth0.com/docs/get-started/authentication-and-authorization-flow)


[API AUTH0 MANAGEMENT](https://auth0.com/docs/api/management/v2)



## Ejecucion del proyecto

1. Clona el repositorio o descargalo como archivo ZIP y extraelo en una carpeta de tu eleccion
```bash
git clone https://github.com/jonathanvidela94/El-Buen-Sabor-Back
```

2. Abre la terminal y asegurate estar en el directorio raiz del proyecto
```bash
D:\...\...\...\El-Buen-Sabor-Back> 
```

3. Asegurate de configurar Auth0 y colocar el archivo .env en el directorio raiz del proyecto y tambien modificar el archivo "application.properties" para utilizar los datos del archivo .env, si esto ya lo hiciste pasa al siguiente paso.

4. Ejecuta el siguiente comando en la terminal desde el directorio raiz para compilar y descargar todas las dependencias necesarias para el funcionamiento correcto del programa
```bash
gradlew build
``` 

5. Una vez que hayas compilado el proyecto, ejecuta el siguiente comando para correr la aplicacion.
```bash
gradlew bootRun
```
6. Si todo es correcto deberas ver en consola que se inicio una aplicacion en el puerto "http://localhost:8080".

7. Fin de la configuracion
