import flask

app = flask.Flask(__name__)


@app.route("/", methods=['GET'])
def root():
    return "Hello there"


@app.route("/dashboard", methods=['GET', 'POST'])
def dashboard():
    return ""

def dispatch_json(json_data):
    if is_auth(json_data):
        return auth(json_data)
    else:
        return "not implemented"

def auth(json_data):
    if not is_valid_auth_json(json_data):
        return invalid_auth_json_data()
    
    if is_confirm_code(json_data):
        if session[get_user(json_data)].ccode == get_ccode(json_data):
            return make_success_auth_response(json_data)
    
    if is_auth_signup(json_data):
        if db_user_ex() or (db_user_session(get_user(json_data)) == 'active'):
            return make_success_auth_response(json_data)
        
        if not db_user_ex():
            return make_success_register_response(json_data)
        
        if db_invalid_user_creds():
            return make_invalid_auth_response(json_data, 'invalid creds')
        
        return make_error_response(json_data, 'unknown error sign up')
    
    if is_auth_ex(json_data):
        if not db_user_ex():
            return make_error_response(json_data, 'user not exist')
        
        session[get_user(json_data)].ccode = SMS_generate_confirm_code()
        SMS_send_to(json_data, ccode)
        
        return "waiting confirm code"
    
    if is_auth_by_session(json_data):
        if db_user_session(get_user(json_data)) == 'active':
            return make_success_auth_response(json_data)
        else:
            return make_error_response(json_data, 'session key is inactive')
        

@app.route("/api", methods=['GET', 'POST'])
def api():
    if flask.request.method == 'GET':
        if not flask.request.is_json():
            return flask.abort(302)
        else:
            return dispatch_json(flask.request.get_json())
    else:
        return "not implemented"


if __name__ == "__main__":
    app.run(
        debug=True
    )
