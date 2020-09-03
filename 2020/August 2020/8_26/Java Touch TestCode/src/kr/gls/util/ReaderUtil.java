package kr.gls.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.smartcardio.CardException;

public class ReaderUtil implements ReaderEvents.TransmitApduHandler, KeyListener{
	private Acr1251U_1252U nfcReader_;
	
	public void buzzer_handle(Integer duration) {
		try
		{
			if (nfcReader_.isConnectionActive())
				nfcReader_.disconnect();
		}
		catch (Exception ex)
		{

		}
		
		// Instantiate class
        nfcReader_ = new Acr1251U_1252U();
        
        //Instantiate an event handler object 
 		nfcReader_.setEventHandler(new ReaderEvents());
     		
 		// Register the event handler implementation of this class
 		nfcReader_.getEventHandler().addEventListener(this);		
    
		
		try
		{
			String[] sReaderList = nfcReader_.listTerminals();
			
		}
		catch (CardException ex)
		{
			
		}
		catch (Exception ex)
		{
		}
		
    	try
		{	
			// Connect directly to the smart card reader
    		nfcReader_.connectDirect(0);
			
		}
    	catch (CardException ex)
		{
		}
		catch (Exception ex)
		{
		}   
		
		try
		{
			if(nfcReader_ == null)
			{
	            return;
	        }

			nfcReader_.setBuzzerStatus(duration.byteValue());
			
		}
		catch (CardException ex)
		{
		}
		catch (Exception ex)
	    {
	    }
		
		try
		{
			if (nfcReader_.isConnectionActive())
				nfcReader_.disconnect();
		}
		catch (Exception ex)
		{

		}
	}
	
	@Override
	public void onSendCommand(ReaderEvents.TransmitApduEventArg event) {
		
	}

	@Override
	public void onReceiveCommand(ReaderEvents.TransmitApduEventArg event) {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
