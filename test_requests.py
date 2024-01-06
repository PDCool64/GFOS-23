import requests


def test_post_on_account():
    url = "http://localhost:8080/Backend/resources/account/"
    data = {
        "vorname": "test",
        "name": "test",
        "email": "test@web.de",
        "password": "test",
        "geburstsdatum": "1990-01-01",
    }
    response = requests.post(url, data=data)
    print(response.status_code)
    print(response.text)
    print(response.json())
    print(response.headers)
    print(response)

def main():
    pass


if __name__ == "__main__":
    main()