package javafxcrudcapacitacao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class API {

    public String sendget() {
        try {
            URL url = new URL("http://127.0.0.1:5000/produtos");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);
            int codigoHttp = conexao.getResponseCode();
            System.out.print("Código retornado: " + String.valueOf(codigoHttp));
            InputStream in;
            if (codigoHttp >= 400 && codigoHttp < 500) {
                in = conexao.getErrorStream();
            } else {
                in = conexao.getInputStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder strResposta = new StringBuilder();
            String strLinhaResposta = null;
            while ((strLinhaResposta = br.readLine()) != null) {
                strResposta.append(strLinhaResposta.trim());
            }
            return strResposta.toString();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar realizar a requisição. Erro: " + ex.getMessage());
            return "Erro na requisição";
        }

    }

    public String sendPost(JSONObject objetoJson) throws Exception {

        try {

            URL url = new URL("http://127.0.0.1:5000/produtos");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("POST");

            conexao.setRequestProperty("Content-Type", "application/json");

            String requestBody = objetoJson.toString();
            conexao.setDoOutput(true);
            OutputStream os = conexao.getOutputStream();
            os.write(requestBody.getBytes());
            os.flush();
            os.close();
            int codigoResposta = conexao.getResponseCode();

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            System.out.println("Código de resposta: " + codigoResposta);
            System.out.println("Resposta: " + resposta.toString());
            System.out.println(resposta);

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inserir";
        }

        return "Inserido com sucesso";
    }

    public String sendPut(JSONObject objetoJson, String atualiza) throws Exception {
        try {
            URL url = new URL("http://127.0.0.1:5000/produtos/" + atualiza);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("PUT");
            conexao.setRequestProperty("Content-Type", "application/json");
            String requestBody = objetoJson.toString();
            conexao.setDoOutput(true);
            OutputStream os = conexao.getOutputStream();
            os.write(requestBody.getBytes());
            os.flush();
            os.close();
            int codigoResposta = conexao.getResponseCode();
            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();
            System.out.println("Código de resposta: " + codigoResposta);
            System.out.println("Resposta: " + resposta.toString());
            System.out.println(resposta);
            return "Atualizado com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao atualizar";
        }
    }

    public String sendDelete(String delete) throws Exception {
        try {
            URL url = new URL("http://127.0.0.1:5000/produtos/" + delete);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("DELETE");

            int codigoResposta = conexao.getResponseCode();

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            System.out.println("Código de resposta: " + codigoResposta);
            System.out.println("Resposta: " + resposta.toString());
            System.out.println(resposta);

            return "Deletado com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao deletar";
        }
    }

    public String sendgetEmbalagem(String id) {
        try {
            URL url = new URL("http://127.0.0.1:5000/produtos/" + id + "/embalagens");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);
            int codigoHttp = conexao.getResponseCode();
            System.out.print("Código retornado: " + String.valueOf(codigoHttp));
            InputStream in;
            if (codigoHttp >= 400 && codigoHttp < 500) {
                in = conexao.getErrorStream();
            } else {
                in = conexao.getInputStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder strResposta = new StringBuilder();
            String strLinhaResposta = null;
            while ((strLinhaResposta = br.readLine()) != null) {
                strResposta.append(strLinhaResposta.trim());
            }
            return strResposta.toString();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar realizar a requisição. Erro: " + ex.getMessage());
            return "Erro na requisição";
        }

    }

    public String sendPostEmbalagem(JSONObject objetoJson, String id) throws Exception {
        try {
            URL url = new URL("http://127.0.0.1:5000/produtos/" + id + "/embalagens");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("POST");

            conexao.setRequestProperty("Content-Type", "application/json");

            String requestBody = objetoJson.toString();
            conexao.setDoOutput(true);
            OutputStream os = conexao.getOutputStream();
            os.write(requestBody.getBytes());
            os.flush();
            os.close();
            int codigoResposta = conexao.getResponseCode();

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);

            }
            leitor.close();

            System.out.println("Código de resposta: " + codigoResposta);
            System.out.println("Resposta: " + resposta.toString());
            System.out.println(resposta);

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inserir";
        }

        return "Inserido com sucesso";
    }

    public String sendPutEmbalagem(JSONObject objetoJson, int cdproduto, int cdembalagem) throws Exception {
        try {
            URL url = new URL("http://127.0.0.1:5000/produtos/" + cdproduto + "/embalagens/" + cdembalagem);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("PUT");
            conexao.setRequestProperty("Content-Type", "application/json");

            String requestBody = objetoJson.toString();
            conexao.setDoOutput(true);
            OutputStream os = conexao.getOutputStream();
            os.write(requestBody.getBytes());
            os.flush();
            os.close();

            int codigoResposta = conexao.getResponseCode();

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            System.out.println("Código de resposta: " + codigoResposta);
            System.out.println("Resposta: " + resposta.toString());
            System.out.println(resposta);

            return "Atualizado com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao atualizar";
        }
    }

    public String sendDeleteEmbalagem(String delete, String id) throws Exception {
        try {
            URL url = new URL("http://127.0.0.1:5000/produtos/" + delete + "/embalagens/" + id);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("DELETE");

            int codigoResposta = conexao.getResponseCode();

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            System.out.println("Código de resposta: " + codigoResposta);
            System.out.println("Resposta: " + resposta.toString());
            System.out.println(resposta);

            return "Deletado com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao deletar";
        }
    }

}
