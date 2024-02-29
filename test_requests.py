import requests
import csv

# Falls Python ganz verboten war: Shhh! Das war nie hier!

liste_mit_namen = csv.reader(open('namen.csv', 'r'))

token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJHRk9TUHJvamVrdCIsInN1YiI6InBoaWxpcHBkb2VyaW5nNjRAd2ViLmRlIn0._fx5iRl4mBBPhll_I76xYImLh1xzjXD9zf5aq4mnNXs"

for eintrag in liste_mit_namen:
    name = eintrag[0]
    url = "http://localhost:8080/Backend/api/account/"
    headers = {
        "content-type": "application/json",
        "Authorization": token,
        "password": '"' + eintrag[3] + '"'
    }
    data = {
        "email": name + "." + eintrag[1] + "@web.de",
        "name": name,
        "vorname": eintrag[1],
        "geburtsdatum": "2008-07-15T00:00:01Z[UTC]",
        "isAdmin": "false"
    }
    print (data)
    response = requests.post(url, headers=headers, json=data)
    print(response.text)

