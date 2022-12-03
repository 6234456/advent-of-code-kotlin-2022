fun main() {
    val judge: (Char)->Int = {
       if (it in 'a'..'z') {
           it - 'a' + 1
       } else{
           it - 'A' + 27
       }

    }


    fun part1(input: List<String>): Int {
        return input.map {
            val a = it.trim().toCharArray()
            val l = a.size / 2
            val m = mutableMapOf<Char, Int>()
            var ret = -1
            a.forEachIndexed { index, c ->
                if (index < l){
                   m[c] = m.getOrDefault(c, 0) + 1
                }else{
                   if (m.containsKey(c)){
                        ret = judge(c)
                       return@forEachIndexed
                   }
                }
            }
            ret
        }.fold(0){
            acc, i ->
            acc + i
        }
    }

    fun part2(input: List<String>): Int {
        var m = setOf<Char>()
        return input.mapIndexed {
            i, s ->
            val a = s.trim().toCharArray()
            var ret = 0

            if (i.mod(3) == 0){
               m = a.toSet()
            }else if (i.mod(3) == 1){
                m = m.intersect(a.toSet())
            }else{
                a.forEach {
                    if (it in m){
                        ret = judge(it)
                        return@forEach
                    }
                }
            }

            ret
        }.fold(0){
                acc, i ->
            acc + i
        }
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
