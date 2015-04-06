class LinkedList:
    """
    linked list implementation
    """

    def __init__(self):
        self.head = []

    def insert(self, key, value):
        cur = self.head
        while cur:
            cur = cur[-1]
        cur.append(key)
        cur.append(value)
        cur.append([])

    def delete(self, key):
        if not self.head:
            print "linked list is empty."
            return
        cur = self.head
        if not cur[-1]:
            if key == cur[0]:
                cur = []
                print key + " is deleted."
            else:
                print "no " + key + " is found."
        else:
            prev = []
            while cur:
                if cur[0] == key:
                    if not prev:
                        self.head = cur[-1]
                    else:
                        prev[-1] = cur[-1]
                    print key + " is deleted."
                    return
                else:
                    prev = cur
                    cur = cur[-1]
            print "no " + key + " is found."

    def _print(self):
        cur = self.head
        if cur:
            while cur:
                print "(" + cur[0] + "," + str(cur[1]) + ") -->",
                cur = cur[-1]
            print "end"


ll = LinkedList()
ll.insert("abc", 10)
ll._print()
ll.insert("bc", 5)
ll._print()
ll.delete("bc")
ll._print()


