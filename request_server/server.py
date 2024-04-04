'''
Created on Feb 14, 2019

@author: SE2
'''
import zmq
import time
import json
import constants

class Shirt:
    def __init__(self, shirt_name):
        self.shirt_name = shirt_name

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
        log(values)
        
        request_type = values[0]
        try:
            shirt_name = values[1]
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
                    response = "{0}".format(shirt.shirt_name)
                    log("Response is {0}".format(shirt.shirt_name))
        if (request_type == 'get shirt names'):
            shirt_names = ""
            for shirt in shirts:
                if shirt.shirt_name not in shirt_names:
                    shirt_names += shirt.shirt_name + ","
                    log("Shirt names are {0}".format(shirt_names))
            response = shirt_names
        if (request_type == 'add shirt'):
            log("Received request: {0}".format(request_type))
            json_parts = values[1:]  # Get all parts of the JSON string except for the command part
            json_string = ','.join(json_parts)  # Rejoin the parts into a complete JSON string
            log("JSON Data Received: {0}".format(json_string))
            shirt_name = None  # Define shirt_name outside of try-except
            try:
        # Attempt to parse the reassembled JSON string.
                shirt_data = json.loads(json_string)
        # Process the shirt data...
                shirt_name = shirt_data['name']
            except json.JSONDecodeError as e:
                response = "JSON Decode Error"
                log(f"Failed to decode JSON: {e}")
            except KeyError as e:
                response = "missing key"
                log(f"Missing key in JSON data: {e}")
                
            if shirt_name and not any(s.shirt_name == shirt_name for s in shirts):
                shirt = Shirt(shirt_name)
                shirts.append(shirt)
        # Log all shirts after adding a new one
                log("Logging all shirts...")
                for s in shirts:
                    log(f"Shirt Name: {s.shirt_name}")
                response = "true"
            elif shirt_name:
                response = "name already exists"
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
        log("Sending response of {0}".format(response))
        socket.send_string(response)

if (__name__ == "__main__"):
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)
