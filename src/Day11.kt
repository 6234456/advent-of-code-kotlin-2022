import java.math.BigInteger

data class Monkey(var item: java.util.LinkedList<Long> = java.util.LinkedList<Long>(), var operation: (Long)->Long = {it},
                  var divisor: Int = 1, var ifTrue: Int = -1, var ifFalse: Int = -1)

data class Monkey0(var item: java.util.LinkedList<BigInteger> = java.util.LinkedList<BigInteger>(), var operation: (BigInteger)->BigInteger = {it},
                  var divisor: BigInteger = BigInteger.ONE, var ifTrue: Int = -1, var ifFalse: Int = -1)


fun main() {
    fun part1(input: List<String>): Int {

        val cnt = (input.size + 1) / 7
        val arr = Array<Monkey>(cnt){Monkey()}

        input.forEachIndexed { index, s ->
            val line = index.mod(7) + 1
            val idx = (index - line + 1) / 7
            val m = arr[idx]

            when(line){
                2 -> m.item.addAll(s.split(": ").last().split(", ").map { it.trim().toLong() })
                3 -> {
                    val a = s.split(" ").takeLast(2)
                    when{
                        a.last() == "old" -> m.operation = {it * it}
                        else -> {
                            val sec = a.last().toInt()
                            if (a.first() == "+") {
                                m.operation = {it + sec}
                            }else{
                                m.operation = {it * sec}
                            }
                        }
                    }
                }
                4 -> {
                    m.divisor = s.split(" ").last().toInt()
                }
                5 -> {
                    m.ifTrue = s.split(" ").last().toInt()
                }
                6 -> {
                    m.ifFalse = s.split(" ").last().toInt()
                }
            }
        }

        val ans = IntArray(cnt){0}

        repeat(20){
            arr.forEachIndexed {
                indexed, m ->
                ans[indexed] = ans[indexed] + arr[indexed].item.size

                while (m.item.isNotEmpty()){
                    val i = m.item.pop()
                    val v = m.operation(i) / 3
                    arr[if (v.mod(m.divisor) == 0) m.ifTrue else m.ifFalse].item.add(v)
                }
            }
        }

        val tmp = ans.sortedByDescending { it }.take(2)

        return tmp[0] * tmp[1]
    }

    fun part2(input: List<String>): Long{
        val cnt = (input.size + 1) / 7
        val arr = Array<Monkey>(cnt){Monkey()}

        input.forEachIndexed { index, s ->
            val line = index.mod(7) + 1
            val idx = (index - line + 1) / 7
            val m = arr[idx]

            when(line){
                2 -> m.item.addAll(s.split(": ").last().split(", ").map { it.trim().toLong() })
                3 -> {
                    val a = s.split(" ").takeLast(2)
                    when{
                        a.last() == "old" -> m.operation = {it * it}
                        else -> {
                            val sec = a.last().toInt()
                            if (a.first() == "+") {
                                m.operation = {it + sec}
                            }else{
                                m.operation = {it * sec}
                            }
                        }
                    }
                }
                4 -> {
                    m.divisor = s.split(" ").last().toInt()
                }
                5 -> {
                    m.ifTrue = s.split(" ").last().toInt()
                }
                6 -> {
                    m.ifFalse = s.split(" ").last().toInt()
                }
            }
        }

        val ans = IntArray(cnt)
        val d = arr.fold(1L){
            acc, monkey ->
            acc * monkey.divisor
        }

        repeat(10000){
            arr.forEachIndexed {
                    indexed, m ->
                ans[indexed] = ans[indexed] + arr[indexed].item.size

                while (m.item.isNotEmpty()){
                    val i = m.item.pop()
                    val v = m.operation(i) % d
                    arr[if (v.mod(m.divisor) == 0) m.ifTrue else m.ifFalse].item.add(v)
                }
            }
        }

        val tmp = ans.sortedByDescending { it }.take(2)

        return tmp[0].toLong()* tmp[1].toLong()
    }

    var input = readInput("Test11")
    println(part1(input))
    println(part2(input))

    input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
