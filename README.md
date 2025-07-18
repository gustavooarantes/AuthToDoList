<h1>Task Manager API</h1>
<h3>Developer: Gustavo Arantes</h3>

<hl>

<h2>Technologies Used on the Project</h2>
<ul>
  <li>Java SE 17</li>
  <li>Spring Boot & Framework</li>
  <li>PostgreSQL</li>
</ul>

<h2>Spring Dependencies</h2>
<p>Spring Web for creating the REST API endpoints</p>
<p>Spring Data JPA as the ORM for data persistence and retrieval</p>
<p>Spring Security and JWT for authentication, authorization and protection</p>
<p>Spring PostgreSQL Driver for making the connection to the main database for the application</p>
<p>Lombok and DevTools to make my life a little easier lol</p>

<hl>

<h2>Project Structure</h2>
<pre>
.
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── gustavooarantes
    │   │           └── authtodo
    │   │               ├── Config
    │   │               │   ├── Exception
    │   │               │   └── Security
    │   │               ├── Controller
    │   │               ├── DTO
    │   │               ├── Model
    │   │               ├── Repository
    │   │               └── Service
    │   └── resources
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── gustavooarantes
                    └── authtodo
</pre>
