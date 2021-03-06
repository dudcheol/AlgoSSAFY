def solution(new_id):
    result = new_id.lower()
    set1 = {chr(i) for i in range(97, 123)}
    set1 = set1 | {str(i) for i in range(0, 10)}
    set1 = set1 | {"-", "_", "."}
    result_list = list(result)
    result_list = list(filter(lambda x: x in set1, result_list))
    stack = []
    for item in result_list:
        if not stack:
            if item == ".":
                continue
            stack.append(item)
        else:
            if item == "." and stack[-1] == ".":
                continue
            stack.append(item)
    if stack and stack[-1] == ".":
        stack.pop()
    if not stack:
        stack.append("a")
    if len(stack) >= 16:
        stack = stack[:15]
        if stack[-1] == ".":
            stack.pop()
    if len(stack) <= 2:
        while len(stack) != 3:
            stack.append(stack[-1])
    return "".join(stack)
