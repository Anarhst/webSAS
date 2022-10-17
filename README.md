# TestProject
Тестовое приложение для введения в интеграции. Создан в рамках обучения написания веб-сервисов.

Находится в стадии доработки и учулшений.


## Установка
1.Клонировать проект из GitHub

2.Запустить проект в IntelliJIdea

3.Запустить сервер postgreSQL

4.Выставить параметры своего сервера в файле `application.properties`
 
```java
spring.jpa.database=POSTGRESQL 
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres //Адрес БД
spring.datasource.username=postgres //Логин
spring.datasource.password=postgres //Пароль
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
```

5. Запустить **Swagger** по адресу `http://localhost:8080/swagger-ui.html#/`

P.S. В дальнейшем планируется деплой сервиса на heroku, с которого процесс установки и запуска сервиса облегчится

## API

`[GET] /api/mirea/health-check` — метод проверки состояния сервиса

`[DELETE] /api/mirea/delete-method` — запрос на удаление данных с сервера. Удаление данных происходит по **id**

`[GET] /api/mirea/get-method` — запрос на получение данных с сервера. Забирает **id** объекта и возвращает данные в формате **JSON**

`[POST] /api/mirea/post-method` — запрос на запись данных на сервер. Данные отправляются в формате **JSON**

**Тело запроса:**

```
{
  "bookTOList": [          //Список книг
    {
      "author": "string",      //Автор книги
      "id": 0,                 //id книги
      "name": "string"         //Название книги
    }
  ],
  "id": 0,                 //id запроса
  "requestValue": "string" //Строковое значение запроса
}
```
`[PUT] /api/mirea/put-method` — запрос на перезапись данных на сервере. Перезапись происходит по **id**,
а данные отправляются на сервер в формате **JSON**

**Тело запроса:**

```
{
  "bookTOList": [            //Список книг
    {
      "author": "string",         //Автор книги
      "id": 0,                    //id книги
      "name": "string"            //Имя книги
    }
  ],
  "id": 0,                   //Id запроса
  "requestValue": "string"   //Строковое значение запроса
}
```


## Примеры запросов

Ниже предсталены примеры запросов

### Health Check

Запрос:

`curl -X GET "http://localhost:8080/api/mirea/health-check" -H "accept: */*"`

URL запроса:

`http://localhost:8080/api/mirea/health-check`

Заголовок ответа:

```
connection: keep-alive 
content-type: application/json 
date: Wed, 30 Dec 2020 18:51:04 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked 
```

Тело ответа:

```
{
  "meta": {
    "code": 0,
    "description": "All good"
  },
  "dataBlock": "Successfully connected"
}
```

### Post запрос

`[POST] /api/mirea/post-method` - Post запрос

Запрос:

```
{
  curl -X POST "http://localhost:8080/api/mirea/post-method" -H "accept: */*" -H "Content-Type: 
  application/json" -d "{ \"bookTOList\": [ { \"author\": \"asd\", \"id\": 3, \"name\": \"asd\" } ], \"id\": 3, \"requestValue\": \"asd\"}"
}
```
URL запроса
```
http://localhost:8080/api/mirea/post-method
```

Заголовок ответа:

```
connection: keep-alive 
content-type: application/json 
date: Sun, 27 Dec 2020 17:06:19 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked 
```
Тело ответа:

```
{
  "meta": {
    "code": 0,
    "description": "All good!"
  },
  "dataBlock": "Successfully inserted row!"
}
```

### Get запрос
`[GET] /api/mirea/get-method` - *get запрос*

Запрос:
```
curl -X GET "http://localhost:8080/api/mirea/get-method?%D0%98%D0%B4%D0%B5%D0%BD%D1%82%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%82%D0%BE%D1%80%20%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D0%B0=2" -H "accept: */*"
```

URL запроса

```
http://localhost:8080/api/mirea/get-method?%D0%98%D0%B4%D0%B5%D0%BD%D1%82%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%82%D0%BE%D1%80%20%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D0%B0=2
```

Заголовок ответа
```
connection: keep-alive 
content-type: application/json 
date: Sun, 27 Dec 2020 17:10:58 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked
```
Тело ответа
```
{
  "meta": {
    "code": 0,
    "description": "All good!"
  },
  "dataBlock": {
    "id": 2,
    "requestValue": "string",
    "bookTOList": [
      {
        "id": 2,
        "author": "string",
        "name": "string"
      }
    ]
  }
}
```

### Put запрос
`[PUT] /api/mirea/put-method` - *put запрос*

Запрос:
```
curl -X PUT "http://localhost:8080/api/mirea/put-method" -H "accept: */*" -H "Content-Type: 
application/json" -d "{ \"bookTOList\": [ { \"author\": \"aaaaaaaa\", \"id\": 2, \"name\": \"aaaaaa\" } ], \"id\": 2, \"requestValue\": \"aaaaa\"}"
```
URL запроса
```
http://localhost:8080/api/mirea/put-method
```
Заголовок ответа
```
 connection: keep-alive 
 content-type: application/json 
 date: Sun, 27 Dec 2020 17:20:53 GMT 
 keep-alive: timeout=60 
 transfer-encoding: chunked 
```
Тело ответа
```
{
  "meta": {
    "code": 0,
    "description": "All good!"
  },
  "dataBlock": "Successfully putted row!"
}
```

### Delete запрос

`[DELETE] /api/mirea/delete-method` - *delete запрос*
Запрос:
```
curl -X DELETE "http://localhost:8080/api/mirea/delete-method?id=252" -H "accept: */*"
```
URL запроса
```
http://localhost:8080/api/mirea/delete-method?id=252
```
Заголовок ответа
```
connection: keep-alive 
content-type: application/json 
date: Sun, 27 Dec 2020 17:23:34 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked
```
Тело ответа
```
{
  "meta": {
    "code": 0,
    "description": "All good!"
  },
  "dataBlock": "Successfully deleted row!"
}
```


## Связаться с разработчиком
[Telegram](http://t.me/Anarhlst)

```mermaid
title Взаимодействие сервисов

Web-Форма->>Микросервис: Отправить данные из формы
Микросервис->>CRM: Отправить обработанные данные в CRM
CRM->>CRM: Проверка регистрации пользователя на мероприятие
alt Пользователь уже зарегистрирован на мероприятие
CRM-->>Микросервис: Отклонение заявки
Микросервис-->>Web-Форма:Вы уже были зарегистрированы на мероприятие. Вся информация направлена вам на адрес электронной почты. Если письмо не дошло,проверьте папку “спам” на сервере или свяжитесь с организаторами
else
CRM->>CRM: Проверка на наличие свободных мест
alt Свободных мест нет
CRM-->>Микросервис: Отклонение заявки
Микросервис-->>Web-Форма: К сожалению, кол-во свободных мест закончилось, мы не можем вас зарегистрировать на мероприятие
else
CRM-->>Микросервис: Принятие заявки
Микросервис-->>Web-Форма: Вы успешно зарегистрированы на мероприятие, подробная информация направлена вам на электронную почту
end
end

```
