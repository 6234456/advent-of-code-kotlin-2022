fun main() {
    fun part1(input: List<String>): List<Long> {
        var tmp = mutableListOf<Int>()
        return input.fold(mutableListOf<Long>()){
            acc, s ->
               if (s.isBlank()){
                   acc.add(tmp.fold(0){a, i -> a + i})
                   tmp.clear()
               }else{
                   tmp.add(s.toInt())
               }

                acc
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day01")
    println(part1(input).sortedDescending().take(3).fold(0L){a, i -> a + i})
}
