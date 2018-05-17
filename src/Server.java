import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try (
                //создаем серверный сокет
                ServerSocket serverSocket = new ServerSocket(3345);

                //создаем клиентский сокет
                Socket clientSocket = serverSocket.accept(); //и переводим серверный сокет в режим ожидания подключения клиента

                //для обмена сообщениями
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))

        ){
            System.out.println("Новое подключение от  " + clientSocket.getRemoteSocketAddress().toString());

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Сообщение от клиента:  " + message);
                out.println(Integer.valueOf(message) * 2);
                System.out.println("Ответ клиенту:  " + (Integer.valueOf(message) * 2));
            }

            System.out.println("Клиент отключился");

        } catch (Throwable cause) {
            System.out.println("Ошибка подключения" + cause.getMessage());
        }

    }
}
