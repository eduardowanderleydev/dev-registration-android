package br.com.ucsal.devregistration.service;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.com.ucsal.devregistration.domain.Address;

/**
 * Classe responsável por fazer a comunicação com o webservice viaCEP, para obtenção dos dados de endereço do usuário através do cep digitado.
 */
public class HttpService extends AsyncTask<Void, Void, Address> {

    private final String cep;

    public HttpService(String cep) {
        this.cep = cep;
    }

    /**
     * Método que contém a lógica para realizar a requisição para o web service em background.
     *
     * @param voids
     * @return o endereço convertido de um objeto JSON para um objeto Address.
     */
    @Override
    protected Address doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if (this.cep != null && this.cep.length() == 8) {
            try {
                URL url = new URL("https://viacep.com.br/ws/" + this.cep + "/json/");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Gson().fromJson(resposta.toString(), Address.class);
    }
}