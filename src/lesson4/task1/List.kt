@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.sqr
import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.pow
import lesson3.task1.digitNumber
import java.lang.StringBuilder

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var summ = 0.0
    for (i in v) summ += sqr(i)
    return sqrt(summ)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty() == true) return 0.0
    return list.sum() / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val middle = list.sum() / list.size
    for (i in 0..list.size - 1) list[i] -= middle
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in 0.. a.size - 1) c += a[i] * b[i]
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var px = 0
    for (i in 0..p.size - 1) px += p[i] * (x.toDouble().pow(i)).toInt()
    return px
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1..list.size - 1) list[i] += list[i - 1]
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var factors = mutableListOf<Int>()
    var i = 2
    var number = n
    while (number > 1) {
        if (number % i == 0) {
            factors.add(i)
            number /= i
        }
        else i++
    }
    return factors
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var numbers = mutableListOf<Int>()
    var digit = n
    while (digit > 0) {
        numbers.add(digit % base)
        digit /= base
    }
    numbers.reverse()
    return numbers
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var alphabet = mapOf(10 to "a", 11 to "b", 12 to "c", 13 to "d", 14 to "e", 15 to "f", 16 to "g", 17 to "h",
        18 to "i", 19 to "j", 20 to "k", 21 to "l", 22 to "m", 23 to "n", 24 to "o", 25 to "p", 26 to "q", 27 to "r",
        28 to "s", 29 to "t", 30 to "u", 31 to "v", 32 to "w", 33 to "x", 34 to "y", 35 to "z")
    var number = n
    var res = ""
    while (number > 0) {
        if (number % base < 10) res = (number % base).toString() + res
        else res = alphabet[number % base] + res
        number /= base
    }
    return res
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var res = 0
    var size = digits.size
    for (i in digits) {
        res += i * (base.toDouble().pow(size - 1)).toInt()
        size--
    }
    return res
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var alphabet = "abcdefghijklmnopqrstuvwxyz"
    var res = 0
    var size = str.length
    for (i in 0..str.length - 1) {
        if (str[i] in alphabet) res += (alphabet.indexOf(str[i]) + 10) * (base.toDouble().pow(size - 1)).toInt()
        else res += str[i].digitToInt() * (base.toDouble().pow(size - 1)).toInt()
        size--
    }
    return res
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var digits = mapOf<Int, String>(1 to "I", 4 to "IV", 5 to "V", 9 to "IX", 10 to "X", 40 to "XL", 50 to "L",
         90 to "XC", 100 to "C", 400 to "CD", 500 to "D", 900 to "CM", 1000 to "M")
    var res = ""
    val count = digitNumber(n)
    if (n in digits) return digits[n].toString()
    if (count == 1) {
        if (n in 2..3) res = digits[1].toString().repeat(n)
        if (n in 6..8) res = digits[5] + digits[1].toString().repeat(n - 5)
    }
    if (count == 2) {
        if (n / 10 in digits) res = digits[n / 10 * 10].toString()
        if (n / 10 in 2..3) res = digits[10].toString().repeat(n / 10)
        if (n / 10 in 6..8) res = digits[50] + digits[10].toString().repeat(n / 10 - 5)
        if (n % 10 in digits) res += digits[n % 10].toString()
        if (n % 10 in 2..3) res += digits[1].toString().repeat(n % 10)
        if (n % 10 in 6..8) res += digits[5] + digits[1].toString().repeat(n % 10 - 5)
    }
    if (count == 3) {
        if (n / 100 in digits) res = digits[n / 100 * 100].toString()
        if (n / 100 in 2..3) res = digits[100].toString().repeat(n / 100)
        if (n / 100 in 6..8) res = digits[500] + digits[100].toString().repeat(n / 100 - 5)
        if (n % 100 / 10 in digits) res += digits[n % 100 / 10 * 10].toString()
        if (n % 100 / 10 in 2..3) res += digits[10].toString().repeat(n % 100 / 10)
        if (n % 100 / 10 in 6..8) res += digits[50] + digits[10].toString().repeat(n % 100 / 10 - 5)
        if (n % 10 in digits) res += digits[n % 10].toString()
        if (n % 10 in 2..3) res += digits[1].toString().repeat(n % 10)
        if (n % 10 in 6..8) res += digits[5] + digits[1].toString().repeat(n % 10 - 5)
    }
    if (count == 4) {
        if (n / 1000 in digits) res = digits[n / 1000 * 1000].toString()
        if (n / 1000 in 2..3) res = digits[1000].toString().repeat(n / 1000)
        if (n % 1000 / 100 in digits) res += digits[n % 1000 / 100 * 100].toString()
        if (n % 1000 / 100 in 2..3) res += digits[100].toString().repeat(n % 1000 / 100)
        if (n % 1000 / 100 in 6..8) res += digits[500] + digits[100].toString().repeat(n % 1000 / 100 - 5)
        if (n % 100 / 10 in digits) res += digits[n % 100 / 10 * 10].toString()
        if (n % 100 / 10 in 2..3) res += digits[10].toString().repeat(n % 100 / 10)
        if (n % 100 / 10 in 6..8) res += digits[50] + digits[10].toString().repeat(n % 100 / 10  - 5)
        if (n % 10 in digits) res += digits[n % 10].toString()
        if (n % 10 in 2..3) res += digits[1].toString().repeat(n % 10)
        if (n % 10 in 6..8) res += digits[5] + digits[1].toString().repeat(n % 10 - 5)
    }
    return res
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val unit = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val decadeFirst = listOf("десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
    val decadeSecond = listOf("двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
    val hundred = listOf("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    var res = ""
    val count = digitNumber(n)
    if (count == 1) return unit[n - 1]
    if (count == 2) {
        if (n / 10 == 1) return decadeFirst[n % 10] else {
            if (n % 10 != 0) return decadeSecond[n / 10 - 2] + " " + unit[n % 10 - 1] else return decadeSecond[n / 10 - 2]
        }
    }
    if (count == 3) {
        res = hundred[n / 100 - 1] + " "
        if (n % 100 / 10 == 1) res += decadeFirst[n % 10] else {
            if (n % 100 / 10 != 0) res += decadeSecond[n % 100 / 10 - 2] + " "
            if (n % 10 != 0) res += unit[n % 10 - 1]
        }
    }
    if (count == 4) {
        if (n / 1000 == 1) res = "тысяча "
        if (n / 1000 == 2) res = "две тысячи "
        if (n / 1000 in 3..4) res = unit[n / 1000 - 1] + " тысячи "
        if (n / 1000 in 5..9) res = unit[n / 1000 - 1] + " тысяч "
        if (n % 1000 / 100 != 0) res += hundred[n % 1000 / 100 - 1] + " "
        if (n % 100 / 10 == 1) res += decadeFirst[n % 10] else {
                if (n % 100 / 10 != 0) res += decadeSecond[n % 100 / 10 - 2] + " "
                if (n % 10 != 0) res += unit[n % 10 - 1]
            }
    }
    if (count == 5) {
        if (n / 10000 == 1) res = decadeFirst[n % 10000 / 1000] + " тысяч" else {
            if (n % 10000 / 1000 != 0) {
                res = decadeSecond[n / 10000 - 2] + " "
                if (n % 10000 / 1000 == 1) res += "одна тысяча"
                if (n % 10000 / 1000 == 2) res += "две тысячи"
                if (n % 10000 / 1000 in 3..4) res += unit[n % 10000 / 1000 - 1] + " тысячи"
                if (n % 10000 / 1000 in 5..9) res += unit[n % 10000 / 1000 - 1] + " тысяч"
            } else res = decadeSecond[n / 10000 - 2] + " тысяч"
        }
        if (n % 1000 / 100 != 0) res += " " + hundred[n % 1000 / 100 - 1]
        if (n % 100 / 10 == 1) res += " " + decadeFirst[n % 10] else {
            if (n % 100 / 10 != 0) res += " " + decadeSecond[n % 100 / 10 - 2]
            if (n % 10 != 0) res += " " + unit[n % 10 - 1]
        }
    }
    if (count == 6) {
        res = hundred[n / 100000 - 1] + " "
        if (n % 100000 / 10000 == 1) res += decadeFirst[n % 10000 / 1000] + " тысяч" else {
            if (n % 100000 / 10000 != 0) {
                res += decadeSecond[n % 100000 / 10000 - 2] + " "
                if (n % 10000 / 1000 == 1) res += "одна тысяча"
                if (n % 10000 / 1000 == 2) res += "две тысячи"
                if (n % 10000 / 1000 in 3..4) res += unit[n % 10000 / 1000 - 1] + " тысячи"
                if (n % 10000 / 1000 in 5..9) res += unit[n % 10000 / 1000 - 1] + " тысяч"
            } else res += "тысяч"
        }
        if (n % 1000 / 100 != 0) res += " " + hundred[n % 1000 / 100 - 1]
        if (n % 100 / 10 == 1) res += " " + decadeFirst[n % 10] else {
            if (n % 100 / 10 != 0) res += " " + decadeSecond[n % 100 / 10 - 2]
            if (n % 10 != 0) res += " " + unit[n % 10 - 1]
        }
    }
    return res
}