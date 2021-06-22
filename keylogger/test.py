from pynput.keyboard import Listener, Key
logs = ''

for i in range(10):

    a = input()

    if a in 'Key':
        a = ' '+a+' '
    logs += a

print(logs)