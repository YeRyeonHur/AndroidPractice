from flask import Flask, jsonify, request
import mariadb

try:
    connection = mariadb.connect(
        user="kanei0415",
        password="kanei0415!",
        host="localhost",
        port=6078,
        database="community"
    )
except mariadb.Error as e:
    print(f"Error connecting to MariaDB Platform: {e}")

cursor = connection.cursor()


def get_app():
    app = Flask("__main__")

    @app.route("/", methods=["GET"])
    def index():
        name = request.args.get('name', 'flask')
        return jsonify({"msg": "hello " + name})

    @app.route("/dataRequest", methods=["POST"])
    def data_request():
        cursor.execute(f"SELECT `index`, `email`, `name` FROM test WHERE `index` = {request.form['index']}")

        for (index, email, name) in cursor:
            data = {
                "index": index,
                "email": email,
                "name": name
            }

        return jsonify(data)

    @app.route("/getUserList", methods=["GET"])
    def userList():
        data = []

        cursor.execute(f"SELECT `index`, `name`, `tel` FROM users")

        for(index, name, tel) in cursor:
            data.append({
                "index": index,
                "name": name,
                "tel": tel
            })

        return jsonify({"data": data})

    return app


if __name__ == "__main__":
    get_app().run(host='127.0.0.1', port=5000, debug=False)
