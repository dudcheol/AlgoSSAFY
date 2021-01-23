import sys

r = sys.stdin.readline
sensor_num = int(r())
center_num = int(r())
sensors = list(map(int, r().split()))

sensors.sort()
gaps = []
for i in range(len(sensors) - 1):
    gaps.append(sensors[i + 1] - sensors[i])

gaps.sort(reverse=True)
if center_num == 1:
    print(sensors[-1] - sensors[0])
else:
    print(sum(gaps[center_num - 1:]))
