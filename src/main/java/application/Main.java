package application;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import components.Window;
import display.Start;

public class Main {
	public static void main(String [] args) {
		try {
			UIManager
			.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
		| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Window.getInstance().setContentPane(new Start());
		Window.getInstance().setVisible(true);
	}
}
