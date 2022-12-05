fun main() {

    fun part1(input: List<String>): String {
        var sep = -1

        input.forEachIndexed { index, s ->
            if (s.isBlank()){
                sep = index
                return@forEachIndexed
            }
        }

        val indexList =  input[sep-1]
            .toCharArray().mapIndexed { index, c -> index to c }
            .filter { it.second.toString().isNotBlank() }.map { it.first }

        val stacks = Array(indexList.size){java.util.Stack<Char>()}

        ((sep - 2) downTo 0).forEach {
            indexList.map { idx ->
                if (input[it].length <= idx)
                    ' '
                else
                    input[it][idx]
             }.forEachIndexed { index, c ->
                if (c.toString().isNotBlank())
                    stacks[index].push(c)
            }
        }

        // orders

        ((sep + 1) until input.size).forEach {
            val orders = """\s+\d+(?:\s+|\b)""".toRegex().findAll(input[it]).map { it.value.trim().toInt()}.toList()
            val cnt = orders.first()
            val src = orders[1]
            val targ = orders.last()

            repeat(cnt){
                stacks[targ-1].push(stacks[src-1].pop())
            }

        }

        return stacks.map { it.peek() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        var sep = -1

        input.forEachIndexed { index, s ->
            if (s.isBlank()){
                sep = index
                return@forEachIndexed
            }
        }

        val indexList =  input[sep-1]
            .toCharArray().mapIndexed { index, c -> index to c }
            .filter { it.second.toString().isNotBlank() }.map { it.first }

        val stacks = Array(indexList.size){java.util.Stack<Char>()}

        ((sep - 2) downTo 0).forEach {
            indexList.map { idx ->
                if (input[it].length <= idx)
                    ' '
                else
                    input[it][idx]
            }.forEachIndexed { index, c ->
                if (c.toString().isNotBlank())
                    stacks[index].push(c)
            }
        }

        // orders
        val tmp = java.util.Stack<Char>()

        ((sep + 1) until input.size).forEach {
            val orders = """\s+\d+(?:\s+|\b)""".toRegex().findAll(input[it]).map { it.value.trim().toInt()}.toList()
            val cnt = orders.first()
            val src = orders[1]
            val targ = orders.last()

            repeat(cnt){
                tmp.push(stacks[src-1].pop())
            }

            while (!tmp.isEmpty()){
                stacks[targ-1].push(tmp.pop())
            }
        }

        return stacks.map { it.peek() }.joinToString("")
    }
    var input = readInput("Test05")
    println(part1(input))
    println(part2(input))

    input = readInput("Day05")
    println(part1(input))
    println(part2(input))


}
