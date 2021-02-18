Для запуска тестов, необходимо сначала забронировать нужное устройство на https://mobilecloud.epam.com/#!/devices  
Затем необходимо на https://mobilecloud.epam.com/#!/automation/sample выбрать нужно устройство и скопировать в androidTNG.xml, webAndroidTNG.xml, iOSTNG.xml и webiOSTNG.xml информацию об устройстве,  
а именно platformName, platformVersion и deviceName  
В pom.xml в корне проекта необходимо в token вписать свой токен  
Также необходимо скачать EPAMTestApp на устройство вручную.
В maven есть 4 профиля, android и iOS для native-app, и webAndroid и webiOS для браузера.
Запускать через mvn -P profile test выбрав нужный профиль, или через интерфейс мавена справа.