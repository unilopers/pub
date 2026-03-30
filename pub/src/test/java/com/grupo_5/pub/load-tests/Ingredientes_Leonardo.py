from locust import HttpUser, task, between
import time

class IngredienteUser(HttpUser):
    wait_time = between(1, 2)
    host = "http://localhost:8080"

    def gerar_ingrediente(self):
        return {
            "nome": f"Ingrediente_{time.time_ns()}",
            "unidadeMedida": "ml",
            "estoqueAtual": 100.0,
            "estoqueMinimo": 10.0
        }


    @task(2)
    def criar_ingrediente(self):
        ingrediente = self.gerar_ingrediente()

        with self.client.post(
                "/ingredientes",
                json=ingrediente,
                catch_response=True
        ) as response:

            if response.status_code not in [200, 201]:
                print("❌ ERRO POST")
                print("Status:", response.status_code)
                print("Resposta:", response.text)
                response.failure("Erro ao criar ingrediente")
            else:
                response.success()


    @task(1)
    def listar_ingredientes(self):
        with self.client.get(
                "/ingredientes",
                catch_response=True
        ) as response:

            if response.status_code != 200:
                print("❌ ERRO GET")
                print("Status:", response.status_code)
                print("Resposta:", response.text)
                response.failure("Erro ao listar ingredientes")
            else:
                response.success()

#Mudança Realizada Temporariamente Na SecurityConfig Para que o teste pudesse ser executado permitindo login
#@Bean
#    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
#        http
#        .csrf(AbstractHttpConfigurer::disable)
#    .authorizeHttpRequests(auth -> auth
#    .anyRequest().permitAll()
#    )
#   .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
#
#   return http.build();
#   }