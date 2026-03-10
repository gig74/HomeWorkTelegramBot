# API интерфейсы для Telegram бота

Добро пожаловать в раздел `api` нашего проекта Telegram бота! Эта директория содержит ключевые интерфейсы, которые определяют абстрактные действия и коммуникационные контракты, требуемые для функционирования бота.

Интерфейсы служат в качестве соглашений между различными компонентами программы, позволяя им взаимодействовать друг с другом без необходимости знания о конкретной реализации. Ниже представлено описание каждого интерфейса вместе с их назначением в рамках бота.

## Контент директории

В этой директории вы найдёте следующие интерфейсы:

### CommandService.java

Этот интерфейс представляет сервис, отвечающий за обработку команд, отправляемых пользователем в чате Telegram. Он содержит основные методы для отработки команд ботом.


### DataService.java

`DataService` отвечает за методы обработки и доступа к данным.

### HttpService.java

Интерфейс `HttpService` используется для выполнения HTTP-запросов к внешним ресурсам. Это может быть полезно, например, для получения данных от сторонних API. Он включает в себя различные методы для отправки GET, POST, PUT, DELETE запросов.

```java
public interface HttpService {
    String sendGetRequest(String url, Map<String, String> headers) throws IOException, URISyntaxException;
    String sendPostRequest(String url, Map<String, String> headers, String body) throws IOException, URISyntaxException;
    String sendPutRequest(String url, Map<String, String> headers, String body) throws IOException, URISyntaxException;
    String sendDeleteRequest(String url, Map<String, String> headers) throws IOException, URISyntaxException;
}
```

### StatusChatService.java

`StatusChatService` отвечает за методы установки и просмотра текущего режима для конкретного чата.
