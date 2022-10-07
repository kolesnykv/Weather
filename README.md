## Simple Weather App
___
Use `git clone` to clone the [app](https://github.com/kolesnykv/Weather.git)
* Enter the city name to get weather forecast:

![img.png](src/main/resources/img.png) 

* run the app to receive a formatted output in console.
## Enjoy!
___
### To extend potential of the app, you can connect endless amount of different APIs, some examples are collected [here](src/main/resources/possibleApis.txt)
#### To add API u simple need:
* create util class which serves for connection with API and parsing response body on java model 
with provided json parser
* create a model class itself
* add `transform` method in util class to transform API`s model to [SimpleWeather](src/main/java/com/knubisoft/utils/SimpleWeather.java) format