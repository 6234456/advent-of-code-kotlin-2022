import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val queue = java.util.LinkedList<Int>()
        input.forEach {
            if (it == "noop"){
                queue.add(0)
            }else{
                queue.add(0)
                queue.add(it.split(" ").last().toInt())
            }
        }

        var v = 1
        val arr = queue.take(220).map {
            val prev = v
            v += it
            prev
        }

        return listOf(20, 60, 100, 140, 180, 220).fold(0){
            acc, i ->
            acc + i * arr[i-1]
        }


    }

    fun part2(input: List<String>){
        val queue = java.util.LinkedList<Int>()
        input.forEach {
            if (it == "noop"){
                queue.add(0)
            }else{
                queue.add(0)
                queue.add(it.split(" ").last().toInt())
            }
        }

        var v = 1
        val arr = queue.take(240).map {
            val prev = v
            v += it
            prev
        }

        arr.forEachIndexed { index, i ->
            val idx = index.mod(40)
            val pos = i

            val sign = if (abs(idx - pos)<=1) "#" else "."
            if (idx == 0) println()
            print(sign)
        }


    }
    var input = readInput("Test10")
    println(part1(input))
    part2(input)

    input = readInput("Day10")
    println(part1(input))
    part2(input)


}
