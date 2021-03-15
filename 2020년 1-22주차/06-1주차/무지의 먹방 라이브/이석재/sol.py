from heapq import *


def solution(food_times, k):
    index_list = [i for i in range(1, len(food_times) + 1)]
    food_times = list(map(lambda x, y: (x, y), food_times, index_list))
    pq = food_times
    heapify(pq)


    cur_time, pq_length, spin_num = 0, len(pq), 0
    while True:
        # 시간이 다 되기 전에 pq가 비면 -1을 반환
        if not pq:
            return -1
        least_food_mount, least_food_index = pq[0]
        cur_time += (least_food_mount - spin_num) * pq_length
        # 현재 최소 양의 음식을 다 먹을때까지 회전시키고난 뒤 시간이 목표 시간보다 크면
        # 더해준 시간을 다시 빼주고 반복문 탈출
        if cur_time > k:
            cur_time -= (least_food_mount - spin_num) * pq_length
            break
        # 시간을 처리했으니 pq에서 최소 양의 음식을 빼줌
        pop_least_food_mount, pop_least_food_index = heappop(pq)
        # pq에서 빠진 음식의 양 = 회전한 횟수 = 다음 최소 양의 음식에서 빼줘야 함
        spin_num = pop_least_food_mount
        pq_length -= 1
    # 이 시점에 pq에는 후보군 밖에 남지 않았다.
    # 그래서 구지 값을 빼주지 않아도 된다. 어짜피 같은 값을 빼주어도 오름차순 정렬한 결과는 같다.
    pq.sort(key=lambda x: x[1])
    return pq[(k - cur_time) % len(pq)][1]


food_times = [3, 1, 2]
k = 5
print(solution(food_times, k))

food_times = [5, 2, 3, 4, 1]
k = 10
print(solution(food_times, k))
