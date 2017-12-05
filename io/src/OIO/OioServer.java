package OIO;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 浼犵粺socket鏈嶅姟绔�
 * @author -鐞村吔-
 *
 */
public class OioServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		//鍒涘缓socket鏈嶅姟,鐩戝惉10101绔彛
		ServerSocket server=new ServerSocket(10101);
		System.out.println("启动一个服务器");
		while(true){
			//鑾峰彇涓�涓鎺ュ瓧锛堥樆濉烇級
			final Socket socket = server.accept();
			System.out.println("来了一个客户端");
			newCachedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					//涓氬姟澶勭悊
					handler(socket);
				}
			});
			
		}
	}
	
	/**
	 * 璇诲彇鏁版嵁
	 * @param socket
	 * @throws Exception
	 */
	public static void handler(Socket socket){
			try {
				byte[] bytes = new byte[1024];
				InputStream inputStream = socket.getInputStream();
				
				while(true){
					//璇诲彇鏁版嵁锛堥樆濉烇級
					int read = inputStream.read(bytes);
					if(read != -1){
						System.out.println(new String(bytes, 0, read));
					}else{
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					System.out.println("socket鍏抽棴");
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
}
