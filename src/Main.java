

import java.awt.*;


public class Main
{
	public static void main( String avg[] )
	{
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int x = ( screen.width * 10 / 100 );

		int y = ( screen.height * 10 / 100 );

		int w = ( screen.width * 80 / 100 );

		int h  = ( screen.height * 80 / 100 );


		HomeScreen hs = new HomeScreen();

		hs.setBounds( x, y, w, h );

		hs.setVisible( true );
//obj.setBackgroundcolor("black");
	}
}