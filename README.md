## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

## Запуск приложения
 Для запуска приложения необходимо задать переменные окружения указанные в [application-sensitive.yaml](src/main/resources/application-sensitive.yaml).
 Мои переменные окружения прописаны в самой IDEA в Environment variables конфигурации запуска JiraRushApplication
---
## Список выполненных задач:
1. Разобраться со структурой проекта (onboarding). 
 - Выполнено
---
2. Удалить социальные сети: vk, yandex. 
 - Выполнено
---
3. Вынести чувствительную информацию в отдельный проперти файл 
 - вынес чувствительную информацию в файл [application-sensitive.yaml](src/main/resources/application-sensitive.yaml), которая будет задаваться переменными окружения
---
4. Переделать тесты так, чтоб во время тестов использовалась in memory БД (H2), а не PostgreSQL.
 - для этого создал отдельный класс [DataSourceConfig](src/main/java/com/javarush/jira/common/internal/config/DataSourceConfig.java) с бинами для выбора базы данных
 - создал отдельный [changelog_h2.sql](src/test/resources/changelog_h2.sql), изменил [application-test.yaml](src/test/resources/application-test.yaml), [data.sql](src/test/resources/data.sql) и переделал сами тесты для H2
---
6. Сделать рефакторинг метода com.javarush.jira.bugtracking.attachment.FileUtil#upload чтобы он использовал современный подход для работы с файловой системой.
 - [Выполнено](src/main/java/com/javarush/jira/bugtracking/attachment/FileUtil.java)
---
7. Добавить новый функционал: добавления тегов к задаче (REST API + реализация на сервисе). Фронт делать необязательно. Таблица task_tag уже создана.
 - для управления тегами в [TaskController](src/main/java/com/javarush/jira/bugtracking/task/TaskController.java) теперь есть три метода addTag, getTags и deleteTag.
---
9. Написать Dockerfile для основного сервера
 - [Выполнено](Dockerfile)
---
11. Добавить локализацию минимум на двух языках для шаблонов писем (mails) и стартовой страницы index.html.
 - добавил локализацию для [EN](src/main/resources/messages_en.properties) и [RU](src/main/resources/messages_ru.properties)
