import kotlin.math.min

fun main() {

    fun part1(input: List<String>): Int {

        var minX = Int.MAX_VALUE
        var maxX = Int.MIN_VALUE
        var maxY = Int.MIN_VALUE

        input.forEach {
            it.split(" -> ").forEach {
                val tmp = it.split(",").map { it.toInt() }
                minX = kotlin.math.min(minX, tmp.first())
                maxX = kotlin.math.max(maxX, tmp.first())
                maxY = kotlin.math.max(maxY, tmp.last())
            }
        }

        val stage = Array<BooleanArray>(maxX - minX + 1){
            BooleanArray(maxY + 1){false}
        }

        input.forEach {
            val t = it.split(" -> ").map {
                val tmp = it.split(",").map { it.toInt() }
                tmp.first() to tmp.last()
            }
            (1 until t.size).forEach {
                val current = t[it]
                val last = t[it - 1]

                if (current.first == last.first){
                    val min = kotlin.math.min(current.second, last.second)
                    val max = kotlin.math.max(current.second, last.second)

                    (min .. max).forEach {
                        stage[current.first - minX][it] = true
                    }
                }else{
                    val min = kotlin.math.min(current.first, last.first)
                    val max = kotlin.math.max(current.first, last.first)

                    (min .. max).forEach {
                        stage[it - minX][current.second] = true
                    }
                }
            }
        }


        fun drop(x0:Int = 500, y0:Int = 0):Boolean{
            var x = x0 - minX
            var y = y0

            while(x < stage.size && x >= 0 && y <= maxY && y >= 0){
                if (stage[x][y]) return false

                if(y+1 <= maxY && stage[x][y+1]){
                    if (x >= 1 && stage[x-1][y+1]){
                        if (x + 1 < stage.size && stage[x+1][y+1]){
                            stage[x][y] = true
                            return true
                        }
                        else{
                            x++
                            y++
                        }
                    }else{
                        x--
                        y++
                    }
                }else{
                    y++
                }
            }

            return false
        }

        var ans = 0

        while (true){
            ans++
            if(!drop()){
               return ans - 1
            }
        }
    }

    fun part2(input: List<String>): Int{
        var minX = Int.MAX_VALUE
        var maxX = Int.MIN_VALUE
        var maxY = Int.MIN_VALUE

        input.forEach {
            it.split(" -> ").forEach {
                val tmp = it.split(",").map { it.toInt() }
                minX = kotlin.math.min(minX, tmp.first())
                maxX = kotlin.math.max(maxX, tmp.first())
                maxY = kotlin.math.max(maxY, tmp.last())
            }
        }

        maxY += 2
        minX = -2000
        maxX = 2000

        val stage = Array<BooleanArray>(maxX - minX + 1){
            BooleanArray(maxY + 1){false}
        }

        input.forEach {
            val t = it.split(" -> ").map {
                val tmp = it.split(",").map { it.toInt() }
                tmp.first() to tmp.last()
            }
            (1 until t.size).forEach {
                val current = t[it]
                val last = t[it - 1]

                if (current.first == last.first){
                    val min = kotlin.math.min(current.second, last.second)
                    val max = kotlin.math.max(current.second, last.second)

                    (min .. max).forEach {
                        stage[current.first - minX][it] = true
                    }
                }else{
                    val min = kotlin.math.min(current.first, last.first)
                    val max = kotlin.math.max(current.first, last.first)

                    (min .. max).forEach {
                        stage[it - minX][current.second] = true
                    }
                }
            }
        }



        (0 until stage.size).forEach {
            stage[it][maxY] = true
        }


        fun drop(x0:Int = 500, y0:Int = 0):Boolean{
            var x = x0 - minX
            var y = y0

            while(x < stage.size && x >= 0 && y <= maxY && y >= 0){
                if (stage[x][y]) return false

                if(y+1 <= maxY && stage[x][y+1]){
                    if (x >= 1 && stage[x-1][y+1]){
                        if (x + 1 < stage.size && stage[x+1][y+1]){
                            stage[x][y] = true
                            return true
                        }
                        else{
                            x++
                            y++
                        }
                    }else{
                        x--
                        y++
                    }
                }else{
                    y++
                }
            }

            return false
        }

        var ans = 0

        while (true){
            ans++
            if(!drop()){
                return ans - 1
            }
        }
    }



    var input = readInput("Test14")
    println(part1(input))
    println(part2(input))

    input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}
