# Spring Boot JDBC Project EduJournal

## Описание
Этот проект представляет собой приложение на **Spring Boot** с использованием **JDBC** для взаимодействия с базой данных.

## Требования
Перед запуском убедитесь, что у вас установлены:
- [JDK 17+](https://adoptium.net/)
- [Maven 3+](https://maven.apache.org/)
- База данных (MySQL)

## Настройка проекта

### 1. Клонирование репозитория
```sh
git clone https://github.com/kimoleg200301/edu-journal-backend.git
cd edu-journal-backend
```

### 2. Конфигурация базы данных
Настройте параметры подключения в `src/main/resources/application.properties`:
```properties
spring.session.jdbc.initialize-schema=always
spring.datasource.url=jdbc:mysql://localhost:3306/edu-journal-db
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
```

### 3. Сборка и запуск проекта
```sh
mvn clean install
mvn spring-boot:run
```

## Структура проекта
```
.
├── src/main/java/com/example/edujournalbackend
│   ├── group  # REST-контроллеры, Логика приложения, JDBC-репозитории, Классы сущностей группы
│   ├── journal     # журанла
│   ├── student  # студента
│   ├── subject       # Дисциплины
│   ├── config      # Конфигурация приложения
│   └── EduJournalBackendApplication.java # Точка входа
│
├── src/main/resources
│   ├── application.properties  # Конфигурация
│   ├── InitTables.sql   # Инициализация таблиц
│   └── InsertCounts.sql     # Начальные данные
│
├── pom.xml          # Maven-конфигурация
└── README.md        # Документация
```

## Основные команды

### Запуск в режиме разработки
```sh
mvn spring-boot:run
```

### Запуск тестов
```sh
mvn test
```

### Создание JAR-файла
```sh
mvn package
```

## API (Пример запроса)
После запуска приложение будет доступно по адресу `http://localhost:8080`.

Пример REST-запроса (GET):
```sh
curl -X GET http://localhost:8080//api/v1/students/
```

## Лицензия
Этот проект распространяется под лицензией MIT.

