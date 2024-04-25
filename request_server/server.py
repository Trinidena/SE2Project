'''
Created on Feb 14, 2019

@author: SE2
'''
import zmq
import time
import json
import constants

class User:
    def __init__(self, creator, password, role):
        self.creator = creator
        self.password = password
        self.role = role

class Shirt:
    def __init__(self, name, has_pocket, shoulder, size, sleeve_length, color, neck_style, material, back_length, text, creator, status, business):
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
        self.status = status
        self.business = business

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
            'creator': shirt.creator,
            'status': shirt.status,
            'business': shirt.business
        }
        shirts_list.append(shirt_dict)
    return json.dumps(shirts_list)

def users_to_json(users):
    users_list = []
    for user in users:
        user_dict = {
            'creatorName': user.creator,
            'password': user.password,
            'role': user.role
        }
        users_list.append(user_dict)
    return json.dumps(users_list)


def main(protocol, ipAddress, port):
    
    response = ''
    valid_messages = ['get shirts', 'add shirt,', 'get users', 'add user,']
    shirts = []
    users = []
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
        if(request_type == 'get users'):
            response = users_to_json(users)  # Use the helper function to get JSON representation
            log("Sending all users data.")
        if (request_type == 'add user'):
            log("Received request: {0}".format(request_type))
            json_parts = values[1:]  # Get all parts of the JSON string except for the command part
            json_string = ','.join(json_parts)  # Rejoin the parts into a complete JSON string
            log("JSON Data Received: {0}".format(json_string))
            user_name = None  # Define shirt_name outside of try-except
            try:
        # Attempt to parse the reassembled JSON string.
                user_data = json.loads(json_string)
        # Process the shirt data...
                creator = user_data['creatorName']
                if creator and not any(u.creator == creator for u in users):
                    user = User(
                        creator=user_data['creatorName'],
                        password=user_data['password'],
                        role=user_data['role']
                        )
                    users.append(user)
            except json.JSONDecodeError as e:
                response = "JSON Decode Error"
                log(f"Failed to decode JSON: {e}")
            except KeyError as e:
                response = "missing key"
                log(f"Missing key in JSON data: {e}")
            response = 'true'
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
                        creator=shirt_data['creatorName'],
                        status=shirt_data['status'],
                        business=shirt_data['businessName'],
                        )
                shirts.append(shirt)
        # Log all shirts after adding a new one
                log("Logging all shirts...")
                for s in shirts:
                    log(f"Shirt Name: {s.name}")
                response = "true"
            elif shirt_name:
                response = "name already exists"
        if request_type == 'update shirt':
            try:
                # Directly use values from the split message data
                if len(values) < 4:
                    response = "Insufficient data provided"
                else:
                    shirt_name = values[1]
                    new_status = values[2]
                    business_id = values[3]  # Example of how you might handle additional data
        
                    shirt_found = False
                    for shirt in shirts:
                        if shirt.name == shirt_name:
                            shirt.status = new_status
                            shirt.business = business_id  # Update the status
                            response = "Update successful"
                            shirt_found = True
                            break
        
                    if not shirt_found:
                        response = "Shirt not found"
            except Exception as e:
                response = f"Error updating shirt: {str(e)}"


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
