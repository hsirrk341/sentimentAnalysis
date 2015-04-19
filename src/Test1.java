

import java.awt.*;


public class Test1
{
	public static void main( String avg[] )
	{
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int x = ( screen.width * 10 / 100 );

		int y = ( screen.height * 10 / 100 );

		int w = ( screen.width * 80 / 100 );

		int h  = ( screen.height * 80 / 100 );


		Homepg obj = new Homepg();

		obj.setBounds( x, y, w, h );

		obj.setVisible( true );
//obj.setBackgroundcolor("black");
	}
}