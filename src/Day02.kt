fun main() {
    val judge: (Char, Char)->Int = {
        a, x ->
        val t1 = a - 'A'
        val t2 = x - 'X'

        (t2 + 1) + (
                if (t1 == t2)
                    3
                else{
                    if((t1 + 2).mod(3) == t2) 0 else 6
                })

    }


    val judge2: (Char, Char)->Int = {
            a, x ->
        val t1 = a - 'A'
        val t2 = x - 'X'

        if (t2 == 0)
            1+ (t1 + 2).mod(3)
        else{
            if(t2 == 1)
               3 + t1 + 1
            else
                7 + (t1 + 1).mod(3)
        }
    }

    fun part1(input: List<String>): Int {
        return input.map {
            val a = it.first()
            val x = it.last()
            judge(a, x)
        }.fold(0){
            acc, i ->
            acc + i
        }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            val a = it.first()
            val x = it.last()
            judge2(a, x)
        }.fold(0){
                acc, i ->
            acc + i
        }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
