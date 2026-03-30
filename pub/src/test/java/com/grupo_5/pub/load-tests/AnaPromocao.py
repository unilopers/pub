from locust import HttpUser, task, between, events
import json
import requests

TOKEN_GLOBAL = ""

@events.test_start.add_listener
def setup_inicial(environment, **kwargs):

    global TOKEN_GLOBAL
    url_base = "http://localhost:8080"

    credenciais = {
        "username": "Hello-kit-teste-de-carga",
        "password": "123"
    }

    requests.post(f"{url_base}/auth/cadastro", json=credenciais)

    resposta = requests.post(f"{url_base}/auth/login", json=credenciais)

    if resposta.status_code == 200:
        TOKEN_GLOBAL = resposta.json().get("token")
        print("Criou certinho")
    else:
        print(f"Error. Status: {resposta.status_code}")


class PromocaoLoadTest(HttpUser):
    wait_time = between(1, 3)

    @task(1)
    def criar_promocao(self):

        xml_payload = """
        <Promocao>
            <nome>Promoção Relâmpago show Hello kit X Locust</nome>
            <descricao>Teste de estresse | Estressando a Hello kit do pub</descricao>
            <dataInicio>2026-03-23</dataInicio>
            <dataFim>2026-12-31</dataFim>
            <tipoDesconto>PERCENTUAL</tipoDesconto>
            <valorDesconto>10.0</valorDesconto>
        </Promocao>
        """

        headers_xml = {
            "Content-Type": "application/xml",
            "Accept": "application/xml",
            "Authorization": f"Bearer {TOKEN_GLOBAL}"
        }

        with self.client.post("/api/promocoes", data=xml_payload, headers=headers_xml, name="Criando promoção", catch_response=True) as response:
            if response.status_code in [200, 201]:
                response.success()
            else:
                response.failure(f"Falha no POST: {response.status_code}")

    @task(2)
    def listar_promocoes(self):

        headers_xml = {
            "Accept": "application/xml",
            "Authorization": f"Bearer {TOKEN_GLOBAL}"
        }

        with self.client.get("/api/promocoes", headers=headers_xml, name="Listando promoções", catch_response=True) as response:
            if response.status_code == 200:
                response.success()
            else:
                response.failure(f"Falha no GET: {response.status_code}")