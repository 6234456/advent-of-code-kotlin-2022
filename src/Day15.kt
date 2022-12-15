import kotlin.math.abs

fun main() {
    fun merge(list: List<Pair<Int, Int>>):List<Pair<Int, Int>>{
        if (list.isEmpty()) return emptyList()

        var ans:MutableList<Pair<Int, Int>> = mutableListOf(list.first())
        
        list.drop(1).forEach {
           val last = ans.last()
            if (it.first > last.second){
                ans.add(it)
            }else{
                ans = ans.dropLast(1).toMutableList()
                ans.add(last.first to kotlin.math.max(last.second, it.second))
            }
        }

        return ans
    }

    fun merge(list: List<Pair<Int, Int>>, y: Int, ma: Int):Long{
        val sums: (Int, Int) -> Long = { x0, x1 -> (x0 .. x1.toLong()).sum() * 4000000 + y * (x1 + 1 - x0)}

        if (list.isEmpty()) return sums(0, ma)

        var ans:MutableList<Pair<Int, Int>> = mutableListOf(list.first())

        list.drop(1).forEach {
            val last = ans.last()
            if (it.first > last.second){
                ans.add(it)
            }else{
                ans = ans.dropLast(1).toMutableList()
                ans.add(last.first to kotlin.math.max(last.second, it.second))
            }
        }

        var cnt = 0
        var answer = 0L

        while (cnt <= ma){
            if (ans.isEmpty()){
                answer += sums(cnt, ma)
                return answer
            }

            val f = ans.first()
            if (cnt < f.first){
               answer += sums(cnt, f.first - 1)
                cnt = f.second + 1
            }else if (f.second >= cnt){
                cnt = f.second + 1
            }

            ans = ans.drop(1).toMutableList()
        }


        return answer
    }
    
    
    fun part1(input: List<String>, y:Int=10): Int {
        val digits = """(-?\d+)""".toRegex()
        val p = input.map {
            val arr = digits.findAll(it).toList().map { it.value.toInt() }
            val vertical = abs(arr[1] - y) 
            val distance =  abs(arr[1] - arr[3]) + abs(arr[0] - arr[2])
            
            if(distance >= vertical){
                (arr[0] - distance + vertical) to (arr[0] + distance - vertical) 
            }else
                null
        }.filterNotNull().sortedBy { it.first }
        return merge(p).map { it.second - it.first }.sum()
    }

    fun part2(input: List<String>, ma:Int = 20, mi:Int = 0): Long{
        val digits = """(-?\d+)""".toRegex()
        val p = input.map {
            digits.findAll(it).toList().map { it.value.toInt() }
        }

        var y = mi
        var ans:Long = 0

        while (y <= ma){
            val tmp = p.map {
                arr ->
                val vertical = abs(arr[1] - y)
                val distance =  abs(arr[1] - arr[3]) + abs(arr[0] - arr[2])

                if(distance >= vertical){
                    (arr[0] - distance + vertical) to (arr[0] + distance - vertical)
                }else
                    null }.filterNotNull().sortedBy { it.first }


            ans += merge(tmp, y, ma)

            y++
        }

        return ans

    }



    var input = readInput("Test15")
    println(part1(input))
    println(part2(input))

    input = readInput("Day15")
    println(part1(input, y=2000000))
    println(part2(input, ma=4000000))
}
