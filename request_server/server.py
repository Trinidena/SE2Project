'''
Created on Feb 14, 2019

@author: SE2
'''
import zmq
import time
import json
import constants

class Shirt:
    def __init__(self, name, has_pocket, shoulder, size, sleeve_length, color, neck_style, material, back_length, text, creator):
        self.name = name
        self.has_pocket = has_pocket
        self.shoulder = shoulder
        self.size = size
        self.sleeve_length = sleeve_length
        self.color = color
        self.neck_style = neck_style
        self.material = material
        self.back_length = back_length
        self.text = text
        self.creator = creator

def log(message):
    print("SERVER::{0}".format(message))
    
def shirts_to_json(shirts):
    shirts_list = []
    for shirt in shirts:
        shirt_dict = {
            'name': shirt.name,
            'has_pocket': shirt.has_pocket,
            'shoulder': shirt.shoulder,
            'size': shirt.size,
            'sleeve_length': shirt.sleeve_length,
            'color': shirt.color,
            'neck_style': shirt.neck_style,
            'material': shirt.material,
            'back_length': shirt.back_length,
            'text': shirt.text,
            'creator': shirt.creator
        }
        shirts_list.append(shirt_dict)
    return json.dumps(shirts_list)

def main(protocol, ipAddress, port):
    
    response = ''
    valid_messages = ['get shirts', 'add shirt,']
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
        log("values = {0}".format(values))
        if (request_type not in valid_messages):
            response = "bad format"
        if(message == b"exit"):
            return
        if(request_type == 'get shirts'):
            response = shirts_to_json(shirts)  # Use the helper function to get JSON representation
            log("Sending all shirts data.")
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
                name = shirt_data['name']
            except json.JSONDecodeError as e:
                response = "JSON Decode Error"
                log(f"Failed to decode JSON: {e}")
            except KeyError as e:
                response = "missing key"
                log(f"Missing key in JSON data: {e}")
                
            if name and not any(s.name == name for s in shirts):
                shirt = Shirt(
                        name=shirt_data['name'],
                        has_pocket=shirt_data['hasPocket'],
                        shoulder=shirt_data['shoulderWidth'],
                        size=shirt_data['size'],
                        sleeve_length=shirt_data['sleeveLength'],
                        color=shirt_data['color'],
                        neck_style=shirt_data['neckStyle'],
                        material=shirt_data['material'],
                        back_length=shirt_data['backLength'],
                        text=shirt_data['shirtText'],
                        creator=shirt_data['creatorName']
                        )
                shirts.append(shirt)
        # Log all shirts after adding a new one
                log("Logging all shirts...")
                for s in shirts:
                    log(f"Shirt Name: {s.name}")
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
            shirts[:] = [s for s in shirts if s.name != name]
            response = 'true'
        #  Do some 'work'
        time.sleep(1)
        #  Send reply back to client
        log("Sending response of {0}".format(response))
        socket.send_string(response)

if (__name__ == "__main__"):
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)
