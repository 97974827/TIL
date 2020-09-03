import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

class test{
	public static void main(String[] args){
		System.out.println(System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyUSB1"));
	}
}
