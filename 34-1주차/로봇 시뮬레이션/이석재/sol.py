import sys
from collections import deque

r = sys.stdin.readline

width, height = map(int, r().split())
robot_num, command_num = map(int, r().split())
board = [[0] * width for _ in range(height)]
robots = {}
for i in range(robot_num):
    y, x, direction = r().split()
    ny = int(y) - 1
    nx = height - int(x)
    robots[i + 1] = [nx, ny, direction]
    board[nx][ny] = i + 1
commands = []
for i in range(command_num):
    target_robot, direction, iter_num = r().split()
    commands.append((int(target_robot), direction, int(iter_num)))
dir_dict = {"N": (-1, 0),
            "S": (1, 0),
            "W": (0, -1),
            "E": (0, 1)}
dir_dict2 = {0: "N",
             1: "E",
             2: "S",
             3: "W"}
dir_dict3 = {"N": 0,
             "E": 1,
             "S": 2,
             "W": 3}


def bfs(robot_num, x, y, command, iter_num):
    cur_dir = robots[robot_num][2]
    rx, ry = x, y
    if command == "R":
        temp = iter_num % 4
        target_index = (dir_dict3[cur_dir] + temp) % 4
        robots[robot_num][2] = dir_dict2[target_index]
        return -2
    elif command == "L":
        temp_p = iter_num % 4
        temp = 4 - temp_p
        target_index = (dir_dict3[cur_dir] + temp) % 4
        robots[robot_num][2] = dir_dict2[target_index]
        return -2
    else:
        board[x][y] = 0
        q = deque([(x, y)])
        visited = [[0] * width for _ in range(height)]
        visited[x][y] = 1
        while q and iter_num:
            cur_x, cur_y = q.popleft()
            new_x, new_y = cur_x + dir_dict[cur_dir][0], cur_y + dir_dict[cur_dir][1]
            if 0 <= new_x < height and 0 <= new_y < width:
                if not visited[new_x][new_y]:
                    if board[new_x][new_y]:
                        return board[new_x][new_y]
                    q.append((new_x, new_y))
                    rx, ry = new_x, new_y
                    iter_num -= 1
        if iter_num:
            return 0
    board[rx][ry] = robot_num
    robots[robot_num] = [rx, ry, cur_dir]
    return -3


for command in commands:
    target_robot, target_command, iter_num = command
    result = bfs(target_robot, robots[target_robot][0], robots[target_robot][1], target_command, iter_num)
    print(robots)
    if result == -2:
        continue
    if result == 0:
        print(f"Robot {target_robot} crashes into the wall")
        break
    elif result > 0:
        print(f"Robot {target_robot} crashes into robot {result}")
        break
    elif result == -3:
        continue
else:
    print("OK")
