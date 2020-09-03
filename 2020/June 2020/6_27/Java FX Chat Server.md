# [2020-06-27 토 TIL]

### Java FX Chat Server





Main.java

```java
package application;
	
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ChatMain extends Application {
	
	public static ExecutorService threadPool; // 갑작스러운 클라이언트 수 증가에 서버성능 저하를 방지 가능 - 한정된 자원으로의 안정된 서버구현
	public static Vector<Client> clients = new Vector<Client>();
	
	ServerSocket serverSocket;
	
	// 서버를 구동시켜서 클라이언트의 연결을 기다리는 메소드 
	public void startServer(String IP, int port) {
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP, port));
		} catch(Exception e) {
			e.printStackTrace();
			if(!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}
		
		// 클라이언트가 접속할 때까지 계속 기다리는 쓰레드 
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						clients.add(new Client(socket));
						System.out.println("[클라이언트 접속] "
											+ socket.getRemoteSocketAddress()
											+ ": " + Thread.currentThread().getName());
					} catch(Exception e) {
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(thread);
	}
	
	// 서버 작동 중지
	public void stopServer() {
		try {
			// 현재 작동중인 모든 소켓 닫기 
			Iterator<Client> iterator = clients.iterator();
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			
			// 서버소켓 객체 닫기
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			// 쓰레드풀 종료 
			if(threadPool != null && !threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// UI를 생성하고, 실직적으로 프로그램을 동작시키는 메소드 
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.setPadding(new Insets(5));
			
			TextArea textArea = new TextArea();
			textArea.setEditable(false);
			textArea.setFont(new Font("나눔고딕", 15));
			root.setCenter(textArea);
			
			Button toggleButton = new Button("시작하기");
			toggleButton.setMaxWidth(Double.MAX_VALUE);
			BorderPane.setMargin(toggleButton, new Insets(1,0,0,0));
			root.setBottom(toggleButton);
			
			String IP = "127.0.0.1";
			int port = 9876;
			
			toggleButton.setOnAction(event -> {
				if(toggleButton.getText().equals("시작하기")) {
					startServer(IP, port);
					
					// GUI 요소 출력 할수 있게 만들어야함 
					Platform.runLater(() -> {
						String message = String.format("[서버 시작]\n", IP, port);
						textArea.appendText(message);
						toggleButton.setText("종료하기");
					});
				} else {
					stopServer();
					Platform.runLater(() -> {
						String message = String.format("[서버 종료]\n", IP, port);
						textArea.appendText(message);
						toggleButton.setText("시작하기");
					});
				}
			});
			
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setTitle("Java FX Chating Server");
			primaryStage.setOnCloseRequest(event -> stopServer());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 프로그램의 진입점 
	public static void main(String[] args) {
		launch(args);
	}
}

```



Client.java

```java
package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.omg.CORBA.ExceptionList;

public class Client {
	
	Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
		receive();
	}
	
	// 클라이언트로부터 메시지를 전달 받는 메소드
	public void receive() {
		Runnable thread = new Runnable() { // 기본적으로 run 함수가지고 있어야함 
			@Override
			public void run() {
				try {
					while(true) {
						InputStream in = socket.getInputStream();
						byte[] buffer = new byte[512];
						int length = in.read(buffer);
						while(length == -1) throw new IOException();
						System.out.println("[메시지 수신 성공] "
												+ socket.getRemoteSocketAddress()
												+ ": " + Thread.currentThread().getName());
						String message = new String(buffer, 0, length, "UTF-8");
						
						for(Client client : ChatMain.clients) {
							client.send(message);
						}
					}
					
				} catch (Exception e) {
					try {
						System.out.println("[메시지 수신 오류] "
												+ socket.getRemoteSocketAddress() 
												+ ": " + Thread.currentThread().getName());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
						
				}
			}
		};
		ChatMain.threadPool.submit(thread);
	}
	
	// 클라이언트에게 메시지를 전송하는 메소드
	public void send(String message) {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e) {
					try {
						System.out.println("[메시지 송신 오류] "
								+ socket.getRemoteSocketAddress() 
								+ ": " + Thread.currentThread().getName());
						ChatMain.clients.remove(Client.this);
						socket.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		ChatMain.threadPool.submit(thread);
	}
}

```

