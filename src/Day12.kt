import java.util.Queue

fun main() {

    fun part1(input: List<String>): Int {
        val rows = input.size
        val cols = input.first().length
        val points = Array<IntArray>(rows){
            IntArray(cols){0}
        }
        val ans = Array<IntArray>(rows){
            IntArray(cols){Int.MAX_VALUE}
        }

        var start: Pair<Int, Int> = Pair(0,0)
        var end: Pair<Int, Int> = Pair(0,0)


        (0 until rows).forEach {
            x ->
            val arr = input[x].toCharArray()
            (0 until cols).forEach {
            y->
                when(arr[y]){
                    'S' -> {
                        start = x to y
                        points[x][y] = 0
                    }
                    'E' -> {
                        end = x to y
                        points[x][y] = 25
                    }
                    else ->
                        points[x][y] = arr[y] - 'a'
                }
            }
        }

        val queue: Queue<Pair<Int, Int>> = java.util.LinkedList<Pair<Int, Int>>().apply { add(start) }
        val visited: MutableSet<Pair<Int, Int>> = mutableSetOf(start,)

        ans[start.first][start.second] = 0

        fun shortest():Int{
            while (queue.isNotEmpty()) {
                val (x0, y0) = queue.poll()
                if (x0 == end.first && y0 == end.second) return ans[end.first][end.second]

                listOf(x0 to y0 + 1, x0 + 1 to y0, x0 to y0 - 1, x0 - 1 to y0).forEach {
                    val x = it.first
                    val y = it.second

                    if (!(x >= rows || y >= cols || x < 0 || y < 0 || (x to y) in visited || points[x][y] - points[x0][y0] > 1)) {
                        ans[x][y] = ans[x0][y0] + 1
                        queue.add(x to y)
                        visited.add(x to y)
                    }
                }
            }

            return -1
        }


        return shortest()
    }

    fun part2(input: List<String>): Int{
        val rows = input.size
        val cols = input.first().length
        val points = Array<IntArray>(rows){
            IntArray(cols){0}
        }
        val ans = Array<IntArray>(rows){
            IntArray(cols){Int.MAX_VALUE}
        }

        var start: Pair<Int, Int> = Pair(0,0)
        var end: Pair<Int, Int> = Pair(0,0)


        (0 until rows).forEach {
                x ->
            val arr = input[x].toCharArray()
            (0 until cols).forEach {
                    y->
                when(arr[y]){
                    'S' -> {
                        start = x to y
                        points[x][y] = 0
                    }
                    'E' -> {
                        end = x to y
                        points[x][y] = 25
                    }
                    else ->
                        points[x][y] = arr[y] - 'a'
                }
            }
        }

        val queue: Queue<Pair<Int, Int>> = java.util.LinkedList<Pair<Int, Int>>().apply { add(end) }
        val visited: MutableSet<Pair<Int, Int>> = mutableSetOf(end)

        ans[end.first][end.second] = 0

        fun shortest():Int{
            while (queue.isNotEmpty()) {
                val (x0, y0) = queue.poll()
                if (points[x0][y0] == 0) return ans[x0][y0]

                listOf(x0 to y0 + 1, x0 + 1 to y0, x0 to y0 - 1, x0 - 1 to y0).forEach {
                    val x = it.first
                    val y = it.second

                    if (!(x >= rows || y >= cols || x < 0 || y < 0 || (x to y) in visited || points[x0][y0] - points[x][y] > 1)) {
                        ans[x][y] = ans[x0][y0] + 1
                        queue.add(x to y)
                        visited.add(x to y)
                    }
                }
            }

            return -1
        }


        return shortest()
    }

    var input = readInput("Test12")
    println(part1(input))
    println(part2(input))

    input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}
