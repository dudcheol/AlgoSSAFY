# https://0equal2.tistory.com/70?category=432951

def time_to_sec(time_string):
    hour, minute, second = time_string.split(":")
    hour, minute, second = int(hour), int(minute), int(second)
    return second + minute * 60 + hour * 3600


def sec_to_time(second_number):
    hour = second_number // 3600
    left = second_number % 3600
    minute = left // 60
    left = left % 60
    second = left
    hour_string = ("0" if hour < 10 else "") + str(hour)
    minute_string = ("0" if minute < 10 else "") + str(minute)
    second_string = ("0" if second < 10 else "") + str(second)

    return hour_string + ":" + minute_string + ":" + second_string


def solution(play_time, adv_time, logs):
    prefix_sum = [0 for i in range(time_to_sec(play_time) + 1)]

    for log in logs:
        start_time, end_time = log.split("-")
        start_sec, end_sec = time_to_sec(start_time), time_to_sec(end_time)
        prefix_sum[start_sec] += 1
        prefix_sum[end_sec] -= 1

    # https://dev-note-97.tistory.com/156
    for i in range(1, time_to_sec(play_time) + 1):
        prefix_sum[i] = prefix_sum[i] + prefix_sum[i - 1]
    for i in range(1, time_to_sec(play_time) + 1):
        prefix_sum[i] = prefix_sum[i] + prefix_sum[i - 1]

    adv_sec, play_sec = time_to_sec(adv_time), time_to_sec(play_time)
    # 1초부터 광고 play_time-1초까지가 최초로 광고를 틀 수 있는 범위이고,
    # 그러므로 광고 play_time-1초의 prefix_sum이 최소의 max_sum이 된다.
    max_sum, result_sec = prefix_sum[adv_sec - 1], 0
    # 아래 반복문은 1초 ~ 광고 play_time초 까지의 prefix_sum을 비교하면서 계산함
    for i in range(adv_sec, play_sec):
        temp = prefix_sum[i] - prefix_sum[i - adv_sec]
        if temp > max_sum:
            max_sum = temp
            result_sec = i - adv_sec + 1
    return sec_to_time(result_sec)
