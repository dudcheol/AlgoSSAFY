import sys

r = sys.stdin.readline


class Node:
    def __init__(self, value=None):
        self.value = value
        self.child = {}


class Trie:
    def __init__(self):
        self.head = Node()

    def insert(self, input_string):
        cur = self.head
        for ch in input_string:
            if ch not in cur.child:
                cur.child[ch] = Node(ch)
            cur = cur.child[ch]
            # 만일 Trie에 삽입하고 있을 때 현재 노드가 자식으로 "*"를 가지고 있다면 
            # 일관성을 유지할 수 없는 경우이므로 False 반환
            if "*" in cur.child:
                return False
        # 만일 "*" 을 삽입하려는 직전에 자식으로 임의의 노드가 있다, 즉 child를 뭐라도 가지고 있으면
        # 일관성을 유지할 수 없는 경우이므로 False 반환
        if cur.child:
            return False
        # 위 두개의 경우를 만족할 경우에는 "*"를 마킹하고 True 반환
        cur.child["*"] = True
        return True


test_case_num = int(r())
for _ in range(test_case_num):
    length = int(r())
    board = []
    for i in range(length):
        board.append(r().rstrip())

    trie = Trie()
    for item in board:
        if not trie.insert(item):
            print("NO")
            break
    else:
        print("YES")
