import sys

r = sys.stdin.readline
melody, fret = map(int, r().split())

guitar = {}
for line in range(1, 7):
    guitar[line] = []
count = 0
for i in range(melody):
    line_num, target_fret = map(int, r().split())
    
    while guitar[line_num] and guitar[line_num][-1] > target_fret:
        guitar[line_num].pop()
        count += 1
    if (guitar[line_num] and guitar[line_num][-1] != target_fret) or not guitar[line_num]:
        guitar[line_num].append(target_fret)
        count += 1
print(count)
