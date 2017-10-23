# testTgRecorderBot
Its a simple implementation of digital notebook.
It provides simple user message recorder in Telegram messanger (https://telegram.org/).

> This bot is a simple research implementation to study TG and SpringBoot, so I think, not much support will be provided for it in the future.

# Features:
This bot provides a set of simple commands:
1. /start - Starts message recording for user chat. After the start it records all user messages in
chat.
2. /help - Simple bot help.
3. /brief - Brief help - simple reference to /help command
4. /my - Prints all recorded user messages.
5. /clear - Clears all recorded data.

# Using bot:

First, checout the repository with git.
Second, configure the bot with properties, defined in `application.properties` file. Things that can be changed:
1. Database connection info.
2. Command codes.
3. Command ouput.

Options you need to change are:
1. spring.datasource.url - Your database url.
2. spring.datasource.username - DB username.
3. spring.datasource.password - DB password.
4. test-tg-bot.bot-token - Telegram bot token.
5. test-tg-bot.bot-name Telegram bot name.

Third, run maven goal `mvn clean spring-boot:repackage`.
As the result of the build in your `target` directory should be builded jar. You can use it to run the bot with `java -jar <jarname.jar>`.

_Or_

You can run the application with `mvn clean spring-boot:run`.

_More_ about spring boot applications launch at spring boot reference guide: https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/

# Supported tg messages and chats:
Bot was tested only in private chat with it, no groups or channels.
Bot supports recording documents, images, audio, video, stickers and plain text messages. No captions or reply headers are supported.
Bot persists only the identifiers of documents and media files, so, if they are removed from server it wont be possible to restore them.
