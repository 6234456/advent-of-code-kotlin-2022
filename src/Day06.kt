fun main() {

    fun part1(input: List<String>): Int {
        val s = input.first()
        val l = s.length
        val dp = Array<IntArray>(l+1){ IntArray(l+1){0} }
        val lastPos = IntArray(26){0}
        lastPos[s[0] - 'a'] = 1

        var j = 1
        var i = j + 1

        while (j <= l){
            while(i <= l) {
                val ls = lastPos[s[i-1] - 'a']
                if (ls < j){
                    dp[j][i] = dp[j][i-1] + 1
                }else{
                    dp[ls+1][i] = i - ls
                    j = ls + 1
                }

                if (dp[j][i] == 14)
                    return i

                lastPos[s[i-1] - 'a'] = i++
            }
        }


        return 0
    }

    fun part2(input: List<String>): String {

        return ""
    }
    var input = readInput("Test06")
    println(part1(input))
    println(part2(input))

    input = readInput("Day06")
    println(part1(input))
    println(part2(input))


}
