# Sklep z filmami

Projekt realizuje opcję Java EE / Spring.

## Zakres funkcjonalny

- przeglądanie katalogu filmów,
- filtrowanie katalogu według kategorii,
- podgląd szczegółów pojedynczego filmu wraz z opisem fabuły,
- koszyk zakupów przechowywany w sesji HTTP,
- dodawanie i usuwanie filmów z koszyka,
- podliczanie całkowitej wartości koszyka,
- przechowywanie filmów w bazie danych przez JPA/Hibernate.

Projekt celowo nie implementuje klientów, logowania, składania zamówień ani administracji katalogiem, bo te elementy są wyłączone z wymagań.

## Technologie

- Java 8,
- Spring Boot 2.7,
- Spring MVC,
- Thymeleaf,
- Spring Data JPA,
- Hibernate,
- H2 Database,
- Maven.

## Uruchomienie

W tym środowisku Maven powinien używać lokalnego repozytorium w katalogu projektu:

```bash
mvn -Dmaven.repo.local=.m2/repository spring-boot:run
```

Po starcie aplikacja jest dostępna pod adresem:

```text
http://localhost:8080/movies
```

Konsola H2:

```text
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:./data/movie-shop
User: sa
Password:
```

## Weryfikacja

```bash
mvn -Dmaven.repo.local=.m2/repository test
```
