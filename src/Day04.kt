fun main() {
    fun overlapping(l: List<List<Int>>):Boolean{
        val a = l.first()
        val b = l.last()

        val a1 = a.first()
        val a2 = a.last()

        val b1 = b.first()
        val b2 = b.last()

        return (a1 <= b1 && b2 <= a2) || (b1 <= a1 && a2 <= b2)
    }

    fun overlapping0(l: List<List<Int>>):Boolean{
        val a = l.first()
        val b = l.last()

        val a1 = a.first()
        val a2 = a.last()

        val b1 = b.first()
        val b2 = b.last()

        return !((a1 > b2) || (b1 > a2))
    }

    fun part1(input: List<String>): Int {
       return input.map {
           it.split(",").map {
               x ->
               x.split("-").map { y -> y.toInt() }
           }
       }.count {
          overlapping(it)
       }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            it.split(",").map {
                    x ->
                x.split("-").map { y -> y.toInt() }
            }
        }.count {
            overlapping0(it)
        }
    }
    var input = readInput("Test04")
    println(part1(input))
    println(part2(input))

    input = readInput("Day04")
    println(part1(input))
    println(part2(input))


}
