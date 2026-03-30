from locust import HttpUser, task, between
from faker import Faker

fake = Faker()

class MesaUser(HttpUser):
    wait_time = between(1, 3)

    def on_start(self):
        response = self.client.post("/auth/login", json={
            "username": "felipearaujo",
            "password": "12345"
        })

        if response.status_code == 200:
            self.token = response.json()["token"]
        else:
            print("Erro ao autenticar:", response.text)
            self.token = None

    @task
    def criar_e_buscar_mesa(self):
        if not self.token:
            return

        headers = {
            "Authorization": f"Bearer {self.token}"
        }

        dados = {
            "capacidade": fake.random_int(min=1, max=10),
            "status": "LIVRE"
        }

        response = self.client.post(
            "/mesas",
            json=dados,
            headers=headers
        )

        if response.status_code in [200, 201]:
            id_mesa = response.json()["id"]

            self.client.get(
                f"/mesas/{id_mesa}",
                headers=headers
            )
        else:
            print("Erro POST mesa:", response.status_code, response.text)