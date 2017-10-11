package groovy

class LazyList {
    private head, tail

    LazyList(head, tail) {
        this.head = head;
        this.tail = tail
    }

    def LazyList getTail() { tail ? tail() : null }

    def List getHead(n) {
        def valuesFromHead = [];
        def current = this
        n.times {
            valuesFromHead << current.head
            current = current.tail
        }
        valuesFromHead
    }

    def LazyList filter(Closure p) {
        if (p(head))
            p.owner.prepend(head, { getTail().filter(p) })
        else
            getTail().filter(p)
    }
}