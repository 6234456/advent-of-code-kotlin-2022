import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign
import kotlin.math.sin

data class Pos(val x: Int, val y: Int){

    fun move(direction: Char):Pos {
        return when(direction){
            'R' -> copy(x = x + 1)
            'L' -> copy(x= x - 1)
            'U' -> copy(y = y - 1)
            'D' -> copy(y = y + 1)
            else -> Pos(0,0)
        }
    }
    fun follow(head: Pos, direction: Char):Pos{
        val dx = head.x - x
        val dy = head.y - y
        val d = Pos(dx, dy).move(direction)

        if (abs(d.x) <= 1 && abs(d.y) <= 1) return this

        return copy(x = x + d.x.sign, y = y + d.y.sign)
    }

    fun follow(newHead: Pos):Pos{
        val dx = newHead.x - x
        val dy = newHead.y - y

        if (abs(dx) <= 1 && abs(dy) <= 1) return this

        return copy(x = x + dx.sign, y = y + dy.sign)
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        var head = Pos(0,0)
        var tail = Pos(0,0)

        return input.fold(mutableSetOf<Pos>(tail)){
            acc, s ->
             val direction = s.first()
             val repeat = s.split( " ").last().toInt()

            repeat(repeat){
             //   println("$head - $tail")
                head = head.move(direction)
                tail = tail.follow(head)
                acc.add(tail)
            }

            acc
        }.size
    }

    fun part2(input: List<String>): Int {
        var head = Pos(0,0)
        var tail = Pos(0,0)
        val posArray = Array<Pos>(8){Pos(0,0)}

        return input.fold(mutableSetOf<Pos>(tail)){
                acc, s ->
            val direction = s.first()
            val repeat = s.split( " ").last().toInt()

            repeat(repeat){
                //   println("$head - $tail")
                head = head.move(direction)
                posArray[0] = posArray[0].follow(head)

                (1..7).forEach {
                    posArray[it] = posArray[it].follow(posArray[it-1])
                }
                tail = tail.follow(posArray[7])
                acc.add(tail)
            }

            acc
        }.size
    }
    var input = readInput("Test09")
    println(part1(input))
    println(part2(input))

    input = readInput("Day09")
    println(part1(input))
    println(part2(input))


}
