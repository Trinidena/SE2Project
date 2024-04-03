'''
Created on Feb 14, 2019

@author: SE2
'''
import zmq
import time
import constants

class Shirt:
    def __init__(self, shirt_name, user_name, password):
        self.shirt_name = shirt_name
        self.user_name = user_name
        self.password = password

def log(message):
    print("SERVER::{0}".format(message))

def main(protocol, ipAddress, port):
    
    response = ''
    valid_messages = ['get shirt names', 'add shirt,']
    shirts = []
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("{0}://{1}:{2}".format(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT))
    
    while True:
        #  Wait for next request from client
        response = ''
        log("waiting for request...")
        message = socket.recv()
        log("Received request: {0}".format(message))
        message_data = message.decode('utf-8')
        log("message_data = {0}".format(message_data))
        values = message_data.split(',')
        
        request_type = values[0]
        try:
            shirt_name = values[1]
            user_name = values[2]
            password = values[3]
        except IndexError:
            log("Index doesn't exist!")
            
        log("values = {0}".format(values))
        if (request_type not in valid_messages):
            response = "bad format"
        if(message == b"exit"):
            return
        if(request_type == 'get shirt'):
            for shirt in shirts:
                if (shirt.shirt_name == shirt_name):
                    response = "{0},{1},{2}".format(shirt.shirt_name,shirt.user_name,shirt.password)
                    log("Response is {0}, {1}, {2}".format(shirt.shirt_name,shirt.user_name,shirt.password))
        if (request_type == 'get shirt names'):
            shirt_names = ""
            for shirt in shirts:
                if shirt.shirt_name not in shirt_names:
                    shirt_names += shirt.shirt_name + ","
                    log("Shirt names are {0}".format(shirt_names))
            response = shirt_names
        if (request_type == 'add shirt'):
            if (shirt_name in [s.shirt_name for s in shirts]):
                response = "name already exists"
            else:
                log("Received add shirt")
                log("Size of request arguments is ".format(str((len(values)))))
                shirt = Shirt(shirt_name, user_name, password)
                shirts.append(shirt)
                response = "true"
        if (request_type == 'update shirt'):
            for i, shirt in enumerate(shirts):
                if (shirt.shirt_name == shirt_name):
                    shirt = Shirt(shirt.shirt_name, user_name, password)
                    shirts[i] = shirt
                    response = "true"
        if (request_type == 'remove shirt'):
            shirts[:] = [s for s in shirts if s.shirt_name != shirt_name]
            response = 'true'
        #  Do some 'work'
        time.sleep(1)
        #  Send reply back to client
        log("Sending response of ".format(response))
        socket.send_string(response)

if (__name__ == "__main__"):
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)
