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
    if (p.isNotEmpty()) px = p[0]
    var x1 = x
    for (i in 1..p.size - 1) {
        px += p[i] * x1
        x1 *= x
    }
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
    if (digit == 0) {
        numbers.add(0)
        return numbers
    }
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
private val ALPHABET = mapOf(10 to "a", 11 to "b", 12 to "c", 13 to "d", 14 to "e", 15 to "f", 16 to "g", 17 to "h",
    18 to "i", 19 to "j", 20 to "k", 21 to "l", 22 to "m", 23 to "n", 24 to "o", 25 to "p", 26 to "q", 27 to "r",
    28 to "s", 29 to "t", 30 to "u", 31 to "v", 32 to "w", 33 to "x", 34 to "y", 35 to "z")
fun convertToString(n: Int, base: Int): String {
    val number = convert(n, base).toMutableList()
    var res = StringBuilder()
    for (i in 0..number.size - 1) {
        if (number[i] in ALPHABET) res.append(ALPHABET[number[i]])
        else res.append(number[i])
    }
    return res.toString()
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val digits = digits.reversed()
    var res = digits[0]
    var base1 = base
    for (i in 1..digits.size - 1) {
        res += digits[i] * base1
        base1 *= base
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
private val alphabet1 = mapOf('a' to 10, 'b' to 11, 'c' to 12, 'd' to 13, 'e' to 14, 'f' to 15, 'g' to 16, 'h' to 17,
    'i' to 18, 'j' to 19, 'k' to 20, 'l' to 21, 'm' to 22, 'n' to 23, 'o' to 24, 'p' to 25, 'q' to 26, 'r' to 27,
    's' to 28, 't' to 29, 'u' to 30, 'v' to 31, 'w' to 32, 'x' to 33, 'y' to 34, 'z' to 35)
fun decimalFromString(str: String, base: Int): Int {
    var str1 = mutableListOf<Int>()
    for (i in 0..str.length - 1) {
        if (alphabet1.containsKey(str[i])) str1.add(alphabet1[str[i]]!!)
        else str1.add(str[i].digitToInt())
    }
    val number = decimal(str1, base)
    return number
}
/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
private val DIGITS = mapOf<Int, String>(1 to "I", 4 to "IV", 5 to "V", 9 to "IX", 10 to "X", 40 to "XL", 50 to "L",
    90 to "XC", 100 to "C", 400 to "CD", 500 to "D", 900 to "CM", 1000 to "M")
fun counter (n: Int, count: Int): String {
    var res = ""
    val length = count
    if (n in DIGITS) res = DIGITS[n * 10.toDouble().pow(length - 1).toInt()].toString()
    if (n in 2..3) res = DIGITS[1 * 10.toDouble().pow(length - 1).toInt()].toString().repeat(n)
    if (n in 6..8) res = DIGITS[5 * 10.toDouble().pow(length - 1).toInt()] +
            DIGITS[1 * 10.toDouble().pow(length - 1).toInt()].toString().repeat(n - 5)
    return res.toString()
}
fun roman(n: Int): String {
    var count = digitNumber(n)
    if (n in DIGITS) return DIGITS[n].toString()
    if (count == 1) return counter(n, count)
    var n = n.toString()
    var res = StringBuilder(counter(n[0].digitToInt(), count))
    count--
    var i = 1
    while (count != 0) {
        res.append(counter(n[i].digitToInt(), count))
        i++
        count--
    }
    return res.toString()
}
/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
val unit = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
val decadeFirst = listOf("десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
    "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
val decadeSecond = listOf("двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
val hundred = listOf("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
fun counter1(n: Int, count: Int): String {
    var res = StringBuilder()
    if (count > 3) res = StringBuilder(" ")
    if (n / 100 != 0) res.append(hundred[n / 100 - 1] + " ")
    if (n % 100 / 10 == 1) res.append(decadeFirst[n % 10]) else {
        if (n % 100 / 10 != 0) res.append(decadeSecond[n % 100 / 10 - 2] + " ")
        if (n % 10 != 0) res.append(unit[n % 10 - 1])
    }
    return res.toString()
}
fun counter2(n: Int): String {
    var res = StringBuilder()
    if (n == 1) res.append("одна тысяча")
    if (n == 2) res.append("две тысячи")
    if (n in 3..4) res.append(unit[n - 1] + " тысячи")
    if (n in 5..9) res.append(unit[n - 1] + " тысяч")
    if (n == 0) res.append("тысяч")
    return res.toString()
}
fun russian(n: Int): String {
    var res = StringBuilder()
    val count = digitNumber(n)
    if (count == 1) return unit[n - 1]
    if (count == 2) {
        if (n / 10 == 1) return decadeFirst[n % 10] else {
            if (n % 10 != 0) return decadeSecond[n / 10 - 2] + " " + unit[n % 10 - 1] else return decadeSecond[n / 10 - 2]
        }
    }
    if (count == 3) return counter1(n, count)
    if (count == 4) {
        res = StringBuilder(counter2(n / 1000))
        res.append(counter1(n % 1000, count))
    }
    if (count == 5) {
        if (n / 10000 == 1) res = StringBuilder(decadeFirst[n % 10000 / 1000] + " тысяч") else {
            if (n % 10000 / 1000 != 0) {
                res = StringBuilder(decadeSecond[n / 10000 - 2] + " ")
                res.append(counter2(n % 10000 / 1000))
            } else res = StringBuilder(decadeSecond[n / 10000 - 2] + " тысяч")
        }
        res.append(counter1(n % 1000, count))
    }
    if (count == 6) {
        res = StringBuilder(hundred[n / 100000 - 1] + " ")
        if (n % 100000 / 10000 == 1) res.append(decadeFirst[n % 10000 / 1000] + " тысяч") else {
            if (n % 100000 / 10000 != 0) {
                res.append(decadeSecond[n % 100000 / 10000 - 2] + " ")
                res.append(counter2(n % 10000 / 1000))
            }
            if (n % 100000 / 10000 == 0) res.append(counter2(n % 10000 / 1000))
        }
        res.append(counter1(n % 1000, count))
    }
    return res.toString().trim()
}