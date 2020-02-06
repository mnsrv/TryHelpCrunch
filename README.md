# TryHelpCrunch

## Установка

1. Склонировать репозиторий
2. Заменить в TryHelpCrunch/ios/TryHelpCrunch/AppDelegate.m на 33 строчке organization, applicationId, applicationSecret на настоящие данные.
3. В папке проекта запустить команду `npm install`
4. В папке ios запустить команду `pod install`
5. В папке проекта запустить команду `npm start`
6. Открыть в Xcode файл `ios/TryHelpCrunch.xcworkspace`
7. Запустить проект в Xcode

## Как воспроизвести падение

1. Сделать фокус на текстовое поле
2. Открыть чат
3. Закрыть чат
4. Сделать фокус на текстовое поле

Приложение падает после фокуса на текстовом поле (не в чате)
