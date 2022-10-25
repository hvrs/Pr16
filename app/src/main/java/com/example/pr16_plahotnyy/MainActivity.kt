package com.example.pr16_plahotnyy


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        1
        создание обработчика событий, который срабатывает на кноку 9
        когда нажмем на эту кнопку, сработает обработчик событий
        и будет вызвана функция setTextFields
        и в эту функцию мы передаем определенную строку. Это строка содержит лишь число 9
        и именно это число будет добавлено в первое тестовое поле
         */
        //основной блок цифр
        nine_btn.setOnClickListener{setTextFields("9")}
        eight_btn.setOnClickListener{setTextFields("8")}
        seven_btn.setOnClickListener{setTextFields("7")}
        six_btn.setOnClickListener{setTextFields("6")}
        five_btn.setOnClickListener{setTextFields("5")}
        four_btn.setOnClickListener{setTextFields("4")}
        three_btn.setOnClickListener{setTextFields("3")}
        two_btn.setOnClickListener{setTextFields("2")}
        one_btn.setOnClickListener{setTextFields("1")}
        zero_btn.setOnClickListener{setTextFields("0")}
        //символы операций
        point_btn.setOnClickListener { setTextFields(".") }
        minus_btn.setOnClickListener{setTextFields("-")}
        plus_btn.setOnClickListener{setTextFields("+")}
        star_btn.setOnClickListener{setTextFields("*")}
        slesh_btn.setOnClickListener{setTextFields("/")}
        bracket_l_btn.setOnClickListener{setTextFields("(")}
        bracket_r_btn.setOnClickListener{setTextFields(")")}

        //кнопки отсичтки
        /*
        2
        Обращаемся к кнопке по ее ID
        Далее устанавливаем для нее setOnClickListener(обработчик событий)
        Прописать,что мы обращаемся к math_operation и устанавливаем его текст, как пустую строку
        Также обратимся к result_text и тоже установим, как пустую строку
         */
        clear_btn.setOnClickListener{
            math_operation.text =""
            result_text.text =""
        }

        /*
        3
        Обращаемся к кнопке по ее ID
        Далее устанавливаем для нее setOnClickListener(обработчик событий)
        Создаем строку и записываем в нее текст из текстового поля math_operation
        Проверяем, что строка не пустая
        Если строка не пустая, то берем текст из math_operation и устанавливаем новое значение
        Берем переменную str, обращаемся к функции substring, которая позволяет обрезать определенную строку по определенным критериям
        Изначально говорим, что мы обрезаем строку начиная с нулевого элемента и заканчивая таким элементом, который у нас идет как str.length - 1
        Т.е. обрезаем строку, начиная с нулевого элемента и заканчивая предпоследним элементом(только последний элемент будет удален)
        Все остальные элементы будут оставлены, и они будут помещены в текстовое поле math_operation
        И очищаем дополнительно текстовое поле с результатом
         */
        deleteonesimv_btn.setOnClickListener {
            val str = math_operation.text.toString()
            if(str.isNotEmpty())
            {
                math_operation.text = str.substring(0, str.length - 1)
            }
            result_text.text=""
        }

        /*
        4
        Обращаемся к кнопке по ее ID
        Далее устанавливаем для нее setOnClickListener(обработчик событий)
        Вызываем блок try-catch, что бы обнаружить какую либо ошибку
        В catch отслеживаем ошибку, которая идет из класса Exception(поможет отследить любую ошибку, которая может произойти)
        Если будет ошибка, то будем выводить в терминал
        В сообщение выведем сам текст сообщения
        В try создаем объект на основе класса ExpressionBuilder(класс загружен вместе с библиотекой) через который сможем вычислять разные математические действия
        из обычной строки
        В качестве параметра указать строку(из которой будут произведены математические действия) и дополнительно приведем к типу string, чтобы точно была строкой
        Также необходимо вызвать функцию build, чтобы мы непросто создали объект, но и дополнительно его инициализировали
        Создаем некую переменную и в ней обращаемся к объекту и функции evaluate(). Эта функция позволяет нам высчитать математическую опперацию из той строки,
        которую мы передали, при создании самого объекта
        В result уже помещено готовое математическое решение
        Прописываем новую переменную longRes и в ней переменную result приводим к типу данных toLong
        и делаем проверку, если переменная result будет равна longRes.toDouble(), то в текстовое поле результата будем записывать переменную longRes и
        приводить к строковому формату
        Иначе в тестовое поле результата будем записывать result и приводить к строковому формату
         */
        round_btn.setOnClickListener {
            try{

                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val  result = ex.evaluate()
                val longRes = result.toLong()
                if(result == longRes.toDouble())
                    result_text.text = longRes.toString()//целое число
                else
                    result_text.text = result.toString()//число с точкой
            }   catch (e:Exception){
                Log.d("Ошибка", "Сообщение: ${e.message}")
            }
        }
    }

    /*
    5
    Функция принимает один параметр
    Если есть какое-то значение в текстовом поле результата, то записываем значение текстового поля результата в значение текстового поля вычисления
    А текстовое поле результата очищаем
    К текстовому полю вычисления добавляем новую строку
     */
    fun setTextFields (str: String) {
        if(result_text.text !=""){
            math_operation.text =result_text.text
            result_text.text = ""
        }

        math_operation.append(str)
    }
}