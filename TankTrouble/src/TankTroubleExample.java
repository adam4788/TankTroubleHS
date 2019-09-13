import java.awt.EventQueue;
import javax.swing.JFrame;

public class TankTroubleExample extends JFrame {

	public TankTroubleExample() {
		initUI();
	}

	private void initUI() {

		add(new TankTroubleBoard());

		setResizable(false);
		pack();

		setTitle("Tank Trouble");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				TankTroubleExample ex = new TankTroubleExample();
				ex.setVisible(true);
			}
		});
	}
}
