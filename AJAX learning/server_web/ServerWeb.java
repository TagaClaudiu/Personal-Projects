import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;

public class ServerWeb {
	public static void main(String[] args) throws IOException {
		System.out.println("#########################################################################");
		System.out.println("Serverul asculta potentiali clienti.");
		// pornește un server pe portul 5678 
		ServerSocket serverSocket = new ServerSocket(5678);
		while(true) {
			// așteaptă conectarea unui client la server
			// metoda accept este blocantă
			// clientSocket - socket-ul clientului conectat
			Socket clientSocket = serverSocket.accept();
			System.out.println("S-a conectat un client.");
			// socketWriter - wrapper peste fluxul de ieșire folosit pentru a transmite date clientului
			PrintWriter socketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			// socketReader - wrapper peste fluxul de intrare folosit pentru a primi date de la client
			BufferedReader socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			// este citită prima linie de text din cerere
			String linieDeStart = socketReader.readLine();
			System.out.println("S-a citit linia de start din cerere: ##### " + linieDeStart + " #####");
			// mesajul citit este transmis la client
            //# TODO interpretarea sirului de caractere `linieDeStart` pentru a extrage numele resursei cerute
            
            
		boolean isHTML = false;
		boolean isICO = false;
		boolean isCSS = false;
		boolean isPNG = false;
		boolean isJS = false;
		boolean isXML = false;
		boolean isJSON = false;
        
        String cerere = "";
        String tempo = linieDeStart.split(" ")[1];
		//System.out.println(tempo);
        
		//	if (tempo.equals("/")) {
		//			tempo = "/index.html";
		//		}
		String extensie = tempo.substring(tempo.lastIndexOf("."));	

            if (extensie.equals(".html"))
			{
				isHTML = true;	
            }
			else if (extensie.equals(".ico"))
			{
				isICO = true;
			}
			else if (extensie.equals(".css"))
			{
				isCSS = true;
			}
			else if (extensie.equals(".png"))
			{
				isPNG = true;
			}
			else if (extensie.equals(".js"))
			{
				isJS = true;
			}
			else if (extensie.equals(".xml"))
			{
				isXML = true;
			}
			else if (extensie.equals(".json"))
			{
				isJSON = true;
			}
           
			cerere = tempo;
			
			cerere = cerere.substring(1); // "/index.html" => "index.html"
			
			String locatieFisier = "../continut/"; // in loc de C:/Users/claud/OneDrive/Documents/GitHub/proiect-1-TagaClaudiu/continut/
			
			locatieFisier += cerere;

			File heyBaby = new File(locatieFisier);
			
			//System.out.println(locatieFisier);
			/*

			//String mesaj ="";
			This doesn't really work. Why? Because you need to read the bytes of png files and stuff.
			Varianta corecta e la final

			StringBuilder contentBuilder = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new FileReader(locatieFisier))) 
			{
 
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) 
				{
					contentBuilder.append(sCurrentLine).append("\n");
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			mesaj = contentBuilder.toString();
			*/

			if (heyBaby.exists())
			{
				String raspuns = "HTTP/1.1 200 OK\r\n";
				raspuns += "Content-Length:" + heyBaby.length() + "\r\n";

				if (isHTML)
				{
				raspuns += "Content-Type:" + "text/html\r\n";
				}
				else if (isICO)
				{
				raspuns += "Content-Type:" + "image/x-icon\r\n";
				}
				else if (isCSS)
				{
				raspuns += "Content-Type:" + "text/css\r\n";
				}
				else if (isPNG)
				{
				raspuns += "Content-Type:" + "image/png\r\n";
				}
				else if (isJS)
				{
				raspuns += "Content-Type:" + "application/javascript\r\n";
				}
				else if (isXML)
				{
				raspuns += "Content-Type:" + "application/xml\r\n";
				}
				else if (isJSON)
				{
					raspuns += "Content-Type:" + "application/JSON\r\n";
				}

				raspuns += "Server:" + "My Server" + "\r\n";
				raspuns += "\r\n";

				socketWriter.print(raspuns); //raspunsul site-ului
				socketWriter.flush();
				FileInputStream fis = new FileInputStream(heyBaby);
					byte[] buffer = new byte[1024];
					int n = 0;
					while ((n = fis.read(buffer)) != -1) {
						clientSocket.getOutputStream().write(buffer, 0, n);
					}
					clientSocket.getOutputStream().flush();
					fis.close();
		}
			// închide conexiunea cu clientul
			// la apelul metodei close() se închid automat fluxurile de intrare și ieșire (socketReader și socketWriter)
			clientSocket.close();
			System.out.println("S-a terminat comunicarea cu clientul.");
		}
		// închide serverul
		//serverSocket.close();
	}
}