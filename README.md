## Prerequisites
* Windows OS
* Install firefox: https://ftp.mozilla.org/pub/firefox/releases/46.0.1/win64/en-US/
* Maven (To build & run)
* Setup Java language level - 8
* Yandex account login and psw to enter yandex post

## Test data limitations
* At the moment test support only attachments from local file
* Test works unstable because Interaction with OS windows from selenium is not so easy and straight forward, keep calm :)

## Setup test data
* Setup login and psw in file src/test/resources/test.properties
* Setup desired test data in file src/test/resources/attachments/test.properties considering limitations
* attachmentRaw value should be a json string of format: [{"type": "LOCALFILE", "data": ["filename1,..."]}]

## Run test
* mvn clean verify -Dlogin=YOUR_LOGIN -Dpassword=YOUR_PASSWORD
* manually from IDEA with VM Options -Dlogin=YOUR_LOGIN -Dpassword=YOUR_PASSWORD

## See post execution report
Serenity report is generated automatically and can be found under target/site/serenity folder.
You are interested in file index.html (or ****.html in case of test has failed)