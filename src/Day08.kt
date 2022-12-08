import kotlin.math.abs
import kotlin.math.max

fun main() {

    fun part1(input: List<String>): Int {
        val src = input.map { it.toCharArray().map { it.digitToInt() }.toIntArray() }.toTypedArray()
        val n = input.size
        // up down left right
        val dps: Array<Array<BooleanArray>> = Array(4){Array(n){ BooleanArray(n){true} } }

        // up -> down

        val maxArray:IntArray = IntArray(n)

        dps[0].indices.forEach { index ->
            if (index == 0){
                (0 until n).forEach {
                   maxArray[it] =  src[index][it]
               }
            }else{
                (0 until n).forEach {
                    dps[0][index][it] = src[index][it] > maxArray[it]
                    maxArray[it] = max(maxArray[it], src[index][it])
                }
            }
        }

        // down -> up
        (n-1 downTo 0).forEach { index ->
            if (index == n-1){
                (0 until n).forEach {
                    maxArray[it] =  src[index][it]
                }
            }else{
                (0 until n).forEach {
                    dps[1][index][it] = src[index][it] > maxArray[it]
                    maxArray[it] = max(maxArray[it], src[index][it])
                }
            }
        }

        // left -> right
        (0 until n).forEach { index ->
            if (index == 0){
                (0 until n).forEach {
                    maxArray[it] =  src[it][index]
                }
            }else{
                (0 until n).forEach {
                    dps[2][it][index] = src[it][index] > maxArray[it]
                    maxArray[it] = max(maxArray[it], src[it][index])
                }
            }
        }

        //   right-> left
        (n-1 downTo 0).forEach { index ->
            if (index == n-1){
                (0 until n).forEach {
                    maxArray[it] =  src[it][index]
                }
            }else{
                (0 until n).forEach {
                    dps[3][it][index] = src[it][index] > maxArray[it]
                    maxArray[it] = max(maxArray[it], src[it][index])
                }
            }
        }

        var res = 0

        (0 until n).forEach {
            i ->
            (0 until n).forEach {
                j ->
                if(dps[0][i][j] || dps[1][i][j] || dps[2][i][j] || dps[3][i][j])
                    res++
            }
        }

        return res
    }

    fun part2(input: List<String>): Int {

        val src = input.map { it.toCharArray().map { it.digitToInt() }.toIntArray() }.toTypedArray()
        val n = input.size
        // up down left right
        val dps: Array<Array<IntArray>> = Array(4){Array(n){ IntArray(n){0} } }

        // up -> down

        val lastPos = Array<IntArray>(n){ IntArray(10){-1} }

        fun reset(){
            (0 until n).forEach {
                    i ->
                (0 until 9).forEach {
                        j ->
                    lastPos[i][j] = -1

                }}
        }

        fun lastP(row: Int, col: Int, order: Int, reversed: Boolean = true):Int{
            val current = src[row][col]

           return  (current .. 9).map {
               if (reversed)
                   if (lastPos[col][it] == -1) order else abs(lastPos[col][it] - row)
               else
                    if (lastPos[row][it] == -1) order else abs(lastPos[row][it] - col)
            }.min()
        }

        dps[0].indices.forEach { index ->
            if (index > 0){
                (0 until n).forEach {
                    dps[0][index][it] = lastP(index, it, index)
                }
            }

            (0 until n).forEach {
                lastPos[it][src[index][it]] = index
            }
        }

        reset()

        // down -> up
        (n-1 downTo 0).forEach { index ->
            if (index < n -1){
                (0 until n).forEach {
                    dps[1][index][it] = lastP(index, it, n-1 - index)
                }
            }

            (0 until n).forEach {
                lastPos[it][src[index][it]] = index
            }
        }

        reset()
        // left -> right  by col
        (0 until n).forEach { index ->
            if (index > 0){
                (0 until n).forEach {
                    dps[2][it][index] = lastP(it, index, index, false)
                }
            }

            (0 until n).forEach {
                lastPos[it][src[it][index]] = index
            }
        }

        reset()

        //   right-> left
        (n-1 downTo 0).forEach { index ->
            if (index < n-1){
                (0 until n).forEach {
                    dps[3][it][index] = lastP(it, index, n-1 - index, false)
                }
            }

            (0 until n).forEach {
                lastPos[it][src[it][index]] = index
            }
        }

        var res = 0

        var i0 = 0
        var j0 = 0
        (0 until n).forEach {
                i ->
            (0 until n).forEach {
                    j ->
                val tmp = max(res,dps[0][i][j]* dps[1][i][j] * dps[2][i][j]*dps[3][i][j])

                if (tmp > res){
                    i0 = i
                    j0 = j
                }

                res = tmp
            }
        }

        println("i : $i0,  j: $j0")

        return res
    }
    var input = readInput("Test08")
    println(part1(input))
    println(part2(input))

    input = readInput("Day08")
    println(part1(input))
    println(part2(input))


}
