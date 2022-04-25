//todo Нигде не определяется диалект БД. Нужно бы его определить.
//      По запросам sql:
//          * Имена столбцов (Id, Title). Cтолбцы должны именоваться маленькими буквами.
//              Конечно, и так таблицы создадутся корректно и маленкими буквами,
//              но на такую запись смотреть не привычно.
//          * CHARACTER VARYING(30) обычно не применяется. Обычно применяется varchar(n).
//              Хоть это и синоним, и для бд разницы нет.
//          * Такое создание таблицы допускает создание полей Title, Price со значением null.
//              Это не логично
package com.example.simplegrouptask7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleGroupTask7Application {

    public static void main(String[] args) {
        SpringApplication.run(SimpleGroupTask7Application.class, args);
    }

}
