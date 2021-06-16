ОПИСАНИЕ ЗАДАЧИ:

Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями: Writer (id, firstName, lastName, List posts) Post (id, content, created, updated, List labels) Label (id, name) PostStatus (enum ACTIVE, UNDER_REVIEW, DELETED)

ТРЕБОВАНИЯ:

Придерживаться шаблона MVC (пакеты model, repository, service, controller, view)
Для миграции БД использовать https://www.liquibase.org/
Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito).
Для импорта библиотек использовать Maven
Результатом выполнения проекта должен быть отдельный репозиторий на github, с описанием задачи, проекта и инструкцией по локальному запуску проекта.

ТЕХНОЛОГИИ:

1) Java
2) MySQL
3) JDBC
4) Maven
5) Liquibase
6) JUnit
7) Mockito

ШАБЛОНЫ ПРОЕКТИРОВАНИЯ:

1) Singleton
2) Factory method
3) MVC

ИНСТРУКЦИЯ ПО ЗАПУСКУ ПРИЛОЖЕНИЯ:

1) Клонировать удалённый репозиторий на локальный.
2) Настроить файл liquibase.properties для корректной работы с БД.
3) Запустить класс Main.
