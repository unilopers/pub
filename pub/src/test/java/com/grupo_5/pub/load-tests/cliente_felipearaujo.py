from locust import HttpUser, task, between
from faker import Faker

fake = Faker()

class ClienteUser(HttpUser):
    wait_time = between(1, 2)

    def on_start(self):
        response = self.client.post("/auth/login", json={
            "username": "felipearaujo",
            "password": "12345"
        })

        if response.status_code == 200:
            self.token = response.json().get("token")
        else:
            print("Erro login:", response.text)
            self.token = None

    @task
    def fluxo_cliente(self):
        if not self.token:
            return

        headers = {
            "Authorization": f"Bearer {self.token}"
        }

        dados = {
            "nome": fake.name(),
            "contato": fake.email()
        }

        # 🔹 POST
        response = self.client.post(
            "/clientes",
            json=dados,
            headers=headers
        )

        # 🔍 DEBUG AQUI
        if response.status_code not in [200, 201]:
            print("ERRO POST:", response.status_code, response.text)
            return

        try:
            data = response.json()
        except:
            print("Resposta não é JSON:", response.text)
            return

        id_cliente = data.get("id")

        if not id_cliente:
            print("ID inválido:", data)
            return

        # 🔹 GET seguro
        response_get = self.client.get(
            f"/clientes/{id_cliente}",
            headers=headers
        )

        if response_get.status_code not in [200, 404]:
            print("ERRO GET:", response_get.status_code, response_get.text)