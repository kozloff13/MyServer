import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try (
                //создание сокета
                Socket socket = new Socket("localhost", 3345);

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            String message;

            //отправляем серверу число
            out.println(2);

            //получаем ответ от сервера в виде числа и отправляем ему в ответ умноженное на 2
            while ((message = in.readLine()) != null) {
                System.out.println("Сообщение от сервера:  " + message);

                int n = Integer.valueOf(message);
                if (n >= 100) {
                    break;
                }

                n *= 2;

                out.println(n);
                System.out.println("Ответ серверу:  " + n);
                Thread.sleep(2000);
            }

            System.out.println("Отключение...");

        } catch (Throwable cause) {
            System.out.println("Ошибка соединения" + cause.getMessage());
        }
    }
}
