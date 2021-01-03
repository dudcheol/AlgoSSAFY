import sys

r = sys.stdin.readline
t = int(r())
for p in range(t):
    array_length, command_length, string_length = map(int, r().split())
    commands = list(r().rstrip())
    input_string = list(map(ord, input())) + [255]

    pair = {}
    stack = []
    for i in range(command_length):
        if commands[i] == "[":
            stack.append(i)
        elif commands[i] == "]":
            pair[stack[-1]] = i
            pair[i] = stack[-1]
            stack.pop()

    mem = [0] * array_length
    cp, mp, sp, count = 0, 0, 0, 0
    right_b = 0


    def sol():
        global cp, mp, sp, count
        if commands[cp] == "-":
            mem[mp] = (mem[mp] - 1) % pow(2, 8)
        elif commands[cp] == "+":
            mem[mp] = (mem[mp] + 1) % pow(2, 8)
        elif commands[cp] == "<":
            mp = (mp - 1) % array_length
        elif commands[cp] == ">":
            mp = (mp + 1) % array_length
        elif commands[cp] == "[":
            if mem[mp] == 0:
                cp = pair[cp]
        elif commands[cp] == "]":
            if mem[mp] != 0:
                cp = pair[cp]
        elif commands[cp] == ".":
            # 틀린부분
            pass
        elif commands[cp] == ",":
            # 틀린부분?
            # 문자열을 다 입력받았는데도 , 가 들어오면 255를 넣어줘야함
            mem[mp] = input_string[sp]
            if sp < string_length:
                sp += 1
        cp += 1

    # 처음 루프는 일단 명령어를 끝까지 실행하거나 5천만번을 돌때까지 일단 돌림
    while cp < command_length:
        count += 1
        sol()
        if count >= 50000000:
            break
    # 명령어를 끝까지 실행해서 조기 종료된경우는 끝내면되는데...
    if cp == command_length:
        print("Terminates")
    # 그게 아니여서 5천만번이상을 돌았다면
    else:
        # 한번 더 5천만번을 돌리면서 현재 루프를 돌고 있는,
        # 가장 오른쪽 외곽의 ] 의 위치를 저장한다
        right_end_bracket_index = cp
        while count >= 0:
            count -= 1
            sol()
            right_end_bracket_index = max(right_end_bracket_index, cp)
        # 출력
        print(f"Loops {pair[right_end_bracket_index]} {right_end_bracket_index}")
