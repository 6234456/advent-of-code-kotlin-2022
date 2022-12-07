data class Node(val name:String, val value: Int = -1, val children: MutableList<Node> = mutableListOf<Node>()){
    fun size():Int{
        if (!isDir()) return value
        return children.fold(0){acc, node -> acc + node.size() }
    }

    fun isDir():Boolean = value < 0
}

fun main() {
    fun buildTree(input: List<String>):Node{
        val queue = java.util.LinkedList<Node>()
        val tmp = java.util.LinkedList<Node>()
        val regDigit = """^(\d+)\s+.+$""".toRegex()

        val head = Node("/")
        var current = head
        queue.add(head)

        input.forEach {
            if (it.startsWith("dir")){
                val n = Node(current.name + it.replaceFirst("dir ", "") + "/")
                tmp.add(n)
                current.children.add(n)
            }else if(regDigit.matches(it)){
                val arr = it.split(" ")
                val n = Node(current.name + arr.last(), arr.first().toInt())
                current.children.add(n)
            }else{
                if (it == "\$ ls"){
                    while (tmp.isNotEmpty()){
                        // Attention: addFirst
                        queue.addFirst(tmp.poll())
                    }
                    current = queue.poll()
                }
            }
        }

        return head
    }

    val map:MutableMap<Node, Int> = mutableMapOf()

    fun walk(node: Node):Int{
        if (!map.containsKey(node)){
            map[node] = if (node.isDir()){
                node.children.fold(0){
                        acc, n ->  acc + walk(n)
                }
            } else node.value
        }

        return map[node]!!
    }


    fun part1(input: List<String>): Int {

        walk(buildTree(input).apply {
            println("size of root: ${this.size()}")
        })

        return map.filter { it.key.isDir() && it.value < 100000 }.values.fold(0){acc, i ->  acc + i }
    }

    fun part2(input: List<String>): Int {

        val root = buildTree(input)
        walk(root)

        val treeSet = java.util.TreeSet<Int>()

        map.filter { it.key.isDir()}.values.forEach {
            treeSet.add(it)
        }

        return treeSet.ceiling( map[root]!! - 40000000) ?: map[root]!!

    }
    var input = readInput("Test07")
    println(part1(input))
    println(part2(input))

    input = readInput("Day07")
    println(part1(input))
    println(part2(input))


}
